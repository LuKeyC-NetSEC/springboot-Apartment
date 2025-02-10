<template>
  <van-skeleton :row="20" :loading="!apartmentDetailInfo?.id">
    <div class="page-container min-h-[100vh] py-[15px]">
      <!--    预约公寓-->
      <div>
        <div class="base-info-title main-container py-[4px]">预约公寓</div>
        <div class="my-[5px] px-[15px]">
          <ApartmentCard :data="apartmentDetailInfo"></ApartmentCard>
        </div>
      </div>
      <!--    预约信息-->
      <div>
        <div class="base-info-title main-container py-[4px]">预约信息</div>
        <div class="my-[5px] relative">
          <van-form @submit="onSubmit">
            <van-cell-group inset>
              <!--            姓名-->
              <van-field
                v-model.trim="formData.name"
                name="name"
                label="姓名"
                autocomplete="off"
                placeholder="请输入姓名"
                :rules="[{ required: true, message: '请填写姓名' }]"
              />
              <!--            手机号-->
              <van-field
                v-model.trim="formData.phone"
                name="phone"
                label="手机号"
                type="tel"
                autocomplete="off"
                placeholder="请输入手机号"
                :rules="[
                  {
                    required: true,
                    pattern: /^1([3589]\d|4[5-9]|6[1-2,4-7]|7[0-8])\d{8}$/,
                    message: '请正确填写手机号'
                  }
                ]"
              />
              <!--            预约日期-->
              <van-field
                v-model="formData.date"
                is-link
                readonly
                name="date"
                label="预约日期"
                placeholder="点击选择日期"
                @click="showDate = true"
                :rules="[{ required: true, message: '请选择预约日期' }]"
              />
              <van-popup v-model:show="showDate" position="bottom">
                <van-date-picker
                  :min-date="new Date()"
                  @confirm="onConfirmAppointmentDateHandle"
                  @cancel="showDate = false"
                />
              </van-popup>
              <!--            预约时间-->
              <van-field
                v-model="formData.time"
                is-link
                readonly
                name="time"
                label="预约时间"
                placeholder="点击选择时间"
                @click="showTime = true"
                :rules="[{ required: true, message: '请选择预约时间' }]"
              />
              <van-popup v-model:show="showTime" position="bottom">
                <van-time-picker
                  v-model="dateInfo.time"
                  title="选择时间"
                  @confirm="onConfirmAppointmentTimeHandle"
                  @cancel="showDate = false"
                  :columns-type="['hour', 'minute', 'second']"
              /></van-popup>
              <!--            备注信息-->
              <van-field
                v-model.trim="formData.additionalInfo"
                name="additionalInfo"
                label="备注信息"
                rows="3"
                autosize
                type="textarea"
                autocomplete="off"
                placeholder="请输入备注信息"
                show-word-limit
                maxlength="50"
              />
            </van-cell-group>
            <div class="mt-[50px] main-container">
              <van-button round block type="primary" native-type="submit">
                {{ formData.id ? "重新预约" : "预约看房" }}
              </van-button>
            </div>
          </van-form>
        </div>
      </div>
      <div class="absolute bottom-0"></div>
    </div>
  </van-skeleton>
</template>
<script setup lang="ts">
import {
  getApartmentDetailById,
  getAppointmentDetailById,
  saveOrUpdateAppointment
} from "@/api/search";
import { onMounted, ref, watch } from "vue";
import dayjs from "dayjs";
import type {
  ApartmentInterface,
  AppointmentQueryInterface
} from "@/api/search/types";
import { useRoute, useRouter } from "vue-router";
import ApartmentCard from "@/components/ApartmentCard/ApartmentCard.vue";
import { AppointmentStatus } from "@/enums/constEnums";
import { showToast } from "vant";
const route = useRoute();
const router = useRouter();
// 公寓的详情信息
const apartmentDetailInfo = ref<ApartmentInterface>({} as ApartmentInterface);
// 获取公寓的详情信息
const getApartmentDetailHandle = async () => {
  const { data } = await getApartmentDetailById(
    route.query.apartmentId as string
  );
  apartmentDetailInfo.value = data;
};
//#region <form表单相关>
const formData = ref<
  AppointmentQueryInterface & { date: string; time: string }
>({
  id: "",
  name: "",
  phone: "",
  apartmentId: (route.query.apartmentId as string) || "",
  appointmentTime: "",
  additionalInfo: "",
  appointmentStatus: AppointmentStatus.WAITING,
  date: "",
  time: ""
});
// 日期选择器
const dateInfo = ref({
  date: [
    dayjs().get("year").toString(),
    (dayjs().get("month") + 1).toString().padStart(2, "0"),
    dayjs().get("date").toString().padStart(2, "0")
  ],
  time: [
    dayjs().get("hour").toString().padStart(2, "0"),
    dayjs().get("minute").toString().padStart(2, "0"),
    dayjs().get("second").toString().padStart(2, "0")
  ]
});
// 监视dateInfo
watch(
  dateInfo,
  (newVal, oldVal) => {
    console.log("newVal", newVal);
    console.log("oldVal", oldVal);
    formData.value.date = newVal.date.join("-");
    formData.value.time = `${newVal.time.join(":")}`;
    formData.value.appointmentTime = `${formData.value.date} ${formData.value.time}`;
  },
  { immediate: true, deep: true }
);
const showDate = ref(false);
const onConfirmAppointmentDateHandle = (date: any) => {
  console.log("date", date);
  dateInfo.value.date = date.selectedValues;
  showDate.value = false;
};
// 时间选择器
const showTime = ref(false);
const onConfirmAppointmentTimeHandle = (date: any) => {
  console.log("date", date);
  showTime.value = false;
};
// 提交表单
async function onSubmit(values: any) {
  console.log("submit", values);
  await saveOrUpdateAppointment(formData.value);
  showToast("操作成功");
  route.query.apartmentId
    ? await router.replace("/myAppointment")
    : await router.back();
}

//#endregion
// 预约信息
async function getAppointmentDetailHandle() {
  const { data } = await getAppointmentDetailById(
    route.query.appointmentId as string
  );
  Object.keys(formData.value).forEach(key => {
    (formData.value as any)[key] = (data as any)[key];
  });
  // 单独处理日期
  dateInfo.value.date = data.appointmentTime.split(" ")[0].split("-");
  dateInfo.value.time = data.appointmentTime.split(" ")[1].split(":");
  apartmentDetailInfo.value = data.apartmentItemVo;
}
onMounted(async () => {
  route.query.apartmentId && (await getApartmentDetailHandle());
  route.query.appointmentId && (await getAppointmentDetailHandle());
});
</script>

<style scoped lang="less">
.base-info-title {
  //background-color: var(--van-primary-background-color);
  font-weight: bold;
  //color: white;
}
::v-deep .van-card {
  background: var(--van-background-2) !important;
}
</style>

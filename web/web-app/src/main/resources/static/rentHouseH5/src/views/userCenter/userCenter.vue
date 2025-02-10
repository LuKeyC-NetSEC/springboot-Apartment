<template>
  <div class="user-container">
    <div class="user h-[30vh] flex flex-col justify-center items-center">
      <van-image
        @click="
          showImagePreview([userStore.userInfo?.avatarUrl || defaultAvatarUrl])
        "
        round
        width="30vw"
        height="30vw"
        :src="userStore.userInfo?.avatarUrl || defaultAvatarUrl"
      >
        <template v-slot:error>加载失败</template>
      </van-image>
      <div class="mt-[8px] font-bold text-[16px]">
        {{ userStore.userInfo?.nickname || "测试" }}
      </div>
    </div>
    <div class="main-container flex justify-around mt-[30px]">
      <div
        v-for="item in navList"
        :key="item.path"
        class="flex flex-col justify-center items-center"
        @click="router.push(item.path)"
      >
        <SvgIcon :name="item.icon" size="50" />
        <span>{{ item.name }}</span>
      </div>
    </div>
    <div class="main-container flex justify-center mt-[150px]">
      <!--      退出登录-->
      <van-button type="primary" class="w-[50vw]" @click="logoutHandle"
        >退出登录</van-button
      >
    </div>
  </div>
</template>
<script setup lang="ts" name="UserCenter">
import { useUserStore } from "@/store/modules/user";
import { showImagePreview } from "vant";
import defaultAvatarUrl from "../../../public/favicon.ico";
import { onMounted, ref } from "vue";
import { useRouter } from "vue-router";
const router = useRouter();
console.log("router.currentRoute.value.path", router);
const navList = ref([
  {
    icon: "历史",
    name: "浏览历史",
    path: "/browsingHistory"
  },
  {
    icon: "预约",
    name: "我的预约",
    path: "/myAppointment"
  },
  {
    icon: "合同",
    name: "我的租约",
    path: "/myAgreement"
  }
]);
const userStore = useUserStore();
// 退出登陆
const logoutHandle = () => {
  userStore.Logout();
  // 清空路由浏览历史记录
  router.replace("/");
};
console.log(userStore);
onMounted(() => {
  userStore.GetInfoAction();
});
</script>

<style scoped lang="less">
.user {
  background: var(--van-primary-background-color);
}
</style>

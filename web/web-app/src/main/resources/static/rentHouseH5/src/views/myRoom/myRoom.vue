<template>
  <div>
    <!--    顶部背景-->
    <van-image :src="bgImgUrl">
      <template v-slot:error>加载失败</template>
      <template v-slot:loading>
        <van-loading type="spinner" size="20" />
      </template>
    </van-image>
    <!--    中间-->
    <div class="main-container flex justify-around mt-[15px]">
      <div
        v-for="item in navList"
        :key="item.path"
        class="flex flex-col justify-center items-center"
        @click="router.push(item.path)"
      >
        <SvgIcon :name="item.icon" size="45" />
        <span>{{ item.name }}</span>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts" name="MyRoom">
import { ref } from "vue";
import bgImgUrl from "@/assets/my_room_bg.png";
import { useRouter } from "vue-router";
const router = useRouter();
const navList = ref([
  {
    icon: "物业费用出账",
    name: "交房租",
    path: ""
  },
  {
    icon: "电费",
    name: "交电费",
    path: ""
  },
  {
    icon: "水费",
    name: "交水费",
    path: ""
  },
  {
    icon: "物业报修",
    name: "报修",
    path: ""
  }
]);
</script>
<style lang="less" scoped></style>

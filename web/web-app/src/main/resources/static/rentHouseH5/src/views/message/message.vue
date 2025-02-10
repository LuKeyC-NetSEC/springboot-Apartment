<template>
  <div>
    <!--    消息列表-->
    <PullDownRefreshContainer
      :request="() => {}"
      ref="pullDownRefreshContainerRef"
      class="min-h-[70vh]"
    >
      <!--    顶部-->
      <van-sticky offset-top="0">
        <div
          class="main-container flex justify-around items-center bg-[--van-primary-background-color] h-[20vh] backdrop-filter backdrop-blur-xl backdrop-saturate-150"
        >
          <div
            v-for="item in navList"
            :key="item.path"
            class="flex flex-col justify-center items-center"
            @click="router.push(item.path)"
          >
            <div
              :style="{ background: item.color }"
              class="flex flex-col justify-center items-center h-[12vw] w-[12vw] rounded-xl py-[3px]"
            >
              <van-icon :name="item.icon" :badge="item.badge" size="25" />
            </div>
            <span class="mt-[2px]">{{ item.name }}</span>
          </div>
        </div>
      </van-sticky>
      <div class="flex flex-col main-container">
        <!--      单条消息-->
        <div class="flex py-[3px] mt-[20px]" v-for="item in 9" :key="item">
          <van-image
            round
            width="15vw"
            height="15vw"
            src="https://fastly.jsdelivr.net/npm/@vant/assets/cat.jpeg"
          >
            <template v-slot:error>加载失败</template>
            <template v-slot:loading>
              <van-loading type="spinner" size="20" />
            </template>
          </van-image>
          <div
            class="flex flex-col justify-center ml-[10px] text-[12px] w-[70vw]"
          >
            <div class="flex justify-between mb-[3px]">
              <div class="font-bold">游湖123456</div>
              <div class="">昨天</div>
            </div>

            <div class="--van-gray-6 van-ellipsis">
              噫吁嚱，危乎高哉！蜀道之难，难于上青天！蚕丛及鱼凫，开国何茫然！尔来四万八千岁，不与秦塞通人烟。西当太白有鸟道，可以横绝峨眉巅。
            </div>
          </div>
        </div>
      </div>
    </PullDownRefreshContainer>
  </div>
</template>

<script setup lang="ts" name="Message">
import { ref } from "vue";
import { useRouter } from "vue-router";
import PullDownRefreshContainer from "@/components/PullDownRefreshContainer/PullDownRefreshContainer.vue";
const router = useRouter();
const navList = ref([
  {
    icon: "volume-o",
    name: "通知",
    path: "",
    color: "#f1be4a",
    badge: null
  },
  {
    icon: "chat-o",
    name: "评论",
    path: "",
    color: "#51b290",
    badge: 9
  },
  {
    icon: "good-job-o",
    name: "赞",
    path: "",
    color: "#f39653",
    badge: null
  }
]);
</script>
<style lang="less" scoped></style>

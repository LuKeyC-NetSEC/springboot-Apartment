<template>
  <div class="bg-[--color-block-background]">
    <PullDownRefreshContainer
      :request="() => {}"
      ref="pullDownRefreshContainerRef"
      class="min-h-[70vh]"
    >
      <van-image :src="bgImgUrl">
        <template v-slot:error>加载失败</template>
        <template v-slot:loading>
          <van-loading type="spinner" size="20" />
        </template>
      </van-image>
      <div v-for="item in 5" :key="item" class="mt-[15px]">
        <div class="bg-[--color-background-3] main-container py-[10px]">
          <!--          头像、昵称、姓名-->
          <div class="flex">
            <van-image
              round
              width="12vw"
              height="12vw"
              src="https://fastly.jsdelivr.net/npm/@vant/assets/cat.jpeg"
            >
              <template v-slot:error>加载失败</template>
              <template v-slot:loading>
                <van-loading type="spinner" size="20" />
              </template>
            </van-image>
            <div class="flex flex-col justify-center ml-[10px] text-[12px]">
              <div class="font-bold">游湖123456</div>
              <div class="--van-gray-6">2022-10-01 12:00:00</div>
            </div>
          </div>
          <!--          正文-->
          <div class="mt-[10px] flex flex-col">
            <div class="font-bold">分享楼下的一家咖啡厅</div>
            <div
              class="van-multi-ellipsis--l3"
              v-on-click-remove-class="['van-multi-ellipsis--l3']"
            >
              噫吁嚱，危乎高哉！蜀道之难，难于上青天！蚕丛及鱼凫，开国何茫然！尔来四万八千岁，不与秦塞通人烟。西当太白有鸟道，可以横绝峨眉巅。地崩山摧壮士死，然后天梯石栈相钩连。上有六龙回日之高标，下有冲波逆折之回川。黄鹤之飞尚不得过，猿猱欲度愁攀援。青泥何盘盘，百步九折萦岩峦。扪参历井仰胁息，以手抚膺坐长叹。
              问君西游何时还？畏途巉岩不可攀。但见悲鸟号古木，雄飞雌从绕林间。又闻子规啼夜月，愁空山。蜀道之难，难于上青天，使人听此凋朱颜！连峰去天不盈尺，枯松倒挂倚绝壁。飞湍瀑流争喧豗，砯崖转石万壑雷。其险也如此，嗟尔远道之人胡为乎来哉！
              剑阁峥嵘而崔嵬，一夫当关，万夫莫开。所守或匪亲，化为狼与豺。朝避猛虎，夕避长蛇；磨牙吮血，杀人如麻。锦城虽云乐，不如早还家。蜀道之难，难于上青天，侧身西望长咨嗟！
            </div>
            <!--            图片-->
            <van-row>
              <van-col :span="8" v-for="item in 5" :key="item">
                <van-image
                  width="25vw"
                  height="25vw"
                  src="https://fastly.jsdelivr.net/npm/@vant/assets/cat.jpeg"
                >
                  <template v-slot:error>加载失败</template>
                  <template v-slot:loading>
                    <van-loading type="spinner" size="20" />
                  </template>
                </van-image>
              </van-col>
            </van-row>
            <!--            点赞、评论-->
            <div class="flex mt-[10px]">
              <div class="flex">
                <van-icon name="good-job-o" size="20" />
                <div class="ml-[5px]">123</div>
              </div>
              <div class="flex ml-[10px]">
                <van-icon name="comment-o" size="20" />
                <div class="ml-[5px]">123</div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </PullDownRefreshContainer>
  </div>
</template>

<script setup lang="ts" name="Group">
import { ref } from "vue";
import bgImgUrl from "@/assets/group_bg.png";
import PullDownRefreshContainer from "@/components/PullDownRefreshContainer/PullDownRefreshContainer.vue";
const pullDownRefreshContainerRef =
  ref<InstanceType<typeof PullDownRefreshContainer>>();
</script>
<style lang="less" scoped></style>

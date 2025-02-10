import type { RouteRecordRaw } from "vue-router";
/**
 * @description 📚 路由参数配置简介
 * @param path ==> 菜单路径
 * @param name ==> 菜单别名
 * @param redirect ==> 重定向地址
 * @param component ==> 视图文件路径
 * @param meta ==> 菜单信息
 * @param meta.icon ==> 菜单图标，只有tabBar菜单才需要配置
 * @param meta.title ==> 菜单标题
 * @param meta.noCache ==> 是否不缓存，默认开启组件缓存，需要vue组件中配置name属性和路由菜单别名一致
 * @param meta.isShowNavBar ==> 是否展示顶部导航栏，默认不展示
 * */

const routes: Array<RouteRecordRaw> = [
  {
    name: "RoomDetail",
    path: "/roomDetail",
    component: () => import("@/views/roomDetail/roomDetail.vue"),
    meta: {
      title: "房间详情",
      noCache: true
    }
  },
  {
    name: "ApartmentDetail",
    path: "/apartmentDetail",
    component: () => import("@/views/apartmentDetail/apartmentDetail.vue"),
    meta: {
      title: "公寓详情",
      noCache: true
    }
  },
  {
    name: "Appointment",
    path: "/appointment",
    component: () => import("@/views/appointment/appointment.vue"),
    meta: {
      title: "预约看房",
      noCache: true
    }
  },
  {
    name: "MyAppointment",
    path: "/myAppointment",
    component: () => import("@/views/myAppointment/myAppointment.vue"),
    meta: {
      title: "我的预约",
      noCache: true
    }
  },
  {
    name: "MyAgreement",
    path: "/myAgreement",
    component: () => import("@/views/myAgreement/myAgreement.vue"),
    meta: {
      title: "我的租约",
      noCache: true
    }
  },
  {
    name: "Agreement",
    path: "/agreement",
    component: () => import("@/views/agreement/agreement.vue"),
    meta: {
      title: "租约详情",
      noCache: true
    }
  },
  {
    name: "BrowsingHistory",
    path: "/browsingHistory",
    component: () => import("@/views/browsingHistory/browsingHistory.vue"),
    meta: {
      title: "浏览历史",
      noCache: true
    }
  },
  {
    name: "Login",
    path: "/login",
    component: () => import("@/views/login/login.vue"),
    meta: {
      title: "登录",
      noCache: true
    }
  }
];

export default routes;

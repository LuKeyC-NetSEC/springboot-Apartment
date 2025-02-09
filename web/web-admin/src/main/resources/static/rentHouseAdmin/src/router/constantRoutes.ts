/**
 * @description 📚 路由参数配置简介
 * @param path ==> 菜单路径
 * @param name ==> 菜单别名
 * @param redirect ==> 重定向地址
 * @param component ==> 视图文件路径
 * @param meta ==> 菜单信息
 * @param meta.icon ==> 菜单图标
 * @param meta.title ==> 菜单标题
 * @param meta.activeMenu ==> 当前路由为详情页时，需要高亮的菜单
 * @param meta.isLink ==> 是否外链
 * @param meta.isHide ==> 是否隐藏
 * @param meta.isFull ==> 是否全屏(示例：数据大屏页面)
 * @param meta.isAffix ==> 是否固定在 tabs nav
 * @param meta.isKeepAlive ==> 是否缓存
 * */

import { RouteRecordRaw } from 'vue-router'
import { HOME_URL, LOGIN_URL } from '@/config/config'

const LAYOUT = () => import('@/layouts/index.vue')

/**
 * @description 静态路由
 */
export const staticRoutes: RouteRecordRaw[] = [
  {
    path: LOGIN_URL,
    name: 'login',
    meta: {
      isHide: true,
    },
    component: () => import('@/views/login/index.vue'),
  },
  {
    path: '/404',
    name: '404',
    meta: {
      isHide: true,
    },
    component: () => import('@/views/error/error-404.vue'),
  },
  {
    path: '/',
    name: 'LAYOUT',
    component: LAYOUT,
    redirect: HOME_URL,
    meta: {
      title: '首页',
      icon: 'HomeFilled',
      isHide: false,
    },
    children: [
      {
        path: '/index',
        name: 'Index',
        component: () => import('@/views/home/index.vue'),
        meta: {
          title: '首页',
          icon: 'HomeFilled',
          affix: true,
        },
      },
      {
        path: '/system/user',
        component: () => import('@/views/system/user/user.vue'),
        meta: {
          title: '用户管理',
          icon: 'UserFilled',
          isHide: false,
          activeMenu: null,
        },
        children: [],
      },
      {
        path: '/system/post',
        component: () => import('@/views/system/post/post.vue'),
        meta: {
          title: '岗位管理',
          icon: 'Postcard',
          isHide: false,
          activeMenu: null,
        },
        children: [],
      },
      {
        path: '/apartmentManagement/apartmentManagement/apartmentManagement',
        component: () =>
          import(
            '@/views/apartmentManagement/apartmentManagement/apartmentManagement.vue'
          ),
        meta: {
          title: '公寓管理',
          icon: 'OfficeBuilding',
          isHide: false,
          activeMenu: '',
        },
        children: [],
      },
      {
        path: '/apartmentManagement/apartmentManagement/addOrEditApartment',
        component: () =>
          import(
            '@/views/apartmentManagement/apartmentManagement/components/addOrEditApartment.vue'
          ),
        meta: {
          title: '公寓详情',
          icon: 'Document',
          isHide: true,
          activeMenu:
            '/apartmentManagement/apartmentManagement/apartmentManagement',
        },
        children: [],
      },
      {
        path: '/apartmentManagement/roomManagement/roomManagement',
        component: () =>
          import(
            '@/views/apartmentManagement/roomManagement/roomManagement.vue'
          ),
        meta: {
          title: '房间管理',
          icon: 'House',
          isHide: false,
          activeMenu: null,
        },
        children: [],
      },
      {
        path: '/apartmentManagement/roomManagement/addOrEditRoom',
        component: () =>
          import(
            '@/views/apartmentManagement/roomManagement/components/addOrEditRoom.vue'
          ),
        meta: {
          title: '房间详情',
          icon: 'Document',
          isHide: true,
          activeMenu: '/apartmentManagement/roomManagement/roomManagement',
        },
        children: [],
      },
      {
        path: '/apartmentManagement/attributeManagement/attributeManagement',
        component: () =>
          import(
            '@/views/apartmentManagement/attributeManagement/attributeManagement.vue'
          ),
        meta: {
          title: '属性管理',
          icon: 'List',
          isHide: false,
          activeMenu: null,
        },
        children: [],
      },
      {
        path: '/rentManagement/appointment/appointment',
        component: () =>
          import('@/views/rentManagement/appointment/appointment.vue'),
        meta: {
          title: '看房预约管理',
          icon: 'Clock',
          isHide: false,
          activeMenu: null,
        },
        children: [],
      },
      {
        path: '/agreementManagement/agreement/agreement',
        component: () =>
          import('@/views/rentManagement/agreement/agreement.vue'),
        meta: {
          title: '租约管理',
          icon: 'CreditCard',
          isHide: false,
          activeMenu: null,
        },
        children: [],
      },
      {
        path: '/rentManagement/agreement/addOrEditAgreement',
        component: () =>
          import(
            '@/views/rentManagement/agreement/components/addOrEditAgreement.vue'
          ),
        meta: {
          title: '租约详情',
          icon: null,
          isHide: true,
          activeMenu: '/agreementManagement/agreement/agreement',
        },
        children: [],
      },
      {
        path: '/userManagement/userManagement',
        component: () => import('@/views/userManagement/userManagement.vue'),
        meta: {
          title: '用户管理',
          icon: 'UserFilled',
          isHide: false,
          activeMenu: null,
        },
        children: [],
      },
    ],
  },
  {
    path: '/:catchAll(.*)',
    meta: {
      isHide: true,
    },
    component: () => import('@/views/error/error-404.vue'), //这个是我自己的路径
  },
]

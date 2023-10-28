import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import UserList from '../views/UserList.vue'
import UserProfile from '@/views/UserProfile.vue'
import LoginView from '@/views/Login.vue'
import NotFound from '@/views/NotFound.vue'
import RegisterView from '@/views/Register.vue'
import MyProfile from '@/views/user/MyProfile.vue'
import UserAttendance from '@/views/user/AttendanceView.vue'
import store from '@/store/index'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
    meta: {
      requestAuth: true
    }
  },
  {
    path: '/userlist/',
    name: 'userlist',
    component: UserList,
    meta: {
      requestAuth: true
    }
  },
  {
    path: '/userprofile/:userId/',
    name: 'userprofile',
    component: UserProfile,
    meta: {
      requestAuth: true
    }
  },
  {
    path: '/myprofile/',
    name: 'myprofile',
    component: MyProfile,
    meta: {
      requestAuth: true
    }
  },
  {
    path: '/user/attendance/',
    name: 'userattendance',
    component: UserAttendance,
    meta: {
      requestAuth: true
    }
  },
  {
    path: '/login/',
    name: 'login',
    component: LoginView,
    meta: {
      requestAuth: false,
    }
  },
  {
    path: '/register/',
    name: 'register',
    component: RegisterView,
    meta: {
      requestAuth: false
    }
  },
  {
    path: '/404/',
    name: 'notfound',
    component: NotFound,
    meta: {
      requestAuth: false
    }
  },
  {
    path: '/:catchAll(.*)',
    redirect: '/404/'
  },
]

const router = createRouter({
  history: createWebHistory(),
  routes
})


router.beforeEach((to, from, next) => {
  // console.log(store.state.user.is_login)
  if (to.meta.requestAuth && !store.state.user.is_login) {
    next({name: "login"});
  }
  else {
    next();
  }
})

export default router

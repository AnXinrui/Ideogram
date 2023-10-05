import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import UserList from '../views/UserList.vue'
import UserProfile from '@/views/UserProfile.vue'
import LoginView from '@/views/Login.vue'
import NotFound from '@/views/NotFound.vue'
import RegisterView from '@/views/Register.vue'


const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/userlist/',
    name: 'userlist',
    component: UserList 
  },
  {
    path: '/userprofile/:userId/',
    name: 'userprofile',
    component: UserProfile
  },
  {
    path: '/login/',
    name: 'login',
    component: LoginView
  },
  {
    path: '/register/',
    name: 'register',
    component: RegisterView
  },
  {
    path: '/404/',
    name: 'notfound',
    component: NotFound
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

export default router

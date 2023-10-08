<template>
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container">
      <router-link class="navbar-brand" :to="{ name: 'home', params: {} }">Ideogram</router-link>
      <!-- <a class="navbar-brand" href="#">Ideogram</a> -->
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText"
        aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarText">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <router-link class="nav-link active" :to="{ name: 'home', params: {} }">首页</router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link" :to="{ name: 'userlist', params: {} }">好友列表</router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link" :to="{ name: 'userprofile', params: { userId: 2 } }">用户动态</router-link>
          </li>
        </ul>
        <ul class="navbar-nav" v-if="$store.state.user.is_login">
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
              {{ $store.state.user.username  }}
            </a>
            <ul class="dropdown-menu">
              <router-link class="dropdown-item" :to="{ name: 'myprofile', params: {} }">我的打卡</router-link>
              <!-- <li><a class="dropdown-item" href="#">我的动态</a></li> -->
              <li><a class="dropdown-item" href="#">其他</a></li>
              <li>
                <hr class="dropdown-divider">
              </li>
              <li><a class="dropdown-item" href="#" @click="logout">退出登录</a></li>
            </ul>
          </li>
        </ul>
        <ul class="navbar-nav" v-else-if="!$store.state.user.pulling_info">
          <li class="nav-item">
            <router-link class="nav-link" :to="{ name: 'login', params: {} }">登录</router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link" :to="{ name: 'register', params: {} }">注册</router-link>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script>
import {useStore} from 'vuex';

export default {
  name: 'NavBar',
  setup() {
    const store = useStore();
    // const route = useRoute();
    // let route_name = computed(() => route.name)
    const logout = () => {
      store.dispatch("logout");
    }
    return {
      // route_name,
      logout,
    }
  },
}


</script>
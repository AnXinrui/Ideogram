<template>
 <NavBar></NavBar>
 <RouterView></RouterView>
</template>

<script>
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/js/bootstrap';
import NavBar from './components/NavBar.vue';
// import HomeView from './views/HomeView.vue'


export default {
  name: 'App',
  components: {
    NavBar,
    // HomeView
  },
}
</script>


<style>
body{
  background-image: url('https://pic.imgdb.cn/item/65211f16c458853aef4f9342.jpg');
  
  background-size: cover;
}
</style>


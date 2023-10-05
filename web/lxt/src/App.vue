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

</style>


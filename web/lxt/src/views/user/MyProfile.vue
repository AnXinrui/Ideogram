<template>
    <Content>my profile</Content>
  </template>
  
  <script>
  // @ is an alias to /src
  import Content from '@/components/Content'
  import $ from 'jquery'
  import {useStore} from 'vuex'
  
  export default {
    name: 'MyProfile',
    components: {
      Content
    },
    setup() {
        const store = useStore();
        // $.ajax({
        //     url: 'http://127.0.0.1:3000/user/attendance/add/',
        //     type: 'POST',
        //     data: {
        //         title: 'test',
        //         description: 'test des',
        //         content: 'test content',
        //     },
        //     headers: {
        //         'Authorization': "Bearer " + store.state.user.token,
        //     },
        //     success(resp) {
        //         console.log(resp);
        //     },  
        //     error(resp){
        //         console.log(resp);
        //     }
        // })
        $.ajax({
            url: 'http://127.0.0.1:3000/user/attendance/remove/',
            type: 'POST',
            data: {
                attendance_id: 2,
            },
            headers: {
                'Authorization': "Bearer " + store.state.user.token,
            },
            success(resp) {
                console.log(resp);
            },  
            error(resp){
                console.log(resp);
            }
        })
  
    }
  
  }
  </script>
  
  <style scoped>
  
  </style>
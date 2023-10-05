<template>
    <Content>
        <div>userlist</div>
        <div class="card" v-for="user in users" :key="user.id" @click="open_user_profile(user.id)">            
            <div class="card-body">
                <div class="row">
                    <div class="col-1 img-field">
                        <img class="img-fluid" :src="user.photo" alt="">
                    </div>
                    <div class="col-11">
                       <div class="username">{{ user.username }}</div>
                       <div class="follow-count">{{ user.followerCount }}</div>
                    </div>
                </div>
           
            </div>
        </div>
   </Content> 
  </template>
  
  <script>
  // @ is an alias to /src
  import Content from '../components/Content'
  import $ from 'jquery';
  import { ref } from 'vue';

  
  export default {
    name: 'UserList',
    components: {
      Content,
    },

    setup() {
      let users = ref([]);

      $.ajax({
            url:'https://app165.acapp.acwing.com.cn/myspace/userlist/',
            type: "get",
            success(resp) {
                console.log(resp)
                users.value = resp;
            }
        });

      return {
        users,
      }
    }
  }
  </script>
  
  <style scoped>
  img {
      border-radius: 50%;
  }
  .card{
      margin-bottom: 20px;
      /* 鼠标变成小手形状 */
      cursor: pointer;
  }
  .username{
      font-weight: bold;
      height: 50%;
  }
  .follow-count{
      font-size: 12px;
      height: 50%;
      color: gray;
  }

  /* 鼠标悬浮阴影效果 */
  .card:hover{
      box-shadow: 2px 2px 10px lightslategray;
      /* 反映周期 */
      transform: 500ms;
  }
  .img-field {
      display: flex;
      flex-direction: column;
      justify-content: center;
  }
  </style>
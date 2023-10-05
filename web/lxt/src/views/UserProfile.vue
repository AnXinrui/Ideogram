<template>
  <Content>
    <div class="row">
      <div class="col-3">
        <UserProfileInfo @follow="follow" @unfollow="unfollow" :user="user" />
        <UserProfileWrite @post_a_post="post_a_post" />
      </div>
      <div class="col-9">
        <UserProfilePosts :posts="posts" />
      </div>
    </div>
  </Content>
</template>
  
<script>
// @ is an alias to /src
import Content from '../components/Content'
import UserProfileInfo from '../components/UserProfileInfo'
import UserProfilePosts from '@/components/UserProfilePosts.vue';
import UserProfileWrite from '@/components/UserProfileWrite.vue';
import { reactive } from 'vue';
import { useRoute } from 'vue-router';

export default {
  name: 'UserProfile',
  components: {
    Content,
    UserProfileInfo,
    UserProfilePosts,
    UserProfileWrite,
  },
  setup() {
    const route = useRoute();
    console.log(route.params.userId)


    // reative 用于对象
    const user = reactive({
      id: 1,
      userName: "axr",
      lastName: "An",
      firstName: "Xinrui",
      followerCount: 123,
      is_followed: true,
    });

    const posts = reactive({
      counts: 3,
      posts: [
        {
          id:1,
          userId:1,
          content:"今天学习了大数据统计非常开心",
        },
        {
          id:2,
          userId:1,
          content:"今天学习了机器学习，开心极了",
        },
        {
          id:3,
          userId:1,
          content:"今天去学Java,实在是太开心了",
        },
      ]
    })


    const follow =()=> {
      if (user.is_followed) {
        return;
      } else {
        user.followerCount++;
        user.is_followed = true;
      }
    }
    const unfollow = () => {
      if (!user.is_followed) return;
      else {
        user.followerCount--;
        user.is_followed = false;
      }
    }

    const post_a_post = (content) => {
      console.log(content);
      posts.count++;
      //  unshift() 方法可向数组的开头添加一个或更多元素，并返回新的长度。
      posts.posts.unshift({
        id:posts.count,
        userId:1,
        content:content,
      })
    }

    return {
      user,
      follow,
      unfollow,
      posts,
      post_a_post,
    }
  }
}
</script>
  
<style scoped></style>
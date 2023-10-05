<template>
    <div class="card">
        <div class="cardbody">
            <div class="row">
                <div class="col-3">
                    <img class="img-fluid" src="https://cdn.acwing.com/media/user/profile/photo/69052_lg_c36420ce7c.png"
                        alt="">
                    <!-- <img class="img-fluid" src="https://cdn.acwing.com/media/user/profile/photo/137402_lg_af7cd7f64e.jpg" alt=""> -->
                </div>
                <div class="col-9">
                    <div class="username">{{ fullName }}</div>
                    <div class="fans">粉丝数： {{ user.followerCount }}</div>
                    <button @click="follow" v-if="!user.is_followed" type="button" class="btn btn-primary">+关注</button>
                    <button @click="unfollow" v-if="user.is_followed" type="button" class="btn btn-dark btn-sm" >取消关注</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { computed } from 'vue';

export default {
    name: 'UserProfileInfo',
    props: {
        user: {
            type: Object,
            required: true,
        },
    },
    // props 是接收父组件的参数， context 触发父组件函数
    setup(props, context) {
        let fullName = computed(() => props.user.lastName + ' ' + props.user.firstName);

        const follow = () => {
            context.emit('follow');
        }
        const unfollow = () => {
            context.emit('unfollow');
        }
        
        return {
            fullName,
            follow,
            unfollow,
        }
    }

}
</script>

<style scoped>
/* 圆形图片 */
img {
    border-radius: 50%;
}

.username {
    font-weight: bold;
}

.fans {
    font-size: 12px;
    color: #999;
}

button {
    font-size: 12px;
    padding: 2px 4px;
}
</style>
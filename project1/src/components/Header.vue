<template>
    <div class="h-content">
        <h3>欢迎来到张豪的公司组织系统</h3>
        <div class="block">
            <el-avatar :size="50" :src="user.avatar"></el-avatar>
            <div>{{user.username}}</div>
        </div>

        <div class="edit">
            <span><el-link href="/code" type="primary">用户模块</el-link></span>
            <el-divider direction="vertical"></el-divider>
            <span><el-link href="/users" type="primary">验证码模块</el-link></span>
            <el-divider direction="vertical"></el-divider>
            <span><el-link href="/attend" type="primary">出勤模块</el-link></span>
            <el-divider direction="vertical"></el-divider>
            <span><el-link type="primary">请假模块</el-link></span>
            <el-divider direction="vertical"></el-divider>
            <span><el-link type="primary">外出模块</el-link></span>
            <el-divider direction="vertical"></el-divider>
            <span><el-link type="primary">会议模块</el-link></span>
            <el-divider direction="vertical"></el-divider>
            <span><el-link type="primary">月统计模块</el-link></span>
            <el-divider direction="vertical"></el-divider>
            <span><el-link href="/login" type="danger">退出</el-link></span>
        </div>
    </div>
</template>

<script>
    export default {
        name: "Header",
        data(){
            return{
                user: {
                    username: '别写了，再写头发就没有了',
                    avatar: 'https://bkimg.cdn.bcebos.com/pic/908fa0ec08fa513defeb0567316d55fbb3fbd9c2?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UyNzI=,g_7,xp_5,yp_5'
                }
            }
        }
    }
</script>

<style scoped>

    .h-content{
        max-width: 960px;
        margin: 0 auto;
        text-align: center;
    }

    .edit{
        margin: 10px 0;
    }

</style>
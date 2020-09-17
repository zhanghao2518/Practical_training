<template>
    <div>
        <el-container>
            <el-header>
                <img class="mlogo" src="https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1599664515&di=f4f93058261aba908806656b05d4bdd2&src=http://img.ui.cn/data/file/4/2/3/204324.png?imageMogr2/auto-orient/format/jpg/strip/thumbnail/!1800%3E/quality/90/" alt="">
            </el-header>
            <el-main>
                <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                    <el-form-item label="用户名" prop="flowerName">
                        <el-input v-model="ruleForm.flowerName"></el-input>
                    </el-form-item>
                    <el-form-item label="密码" prop="password">
                        <el-input type="password" v-model="ruleForm.password"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="submitForm('ruleForm')">登陆</el-button>
                        <el-button @click="resetForm('ruleForm')">重置</el-button>
                        <el-button type="primary" @click="creatForm('ruleForm')">注册</el-button>
                    </el-form-item>
                </el-form>
            </el-main>
        </el-container>
    </div>
</template>

<script>
    import qs from 'qs'
    export default {
        name: "Login",
        data() {
            return {
                ruleForm: {
                    flowerName: '',
                    password: ''
                },
                rules: {
                    flowerName: [
                        { required: true, message: '请输入用户名', trigger: 'blur' },
                        { min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur' }
                    ],
                    password: [
                        { required: true, message: '请输入密码', trigger: 'change' }
                    ]
                }
            };
        },
        methods: {
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        // alert('注册成功!');
                        const _this = this
                        this.$axios.post('/login',qs.stringify(this.ruleForm)).then(res => {
                            const userInfo = res.data

                            _this.$store.commit("SET_USERINFO",userInfo)

                            console.log(_this.$store.getters.getUser)

                            _this.$router.push("/users")
                        })
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },
            creatForm(formName) {
                // this.$refs[formName].validate((valid) => {
                //     if (valid){
                //         this.$router.push("/logon")
                //     }
                // })
                this.$router.push("/logon")
            }
        }
    }
</script>

<style scoped>
    .el-header, .el-footer {
        background-color: #B3C0D1;
        color: #333;
        text-align: center;
        line-height: 60px;
    }

    .el-aside {
        background-color: #D3DCE6;
        color: #333;
        text-align: center;
        line-height: 200px;
    }

    .el-main {
        /*background-color: #E9EEF3;*/
        color: #333;
        text-align: center;
        line-height: 160px;
    }

    body > .el-container {
        margin-bottom: 40px;
    }

    .el-container:nth-child(5) .el-aside,
    .el-container:nth-child(6) .el-aside {
        line-height: 260px;
    }

    .el-container:nth-child(7) .el-aside {
        line-height: 320px;
    }
    .mlogo {
        height: 100%;
        /*margin-top: 10px;*/
    }
    .demo-ruleForm{
        max-width: 500px;
        margin : 0 auto;
    }
</style>
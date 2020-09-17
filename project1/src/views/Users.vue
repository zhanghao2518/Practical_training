<template>
    <div >
        <Header></Header>

        <div class="block">
            <el-timeline>
                <el-timeline-item timestamp="2020/9/18" placement="top" v-for="test in tests">
                    <el-card>
                        <h4>
                            <router-link :to="{name: 'Delete'}">
                                {{test.id}}
                            </router-link>
                        </h4>
                        <p>{{test.verificationCode}}</p>
                    </el-card>
                </el-timeline-item>
            </el-timeline>

            <el-pagination class="mpage"
                    background
                    layout="prev, pager, next"
                    :current-page="pageNum"
                    :page-size="pageSize"
                    :total="total">
                    @current-change="page"
            </el-pagination>
        </div>

    </div>
</template>

<script>
    import Header from "../components/Header";
    export default {
        name: "Users",
        components: {Header},
        data(){
            return{
                tests: {},
                pageNum: 1,
                total: 0,
                pageSize: 10
            }
        },
        methods: {
            page(pageNum,pageSize){
                const _this = this
                _this.$axios.get("/test/list?pageNum=" + pageNum + "&pageSize=" + pageSize).then(res =>{
                    _this.tests = res.data.list
                    _this.pageNum = res.data.pageNum
                    _this.pageSize = res.data.pageSize
                    _this.total = res.data.total
                })
            }
        },
        created() {
            this.page(1,10)
        }
    }
</script>

<style scoped>

</style>
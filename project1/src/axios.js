import axios from 'axios'
import Element from 'element-ui'

axios.defaults.baseURL="http://localhost:8081"

axios.interceptors.request.use(config => {
    return config
})

axios.interceptors.response.use(response =>{
    // let res = response.data;

    // console.log(res)
    if(response.status === 200){
        return response
    }else{
        Element.Message.error('错了哦，这是一条错误消息');
        return Promise.reject("信息错误")
    }
})
import axios from "axios";
import  useTokenStore from '@/stores/useToken'
import { ElMessage } from 'element-plus';
// 先建一个api
const api = axios.create({
    baseURL: "http://192.168.1.196:8081",
    timeout: 5000
});
// 发送请求前拦截
api.interceptors.request.use(
    config =>{
const useToken = useTokenStore();
if(useToken.token){
    console.log("请求头toekn=====>", useToken.token);
    // 设置请求头
    // config.headers['token'] = useToken.token;
    config.headers.token = useToken.token;
}
        return config;
 
},
error =>{
 
    return Promise.reject(error);
}
)
 
// 响应前拦截
api.interceptors.response.use(
    response =>{
        console.log("响应数据", response);
if(response.data.code !=200){
    ElMessage.error(response.data.message);
}
 
        return response;
},
error =>{
    return Promise.reject(error);
}
)
 
export default api;
 
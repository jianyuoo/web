<template>
    <div class="common-layout">
    <el-container>
          <el-header height="100px">
            头部
            <el-button type="primary" @click="getUserInfo">获取用户信息</el-button>
            <el-button type="success" @click="Logout">退出登录</el-button>
        </el-header>
    
    <el-container>
    <el-aside width="200px">
        菜单栏
    </el-aside>
    <el-main>
        展示区
    </el-main>
    </el-container>
    </el-container>
  </div>
 
 
</template>
 
<script lang="ts" setup name="Layout">
import { ref } from 'vue'
import api from '../utils/request'
import {ElMessage} from 'element-plus'
import { useRouter } from 'vue-router'
import  useToeknStore from '../stores/useToken'
const router = useRouter()
 
const Logout = async () => {
  try {
    const response = await api.get("/user/logout");
    
    if (response.data.code === 200) {
      ElMessage.success('退出成功');
      // 清除 token
      useToeknStore().removeToken();
      router.replace({ name: 'login' });
    }
  } catch (error) {
    ElMessage.error('退出失败，请稍后再试');
  }
};
 
const getUserInfo = async() => {
 
let data=await api.get("/user/info")
 
console.log('@',data);
 
}
 
 
</script>
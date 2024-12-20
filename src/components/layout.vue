<template>
  <div class="container-layout">
    <el-button type="primary" @click="getUserInfo">获取用户信息</el-button>
    <el-button type="success" @click="Logout">退出登录</el-button>
  </div>


</template>

<script lang="ts" setup name="Layout">
import { ref } from 'vue'
import api from '../utils/request'
import { ElMessage } from 'element-plus'
import { useRouter } from 'vue-router'
import useToeknStore from '../stores/useToken'
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
    // 登录失败时清除 token 并跳转到登录页
    router.replace({ name: 'login' });
  }
};

const getUserInfo = async () => {

  let data = await api.get("/user/info")

  console.log('@', data);

}


</script>
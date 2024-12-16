<template>
    <div class="background" style="font-family:kaiti" >
 
<!-- 注册表单 -->
<el-dialog v-model="isRegister" title="用户注册" width="30%">
    <el-form label-width="120px" v-model="registerForm">
        <el-form-item label="用户名">
            <el-input type="text"   v-model="registerForm.username"   >
              <template #prefix>
                <el-icon><Avatar /></el-icon>
              </template>
            </el-input>
        </el-form-item>
        <el-form-item label="密码">
            <el-input  type="password" v-model="registerForm.password" >
              <template #prefix>
        <el-icon><Lock /></el-icon>
        </template>
            </el-input>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="registerAdd" >提交</el-button>
            <el-button @click="isRegister = false">取消</el-button>
        </el-form-item>
    </el-form>
</el-dialog>
 
<!-- 登陆框 -->
<div class="login-box">
<el-form
    label-width="100px"
    :model="loginFrom"
    style="max-width: 460px"
    :rules="Loginrules"
    ref="ruleFormRef"
  >
    <el-form-item label="用户名"  prop="username">
      <el-input v-model="loginFrom.username"  clearable  >
        <template #prefix>
                <el-icon><Avatar /></el-icon>
              </template>
        </el-input>
    </el-form-item>
    <el-form-item label="密码" prop="password">
 
      <el-input v-model="loginFrom.password"   show-password   clearable  type="password" >
        <template #prefix>
        <el-icon><Lock /></el-icon>
        </template>
      </el-input>
    </el-form-item>
 
    <el-form-item label="验证码"  prop="codeValue">
      <el-input v-model="loginFrom.codeValue"  style="width: 100px;"  clearable  >
      </el-input>
      <img :src="codeImage" @click="getCode" style="transform: scale(0.9);"/>
    </el-form-item>
 
    <el-button type="success" @click="getLogin(ruleFormRef)"  style="transform: translateX(50px)"  class="my-button">登录</el-button>
    <el-button type="primary" @click="isRegister=true" class="my-button">注册</el-button>
  </el-form>
 
</div>
 
    </div>
</template>
 
<script lang="ts" setup>
import { ref,onMounted,reactive } from 'vue';
import { useRouter } from 'vue-router';
import { ElMessage } from 'element-plus';
import useTokenStore  from '../stores/useToken'
import  api  from '@/utils/request'
import type { FormInstance, FormRules } from 'element-plus'
const ruleFormRef =  ref<FormInstance>()
 
const loginFrom=ref({
username:'',
password:'',
codeKey:'',
codeValue:''
})
 
const Loginrules=reactive({
 
  username: [
    { required: true, message: '请输入用户名', trigger: 'blur' }
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    { min: 6, max: 12, message: '长度在 6 到 12 个字符', trigger: 'blur'}
  ],
  codeValue: [
    { required: true, message: '请输入验证码', trigger: 'blur' }
  ]
 
})
 
const registerForm=ref({
  username:'',
  password:''
})
 
const codeImage=ref('')
 
const isRegister=ref(true)
 
const tokenStore = useTokenStore();
 
 
const router = useRouter()
 
 
 
const getLogin = async(formEl: FormInstance | undefined) => {
 
  if (!formEl)  return
 
  await formEl.validate((valid, fields) => {
    if (valid) {
      console.log('submit!')
      
    } else {
      ElMessage('请输入完整信息')
      return;
    }
  })
 
  let {data}=await api.post('/user/login',loginFrom.value)
 
if(data.code==200){
  ElMessage('登录成功')
  console.log(data);
  tokenStore.token=data.data
  router.replace({name:'layout'})
}else{
 
  ElMessage('登录失败')
}
 
 
}
 
 
const getCode=async()=>{
  let {data}=await api.get('/user/captcha')

  loginFrom.value.codeKey=data.data.codeKey
  codeImage.value=data.data.codeValue
 
}
 
const registerAdd=async()=>{
let {data}=await api.post('/user/register',registerForm.value)
 
if(data.code==200){
  ElMessage('注册成功')
  isRegister.value=false
}else{
 
  ElMessage('注册失败')
  isRegister.value=false
  }
 
}
 
// 页面加载完成获取验证码
 
onMounted(()=>{
getCode()
 
})
</script>
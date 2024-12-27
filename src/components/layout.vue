<template>
  <div class="Layout">
    <el-container>
      <Header />
      <el-main>
        <div class="body">
          <div class="body-item"></div>
          <div class="body-item"></div>
        </div>
      </el-main>

      <el-footer>
        <el-button type="primary" round @click="showAppointmentForm = true">预约挂号</el-button>
        <div class="el-button-group">
          <el-button type="primary" round @click="Logout">退出登录</el-button>
        </div>
      </el-footer>

      <!-- 预约挂号表单 -->
      <div v-if="showAppointmentForm" class="appointment-form-container">
        <div class="appointment-form">
          <h2>预约挂号</h2>
          <el-form>
            <el-form-item label="患者姓名：">
              <el-input v-model="appointmentData.username"></el-input>
            </el-form-item>
            <el-form-item label="身份证号：">
              <el-input v-model="appointmentData.patientid"></el-input>
            </el-form-item>
            <el-form-item label="预约科室：">
              <el-select v-model="appointmentData.departmentName" @change="handleDepartmentChange" placeholder="请选择科室">
                <el-option v-for="dept in departmentName" :key="dept" :label="dept" :value="dept" />
              </el-select>
            </el-form-item>

            <el-form-item label="预约医生：">
              <el-select v-model="appointmentData.doctorid" placeholder="请选择医生">
                <el-option v-for="doc in doctors" :key="doc.id" :label="doc.name" :value="doc.id" /> 
              </el-select>
            </el-form-item>


            <el-form-item>
              <el-button type="primary" @click="submitAppointment">提交</el-button>
              <el-button @click="showAppointmentForm = false">取消</el-button>
            </el-form-item>
          </el-form>
        </div>
      </div>
    </el-container>
  </div>
</template>

<script lang="ts" setup name="Layout">
import { ref, onMounted } from 'vue'
import api from '../utils/request'
import { ElMessage } from 'element-plus'
import { useRouter } from 'vue-router'
import useTokenStore from '../stores/useToken'
import { reactive } from 'vue'
import Header from '@/components/Header.vue'

const router = useRouter()

// 控制预约表单的显示状态
const showAppointmentForm = ref(false);

// 预约数据
const appointmentData = reactive({
  username: '',
  patientid: '',
  departmentName: '',
  doctorid: ''
});

// 存储科室和医生信息
const departmentName = ref([]);
const doctors = ref([]);

// 获取科室信息
const fetchDepartments = async () => {
  try {
    const response = await api.get("/department/selectDepartment");
    if (response.data.code === 200) {
      departmentName.value = response.data.data;
    } else {
      ElMessage.error('获取部门信息失败');
    }
  } catch (error) {
    ElMessage.error('请求失败');
  }
};

// 获取医生信息的函数
const fetchDoctors = async (departmentName) => {
  try {
    const response = await api.get(`/department/selectDoctor?departmentName=${departmentName}`);
    if (response.data.code === 200) {
      
      doctors.value = response.data.data; 
      console.log(response.data.data);
    } else {
      ElMessage.error('获取医生信息失败');
    }
  } catch (error) {
    ElMessage.error('请求失败');
  }
};

// 监听预约科室的变化
const handleDepartmentChange = (departmentName) => {
  if (departmentName) {
    fetchDoctors(departmentName);
  }
};

// 在组件挂载后获取部门信息
onMounted(() => {
  fetchDepartments();
});
// 提交预约
const submitAppointment = async () => {
  try {
    const response = await api.post('/register/addRegistered', appointmentData);
    if (response.data.code === 200) {
      ElMessage.success('预约提交成功！');
      showAppointmentForm.value = false; // 关闭预约表单
    } else {
      ElMessage.error('预约提交失败：' + response.data.message);
    }
  } catch (error) {
    ElMessage.error('请求失败：' + error.message);
  }
};

// 退出登录逻辑...
const Logout = () => {
  useTokenStore().removeToken();
  router.push('/');
};
</script>

<style scoped>
.Layout {
  width: 100%;
  height: 100%;
  display: flex;
}

.body {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
}

.body-item {
  height: 600px;
  width: 50%;
  margin: 10px;
  padding: 20px;
  border: 1px solid #d0d0d0;
  border-radius: 10px;
}

.appointment-form-container {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: rgba(0, 0, 0, 0.5);
}

.appointment-form {
  padding: 20px;
  background-color: #f9f9f9;
  border: 1px solid #d0d0d0;
  border-radius: 10px;
}
</style>

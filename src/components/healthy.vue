<template>
    <div class="healthy-container">
        <el-container direction = "vertical">
            <Header />

            <el-main>
                <div class="healthy-body">
                    <h1 class="title">健康常识</h1>
                    <p class="description">
                        健康常识是指人们在生活中需要了解和掌握的健康相关知识。它能够帮助我们更好地维护身体健康。
                    </p>
                    <el-row>
                        <el-col v-for="(item, index) in healthKnowledge" :key="index" :span="8">
                            <el-card class="card">
                                <img :src="item.image" alt="健康常识图片" class="card-image" />
                                <div class="card-content">
                                    <h3>{{ item.title }}</h3>
                                    <p>{{ item.description }}</p>
                                </div>
                            </el-card>
                        </el-col>
                    </el-row>
                </div>
            </el-main>
        </el-container>
    </div>
</template>

<script setup>
import Header from '@/components/Header.vue';
import { ref } from 'vue';

// 健康常识数据
const healthKnowledge = ref([
    {
        title: '均衡饮食',
        description: '保持每天摄入不同种类的食物，确保营养均衡。',
        image: 'https://via.placeholder.com/400x200?text=Food',
    },
    {
        title: '适量运动',
        description: '每周至少进行150分钟的中等强度运动。',
        image: 'https://via.placeholder.com/400x200?text=Exercise',
    },
    {
        title: '充足睡眠',
        description: '成年人推荐每晚7-9小时的睡眠。',
        image: 'https://via.placeholder.com/400x200?text=Sleep',
    },
    {
        title: '定期体检',
        description: '定期进行健康检查，以便尽早发现潜在健康问题。',
        image: 'https://via.placeholder.com/400x200?text=Checkup',
    },
    {
        title: '保持心理健康',
        description: '适当的放松和心理疏导有助于提高生活质量。',
        image: 'https://via.placeholder.com/400x200?text=Mental+Health',
    },
]);
</script>

<style scoped>
.healthy-container {
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: column;
}

.healthy-body {
    display: flex;
    flex-direction: column; /* 垂直方向排列 */
    justify-content: center; /* 垂直居中 */
    align-items: center; /* 水平居中 */
}

h1.title {
    text-align: center;
    margin: 20px 0;
}

.description {
    text-align: center;
    margin-bottom: 20px;
    font-size: 16px;
    color: #666; /* 描述的颜色 */
}

.el-row {
    display: flex; /* 确保行的内容在水平方向上排布 */
    justify-content: center; /* 水平居中 */
}

.card {
    margin: 10px;
    border-radius: 10px;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

.card-image {
    width: 100%;
    height: auto;
    border-radius: 10px;
}

.card-content {
    padding: 15px;
}
</style>

<template>
    <el-header>
        <img src="../assets/images/logo-BsTXx7oy.png" alt="logo">
        <el-menu :default-active="activeMenu" class="el-header-menu" @select="handleSelect">
            <el-menu-item index="1" class="menu-item">
                <router-link to="/layout" class="el-header-link">个人信息</router-link>
            </el-menu-item>
            <el-menu-item index="2" class="menu-item">
                <router-link to="/healthy" class="el-header-link">健康常识</router-link>
            </el-menu-item>
        </el-menu>
        <div class="block">
            <el-avatar :size="50" :src="circleUrl" />
        </div>
    </el-header>
</template>

<style scoped>
.img {
    width: 454px;
    height: 70px;
}
.el-header {
    display: flex;
    width: 100%;
    --el-header-padding: 0 0px;
    --el-header-height: 70px;
    box-sizing: border-box;
    flex-shrink: 0;
    height: var(--el-header-height);
    padding: var(--el-header-padding);
    justify-content: flex-start;
    background-color: bisque;
    border-radius: 10px;
}

.el-header-menu {
    display: flex;
    padding: 0; /* 去掉菜单的内边距 */
    background-color: transparent;
}
.menu-item {
    margin: 0; /* 去掉菜单项的外边距 */
    padding: 10px 0; /* 设置菜单项的内边距 */
    border-radius: 5px;
    transition: background-color 0.3s ease;
}

.menu-item:hover {
    background-color: rgba(255, 255, 255, 0.2); /* 悬停时背景色 */
}

.menu-item.active {
    background-color: rgba(255, 255, 255, 0.5); /* 选中时背景色 */
}

.el-header-link {
    font-size: 25px;
    font-family: "Microsoft YaHei";
    padding: 15px 25px; /* 可以根据需要调整 */
    color: black;
    text-decoration: none;
}

.block {
    position: absolute;
    top: 20px;
    right: 20px;
    display: flex;
    align-items: center;
}

.el-header-link:hover {
    color: white; /* 鼠标悬停时文本颜色 */
}
</style>


<script lang="ts" setup>
import { reactive, toRefs } from 'vue';
import { ref, watch } from 'vue';
import { useRouter, useRoute } from 'vue-router';

const state = reactive({
    circleUrl: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
});

const { circleUrl } = toRefs(state);

const router = useRouter();
const route = useRoute();
const activeMenu = ref('2'); // 默认选中的菜单项为健康常识，即 index 2

// 监听当前路由的变化，更新选中菜单项
watch(route, (to) => {
    switch (to.path) {
        case '/layout':
            activeMenu.value = '1';
            break;
        case '/healthy':
            activeMenu.value = '2';
            break;
        case '/other':
            activeMenu.value = '3';
            break;
        default:
            activeMenu.value = '2'; // 默认选中
    }
});

const handleSelect = (index: any) => {
    console.log('Selected menu index:', index);
    switch (index) {
        case '1':
            router.push('/layout');
            break;
        case '2':
            router.push('/healthy');
            break;
        case '3':
            router.push('/other');
            break;
    }
};
</script>

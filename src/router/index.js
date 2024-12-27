import { createRouter, createWebHistory } from 'vue-router'
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'login',
      component: () => import('@/components/Login.vue')
    },
    {
      path: '/layout',
      name: 'layout',
      component: () => import('@/components/Layout.vue')
    },
    {
      path: '/healthy',
      name: 'healthy',
      component: () => import('@/components/Healthy.vue')
    },
    {
      path: '/system',
      name: 'system',
      component: () =>import('../views/system.vue')
    }
  ]
})
 
export default router
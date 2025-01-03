
import { createPinia } from 'pinia'
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import piniaPersistedState from 'pinia-plugin-persistedstate';

const app = createApp(App)

const pinia = createPinia()
app.use(pinia)
pinia.use(piniaPersistedState);
app.use(router)
app.use(ElementPlus)
 
app.mount('#app')
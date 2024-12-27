   // useToken.ts
   import { defineStore } from 'pinia';
   import { ref } from 'vue';
   import piniaPersistedState from 'pinia-plugin-persistedstate'; // 导入持久化插件（确保这个行是有效的）

   const useTokenStore = defineStore('token', () => {
     const token = ref('');

     const removeToken = () => {
       token.value = '';
     };

     return { token, removeToken };
   }, {
     persist: true, // 直接在这里配置
   });

   export default useTokenStore;
   
import { createApp } from 'vue';
import './plugins/assets';
import Simplebar from 'simplebar-vue';
import { setupAppVersionNotification, setupDayjs, setupIconifyOffline, setupLoading, setupNProgress } from './plugins';
import { setupStore } from './store';
import { setupRouter } from './router';
import { setupI18n } from './locales';
import App from './App.vue';
import 'simplebar-vue/dist/simplebar.min.css';

async function setupApp() {
  setupLoading();

  setupNProgress();

  setupIconifyOffline();

  setupDayjs();

  const app = createApp(App);

  setupStore(app);

  await setupRouter(app);

  setupI18n(app);

  setupAppVersionNotification();

  // 全局注册 Simplebar 组件
  app.component('Simplebar', Simplebar);

  app.mount('#app');
}

setupApp();

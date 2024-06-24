import './assets/main.css'
import axios from 'axios';
import AuthService from './services/AuthService';
import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'

const app = createApp(App)

app.use(createPinia())
app.use(router)

app.mount('#app')
axios.interceptors.request.use(
    config => {
        const currentUser = AuthService.getCurrentUser();
        if (currentUser && currentUser.token) {
            config.headers.Authorization = `${currentUser.token}`;
        }
        return config;
    },
    error => {
        return Promise.reject(error);
    }
);
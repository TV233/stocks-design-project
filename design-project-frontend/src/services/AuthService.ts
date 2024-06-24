// src/services/AuthService.ts
import axios from 'axios';

const API_URL = 'http://localhost:8080/';

class AuthService {
    login(user: any) {
        // 创建一个 URLSearchParams 对象
        const params = new URLSearchParams();
        params.append('username', user.username);
        params.append('password', user.password);

        return axios.post(API_URL + 'user/login', params, {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        })
            .then(response => {
                // 检查返回的数据中是否包含token
                if (response.data && response.data.data) {
                    console.log(response.data.data)
                    // 保存令牌到localStorage
                    localStorage.setItem('user', JSON.stringify({ token: response.data.data }));
                }
                return response.data;
            });
    }

    logout() {
        localStorage.removeItem('user');
    }

    getCurrentUser() {
        const userStr = localStorage.getItem('user');
        if (!userStr) {
            return { token: null }; // 返回一个具有空token的对象
        }
        return JSON.parse(userStr);
    }
}

export default new AuthService();

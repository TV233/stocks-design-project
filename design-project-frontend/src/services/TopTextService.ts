// src/services/TopTextService.ts
import axios from 'axios';

const API_URL = 'http://localhost:8080/api/';

class TopTextService {
    getTopText() {
        return axios.get(API_URL + 'top_text')
            .then(response => {
                if (response.data.code === 0) {
                    // 正确处理返回的数据
                    return response.data.data;
                } else {
                    // 处理可能的错误或无效的响应
                    throw new Error(response.data.message || "Error fetching data");
                }
            })
            .catch(error => {
                console.error('Error during API call:', error);
                throw error;
            });
    }
}

export default new TopTextService();

<template>
  <div>
    <!-- 用户信息 -->
    <a-descriptions title="用户信息" bordered>
      <a-descriptions-item label="ID">
        <a-input v-model:value="userInfo.id" @change="handleChange" />
      </a-descriptions-item>
      <a-descriptions-item label="用户名">
        <a-input v-model:value="userInfo.username" @change="handleChange" />
      </a-descriptions-item>
      <a-descriptions-item label="昵称">
        <a-input v-model:value="userInfo.nickname" @change="handleChange" />
      </a-descriptions-item>
      <a-descriptions-item label="邮箱">
        <a-input v-model:value="userInfo.email" @change="handleChange" />
      </a-descriptions-item>
      <a-descriptions-item label="创建时间">
        <a-input v-model:value="userInfo.createTime" @change="handleChange" />
      </a-descriptions-item>
      <a-descriptions-item label="更新时间">
        <a-input v-model:value="userInfo.updateTime" @change="handleChange" />
      </a-descriptions-item>
    </a-descriptions>

    <!-- 重置密码按钮 -->
    <a-button type="primary" @click="showModal" class='mt-4'>
      重置密码
    </a-button>

    <!-- 重置密码模态框 -->
    <a-modal v-model:visible="visible" title="重置密码" @ok="handleOk" @cancel="handleCancel">
      <a-form ref="formRef" :model="model" :rules="rules">
        <a-form-item name="old_pwd" label="旧密码">
          <a-input-password v-model:value="model.old_pwd" size="large" placeholder="请输入旧密码" />
        </a-form-item>
        <a-form-item name="new_pwd" label="新密码">
          <a-input-password v-model:value="model.new_pwd" size="large" placeholder="请输入新密码" />
        </a-form-item>
        <a-form-item name="re_pwd" label="确认新密码">
          <a-input-password v-model:value="model.re_pwd" size="large" placeholder="请确认新密码" />
        </a-form-item>
      </a-form>
    </a-modal>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import { request } from '@/service/request';

// 初始默认值
const userInfo = ref({
  id: 0,
  username: '默认用户名',
  nickname: '默认昵称',
  email: '默认邮箱',
  createTime: '默认创建时间',
  updateTime: '默认更新时间'
});

const model = ref({
  old_pwd: '',
  new_pwd: '',
  re_pwd: ''
});

const rules = {
  old_pwd: [{ required: true, message: '请输入旧密码', trigger: 'blur' }],
  new_pwd: [{ required: true, message: '请输入新密码', trigger: 'blur' }],
  re_pwd: [{ required: true, message: '请确认新密码', trigger: 'blur' }]
};

const formRef = ref(null);
const visible = ref(false);

const getUserInfo = async () => {
  try {
    const response = await request({
      url: '/user/userInfo',
      method: 'GET',
      headers: {
        Authorization: 'Bearer your_token_here' // 替换为实际的 token
      }
    });
    if (response.code === 0) {
      userInfo.value = response.data;
    } else {
      console.error('获取用户信息失败:', response.message);
    }
  } catch (error) {
    console.error('获取用户信息失败:', error);
  }
};

const updateUserInfo = async () => {
  try {
    const response = await request({
      url: '/user/update',
      method: 'PUT',
      headers: {
        Authorization: 'Bearer your_token_here' // 替换为实际的 token
      },
      data: {
        id: userInfo.value.id,
        nickname: userInfo.value.nickname,
        email: userInfo.value.email
      }
    });
    if (response.code === 0) {
      console.log('用户信息更新成功');
    } else {
      console.error('用户信息更新失败:', response.message);
    }
  } catch (error) {
    console.error('用户信息更新失败:', error);
  }
};

const updateUserPassword = async () => {
  try {
    const response = await request({
      url: '/user/updatePwd',
      method: 'PATCH',
      headers: {
        Authorization: 'Bearer your_token_here' // 替换为实际的 token
      },
      data: {
        old_pwd: model.value.old_pwd,
        new_pwd: model.value.new_pwd,
        re_pwd: model.value.re_pwd
      }
    });
    if (response.code === 0) {
      console.log('密码更新成功');
      visible.value = false;
    } else {
      console.error('密码更新失败:', response.message);
    }
  } catch (error) {
    console.error('密码更新失败:', error);
  }
};

const handleSubmit = () => {
  formRef.value.validate().then(() => {
    updateUserPassword();
  }).catch(error => {
    console.log('验证失败', error);
  });
};

const showModal = () => {
  visible.value = true;
};

const handleOk = () => {
  handleSubmit();
};

const handleCancel = () => {
  visible.value = false;
};

const handleChange = () => {
  updateUserInfo();
};

// 示例调用
getUserInfo();
</script>

<style scoped>
/* 你的样式代码 */
</style>

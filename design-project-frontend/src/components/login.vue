<template>
  <div>
    <input v-model="username" placeholder="Username">
    <input type="password" v-model="password" placeholder="Password">
    <button @click="login">Login</button>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue';
import AuthService from '../services/AuthService';
import { useRouter } from 'vue-router';

export default defineComponent({
  setup() {
    const router = useRouter();
    const username = ref('');
    const password = ref('');

    const login = async () => {
      try {
        const data = await AuthService.login({ username: username.value, password: password.value });
        alert('Login successful!');
        router.push('/');
      } catch (error) {
        alert('Login failed!');
      }
    };

    return {
      username,
      password,
      login
    };
  }
});
</script>

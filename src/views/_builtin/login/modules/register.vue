<script setup lang="ts">
import { computed, reactive } from 'vue';
import { $t } from '@/locales';
import { useRouterPush } from '@/hooks/common/router';
import { useAntdForm, useFormRules } from '@/hooks/common/form';
import { registerUser } from '@/service/api/auth'; // 假设你将这个函数放在了 auth.ts 文件中

// import { useCaptcha } from '@/hooks/business/captcha';

defineOptions({
  name: 'Register'
});

// const authStore = useAuthStore();

const { toggleLoginModule } = useRouterPush();
const { formRef, validate } = useAntdForm();
// const { label, isCounting, loading, getCaptcha } = useCaptcha();

interface FormModel {
  userName: string;
  // code: string;
  password: string;
  confirmPassword: string;
}

const model: FormModel = reactive({
  userName: '',
  // code: '',
  password: '',
  confirmPassword: ''
});

const rules = computed<Record<keyof FormModel, App.Global.FormRule[]>>(() => {
  const { formRules, createConfirmPwdRule } = useFormRules();

  return {
    userName: formRules.userName,
    // code: formRules.code,
    password: formRules.pwd,
    confirmPassword: createConfirmPwdRule(model.password)
  };
});

async function handleSubmit() {
  await validate();
  try {
    const response = await registerUser(model.userName, model.password);

    if (response.error?.response?.data.code == '0') {
      window.$message?.success('注册成功');
    } else {
      window.$message?.error(response.error?.response?.data.message || '注册失败');
    }
  } catch (error) {
    window.$message?.error('注册失败');
  }
  await new Promise(resolve => {
    setTimeout(resolve, 1000);
  });
  toggleLoginModule('pwd-login');
}
</script>

<template>
  <AForm ref="formRef" :model="model" :rules="rules">
    <AFormItem name="userNmae">
      <AInput v-model:value="model.userName" size="large" :placeholder="$t('page.login.common.userNamePlaceholder')" />
    </AFormItem>
    <!-- 无验证码,担心ddos攻击 -->
    <!--
 <AFormItem name="code">
      <div class="w-full flex-y-center gap-16px">
        <AInput v-model:value="model.code" size="large" :placeholder="$t('page.login.common.codePlaceholder')" />
        <AButton size="large" :disabled="isCounting" :loading="loading" @click="getCaptcha(model.phone)">
          {{ label }}
        </AButton>
      </div>
    </AFormItem>
-->
    <AFormItem name="password">
      <AInputPassword
        v-model:value="model.password"
        size="large"
        :placeholder="$t('page.login.common.passwordPlaceholder')"
      />
    </AFormItem>
    <AFormItem name="confirmPassword">
      <AInputPassword
        v-model:value="model.confirmPassword"
        size="large"
        :placeholder="$t('page.login.common.confirmPasswordPlaceholder')"
      />
    </AFormItem>
    <ASpace direction="vertical" size="large" class="w-full">
      <AButton type="primary" block size="large" shape="round" @click="handleSubmit">
        {{ $t('common.confirm') }}
      </AButton>
      <AButton block size="large" shape="round" @click="toggleLoginModule('pwd-login')">
        {{ $t('page.login.common.back') }}
      </AButton>
    </ASpace>
  </AForm>
</template>

<style scoped></style>

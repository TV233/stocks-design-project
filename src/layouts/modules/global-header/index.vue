<script lang="ts" setup>
import { computed, ref } from 'vue';
import { useFullscreen } from '@vueuse/core';
import { useAppStore } from '@/store/modules/app';
import { useThemeStore } from '@/store/modules/theme';
import { useRouteStore } from '@/store/modules/route';
import { request } from '@/service/request';
import { useRouterPush } from '@/hooks/common/router';
import HorizontalMenu from '../global-menu/base-menu.vue';
import GlobalLogo from '../global-logo/index.vue';
import { useMixMenuContext } from '../../context';
import UserAvatar from './components/user-avatar.vue';
const { routerPushByKey } = useRouterPush();
defineOptions({
  name: 'GlobalHeader'
});

interface Props {
  /** Whether to show the logo */
  showLogo?: App.Global.HeaderProps['showLogo'];
  /** Whether to show the menu toggler */
  showMenuToggler?: App.Global.HeaderProps['showMenuToggler'];
  /** Whether to show the menu */
  showMenu?: App.Global.HeaderProps['showMenu'];
}

defineProps<Props>();

const appStore = useAppStore();
const themeStore = useThemeStore();
const routeStore = useRouteStore();
const { isFullscreen, toggle } = useFullscreen();
const { menus } = useMixMenuContext();

const headerMenus = computed(() => {
  if (themeStore.layout.mode === 'horizontal') {
    return routeStore.menus;
  }

  if (themeStore.layout.mode === 'horizontal-mix') {
    return menus.value;
  }

  return [];
});

const searchQuery = ref('');
const searchResults = ref<Array<{ stockCode: string; stockName: string }>>([
  {
    stockCode: '000001',
    stockName: '平安银行'
  },
  {
    stockCode: '001359',
    stockName: '平安电工'
  },
  {
    stockCode: '601318',
    stockName: '中国平安'
  }
]);
const selectedStock = ref('');

// 从后端获取搜索结果
async function fetchSearchResults() {
  try {
    const result = await request({
      url: '/stock/find',
      method: 'GET',
      params: {
        stockName: searchQuery.value
      }
    });
    if (result && result.code === 0) {
      searchResults.value = result.data;
    } else {
      // console.error('Error fetching search results:', result.message);
    }
  } catch (error) {
    // console.error('Error fetching search results:', error);
  }
}

const querySearchAsync = (queryString: string) => {
  searchQuery.value = queryString;
  fetchSearchResults();
};

const handleSelect = (stockCode: string) => {
  console.log(stockCode);
};
</script>

<template>
  <DarkModeContainer class="h-full flex-y-center px-12px shadow-header">
    <GlobalLogo v-if="showLogo" class="h-full" :style="{ width: themeStore.sider.width + 'px' }" />
    <HorizontalMenu v-if="showMenu" mode="horizontal" :menus="headerMenus" class="px-12px" />
    <div v-else class="h-full flex-y-center flex-1-hidden">
      <MenuToggler v-if="showMenuToggler" :collapsed="appStore.siderCollapse" @click="appStore.toggleSiderCollapse" />
    </div>
    <ElSelect
      v-model="selectedStock"
      filterable
      remote
      :remote-method="querySearchAsync"
      placeholder="输入您想查询的股票名字或代码"
      size="large"
      class="mr-66 !w-96"
      @change="handleSelect"
    >
      <ElOption
        v-for="item in searchResults"
        :key="item.stockCode"
        :label="item.stockName"
        :value="item.stockCode"
        @click="routerPushByKey('detail', { query: { stockCode: item.stockCode } })"
      />
    </ElSelect>

    <div class="h-full flex-y-center justify-end">
      <FullScreen v-if="!appStore.isMobile" :full="isFullscreen" @click="toggle" />
      <UserAvatar />
    </div>
  </DarkModeContainer>
</template>

<style scoped></style>

<script setup lang="ts">
import { useRoute, useRouter } from 'vue-router';
import { computed, ref } from 'vue';

const route = useRoute();
const router = useRouter();

const routeQuery = computed(() => JSON.stringify(route.query));
const stockDetail = ref({
  stockCode: '000821',
  stockName: '京山轻机',
  industry: null,
  latestPrice: '11.56',
  priceChangeRate: '2.76',
  priceChange: '0.31',
  riseSpeed: '0.09',
  ratingOrgNum: 9,
  ratingBuyNum: 7,
  ratingAddNum: 2,
  ratingNeutralNum: 0,
  ratingReduceNum: 0,
  ratingSaleNum: 0,
  year1: 2023,
  eps1: 0.540262,
  year2: 2024,
  eps2: 0.87375,
  year3: 2025,
  eps3: 1.11375,
  year4: 2026,
  eps4: 1.48125
});
// 将字符串属性转换为数字并保留两位小数
stockDetail.value = {
  ...stockDetail.value,
  latestPrice: Math.round(Number.parseFloat(stockDetail.value.latestPrice) * 100) / 100,
  priceChangeRate: Math.round(Number.parseFloat(stockDetail.value.priceChangeRate) * 100) / 100,
  priceChange: Math.round(Number.parseFloat(stockDetail.value.priceChange) * 100) / 100,
  riseSpeed: Math.round(Number.parseFloat(stockDetail.value.riseSpeed) * 100) / 100
};
const goBack = () => {
  router.back();
};
</script>

<template>
  <div>
    <ATooltip placement="right">
      <template #title>返回</template>
      <AButton shape="circle" @click="goBack"><icon-ic:outline-keyboard-arrow-left class="h-8 w-8 pb-2" /></AButton>
    </ATooltip>
    <ARow class="flex justify-between">
      <ACard :bordered="false" class="mt-2 w-40% card-wrapper">
        <div class="flex justify-between">
          <div>
            <div class="mb--1 text-5 font-bold">{{ stockDetail.stockName }}</div>
            <div class="flex-x-center text-4">{{ stockDetail.stockCode }}</div>
            <div class="flex-x-center text-4">{{ stockDetail.industry }}</div>
          </div>
          <div>
            <div>
              <CountTo
                suffix=""
                :start-value="1"
                :end-value="stockDetail.latestPrice"
                :decimals="2"
                class="text-6 font-sans"
                :class="stockDetail.priceChange >= 0 ? 'text-[#fe2435]' : 'text-[#08aa4b]'"
              />
            </div>
            <div class="mt--2 flex justify-between">
              <CountTo
                suffix=""
                :start-value="1"
                :end-value="stockDetail.priceChange"
                :decimals="2"
                class="text-3 font-sans"
                :class="stockDetail.priceChange >= 0 ? 'text-[#fe2435]' : 'text-[#08aa4b]'"
              />
              <CountTo
                suffix="%"
                :start-value="1"
                :end-value="stockDetail.priceChangeRate"
                :decimals="2"
                class="ml-1.5 text-3 font-sans"
                :class="stockDetail.priceChange >= 0 ? 'text-[#fe2435]' : 'text-[#08aa4b]'"
              />
            </div>
          </div>
          <ATooltip placement="topRight">
            <template #title>加入自选</template>
          <AButton class="mt-2" type="primary" shape="circle" size="large"><icon-material-symbols-light:add-rounded class="w-10 h-12 mt--2.5"/></AButton>
        </ATooltip></div>
      </ACard>
      <ACard :bordered="false" class="mt-2 w-40% card-wrapper">1</ACard>
    </ARow>
  </div>
</template>

<style scoped></style>

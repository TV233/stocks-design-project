<script setup lang="ts">
import { computed, ref } from 'vue';
import { createReusableTemplate } from '@vueuse/core';
import ProgressBar from './progress-bar.vue';
defineOptions({
  name: 'CardData'
});

interface CardData {
  key: string;
  title: string;
  value: number;
  unit: string;
  color: {
    start: string;
    end: string;
  };
  icon: string;
}
const up = ref(true);
const cardData = computed<any>(() => [
  {
    id: 1,
    title: '上证指数',
    value: 9725,
    change: 123,
    rate: 12,
    color: {
      start: up.value ? '#ffe9eb' : '#e6f6ec',
      end: up.value ? '#fef6f7' : '#f7fcf8'
    }
  },
  {
    id: 2,
    title: '深证成指',
    value: 9725,
    change: 123,
    rate: 12,
    color: {
      start: up.value ? '#ffe9eb' : '#e6f6ec',
      end: up.value ? '#fef6f7' : '#f7fcf8'
    }
  },
  {
    id: 3,
    title: '创业板',
    value: 9725,
    change: 123,
    rate: 12,
    color: {
      start: up.value ? '#ffe9eb' : '#e6f6ec',
      end: up.value ? '#fef6f7' : '#f7fcf8'
    }
  },
  {
    id: 4,
    title: 'b',
    value: 8500,
    change: 98,
    rate: 11,
    color: {
      start: up.value ? '#ffe9eb' : '#e6f6ec',
      end: up.value ? '#fef6f7' : '#f7fcf8'
    }
  },
  {
    id: 5,
    title: 'd',
    value: 10200,
    change: 150,
    rate: 14,
    color: {
      start: up.value ? '#ffe9eb' : '#e6f6ec',
      end: up.value ? '#fef6f7' : '#f7fcf8'
    }
  },
  {
    id: 6,
    title: 'a',
    value: 7800,
    change: 75,
    rate: 9,
    color: {
      start: up.value ? '#ffe9eb' : '#e6f6ec',
      end: up.value ? '#fef6f7' : '#f7fcf8'
    }
  },
  {
    id: 7,
    title: 'fd',
    value: 11000,
    change: 180,
    rate: 16,
    color: {
      start: up.value ? '#ffe9eb' : '#e6f6ec',
      end: up.value ? '#fef6f7' : '#f7fcf8'
    }
  },
  {
    id: 8,
    title: 'dasf',
    value: 9200,
    change: 110,
    rate: 13,
    color: {
      start: up.value ? '#ffe9eb' : '#e6f6ec',
      end: up.value ? '#fef6f7' : '#f7fcf8'
    }
  },
  {
    id: 9,
    title: 'das',
    value: 8800,
    change: 85,
    rate: 10,
    color: {
      start: up.value ? '#ffe9eb' : '#e6f6ec',
      end: up.value ? '#fef6f7' : '#f7fcf8'
    }
  }
]);

interface GradientBgProps {
  gradientColor: string;
}

const [DefineGradientBg, GradientBg] = createReusableTemplate<GradientBgProps>();

function getGradientColor(color: CardData['color']) {
  return `linear-gradient(to bottom, ${color.start}, ${color.end})`;
}
</script>

<template>
  <ACard :bordered="false" size="small" class="card-wrapper">
    <!-- define component start: GradientBg -->
    <DefineGradientBg v-slot="{ $slots, gradientColor }">
      <div class="rd-8px px-16px pb-4px pt-8px text-white" :style="{ backgroundImage: gradientColor }">
        <component :is="$slots.default" />
      </div>
    </DefineGradientBg>
    <!-- define component end: GradientBg -->

    <ADescriptions title="A股 开盘中" class="font-sans"></ADescriptions>

    <ARow :gutter="[16, 16]" class="flex-nowrap overflow-auto pb-2">
      <ACol v-for="item in cardData" :key="item.id" :span="24" :sm="6" :lg="3">
        <GradientBg :gradient-color="getGradientColor(item.color)" class="flex-1">
          <h3 class="w-full flex justify-center text-16px text-dark font-600">{{ item.title }}</h3>
          <div class="mt--2 flex justify-center">
            <CountTo :prefix="item.unit" :start-value="1" :end-value="item.value"
              class="text-6 text-[upcolor.value] dark:text-white" :class="up ? 'text-[#fe2435]' : 'text-[#08aa4b]'" />
          </div>
          <div class="mt--2 flex justify-between">
            <CountTo :prefix="up ? '+' : '-'" :start-value="1" :end-value="item.change" class="text-3 dark:text-white"
              :class="up ? 'text-[#fe2435]' : 'text-[#08aa4b]'" />
            <CountTo suffix="%" :start-value="1" :end-value="item.rate" class="text-3 dark:text-white"
              :class="up ? 'text-[#fe2435]' : 'text-[#08aa4b]'" />
          </div>
        </GradientBg>
      </ACol>
    </ARow>
    <ProgressBar :decrease="2345" :increase="345" />
    <div class="mb--5 mt-2 font-sans h-12 text-4 font-bold">今日实时成交额
      <CountTo suffix="亿" :start-value="1" :end-value="默认值" class="text-4 dark:text-white"
         />
    </div>
  </ACard>
</template>

<style scoped></style>

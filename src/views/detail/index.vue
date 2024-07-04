<script setup lang="ts">
import { useRoute, useRouter } from 'vue-router';
import { computed, onMounted, onUnmounted, ref, watch } from 'vue';
import * as echarts from 'echarts';
import { fetchStockDataAndPrediction, fetchStockFinancialData } from '@/service/api/stock-detail';

const route = useRoute();
const router = useRouter();

const stockDetail = ref({});
const stockInfo = ref({});

// 获取股票年报数据
fetchStockFinancialData(route.query.stockCode).then(response => {
  stockInfo.value = response.error.response.data.data;
});

// 获取股票实时涨跌数据和预测数据
fetchStockDataAndPrediction(route.query.stockCode).then(response => {
  stockDetail.value = response.error.response.data.data;
});

// 将字符串属性转换为数字并保留两位小数
const formattedStockDetail = computed(() => {
  const detail = stockDetail.value;
  return {
    ...detail,
    latestPrice: Math.round(Number.parseFloat(detail.latestPrice) * 100) / 100,
    priceChangeRate: Math.round(Number.parseFloat(detail.priceChangeRate) * 100) / 100,
    priceChange: Math.round(Number.parseFloat(detail.priceChange) * 100) / 100,
    riseSpeed: Math.round(Number.parseFloat(detail.riseSpeed) * 100) / 100
  };
});

const goBack = () => {
  router.back();
};

const chartRef = ref<HTMLDivElement | null>(null);
let chartInstance: echarts.ECharts | null = null;

const pieChartConfig = computed(() => ({
  backgroundColor: 'transparent',
  series: [
    {
      type: 'pie',
      radius: '40%', // 缩小图表半径
      data: [
        { value: stockDetail.value.ratingBuyNum, name: '买进指数', itemStyle: { color: '#d62728' } }, // 红色
        { value: stockDetail.value.ratingAddNum, name: '增持指数', itemStyle: { color: '#ff7f0e' } }, // 橙色
        { value: stockDetail.value.ratingNeutralNum || 0, name: '观望指数', itemStyle: { color: '#2ca02c' } }, // 绿色
        { value: stockDetail.value.ratingReduceNum || 0, name: '减持指数', itemStyle: { color: '#9467bd' } }, // 紫色
        { value: stockDetail.value.ratingSaleNum || 0, name: '卖出指数', itemStyle: { color: '#1f77b4' } } // 蓝色
      ].filter(item => item.value > 0), // 过滤掉值为0的数据
      label: {
        fontSize: 10, // 缩小字体
        formatter(params) {
          return `${params.name}: ${params.value}`;
        }
      },
      emphasis: {
        itemStyle: {
          shadowBlur: 10,
          shadowOffsetX: 0,
          shadowColor: 'rgba(0, 0, 0, 0.5)'
        }
      }
    }
  ]
}));

onMounted(() => {
  if (chartRef.value) {
    chartInstance = echarts.init(chartRef.value);
    chartInstance.setOption(pieChartConfig.value);
  }
});

onUnmounted(() => {
  if (chartInstance) {
    chartInstance.dispose();
  }
});

const barChartRef = ref<HTMLDivElement | null>(null);
let barChartInstance: echarts.ECharts | null = null;

const barChartConfig = computed(() => ({
  backgroundColor: 'transparent',
  title: {
    text: '股票收益预测',
    left: 'center'
  },
  tooltip: {
    trigger: 'axis',
    axisPointer: {
      type: 'shadow'
    }
  },
  xAxis: {
    type: 'category',
    data: [stockDetail.value.year1, stockDetail.value.year2, stockDetail.value.year3, stockDetail.value.year4],
    name: ' ',
    nameLocation: 'middle',
    nameGap: 25
  },
  yAxis: {
    type: 'value',
    name: '每股收益 (EPS)',
    nameLocation: 'middle',
    nameGap: 40
  },
  series: [
    {
      data: [stockDetail.value.eps1, stockDetail.value.eps2, stockDetail.value.eps3, stockDetail.value.eps4],
      type: 'bar',
      itemStyle: {
        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
          { offset: 0, color: '#ff7f0e' },
          { offset: 0.5, color: '#ff5722' },
          { offset: 1, color: '#e64a19' }
        ])
      },
      emphasis: {
        itemStyle: {
          color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
            { offset: 0, color: '#ff5722' },
            { offset: 0.7, color: '#e64a19' },
            { offset: 1, color: '#d84315' }
          ])
        }
      },
      animationDelay(idx) {
        return idx * 200;
      }
    }
  ]
}));

onMounted(() => {
  if (barChartRef.value) {
    barChartInstance = echarts.init(barChartRef.value);
    barChartInstance.setOption(barChartConfig.value);
  }
});

onUnmounted(() => {
  if (barChartInstance) {
    barChartInstance.dispose();
  }
});

const lineChartRef = ref<HTMLDivElement | null>(null);
let lineChartInstance: echarts.ECharts | null = null;

const lineChartConfig = computed(() => ({
  backgroundColor: 'transparent',
  title: {
    text: '股票年报数据 ',
    left: 'center',
    top: '2%'
  },
  tooltip: {
    trigger: 'axis'
  },
  legend: {
    data: ['营业总收入', '归母净利润', '扣非归母净利润'],
    top: '8%'
  },
  xAxis: {
    type: 'category',
    data: Object.keys(stockInfo.value.financialDataByYear),
    name: ' ',
    nameLocation: 'middle',
    nameGap: 25
  },
  yAxis: [
    {
      type: 'value',
      name: '营业总收入',
      nameLocation: 'middle',
      nameGap: 40,
      axisLabel: {
        formatter: '{value}'
      }
    },
    {
      type: 'value',
      name: '归母净利润/扣非归母净利润',
      nameLocation: 'middle',
      nameGap: 60,
      axisLabel: {
        formatter: '{value}'
      }
    }
  ],
  series: [
    {
      name: '营业总收入',
      type: 'line',
      yAxisIndex: 0,
      data: Object.values(stockInfo.value.financialDataByYear).map(yearData => yearData.totalOperatereveIncrease),
      areaStyle: {},
      itemStyle: {
        color: '#ff0000'
      },
      animationDelay(idx) {
        return idx * 200;
      }
    },
    {
      name: '归母净利润',
      type: 'line',
      yAxisIndex: 1,
      data: Object.values(stockInfo.value.financialDataByYear).map(yearData => yearData.parentNetprofitIncrease),
      itemStyle: {
        color: '#00ff00'
      },
      animationDelay(idx) {
        return idx * 200;
      }
    },
    {
      name: '扣非归母净利润',
      type: 'line',
      yAxisIndex: 1,
      data: Object.values(stockInfo.value.financialDataByYear).map(yearData => yearData.dnetprofitatpcTcalIncrease),
      itemStyle: {
        color: '#0000ff'
      },
      animationDelay(idx) {
        return idx * 200;
      }
    }
  ],
  animationEasing: 'linear',
  animationDuration: 2000
}));

onMounted(() => {
  if (lineChartRef.value) {
    lineChartInstance = echarts.init(lineChartRef.value);
    lineChartInstance.setOption(lineChartConfig.value);
  }
});

onUnmounted(() => {
  if (lineChartInstance) {
    lineChartInstance.dispose();
  }
});
// 监听 stockDetail 的变化
watch(
  stockDetail,
  newVal => {
    if (chartRef.value) {
      chartInstance = echarts.init(chartRef.value);
      chartInstance.setOption(pieChartConfig.value);
    }
    if (barChartRef.value) {
      barChartInstance = echarts.init(barChartRef.value);
      barChartInstance.setOption(barChartConfig.value);
    }
    if (lineChartRef.value) {
      lineChartInstance = echarts.init(lineChartRef.value);
      lineChartInstance.setOption(lineChartConfig.value);
    }
  },
  { deep: true }
);
// 监听 stockDetail 的变化
watch(stockInfo, (newVal) => {
  if (chartRef.value) {
    chartInstance = echarts.init(chartRef.value);
    chartInstance.setOption(pieChartConfig.value);
  }
  if (barChartRef.value) {
    barChartInstance = echarts.init(barChartRef.value);
    barChartInstance.setOption(barChartConfig.value);
  }
  if (lineChartRef.value) {
    lineChartInstance = echarts.init(lineChartRef.value);
    lineChartInstance.setOption(lineChartConfig.value);
  }
}, { deep: true });
</script>

<template>
  <div>
    <ATooltip placement="right">
      <template #title>返回</template>
      <AButton shape="circle" @click="goBack"><icon-ic:outline-keyboard-arrow-left class="h-8 w-8 pb-2" /></AButton>
    </ATooltip>
    <ARow class="flex justify-between">
      <ACard :bordered="false" class="mt-2 w-35% card-wrapper">
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
                :end-value="formattedStockDetail.latestPrice"
                :decimals="2"
                class="text-6 font-sans"
                :class="formattedStockDetail.priceChange >= 0 ? 'text-[#fe2435]' : 'text-[#08aa4b]'"
              />
            </div>
            <div class="mt--2 flex justify-between">
              <CountTo
                suffix=""
                :start-value="1"
                :end-value="formattedStockDetail.priceChange"
                :decimals="2"
                class="text-3 font-sans"
                :class="formattedStockDetail.priceChange >= 0 ? 'text-[#fe2435]' : 'text-[#08aa4b]'"
              />
              <CountTo
                suffix="%"
                :start-value="1"
                :end-value="formattedStockDetail.priceChangeRate"
                :decimals="2"
                class="ml-1.5 text-3 font-sans"
                :class="formattedStockDetail.priceChange >= 0 ? 'text-[#fe2435]' : 'text-[#08aa4b]'"
              />
            </div>
          </div>
          <ATooltip placement="topRight">
            <template #title>加入自选</template>
            <AButton class="mt-2" type="primary" shape="circle" size="large">
              <icon-material-symbols-light:add-rounded class="mt--2.5 h-12 w-10" />
            </AButton>
          </ATooltip>
        </div>
        <div class="mb-2 mt-5 w-full flex-x-center text-5 font-bold">
          投资评级
          <icon-tdesign:compass class="ml-1 mt-2" />
        </div>
        <div ref="chartRef" class="chart-container mb--17 mt--14 flex-x-center"></div>
      </ACard>
      <ACard :bordered="false" class="mt-2 w-60% card-wrapper">
        <div ref="barChartRef" class="bar-chart-container mb--14 mt--5 flex-x-center"></div>
      </ACard>
    </ARow>
    <ACard :bordered="false" class="mt-4 w-full card-wrapper">
      <div ref="lineChartRef" class="line-chart-container mb--14 mt--5 flex-x-center"></div>
    </ACard>
  </div>
</template>

<style scoped>
.chart-container {
  width: 100%; /* 缩小图表宽度 */
  height: 15rem; /* 缩小图表高度 */
}
.bar-chart-container {
  width: 100%;
  height: 18rem;
}
.line-chart-container {
  width: 100%;
  height: 20rem;
}
</style>

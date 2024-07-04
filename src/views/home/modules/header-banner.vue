<script setup lang="ts">
import { computed, onMounted, onUnmounted, ref } from 'vue';
import * as echarts from 'echarts';

defineOptions({
  name: 'HeaderBanner'
});

const chartRef = ref<HTMLDivElement | null>(null);
let chartInstance: echarts.ECharts | null = null;

const colors = ['#00c05a', '#e6a900', '#e00005']; // 调整颜色顺序，从绿渐变为红
const fontColor = '#0089fa';
const emotionIndex = 0.6043; // 情绪指数值，范围从0到1，保留四位小数

const option = {
  backgroundColor: 'transparent', // 设置背景为透明
  series: [
    {
      type: 'gauge',
      name: '情绪指数',
      radius: '60%', // 缩小图表

      pointer: {
        show: true,
        length: '60%',
        width: 6,
        animation: true, // 增加指针动画效果
        animationDuration: 2000, // 动画持续时间
        animationEasing: 'bounceOut' // 动画效果
      },
      detail: {
        show: true,
        offsetCenter: [0, '60%'],
        fontSize: 14,
        color: '#000',
        formatter(value: number) {
          return (value / 100).toFixed(4);
        }
      },
      data: [
        {
          value: emotionIndex * 100, // 将情绪指数转换为百分比
          name: '市场情绪指数'
        }
      ],
      title: {
        show: true,
        offsetCenter: [0, '105%'],
        textStyle: {
          color: '#cf0f0f'
        }
      },
      axisLine: {
        lineStyle: {
          width: 20, // 缩小环的宽度
          color: [
            [0.1, colors[0]],
            [
              0.3,
              new echarts.graphic.LinearGradient(0, 1, 0, 0, [
                {
                  offset: 0,
                  color: colors[0]
                },
                {
                  offset: 0.8,
                  color: colors[1]
                }
              ])
            ],
            [0.7, colors[1]],
            [
              0.9,
              new echarts.graphic.LinearGradient(0, 1, 0, 0, [
                {
                  offset: 0,
                  color: colors[2]
                },
                {
                  offset: 0.6,
                  color: colors[1]
                }
              ])
            ],
            [1, colors[2]]
          ]
        }
      },
      splitLine: {
        show: false, // 取消内侧刻度
        length: 15,
        lineStyle: {
          width: 2,
          color: '#ffffff'
        }
      },
      axisTick: {
        show: false, // 取消内侧刻度
        lineStyle: {
          width: 1,
          color: '#ffffff'
        }
      },
      axisLabel: {
        show: false, // 取消内侧刻度
        color: fontColor,
        distance: 20,
        fontSize: 13
      },
      itemStyle: {
        normal: {
          color: ' #cf0f0f'
        }
      },
      silent: false
    }
  ]
};

const marketInfo = ref({
  positionIndex: 49,
  title: '把握市场节奏仍是关键',
  content: '热点轮动进一步提速 把握市场节奏仍是关键 '
});
onMounted(() => {
  if (chartRef.value) {
    chartInstance = echarts.init(chartRef.value);
    chartInstance.setOption(option);
  }
});

onUnmounted(() => {
  if (chartInstance) {
    chartInstance.dispose();
  }
});
</script>

<template>
  <ACard :bordered="false" class="h-37 card-wrapper p-y--30">
    <div class="text-7 font-bold">
      <span class="logo m--3 inline-block h-10 w-10 rounded-50%"></span>
      市场总览 : {{ marketInfo.title }}
    </div>
    <ARow :gutter="[16, 16]">
      <ACol :span="12" :md="10">
        <div class="text-5 font-sans mt-2 ml-7 mr--22">{{ marketInfo.content }}</div>
      </ACol>
      <ACol :span="24" :md="6">
        <div ref="chartRef" class="chart-container mt--25 "></div>
      </ACol>
    </ARow>
  </ACard>
</template>

<style scoped>
.chart-container {
  width: 100%;
  height: 200px; /* 缩小图表高度 */
}
.logo {
  background-image: url(../../../assets/svg-icon/logo.svg);
  background-repeat: no-repeat;
  background-size: cover;
}
</style>

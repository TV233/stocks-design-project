<script lang="ts" setup>
import { ref } from 'vue';
import { useRouterPush } from '@/hooks/common/router';
const { routerPushByKey } = useRouterPush();
const columns = [
  { title: '股票名称', dataIndex: 'name', key: 'name', fixed: 'left', width: 90 },
  { title: '股票代码', dataIndex: 'code', key: 'code', width: 60 },
  { title: '当前股价', dataIndex: 'price', key: 'price', width: 60 },
  { title: '股票涨幅', dataIndex: 'change', key: 'change', width: 60 },
  { title: '增长率', dataIndex: 'rate', key: 'rate', width: 75 },
  { title: '', key: 'action', fixed: 'right', width: 100 }
];

const data = ref([
  {
    key: 1,
    name: 'Apple Inc.',
    code: '123456',
    price: 150.75,
    change: 1.25,
    rate: 0.83
  },
  {
    key: 2,
    name: 'Microsoft Corporation',
    code: 'MSFT',
    price: 295.4,
    change: -2.3,
    rate: -0.77
  },
  {
    key: 3,
    name: 'Tesla, Inc.',
    code: 'TSLA',
    price: 780.79,
    change: 15.21,
    rate: 2.0
  }
]);

const printStockInfo = stock => {
  console.log(`Selected Stock: ${stock.name}`);
  console.log(`code: ${stock.code}`);
  console.log(`Price: ${stock.price.toFixed(2)}`);
  console.log(`Change: ${stock.change.toFixed(2)}`);
  console.log(`Change Percent: ${stock.rate.toFixed(2)}%`);
};

const onDelete = (key: number) => {
  data.value = data.value.filter(item => item.key !== key);
};
</script>

<template>
  <ACard>
    <div class="mb-3 text-7 font-bold">我的仓库</div>
    <ATable :columns="columns" :data-source="data" :scroll="{ x: 0 }" :pagination="false">
      <template #bodyCell="{ column, record }">
        <template v-if="column.key === 'price' || column.key === 'change' || column.key === 'rate'">
          <span :style="{ color: record.change >= 0 ? 'red' : 'green' }">
            {{ column.key === 'rate' ? record[column.key] + '%' : record[column.key] }}
          </span>
        </template>
        <template v-else-if="column.key === 'action'">
          <div style="display: flex; align-items: center">
            <AButton danger style="margin-right: 8px" @click="routerPushByKey('detail', { query: { a: '1' } })">
              查看详情
            </AButton>
            <APopconfirm v-if="data.length" title="确定要删除吗？" @confirm="onDelete(record.key)">
              <AButton type="primary">删除</AButton>
            </APopconfirm>
          </div>
        </template>
      </template>
    </ATable>
  </ACard>
</template>

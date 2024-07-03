<script lang="ts" setup>
defineOptions({ name: 'BestRank' });

const columns = [
  { title: '排名', dataIndex: 'id' },
  { title: '股票名称', dataIndex: 'stockName' },
  { title: '股票代码', dataIndex: 'stockCode' },
  {
    title: '',
    dataIndex: 'action',
    slots: { customRender: 'action' }
  }
];

const data = [
  {
    stockCode: '600519',
    stockName: '贵州茅台'
  },
  {
    stockCode: '600519',
    stockName: '贵州茅台'
  },
  {
    stockCode: '600519',
    stockName: '贵州茅台'
  },
  {
    stockCode: '600519',
    stockName: '贵州茅台'
  },
  {
    stockCode: '600519',
    stockName: '贵州茅台'
  },
  {
    stockCode: '600519',
    stockName: '贵州茅台'
  },
  {
    stockCode: '600519',
    stockName: '贵州茅台'
  },
  {
    stockCode: '600519',
    stockName: '贵州茅台'
  },
  {
    stockCode: '600519',
    stockName: '贵州茅台'
  },
  {
    stockCode: '600519',
    stockName: '贵州茅台'
  },
  {
    stockCode: '600519',
    stockName: '贵州茅台'
  },
  {
    stockCode: '600519',
    stockName: '贵州茅台'
  },
  {
    stockCode: '600519',
    stockName: '贵州茅台'
  },
  {
    stockCode: '600519',
    stockName: '贵州茅台'
  },
  {
    stockCode: '600519',
    stockName: '贵州茅台'
  },
  {
    stockCode: '600519',
    stockName: '贵州茅台'
  },
  {
    stockCode: '600519',
    stockName: '贵州茅台'
  },
  {
    stockCode: '600519',
    stockName: '贵州茅台'
  },
  {
    stockCode: '600519',
    stockName: '贵州茅台'
  },
  {
    stockCode: '600519',
    stockName: '贵州茅台'
  }
];

const rankData = data.map((item, index) => ({
  id: index + 1,
  ...item
}));

const handleAction = (record: any) => {
  console.log(record);
};
</script>

<template>
  <ATable
    :pagination="false"
    :scroll="{ y: 690 }"
    class="ant-table-striped"
    size="middle"
    :columns="columns"
    :data-source="rankData"
  >
    <template #action="{ record }">
      <a @click="handleAction(record)">
        <AButton shape="circle" class="mt-2 h-8 w-4">
          <icon-material-symbols:arrow-forward-ios class="h-5 w-5" />
        </AButton>
      </a>
    </template>
  </ATable>
</template>

<style scoped></style>

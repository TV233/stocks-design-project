<script lang="ts" setup>
import { useRouterPush } from '@/hooks/common/router';
defineOptions({ name: 'BestRank' });
const { routerPushByKey } = useRouterPush();
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
  }
  // 其他数据项...
];

const rankData = data.map((item, index) => ({
  id: index + 1,
  ...item
}));

const handleAction = (record: any) => {
  const stockCode = record.stockCode;
  routerPushByKey('detail', { query: { stockCode } });
};
</script>

<template>
  <Simplebar>
    <ATable
      :pagination="false"
      :scroll="{ y: 730 }"
      class="ant-table-striped"
      size="middle"
      :columns="columns"
      :data-source="rankData"
    >
      <template #action="{ record }">
        <a @click="handleAction(record)">
          <ATooltip placement="bottom">
            <template #title>查看详情</template>

            <AButton shape="circle" class="mt-2 h-8 w-4">
              <icon-material-symbols:arrow-forward-ios class="h-5 w-5" />
            </AButton>
          </ATooltip>
        </a>
      </template>
    </ATable>
  </Simplebar>
</template>

<style scoped></style>

<script setup lang="ts">
import { ref } from 'vue';
import type { CarouselProps } from 'ant-design-vue';
defineOptions({
  name: 'HotTopic'
});

const dotPosition = ref<CarouselProps['dotPosition']>('right');
</script>

<template>
  <ACard :bordered="false" class="card-wrapper">
    <ACarousel :dot-position="dotPosition" autoplay :dots="false">
      <div>
        <ACard :bordered="false" class="h-100%">

          <div class="flex justify-between">
            <div class=' text-5 font-bold'>Sora概念(文生视频)</div>
            <div class=' text-4 border-2 border-[#fe2435] font-bold'>会畅通讯 300578</div>
          </div>
        </ACard>
      </div>
      <!-- <div>
        <ACard :bordered="false" class="card-wrapper">das </ACard>

      </div>
      <div>
        <ACard :bordered="false" class="card-wrapper">ff </ACard>

      </div>
      <div>
        <ACard :bordered="false" class="card-wrapper">das </ACard>

      </div> -->
    </ACarousel>
  </ACard>

</template>

<style scoped>
/* For demo */
:deep(.slick-slide) {
  height: 20rem;
  overflow: hidden;
}

:deep(.slick-slide h3) {
  color: #000;
}
</style>

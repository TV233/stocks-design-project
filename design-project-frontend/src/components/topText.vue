<template>
  <div>
    <h1>{{ topText.title }}</h1>
    <p>{{ topText.content }}</p>
  </div>
</template>

<script lang="ts">
import { defineComponent, onMounted, ref } from 'vue';
import TopTextService from '../services/TopTextService';

export default defineComponent({
  setup() {
    const topText = ref({ positionIndex: 0, title: '', content: '' });

    onMounted(() => {
      TopTextService.getTopText().then(data => {
        topText.value = data;
      }).catch(error => {
        console.error('Error fetching top text:', error);
      });
    });

    return {
      topText
    };
  }
});
</script>

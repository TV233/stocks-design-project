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
  name: 'TopTextViewer',
  setup() {
    const topText = ref({ positionIndex: 0, title: '', content: '' });

    onMounted(() => {
      TopTextService.getTopText().then(response => {
        topText.value = response.data;
      }).catch(error => {
        console.error('There was an error!', error);
      });
    });

    return {
      topText
    };
  }
});
</script>

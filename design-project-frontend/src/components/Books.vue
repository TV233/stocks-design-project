<template>
  <div>
    <table>
      <tr>
        <td>编号</td>
        <td>名字</td>
        <td>作者</td>
      </tr>
      <tr v-for="book in books">
        <td>{{ book.id }}</td>
        <td>{{ book.name }}</td>
        <td>{{ book.author }}</td>
      </tr>
    </table>
  </div>
</template>

<script>
export default {
  name: "Book",
  data() {
    return {
      books: [
        {
          id: 1,
          name: "三体",
          author: "刘慈欣",
        },
        {
          id: 2,
          name: "java从零到入土",
          author: "我",
        },
      ],
    };
  },
};
</script>

<style scoped>
</style>
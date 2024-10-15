<template>
  <section>
    <div class="icons">
      <font-awesome-icon id="edit" icon="fa-regular fa-pen-to-square" />
      <font-awesome-icon id="delete" icon="fa-regular fa-trash-can" />
    </div>
    <ul>
      <li v-for="character in savedCharacters" :key="character.id">
        {{ character.name }}
      </li>
      <!-- make dropdown menu -->
    </ul>
  </section>
</template>

<script>
export default {
  props: [
    'savedCharacters'
  ],
}
</script>

<style>

</style>
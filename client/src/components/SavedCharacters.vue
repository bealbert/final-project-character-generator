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
  computed: {
    // savedCharacters() {
    //   return this.$store.state.characters.filter((character) => {
    //     return character.userId == this.$route.params.userId;
    //   })
    //}
  }
}
</script>

<style>

</style>
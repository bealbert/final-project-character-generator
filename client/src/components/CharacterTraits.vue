<template>
  <section>
    <ul>
      <li v-for="name in names" :key="name.id">{{ name }}</li>
    </ul>
    <ul>
      <li v-for="appearance in appearances" :key="appearance.id">{{ appearance }}</li>
    </ul>
    <ul>
      <li v-for="definingTrait in definingTraits" :key="definingTrait.id">{{ definingTrait }}</li>
    </ul>
    <ul>
      <li v-for="adventurerRole in adventurerRoles" :key="adventurerRole.id">{{ adventurerRole }}</li>
    </ul>
  </section>
</template>

<script>
export default {
  computed: {
    names() {
      return this.$store.state.names;
    },
    appearances() {
      return this.$store.state.appearances;
    },
    definingTraits() {
      return this.$store.state.definingTraits;
    },
    adventurerRoles() {
      return this.$store.state.adventurerRoles;
    }
  }
}
</script>

<style>

</style>
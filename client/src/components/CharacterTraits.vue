<template>
  <section>
    <table>
      <tr>
        <th>Names</th>
        <th>Appearances</th>
        <th>Defining Traits</th>
        <th>Adventurer Roles</th>
      </tr>
      <tr>
        <td v-if="name in names" :key="name.id">{{ name }}</td>
      </tr>
    </table>
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
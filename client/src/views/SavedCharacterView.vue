<template>
  <saved-characters />
</template>

<script>
import SavedCharacters from '../components/SavedCharacters.vue';
export default {
  components: { SavedCharacters },
  created() {
    this.$store.commit('SET_CHARACTERS', )
  }
}
</script>

<style></style>
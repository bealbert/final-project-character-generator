<template>
  <saved-characters v-bind:savedCharacters="savedCharacters" />
</template>

<script>
import SavedCharacters from '../components/SavedCharacters.vue';
import { resourceService } from '../services/ResourceService';
export default {
  components: { SavedCharacters },
  data() {
    return {
      savedCharacters: [],
    }
  },
  created() {
    resourceService.getCharacters().then((response) => {
      this.$store.commit('SET_CHARACTERS', response.data);
      this.savedCharacters = this.$store.state.characters.filter((character) => {
        return character.userId == this.$store.state.user.id;
      });
      console.log(this.savedCharacters);
    })
  }
}
</script>

<style></style>
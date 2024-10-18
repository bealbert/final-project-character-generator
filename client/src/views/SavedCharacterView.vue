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
    Promise.all([
      resourceService.getNames(),
      resourceService.getAppearances(),
      resourceService.getDefiningTraits(),
      resourceService.getAdventurerRoles(),
      resourceService.getCharacters()
    ]).then(([nameResponse, appearanceResponse, definingTraitResponse, adventurerRoleResponse, charactersResponse]) => {
      this.$store.commit('SET_NAMES', nameResponse.data);
      this.$store.commit('SET_APPEARANCES', appearanceResponse.data);
      this.$store.commit('SET_DEFINING_TRAITS', definingTraitResponse.data);
      this.$store.commit('SET_ADVENTURER_ROLES', adventurerRoleResponse.data);
      this.$store.commit('SET_CHARACTERS', charactersResponse.data);
      this.savedCharacters = this.$store.state.characters.filter((character) => {
        return character.userId == this.$store.state.user.id;
      })
    }).catch((error) => {
      console.log(error);
    });
  }
}
</script>

<style></style>
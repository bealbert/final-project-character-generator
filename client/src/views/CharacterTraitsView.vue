<template>
    <div id="main-div">
        <character-traits />
    </div>
</template>

<script>
import CharacterTraits from '../components/CharacterTraits.vue';
import { resourceService } from '../services/ResourceService.js';
import LoadingSpinner from '../components/LoadingSpinner.vue';
export default {
    components: { CharacterTraits },
    data() {
        return {
            isLoading: false,
        };
    },
    created() {
        this.isLoading = true;

        Promise.all([
            resourceService.getNames(),
            resourceService.getAppearances(),
            resourceService.getDefiningTraits(),
            resourceService.getAdventurerRoles(),
        ]).then(([nameResponse, appearanceResponse, definingTraitResponse, adventurerRoleResponse]) => {
            this.$store.commit('SET_NAMES', nameResponse.data);
            this.$store.commit('SET_APPEARANCES', appearanceResponse.data);
            this.$store.commit('SET_DEFINING_TRAITS', definingTraitResponse.data);
            this.$store.commit('SET_ADVENTURER_ROLES', adventurerRoleResponse.data);
        }).catch((error) => {
            console.log(error);
        }).finally(() => {
            this.isLoading = false;
        });
    },
}
</script>

<style>

</style>
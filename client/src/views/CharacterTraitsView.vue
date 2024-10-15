<template>
    <character-traits />
</template>

<script>
import CharacterTraits from '../components/CharacterTraits.vue';
import { resourceService } from '../services/ResourceService.js';
export default {
    components: { CharacterTraits },
    created() {
        this.$store.commit('SET_NAMES', )
        this.$store.commit('SET_APPEARANCES',)
        this.$store.commit('SET_DEFINING_TRAITS',)
        this.$store.commit('SET_ADVENTURER_ROLES',)

    }
}
</script>

<style></style>
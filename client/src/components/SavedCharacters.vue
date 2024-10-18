<template>
  <section class="saved-characters">
    <form v-show="showForm" id="edit-character-form">
      <label>Name: </label>
      <input type="text" v-model="selectedCharacter.name">
      <label>Appearance: </label>
      <input type="text" v-model="selectedCharacter.appearance">
      <label>Defining Trait: </label>
      <input type="text" v-model="selectedCharacter.definingTrait">
      <label>Adventurer Role: </label>
      <input type="text" v-model="selectedCharacter.adventurerRole">
      <input type="submit">
    </form>
    <div class="edit-icons">
      <font-awesome-icon id="edit" icon="fa-solid fa-pen-to-square" @click="showForm = !showForm"/>
      <br>
      <font-awesome-icon id="delete" icon="fa-solid fa-trash-can" />
    </div>
    <ul id="saved">
      <li v-for="character in savedCharacters" :key="character.id">
        <input type="checkbox">
        {{ character.name }}
        <ul id="saved-appearance">
          <li>{{ character.appearance }}</li>
        </ul>
        <ul id="saved-defining-trait">
          <li>{{ character.definingTrait }}</li>
        </ul>
        <ul id="saved-adventurer-role">
          <li>{{ character.adventurerRole }}</li>
        </ul>
      </li>
    </ul>
  </section>
</template>

<script>
import { resourceService } from '../services/ResourceService';

export default {
  data() {
    return {
      selectedCharacter: {
        characterId: 0,
        name: '',
        appearance: '',
        definingTrait: '',
        adventurerRole: '',
      },
      showForm: false,
    };
  },
  methods: {

    editCharacter(selectedCharacter) {
      resourceService.editCharacter(this.selectedCharacter.characterId, this.selectedCharacter).then((response) => {
        this.$router.push({ name: 'saved' });
      }).catch((error) => {
        console.log(error);
      });
    }
  },
  props: [
    'savedCharacters'
  ],
}
</script>

<style>
section.saved-characters {
  font-family: Georgia, 'Times New Roman', Times, serif;
  font-variant: small-caps;
  display: flex;
  flex-direction: column-reverse;
}

#saved {
  list-style: none;
  border-radius: 5px;
  background-color: #363537;
  color: white;
  justify-content: flex-start;
}

div.edit-icons {
  align-self: center;
  display: flex;
}

#edit {
  font-size: xx-large;
  color: #363537;
  padding: 20px;
}

#edit:hover {
  color: white;
  cursor: pointer;
}

#delete {
  font-size: xx-large;
  color: #363537;
  padding: 20px;
}

#delete:hover {
  color: white;
  cursor: pointer;
}
</style>
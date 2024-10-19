<template>
  <section class="saved-characters">
    <form v-show="showForm" id="edit-character-form" @submit.prevent.stop="editCharacter(selectedCharacter)">
      <label>Name: </label>
      <select name="edit-names" v-model="selectedCharacter.name">
        <option v-for="name in names" :key="name.traitId">{{ name.traitDescription }}</option>
      </select>
      <label>Appearance: </label>
      <select name="edit-appearances" v-model="selectedCharacter.appearance">
        <option v-for="appearance in appearances" :key="appearance.traitId">{{ appearance.traitDescription }}</option>
      </select>
      <label>Defining Trait: </label>
      <select name="edit-defining-traits" v-model="selectedCharacter.definingTrait">
        <option v-for="definingTrait in definingTraits" :key="definingTrait.traitId">{{ definingTrait.traitDescription }}
        </option>
      </select>
      <label>Adventurer Role: </label>
      <select name="edit-adventurer-role" v-model="selectedCharacter.adventurerRole">
        <option v-for="adventurerRole in adventurerRoles" :key="adventurerRole.traitId">
          {{ adventurerRole.traitDescription }}
        </option>
      </select>
      <input type="submit">
    </form>
    <div class="edit-icons">
      <font-awesome-icon id="edit" icon="fa-solid fa-pen-to-square" @click="showForm = !showForm" />
      <br>
      <font-awesome-icon id="delete" icon="fa-solid fa-trash-can"
        @click="deleteSelectedCharacters(deleteCharactersList)" />
    </div>
    <ul id="saved">
      <li v-for="character in savedCharacters" :key="character.id">
        <input type="checkbox" @change="addToSelectedCharacter(character, $event)" v-bind:value="character.characterId"
          v-bind:id="character.characterId" v-model="deleteCharactersList" />
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
        userId: 0,
        nameId: 0,
        appearanceId: 0,
        definingTraitId: 0,
        adventurerRoleId: 0
      },
      deleteCharactersList: [],
      showForm: false,
    };
  },
  methods: {
    addToSelectedCharacter(character, event) {
      if (event.target.checked) {
        this.selectedCharacter = character;
      }
    },
    editCharacter(selectedCharacter) {
      this.selectedCharacter.nameId = this.names.find((name) => {
        return name.traitDescription == selectedCharacter.name;
      }).traitId;
      this.selectedCharacter.appearanceId = this.appearances.find((appearance) => {
        return appearance.traitDescription == selectedCharacter.appearance;
      }).traitId;
      this.selectedCharacter.definingTraitId = this.definingTraits.find((definingTrait) => {
        return definingTrait.traitDescription == selectedCharacter.definingTrait;
      }).traitId;
      this.selectedCharacter.adventurerRoleId = this.adventurerRoles.find((adventurerRole) => {
        return adventurerRole.traitDescription == selectedCharacter.adventurerRole;
      }).traitId;
      resourceService.editCharacter(this.selectedCharacter.characterId, this.selectedCharacter).then((response) => {
        if (response.status === 201) {
          resourceService.getCharacters().then((response) => {
            this.$store.commit('SET_CHARACTERS', response.data);
            this.selectedCharacter = {};
            this.showForm = !this.showForm;
          });
        }
      }).catch((error) => {
        console.log(error);
      });
    },
    deleteSelectedCharacters(deleteCharactersList) {
      deleteCharactersList.forEach(characterId => {
        resourceService.deleteCharacter(characterId).then((response => {
          if (response.status === 204) {
            resourceService.getCharacters().then((charactersResponse) => {
              this.$store.commit('SET_CHARACTERS', charactersResponse.data);
            })
            this.deleteCharactersList = [];
          }
        }));
      });
    }
  },
  props: [
    'savedCharacters'
  ],
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

<style scoped>
form#edit-character-form {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}

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
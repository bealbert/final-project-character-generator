<template>
  <section class="traits-list">
    <ul id="name-traits">
      <h1>Names</h1>
      <li v-for="name in names" :key="name.traitId">
        <input type="checkbox" v-bind:value="name.traitId" v-bind:id="name.traitId" v-model="selectedNames" />
        {{ name.traitId + ": " + name.traitDescription }}
      </li>
    </ul>
    <ul id="appearance-traits">
      <h1>Appearances</h1>
      <li v-for="appearance in appearances" :key="appearance.traitId">
        <input type="checkbox" :value="appearance.traitId" :id="appearance.traitId" v-model="selectedAppearances" />
        {{ appearance.traitId + ": " + appearance.traitDescription }}
      </li>
    </ul>
    <ul id="defining-traits">
      <h1>Defining Traits</h1>
      <li v-for="definingTrait in definingTraits" :key="definingTrait.traitId">
        <input type="checkbox" :value="definingTrait.traitId" :id="definingTrait.traitId"
          v-model="selectedDefiningTraits" />
        {{ definingTrait.traitId + ": " + definingTrait.traitDescription }}
      </li>
    </ul>
    <ul id="adventurer-role-traits">
      <h1>Adventurer Roles</h1>
      <li v-for="adventurerRole in adventurerRoles" :key="adventurerRole.traitId">
        <input type="checkbox" :value="adventurerRole.traitId" :id="adventurerRole.traitId"
          v-model="selectedAdventurerRoles" />
        {{ adventurerRole.traitId + ": " + adventurerRole.traitDescription }}
      </li>
    </ul>
  </section>
  <form v-show="showForm" id="add-new-trait" @submit.prevent.stop="addNewTrait(newTrait)">
    <label>Choose Trait Category: </label>
    <select name="trait-options" v-model="newTrait.traitCategory">
      <option value="names">Names</option>
      <option value="appearances">Appearances</option>
      <option value="defining-traits">Defining Traits</option>
      <option value="adventurer-roles">Adventurer Roles</option>
    </select>
    <label>Trait Description: </label>
    <input type="text" v-model="newTrait.traitDescription">
    <input type="submit" id="submit-btn">
  </form>
  <div class="trait-buttons">
    <button id="add-new-btn" @click="showForm = !showForm">Add New Trait</button>
    <button id="delete-btn"
      @click="deleteTraits(selectedNames, selectedAppearances, selectedDefiningTraits, selectedAdventurerRoles)">
      Delete Trait</button>
  </div>
</template>

<script>
import { resourceService } from '../services/ResourceService';

export default {
  data() {
    return {
      selectedNames: [],
      selectedAppearances: [],
      selectedDefiningTraits: [],
      selectedAdventurerRoles: [],
      newTrait: {
        traitCategory: '',
        traitDescription: ''
      },
      showForm: false,
    }
  },
  methods: {
    deleteTraits(selectedNames, selectedAppearances, selectedDefiningTraits, selectedAdventurerRoles) {
      if(selectedNames.length > 0) {
        selectedNames.forEach(nameId => {
        resourceService.deleteName(nameId).then((nameResponse) => {
          this.$store.commit('SET_NAMES', nameResponse.data);
        })
      });
      selectedNames = [];
      }
      if (selectedAppearances.length > 0) {
        selectedAppearances.forEach(appearanceId => {
        resourceService.deleteAppearance(appearanceId).then((appearanceResponse) => {
          this.$store.commit('SET_APPEARANCES', appearanceResponse.data);
        })
      });
      selectedAppearances = [];
      }
      if(selectedDefiningTraits.length > 0) {
        selectedDefiningTraits.forEach(definingTraitId => {
        resourceService.deleteDefiningTrait(definingTraitId).then((definingTraitResponse) => {
          this.$store.commit('SET_DEFINING_TRAITS', definingTraitResponse.data);
        })
      });
      selectedDefiningTraits = [];
      }
      if(selectedAdventurerRoles.length > 0) {
        selectedAdventurerRoles.forEach(adventurerRoleId => {
        resourceService.deleteAdventurerRole(adventurerRoleId).then((adventurerRoleResponse) => {
          this.$store.commit('SET_ADVENTURER_ROLES', adventurerRoleResponse.data);
        })
      });
      selectedAdventurerRoles = [];
      }
    },
    addNewTrait(newTrait) {
      if (newTrait.traitCategory == 'names') {
        resourceService.addName(newTrait).then((nameResponse) => {
          this.$store.commit('SET_NAMES', nameResponse.data)
        });
      } else if (newTrait.traitCategory == 'appearances') {
        resourceService.addAppearance(newTrait).then((appearanceResponse) => {
          this.$store.commit('SET_APPEARANCES', appearanceResponse.data);
        });
      } else if (newTrait.traitCategory == 'defining-traits') {
        resourceService.addDefiningTrait(newTrait).then((definingTraitResponse) => {
          this.$store.commit('SET_DEFINING_TRAITS', definingTraitResponse.data);
        });
      } else if (newTrait.traitCategory == 'adventurer-roles') {
        resourceService.addAdventurerRole(newTrait).then((adventurerRoleResponse) => {
          this.$store.commit('SET_ADVENTURER_ROLES', adventurerRoleResponse.data);
        });
      }
      this.showForm = !this.showForm;
    }
  },
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
ul {
  list-style: none;
  background-color: #363537;
  border-radius: 5px;
  color: white;
  margin: 10px;
}

.traits-list {
  display: flex;
  font-family: Georgia, 'Times New Roman', Times, serif;
  font-variant: small-caps;
}

.trait-buttons {
  font-variant: small-caps;
  font-weight: 900;
  font-size: larger;
  font-family: Georgia, 'Times New Roman', Times, serif;
}

#delete-btn {
  color: white;
  margin: 10px;
  margin-top: 50px;
  background-color: #F15025;
  border-radius: 4px;
  cursor: pointer;
}

#add-new-btn {
  color: white;
  margin: 10px;
  margin-top: 50px;
  background-color: #F15025;
  border-radius: 4px;
  cursor: pointer;
}
</style>
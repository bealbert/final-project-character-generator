<template>
  <section class="traits-list">
    <ul id="name-traits">
      <h1>Names</h1>
      <li v-for="name in $store.state.names" :key="name.traitId">
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
      if (selectedNames.length > 0) {
        selectedNames.forEach(nameId => {
          resourceService.deleteName(nameId).then((nameResponse) => {
            if (nameResponse.status === 204) {
              resourceService.getNames().then((namesResponse) => {
                this.$store.commit('SET_NAMES', namesResponse.data);
              });
            }
          });
        });
        selectedNames = [];
      }
      if (selectedAppearances.length > 0) {
        selectedAppearances.forEach(appearanceId => {
          resourceService.deleteAppearance(appearanceId).then((appearanceResponse) => {
            if (appearanceResponse.status === 204) {
              resourceService.getAppearances().then((appearancesResponse) => {
                this.$store.commit('SET_APPEARANCES', appearancesResponse.data);
              });
            }
          });
        });
        selectedAppearances = [];
      }
      if (selectedDefiningTraits.length > 0) {
        selectedDefiningTraits.forEach(definingTraitId => {
          resourceService.deleteDefiningTrait(definingTraitId).then((definingTraitResponse) => {
            if (definingTraitResponse === 204) {
              resourceService.getDefiningTraits().then((definingTraitsResponse) => {
                this.$store.commit('SET_DEFINING_TRAITS', definingTraitsResponse.data);
              });
            }
          });
        });
        selectedDefiningTraits = [];
      }
      if (selectedAdventurerRoles.length > 0) {
        selectedAdventurerRoles.forEach(adventurerRoleId => {
          resourceService.deleteAdventurerRole(adventurerRoleId).then((adventurerRoleResponse) => {
            if (adventurerRoleResponse.status === 204) {
              resourceService.getAdventurerRoles((adventurerRolesResponse) => {
                this.$store.commit('SET_ADVENTURER_ROLES', adventurerRolesResponse.data);
              });
            }
          });
        });
        selectedAdventurerRoles = [];
      }
    },
    addNewTrait(newTrait) {
      if (newTrait.traitCategory == 'names') {
        resourceService.addName(newTrait).then((nameResponse) => {
          if (nameResponse.status === 201) {
            resourceService.getNames().then((namesResponse) => {
              this.$store.commit('SET_NAMES', namesResponse.data);
            });
          }
        });
      } else if (newTrait.traitCategory == 'appearances') {
        resourceService.addAppearance(newTrait).then((appearanceResponse) => {
          if (appearanceResponse.status === 201) {
            resourceService.getAppearances().then((appearancesResponse) => {
              this.$store.commit('SET_APPEARANCES', appearancesResponse.data);
            });
          }
        });
      } else if (newTrait.traitCategory == 'defining-traits') {
        resourceService.addDefiningTrait(newTrait).then((definingTraitResponse) => {
          if (definingTraitResponse.status === 201) {
            resourceService.getDefiningTraits().then((definingTraitsResponse) => {
              this.$store.commit('SET_DEFINING_TRAITS', definingTraitsResponse.data);
            });
          }
        });
      } else if (newTrait.traitCategory == 'adventurer-roles') {
        resourceService.addAdventurerRole(newTrait).then((adventurerRoleResponse) => {
          if (adventurerRoleResponse.status === 201) {
            resourceService.getAdventurerRoles().then((adventurerRolesResponse) => {
              this.$store.commit('SET_ADVENTURER_ROLES', adventurerRolesResponse.data);
            });
          }
        });
      }
      this.newTrait = {};
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

<style scoped>
ul {
  list-style: none;
  background-color: #363537;
  border-radius: 5px;
  color: white;
  margin: 10px;
  padding-bottom: 20px;
  padding-left: 10px
}

.traits-list {
  display: flex;
  font-family: Georgia, 'Times New Roman', Times, serif;
  font-variant: small-caps;
  flex-direction: column;
}

h1 {
  text-align: center;
  padding-right: 15%;
  font-size: large;
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

@media only screen and (max-width: 425px) {
  
}
</style>
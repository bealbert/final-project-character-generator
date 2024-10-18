import axios from "axios";

const resourceService = {
  getCharacters() {
    return axios.get('/characters')
  },

  generateCharacter() {
    return axios.post('/characters');
  },

  getNames() {
    return axios.get('/traits/names');
  },
  
  getAppearances() {
    return axios.get('/traits/appearances');
  },

  getDefiningTraits() {
    return axios.get('/traits/defining_traits');
  },

  getAdventurerRoles() {
    return axios.get('/traits/adventurer_roles');
  },

  addName(name) {
    return axios.post('/traits/names', name);
  },

  addAppearance(appearance) {
    return axios.post('/traits/appearances', appearance);
  },

  addDefiningTrait(definingTrait) {
    return axios.post('/traits/defining_traits', definingTrait);
  },

  addAdventurerRole(adventurerRole) {
    return axios.post('/traits/adventurer_roles', adventurerRole);
  },

  updateName(nameId, name) {
    return axios.put(`/traits/names/${nameId}`, name);
  },

  updateAppearance(appearanceId, appearance) {
    return axios.put(`/traits/appearances/${appearanceId}`, appearance);
  },

  updateDefiningTrait(definingTraitId, definingTrait) {
    return axios.put(`/traits/defining_traits/${definingTraitId}`, definingTrait);
  },

  updateAdventurerRole(adventurerRoleId, adventurerRole) {
    return axios.put(`/traits/adventurer_roles/${adventurerRoleId}`, adventurerRole);
  },

  editCharacter(characterId, character) {
    return axios.put(`/characters/${characterId}`, character);
  },

  deleteCharacter(characterId) {
    return axios.delete(`/characters/${characterId}`);
  }
};

export { resourceService };

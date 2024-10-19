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

  editCharacter(characterId, character) {
    return axios.put(`/characters/${characterId}`, character);
  },

  deleteName(nameId) {
    return axios.delete(`/traits/names/${nameId}`);
  },

  deleteAppearance(appearanceId) {
    return axios.delete(`/traits/appearances/${appearanceId}`);
  },

  deleteDefiningTrait(definingTraitId) {
    return axios.delete(`/traits/defining_traits/${definingTraitId}`);
  },

  deleteAdventurerRole(adventurerRoleId) {
    return axios.delete(`/traits/adventurer_roles/${adventurerRoleId}`);
  },

  deleteCharacter(characterId) {
    return axios.delete(`/characters/${characterId}`);
  }
};

export { resourceService };

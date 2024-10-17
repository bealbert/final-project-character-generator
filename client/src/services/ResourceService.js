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
  // saveCharacter() {
  //   return axios.post('/user/user-id/characters/character-id');
  // }
  editCharacter(characterId, character) {
    return axios.put(`/characters/${characterId}`, character);
  }
};

export { resourceService };

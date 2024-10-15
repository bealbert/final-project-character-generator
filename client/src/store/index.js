import { createStore as _createStore } from 'vuex';
import axios from 'axios';

export function createStore(currentToken, currentUser) {
  let store = _createStore({
    state: {
      savedCharacters: [],
        names: [],
        appearances: [],
        definingTraits: [],
        adventurerRoles: [],
      token: currentToken || '',
      user: currentUser || {},
    },
    mutations: {
      SET_SAVED_CHARACTERS(state, characters) {
        // state.savedCharacters = characters.filter((character) => {
        //   return character.userId = userId;
        // })
      },
      SET_CHARACTERS(state, characters) {
        state.characters = characters;
      },
      SET_NAMES(state, names) {
        state.names = names;
      },
      SET_APPEARANCES(state, appearances) {
        state.appearances = appearances;
      },
      SET_DEFINING_TRAITS(state, definingTraits) {
        state.definingTraits = definingTraits;
      },
      SET_ADVENTURER_ROLES(state, adventurerRoles) {
        state.adventurerRoles = adventurerRoles;
      },
      SET_AUTH_TOKEN(state, token) {
        state.token = token;
        localStorage.setItem('token', token);
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
      },
      SET_USER(state, user) {
        state.user = user;
        localStorage.setItem('user', JSON.stringify(user));
      },
      LOGOUT(state) {
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        state.token = '';
        state.user = {};
        axios.defaults.headers.common = {};
      }
    },

  })
  return store;
}
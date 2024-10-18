import { createStore as _createStore } from 'vuex';
import axios from 'axios';

export function createStore(currentToken, currentUser) {
  let store = _createStore({
    state: {
      characters: [],
        names: {
          traitId: 0,
          traitDescription: ''
        },
        appearances: {
          traitId: 0,
          traitDescription: ''
        },
        definingTraits: {
          traitId: 0,
          traitDescription: ''
        },
        adventurerRoles: {
          traitId: 0,
          traitDescription: ''
        },
      token: currentToken || '',
      user: currentUser || {},
    },
    mutations: {
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
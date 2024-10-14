import { createStore as _createStore } from 'vuex';
import axios from 'axios';

export function createStore(currentToken, currentUser) {
  let store = _createStore({
    state: {
      savedCharacters: [],
      traits: {
        names: [],
        appearances: [],
        definingTraits: [],
        roles: []
      },
      token: currentToken || '',
      user: currentUser || {},
    },
    mutations: {
      SET_SAVED_CHARACTERS(state, characters) {
        state.savedCharacters = characters;
        //filter characters by userId?
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
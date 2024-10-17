<template>
  <section class="character-info">
    <div class="icons">
      <font-awesome-icon id="name" icon="fa-solid fa-dice-d20" />
      <font-awesome-icon id="appearance" icon="fa-solid fa-palette" />
      <font-awesome-icon id="defining-trait" icon="fa-solid fa-star" />
      <font-awesome-icon id="role" icon="fa-solid fa-hat-wizard" />
    </div>
    <ul class="traits">
      <li id="name-txt">Name: {{ character.name }}</li>
      <li id="appearance-txt">Appearance: {{ character.appearance }}</li>
      <li id="defining-trait-txt">Defining Trait: {{ character.definingTrait }}</li>
      <li id="role-txt">Role: {{ character.adventurerRole }}</li>
    </ul>
  </section>
  <button @click="generate" id="btn">Generate New Character</button>
</template>
<!-- button needs to change to "save character" when char is generated -->
<!-- icons need text boxes added on click, create submit form -->

<script>
import { resourceService } from '../services/ResourceService';

export default {
  data() {
    return {
      character: {
        id: 0,
        name: '',
        appearance: '',
        definingTrait: '',
        adventurerRole: '',
      },
    }
  },
  methods: {
    generate() {
      resourceService.generateCharacter().then((response) => {
        this.character = response.data;
      })
    }
  }
}
</script>

<style>
.character-info {
  display: flex;
  justify-content: flex-start;
  width: 82%;
  color: white;
}

div.icons {
  display: grid;
  margin-left: 40px;
  border-radius: 4px;
  align-items: center;
  padding: 10px;
  color: #363537;
}

#name {
  color: #363537;
  font-size: xx-large;
  align-self: self-start;
  padding-top: 41px;
  padding-left: 10px;
  padding-bottom: 41px;
}

#appearance {
  margin-top: 10px;
  font-size: xx-large;
  align-self: self-start;
  padding-top: 40px;
  padding-left: 10px;
  padding-bottom: 41px;
}

#defining-trait {
  margin-top: 10px;
  font-size: xx-large;
  align-self: self-start;
  padding-top: 40px;
  padding-left: 10px;
  padding-bottom: 41px;
}

#role {
  margin-top: 10px;
  font-size: xx-large;
  align-self: self-start;
  padding-top: 30px;
  padding-left: 10px;
  padding-bottom: 45px;
}

.traits {
  display: grid;
  list-style: none;
  font-weight: bold;
  font-size: small;
  border-radius: 4px;
  padding: 0;
  width: 95%;
  margin: 5px;
}

.traits>li {
  margin: 10px;
  border-radius: 5px;
  background-color: #363537;
  padding: 40px 40px 40px 5px;
}

#btn {
  align-self: center;
  margin: 10px;
  margin-top: 50px;
  font-variant: small-caps;
  background-color: #F15025;
  border-radius: 4px;
  font-weight: 900;
  cursor: pointer;
  display: block;
  font-size: larger;
  font-family: Georgia, 'Times New Roman', Times, serif;
  color: white;
}

#btn:hover {
  color: black;
}
</style>
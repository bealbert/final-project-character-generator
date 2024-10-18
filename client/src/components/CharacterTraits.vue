<template>
  <section class="traits-list">
    <ul id="name-traits">
    <h1>Names</h1>
      <li v-for="name in names" :key="name.traitId">
        <input type="checkbox"/>
        {{name.traitId + ": " + name.traitDescription }}</li>
    </ul>
    <ul id="appearance-traits">
      <h1>Appearances</h1>
      <li v-for="appearance in appearances" :key="appearance.traitId">
        <input type="checkbox"/>
        {{appearance.traitId + ": " +  appearance.traitDescription }}</li>
    </ul>
    <ul id="defining-traits">
      <h1>Defining Traits</h1>
      <li v-for="definingTrait in definingTraits" :key="definingTrait.traitId">
        <input type="checkbox"/>
        {{definingTrait.traitId + ": " +  definingTrait.traitDescription }}</li>
    </ul>
    <ul id="adventurer-role-traits">
      <h1>Adventurer Roles</h1>
      <li v-for="adventurerRole in adventurerRoles" :key="adventurerRole.traitId">
        <input type="checkbox"/>
        {{adventurerRole.traitId + ": " +  adventurerRole.traitDescription }}</li>
    </ul>
  </section>
  <div class="trait-buttons">
    <button id="edit-btn">Edit Trait</button>
    <button id="delete-btn">Delete Trait</button>
    <button id="add-new-btn">Add New Trait</button>
  </div>
</template>

<script>
export default {
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
</style>
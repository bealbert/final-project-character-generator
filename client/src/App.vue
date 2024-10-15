<template>
  <div id="character-generator-app">
    <header>
      <img src="./assets/logo_white.png" alt="" />
      <h1>B.E. Albert Character Generator</h1>
      <nav>
        <router-link v-bind:to="{ name: 'home' }">
          <button>Home</button>
        </router-link>
        <router-link v-bind:to="{ name: 'saved' }">
          <button>Saved</button>
        </router-link>
        <router-link v-bind:to="{ name: 'traits' }">
          <button>Traits</button>
        </router-link>
        <router-link v-bind:to="{ name: 'logout' }" v-if="this.$store.state.token">
          <button>Logout</button>
        </router-link>
        <router-link v-bind:to="{ name: 'login' }" v-else>
          <button>Login</button>
        </router-link>
      </nav>
    </header>
    <main>
      <router-view />
    </main>
    <footer>2024 &copy; All rights reserved.</footer>
  </div>
</template>

<script>

export default {

}
</script>


<style>
#character-generator-app {
  display: grid;
  grid-template-columns: 1fr;
  grid-template-rows: 50px 1fr 10px;
  grid-template-areas:
    "header"
    "main"
    "footer";
  gap: 10px;
  background-color: #E6E8E6;
  font-family: Georgia, 'Times New Roman', Times, serif;
}

header {
  grid-area: header;
  width: 98%;
  background-color: #F15025;
  border-radius: 4px;
  display: flex;
  align-items: center;
  justify-content: left;
  flex-direction: row-reverse;
  position: fixed;
  color: black;
}

header>nav {
  list-style: none;
  display: flex;
  padding: 0;
  font-size: small;
  font-weight: bold;
  margin: 5px;
  font-variant: small-caps;
}

h1 {
  font-size: medium;
  font-variant: small-caps;
  margin-left: 20%;
}

img {
  width: 15%;
  position: fixed;
  top: 70px;
  right: 10px;
  border-radius: 4px;
}

button:hover {
  color: purple;
}

main {
  grid-area: main;
  display: flex;
  flex-direction: column;
}

footer {
  grid-area: footer;
  justify-self: center;
  margin: 10px;
  align-self: flex-start;
  background-color: #F15025;
  font-weight: bold;
  /* try to stretch footer so orange runs across the bottom view */
}

@media only screen and (max-width: 425px) {
  h1 {
    font-size: small;
    margin: 10px;
    text-align: center;
  }

  header {
    height: 12%;
    font-size: small;
  }

  img {
    top: 70px;
  }

  footer {
    align-self: flex-start;
  }
}</style>
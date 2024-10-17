<template>
  <div id="login">
    <form v-on:submit.prevent="login">
      <h1>Please Sign In</h1>
      <div id="fields">
        <label for="username">Username</label>
        <input type="text" id="username" placeholder="Username" v-model="user.username" required autofocus />
        <label for="password">Password</label>
        <input type="password" id="password" placeholder="Password" v-model="user.password" required />
        <div id="submit"><button type="submit">Sign in</button></div>
      </div>
      <hr />
      Need an account? <router-link v-bind:to="{ name: 'register' }">Register!</router-link>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch((error) => {
          const response = error.response;
          if (!response) {
            alert(error);
          } else if (response.status === 401) {
            alert("Invalid username and password!");
          } else {
            alert(response.message);
          }
        });
    },
  },
};
</script>

<style scoped>
form {
  display: grid;
  grid-template-columns: 1fr;
  grid-template-rows: 50px 1fr 10px;
  grid-template-areas:
    "header"
    "fields"
    "submit";
  gap: 10px;
  background-color: #E6E8E6;
  font-family: Georgia, 'Times New Roman', Times, serif;
  align-items: center;
}

form>h1 {
  grid-area: 'header';
  font-size: medium;
  font-variant: small-caps;
  justify-self: center;
  padding-right: 20%;
}

#fields {
  grid-area: 'fields';
  background-color: #363537;
  color: white;
  padding-top: 20px;
  padding-left: 25px;
  padding-right: 25px;
  padding-bottom: 20px;
  border-radius: 5px;
  justify-self: center;
  display: flex;
  flex-direction: column;
}

label {
  font-variant: small-caps;
  font-weight: 400;
}

#username {
  margin: 10px;
}

#password {
  margin: 10px;
}

div#submit {
  grid-area: 'submit';
  align-self: center;
  font-weight: bold;
  font-variant: small-caps;
}
</style>

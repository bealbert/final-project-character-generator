<template>
  <div id="register">
    <form v-on:submit.prevent="register">
      <h1>Create Account</h1>
      <div id="fields">
        <label for="username">Username</label>
        <input type="text" id="username" placeholder="Username" v-model="user.username" required autofocus />
        <label for="name">Name</label>
        <input type="text" id="input-name" placeholder="Name" v-model="user.name" required />
        <label for="password">Password</label>
        <input type="password" id="password" placeholder="Password" v-model="user.password" required />
        <label for="confirmPassword">Confirm password</label>
        <input type="password" id="confirmPassword" placeholder="Confirm Password" v-model="user.confirmPassword"
          required />

        <label for="address">Address</label>
        <input type="text" id="address" placeholder="Address" v-model="user.address" />

        <label for="city">City</label>
        <input type="text" id="city" placeholder="City" v-model="user.city" />

        <label for="state">State</label>
        <input type="text" id="state" placeholder="State" v-model="user.stateCode" maxlength="2" required />

        <label for="zip">ZIP</label>
        <input type="number" id="zip" placeholder="ZIP" v-model="user.zip" required minlength="5" maxlength="5" />
      </div>
      <div id="create">
        <button id="create-account" type="submit">Create Account</button>
      </div>
      <hr />
      Have an account?
      <router-link id="login-btn" v-bind:to="{ name: 'login' }">Sign in!</router-link>
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
        name: "",
        password: "",
        confirmPassword: "",
        address: "",
        city: "",
        stateCode: "",
        zip: "",
        role: "user",
      },
    };
  },
  methods: {
    error(msg) {
      alert(msg);
    },
    success(msg) {
      alert(msg);
    },
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.error("Password & Confirm Password do not match");
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.success("Thank you for registering, please sign in.");
              this.$router.push({
                path: "/login",
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            if (!response) {
              this.error(error);
            } else if (response.status === 400) {
              if (response.data.errors) {
                // Show the validation errors
                let msg = "Validation error: ";
                for (let err of response.data.errors) {
                  msg += `'${err.field}':${err.defaultMessage}. `;
                }
                this.error(msg);
              } else {
                this.error(response.data.message);
              }
            } else {
              this.error(response.data.message);
            }
          });
      }
    },
  },
};
</script>

<style scoped>
form {
  gap: 10px;
  background-color: #E6E8E6;
  font-family: Georgia, 'Times New Roman', Times, serif;
  align-items: center;
  display: grid;
  grid-template-columns: 1fr;
  grid-template-rows: 50px 1fr 10px;
  grid-template-areas:
    "fields"
    "submit-btn"
    "login";
}

form>h1 {
  font-size: medium;
  font-variant: small-caps;
  justify-self: center;
}

#fields {
  grid-area: "fields";
  background-color: #363537;
  color: white;
  padding-top: 10px;
  padding-left: 25px;
  padding-right: 25px;
  padding-bottom: 10px;
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
  margin-bottom: 5px;
}

#input-name {
  margin-bottom: 5px;
}

#password {
  margin-bottom: 5px;
}

#confirmPassword {
  margin-bottom: 5px;
}

#address {
  margin-bottom: 5px;
}

#city {
  margin-bottom: 5px;
}

#state {
  margin-bottom: 5px;
}

#zip {
  margin-bottom: 5px;
}

div#create{
  grid-area: "submit-btn";
  justify-self: center;
}
#create-account {
  font-weight: bold;
  font-variant: small-caps;
  border-radius: 4px;
  padding-left: 20px;
  padding-right: 20px;
  padding-bottom: 3px;
}

#login-btn {
  grid-area: 'login'
}
</style>

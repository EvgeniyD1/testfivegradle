<template>
  <v-app>
    <v-app-bar>
      <v-toolbar-title>TestFiveApplication</v-toolbar-title>
      <div>
        {{ user }}
      </div>
      <input type="text"
             v-model="search"
             class="input"
      >
      <v-btn @click="findOrCreateU">Log In Or Create</v-btn>
    </v-app-bar>
    <message-form :user="user" @create="sendMessage" v-if="this.user!==''"/>
    <message-list :messages="messages" v-if="this.user!==''"/>
  </v-app>
</template>

<script>
import MessageForm from "./components/MessageForm.vue";
import MessageList from "./components/MessageList.vue";
import axios from "axios";
import {getIndex, onConnected} from "./ws";
import {sendMessage} from "./ws";
import {addHandler} from "./ws";

export default {
  components: {
    MessageForm, MessageList
  },
  data() {
    return {
      messages: [],
      search: '',
      user: '',
    }
  },
  mounted() {
    addHandler(data => {
      let index = getIndex(this.messages, data.id)
      if (index > -1) {
        this.messages.splice(index, 1, data)
      } else {
        this.messages.push(data)
      }
    })
  },
  methods: {
    sendMessage(message) {
      const newMessage = {
        sentFrom: this.user,
        title: message.title,
        text: message.text,
        sentTo: message.sendTo
      }
      // onConnected(message.sendTo)
      sendMessage(newMessage)
    },

    async findOrCreateU() {
      try {
        this.user = this.search;
        let url = '/users/' + this.user;
        const response = await axios.get(url);
        if (response.data.messages){
          this.messages = response.data.messages;
        }

        if (response.data.length === 0) {
          url = '/users'
          let newUser = {
            username: this.user
          }
          await axios.post(url, newUser)
          this.messages = [];
        }

        onConnected(this.user)
        this.search = ''
      } catch (e) {
        alert("Enter Username Or Some Error")
      }
    },

  },

}
</script>

<style>
body {
  padding-top: 100px;
}

.input {
  border: 1px red;
  background: snow;
  padding: 5px;
  margin: 5px;
}
</style>
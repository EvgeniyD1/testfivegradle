<template>
  <v-container style="padding: 0">
    <v-combobox v-model="message.sendTo"
                placeholder="send to"
                :items="users"
                @input="findByPattern"
    ></v-combobox>
    <v-text-field v-model="message.title"
                  placeholder="title"
    >
    </v-text-field>
    <v-textarea v-model="message.text"
                placeholder="message">
    </v-textarea>
    <v-btn @click="sendMessage">Send</v-btn>
  </v-container>
</template>

<script>
import axios from "axios";

export default {
  props: {
    user: {
      type: String,
      required: true
    }
  },
  data() {
    return {
      message: {
        sentFrom: '',
        sendTo: '',
        date: '',
        title: '',
        text: ''
      },
      users: [],
      count: 0
    }
  },
  emits: ['create'],
  methods: {
    sendMessage() {
      this.message.id = Date.now();
      this.message.sentFrom = this.user;
      this.message.date = new Date();
      this.$emit('create', this.message);
      this.message = {
        sendFrom: '',
        sentTo: '',
        title: '',
        text: ''
      };
      this.users = []
    },
    async findByPattern() {
      this.count += 1
      if (this.count % 3 === 0) {
        let url = '/users/byPattern/' + this.message.sendTo;
        const response = await axios.get(url);
        this.users = response.data;
        console.log(response)
      }
    }
  },
}
</script>

<style scoped>

</style>
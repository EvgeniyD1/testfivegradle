<template>
  <v-container style="padding: 0">
    <v-expansion-panels>
      <strong style="margin: 20px">My Messages</strong>
      <!--    messages-->
      <message-item v-for="message in orderedMessages"
                    :key="message" :message="message"
      >
      </message-item>
    </v-expansion-panels>
  </v-container>
</template>

<script>
import MessageItem from "./MessageItem.vue";
import _ from "lodash";

export default {
  components: {MessageItem},
  props: {
    messages: {
      type: Array,
      required: true
    }
  },
  computed: {
    orderedMessages: function () {
      return _.orderBy(this.messages, 'date', 'desc')
    }
  }

}
</script>

<style scoped>

</style>
<template>
  <v-expansion-panel>
    <v-expansion-panel-title>
      From: {{ message.sentFrom }}
      Date: {{message.date}}
      Title: {{ message.title }}
    </v-expansion-panel-title>
    <v-expansion-panel-text>
      {{ message.text }}
    </v-expansion-panel-text>
  </v-expansion-panel>
</template>

<script>
export default {
  props: {
    message: {
      type: Object,
      required: true
    }
  }
}
</script>

<style scoped>

</style>
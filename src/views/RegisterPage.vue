<template>
  <ion-page><ion-content class="auth-content"><main class="auth-card">
    <div class="brand"><ion-icon :icon="imagesOutline" /><span>Minha Galeria</span></div>
    <div><h1>Crie sua conta</h1><p>Organize suas fotos em um só lugar.</p></div>
    <form @submit.prevent="register"><ion-list lines="none">
      <ion-item><ion-input v-model="name" label="Nome" label-placement="stacked" autocomplete="name" placeholder="Como devemos chamar você?" required /></ion-item>
      <ion-item><ion-input v-model="email" label="E-mail" label-placement="stacked" type="email" autocomplete="email" placeholder="seu@email.com" required /></ion-item>
      <ion-item><ion-input v-model="password" label="Senha" label-placement="stacked" type="password" :minlength="6" placeholder="Mínimo de 6 caracteres" required /></ion-item>
    </ion-list><ion-text color="danger" v-if="error"><p class="error">{{ error }}</p></ion-text><ion-button type="submit" expand="block" class="primary-button">Cadastrar</ion-button></form>
    <p class="bottom-link">Já tem uma conta? <router-link to="/login">Entrar</router-link></p>
  </main></ion-content></ion-page>
</template>
<script setup lang="ts">
import { ref } from 'vue'; import { useRouter } from 'vue-router'; import { IonPage, IonContent, IonIcon, IonList, IonItem, IonInput, IonText, IonButton } from '@ionic/vue'; import { imagesOutline } from 'ionicons/icons';
const router = useRouter(); const name = ref(''); const email = ref(''); const password = ref(''); const error = ref('');
const register = () => { if (password.value.length < 6) { error.value = 'A senha deve ter pelo menos 6 caracteres.'; return; } localStorage.setItem('galeria-user', JSON.stringify({ name: name.value.trim(), email: email.value.trim().toLowerCase(), password: password.value })); localStorage.setItem('galeria-session', 'true'); router.replace('/home'); };
</script>

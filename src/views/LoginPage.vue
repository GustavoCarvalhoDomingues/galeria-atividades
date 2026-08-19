<template>
  <ion-page><ion-content class="auth-content"><main class="auth-card">
    <div class="brand"><ion-icon :icon="imagesOutline" /><span>Minha Galeria</span></div>
    <div><h1>Boas-vindas!</h1><p>Entre para acessar suas memórias.</p></div>
    <form @submit.prevent="login"><ion-list lines="none">
      <ion-item><ion-input v-model="email" label="E-mail" label-placement="stacked" type="email" autocomplete="email" placeholder="seu@email.com" required /></ion-item>
      <ion-item><ion-input v-model="password" label="Senha" label-placement="stacked" type="password" autocomplete="current-password" placeholder="••••••••" required /></ion-item>
    </ion-list><ion-text color="danger" v-if="error"><p class="error">{{ error }}</p></ion-text><ion-button type="submit" expand="block" class="primary-button">Entrar</ion-button></form>
    <p class="bottom-link">Ainda não possui uma conta? <router-link to="/cadastro">Cadastre-se</router-link></p>
  </main></ion-content></ion-page>
</template>
<script setup lang="ts">
import { ref } from 'vue'; import { useRouter } from 'vue-router';
import { IonPage, IonContent, IonIcon, IonList, IonItem, IonInput, IonText, IonButton } from '@ionic/vue'; import { imagesOutline } from 'ionicons/icons';
const router = useRouter(); const email = ref(''); const password = ref(''); const error = ref('');
const login = () => { const saved = localStorage.getItem('galeria-user'); if (!saved) { error.value = 'Nenhuma conta encontrada. Faça seu cadastro.'; return; } const user = JSON.parse(saved); if (user.email !== email.value.trim().toLowerCase() || user.password !== password.value) { error.value = 'E-mail ou senha inválidos.'; return; } localStorage.setItem('galeria-session', 'true'); router.replace('/home'); };
</script>

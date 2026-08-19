<template>
  <ion-page><ion-header class="ion-no-border"><ion-toolbar><ion-buttons slot="start"><ion-back-button default-href="/home" /></ion-buttons><ion-title>Sobre</ion-title></ion-toolbar></ion-header>
  <ion-content><main class="about-content"><div class="about-logo"><ion-icon :icon="imagesOutline" /></div><h1>Minha Galeria</h1><p class="version">Versão 1.0.0</p>
  <ion-list inset><ion-item><ion-icon slot="start" :icon="documentTextOutline" /><ion-label><h2>Termos de uso</h2><p>Use o aplicativo de forma responsável. As fotos ficam armazenadas localmente no dispositivo.</p></ion-label></ion-item><ion-item><ion-icon slot="start" :icon="shieldCheckmarkOutline" /><ion-label><h2>Termos de privacidade</h2><p>Não coletamos nem enviamos suas fotos ou dados pessoais para servidores externos.</p></ion-label></ion-item></ion-list></main></ion-content></ion-page>
</template>
<script setup lang="ts">import { IonPage, IonHeader, IonToolbar, IonButtons, IonBackButton, IonTitle, IonContent, IonIcon, IonList, IonItem, IonLabel } from '@ionic/vue'; import { imagesOutline, documentTextOutline, shieldCheckmarkOutline } from 'ionicons/icons';</script>

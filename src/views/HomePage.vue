<template>
  <ion-page>
    <ion-header class="ion-no-border"><ion-toolbar><ion-title>Minha Galeria</ion-title><ion-buttons slot="end"><ion-button aria-label="Sobre o aplicativo" @click="router.push('/sobre')"><ion-icon slot="icon-only" :icon="informationCircleOutline" /></ion-button><ion-button aria-label="Sair" @click="logout"><ion-icon slot="icon-only" :icon="logOutOutline" /></ion-button></ion-buttons></ion-toolbar></ion-header>
    <ion-content><section class="gallery-intro"><h1>Olá, {{ userName }}!</h1><p>Suas fotos ficam organizadas aqui.</p></section>
      <section v-if="photos.length" class="photo-grid"><article v-for="(photo, index) in photos" :key="photo" class="photo-card"><img :src="photo" :alt="`Foto ${index + 1}`" /><ion-button class="remove" color="danger" fill="solid" size="small" :aria-label="`Remover foto ${index + 1}`" @click="removePhoto(index)"><ion-icon slot="icon-only" :icon="trashOutline" /></ion-button></article></section>
      <section v-else class="empty-state"><ion-icon :icon="imagesOutline" /><h2>Nenhuma foto ainda</h2><p>Toque no botão + para abrir a câmera ou escolher imagens da galeria.</p></section>
      <ion-fab slot="fixed" vertical="bottom" horizontal="end"><ion-fab-button aria-label="Adicionar foto"><ion-icon :icon="add" /></ion-fab-button><ion-fab-list side="top"><ion-fab-button aria-label="Abrir câmera" @click="takePhoto"><ion-icon :icon="cameraOutline" /></ion-fab-button><ion-fab-button aria-label="Abrir galeria" @click="pickPhoto"><ion-icon :icon="imagesOutline" /></ion-fab-button></ion-fab-list></ion-fab>
    </ion-content>
  </ion-page>
</template>
<script setup lang="ts">
import { ref } from 'vue'; import { useRouter } from 'vue-router'; import { Camera, CameraResultType, CameraSource } from '@capacitor/camera';
import { IonPage, IonHeader, IonToolbar, IonTitle, IonButtons, IonButton, IonIcon, IonContent, IonFab, IonFabButton, IonFabList } from '@ionic/vue'; import { add, cameraOutline, imagesOutline, informationCircleOutline, logOutOutline, trashOutline } from 'ionicons/icons';
const router = useRouter(); const user = JSON.parse(localStorage.getItem('galeria-user') || '{"name":""}'); const userName = user.name || 'visitante'; const photos = ref<string[]>(JSON.parse(localStorage.getItem('galeria-photos') || '[]'));
const save = () => localStorage.setItem('galeria-photos', JSON.stringify(photos.value));
const addPhoto = async (source: CameraSource) => { try { const photo = await Camera.getPhoto({ quality: 80, allowEditing: false, resultType: CameraResultType.DataUrl, source }); if (photo.dataUrl) { photos.value.unshift(photo.dataUrl); save(); } } catch { /* usuário cancelou */ } };
const takePhoto = () => addPhoto(CameraSource.Camera); const pickPhoto = () => addPhoto(CameraSource.Photos); const removePhoto = (index: number) => { photos.value.splice(index, 1); save(); }; const logout = () => { localStorage.removeItem('galeria-session'); router.replace('/login'); };
</script>

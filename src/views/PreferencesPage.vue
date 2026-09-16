<template>
  <ion-page>
    <ion-header class="ion-no-border">
      <ion-toolbar>
        <ion-buttons slot="start"><ion-back-button default-href="/home" /></ion-buttons>
        <ion-title>Armazenamento local</ion-title>
      </ion-toolbar>
    </ion-header>

    <ion-content class="ion-padding">
      <main class="preferences-content">
        <h1>Preferences</h1>
        <p>Teste o armazenamento local do dispositivo.</p>

        <ion-list inset>
          <ion-item>
            <ion-label>
              <h2>Nome do usuário</h2>
              <p>{{ nomeSalvo === null ? 'Nenhum nome salvo' : nomeSalvo }}</p>
            </ion-label>
          </ion-item>
          <ion-item>
            <ion-label>
              <h2>Tarefas</h2>
              <p>{{ tarefasSalvas.length ? JSON.stringify(tarefasSalvas) : 'Nenhuma tarefa salva' }}</p>
            </ion-label>
          </ion-item>
        </ion-list>

        <div class="actions">
          <ion-button expand="block" @click="salvarNome">Salvar nome (Maria)</ion-button>
          <ion-button expand="block" fill="outline" @click="recuperarNome">Recuperar nome</ion-button>
          <ion-button expand="block" @click="salvarLista">Salvar tarefas</ion-button>
          <ion-button expand="block" fill="outline" @click="recuperarLista">Recuperar tarefas</ion-button>
          <ion-button expand="block" color="warning" @click="removerNome">Remover nome</ion-button>
          <ion-button expand="block" color="danger" @click="limparTudo">Limpar tudo</ion-button>
        </div>

        <ion-note v-if="mensagem" color="medium">{{ mensagem }}</ion-note>
      </main>
    </ion-content>
  </ion-page>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import { IonBackButton, IonButton, IonButtons, IonContent, IonHeader, IonItem, IonLabel, IonList, IonNote, IonPage, IonTitle, IonToolbar } from '@ionic/vue';
import { limparPreferencias, recuperarNomeUsuario, recuperarTarefas, removerNomeUsuario, salvarNomeUsuario, salvarTarefas, type Tarefa } from '@/services/preferences';

const nomeSalvo = ref<string | null>(null);
const tarefasSalvas = ref<Tarefa[]>([]);
const mensagem = ref('');
const tarefasExemplo: Tarefa[] = [{ id: 1, texto: 'Vue', feita: false }];

const salvarNome = async () => {
  await salvarNomeUsuario();
  nomeSalvo.value = 'Maria';
  mensagem.value = 'Nome salvo com a chave "usuario-nome".';
};

const recuperarNome = async () => {
  nomeSalvo.value = await recuperarNomeUsuario();
  mensagem.value = nomeSalvo.value ? 'Nome recuperado.' : 'A chave "usuario-nome" não possui valor.';
};

const salvarLista = async () => {
  await salvarTarefas(tarefasExemplo);
  tarefasSalvas.value = tarefasExemplo;
  mensagem.value = 'Lista salva como JSON com a chave "tarefas".';
};

const recuperarLista = async () => {
  tarefasSalvas.value = await recuperarTarefas();
  mensagem.value = tarefasSalvas.value.length ? 'Tarefas recuperadas e convertidas de JSON.' : 'A chave "tarefas" não possui valor.';
};

const removerNome = async () => {
  await removerNomeUsuario();
  nomeSalvo.value = null;
  mensagem.value = 'A chave "usuario-nome" foi removida.';
};

const limparTudo = async () => {
  await limparPreferencias();
  nomeSalvo.value = null;
  tarefasSalvas.value = [];
  mensagem.value = 'Todos os dados do Preferences foram removidos.';
};
</script>

<style scoped>
.preferences-content { max-width: 560px; margin: 0 auto; }
.actions { display: grid; gap: 10px; margin-top: 20px; }
ion-note { display: block; margin-top: 18px; text-align: center; }
</style>

import { Preferences } from '@capacitor/preferences';

export interface Tarefa {
  id: number;
  texto: string;
  feita: boolean;
}

const CHAVE_USUARIO = 'usuario-nome';
const CHAVE_TAREFAS = 'tarefas';

export async function salvarNomeUsuario(nome = 'Maria'): Promise<void> {
  await Preferences.set({ key: CHAVE_USUARIO, value: nome });
}

export async function recuperarNomeUsuario(): Promise<string | null> {
  const { value } = await Preferences.get({ key: CHAVE_USUARIO });
  return value;
}

export async function salvarTarefas(tarefas: Tarefa[]): Promise<void> {
  await Preferences.set({ key: CHAVE_TAREFAS, value: JSON.stringify(tarefas) });
}

export async function recuperarTarefas(): Promise<Tarefa[]> {
  const { value: raw } = await Preferences.get({ key: CHAVE_TAREFAS });
  if (!raw) return [];

  try {
    return JSON.parse(raw) as Tarefa[];
  } catch {
    return [];
  }
}

export async function removerNomeUsuario(): Promise<void> {
  await Preferences.remove({ key: CHAVE_USUARIO });
}

export async function limparPreferencias(): Promise<void> {
  await Preferences.clear();
}

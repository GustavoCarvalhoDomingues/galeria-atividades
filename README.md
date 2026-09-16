# Minha Galeria

Repositório público: https://github.com/GustavoCarvalhoDomingues/galeria-atividades

> A versão nativa entregue para Android Studio está na pasta `android-compose`.

Aplicativo mobile para cadastro e login de usuários, captura ou seleção de fotos e organização delas em uma galeria pessoal.

## Aluno

- **Nome:** Gustavo Domingues
- **Curso:** Informática
- **Unidade curricular:** Codificar aplicações para dispositivos móveis

## Sobre o projeto

O aplicativo foi desenvolvido com Ionic, Vue e Capacitor. Após criar uma conta e fazer login, o usuário tem acesso a uma galeria protegida. Pelo botão flutuante é possível tirar uma foto com a câmera ou selecionar uma imagem da galeria do dispositivo. As imagens adicionadas podem ser removidas individualmente.

As credenciais e as fotos são armazenadas localmente no dispositivo, sem envio para servidores externos. A tela **Sobre** apresenta a versão do aplicativo, os termos de uso e a política de privacidade.

## Como rodar

### Android Studio (versão nativa em Kotlin/Compose)

1. Abra a pasta `android-compose` no Android Studio.
2. Aguarde a sincronização do Gradle e execute em um emulador ou dispositivo Android (API 24+).
3. No primeiro uso, conceda as permissões de câmera, fotos e localização quando solicitadas.

Esta versão permite adicionar fotos pela câmera ou galeria, remover e compartilhar cada foto com outros aplicativos (WhatsApp, Telegram etc.). A área **Sobre** informa o status da internet, exibe latitude, longitude e altitude mediante permissão e permite ativar o tema escuro, que permanece salvo no dispositivo.

### Pré-requisitos

- Node.js 20 ou superior
- Android Studio configurado, para executar no Android

### Web

```bash
npm install
npm run dev
```

### Android

```bash
npm run build
npx cap sync android
npx cap open android
```

No Android Studio, selecione um emulador ou aparelho e execute o projeto. Na primeira utilização, o Android solicitará as permissões necessárias para câmera e acesso às fotos.

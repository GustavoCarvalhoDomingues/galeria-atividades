# Minha Galeria

Aplicativo mobile para cadastro e login de usuários, captura ou seleção de fotos e organização delas em uma galeria pessoal.

## Aluno

- **Nome:** Gustavo Domingues
- **Curso:** Informática
- **Unidade curricular:** Codificar aplicações para dispositivos móveis

## Sobre o projeto

O aplicativo foi desenvolvido com Ionic, Vue e Capacitor. Após criar uma conta e fazer login, o usuário tem acesso a uma galeria protegida. Pelo botão flutuante é possível tirar uma foto com a câmera ou selecionar uma imagem da galeria do dispositivo. As imagens adicionadas podem ser removidas individualmente.

As credenciais e as fotos são armazenadas localmente no dispositivo, sem envio para servidores externos. A tela **Sobre** apresenta a versão do aplicativo, os termos de uso e a política de privacidade.

## Como rodar

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

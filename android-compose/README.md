# AppGaleria

Repositório público: https://github.com/GustavoCarvalhoDomingues/galeria-atividades

## Entrega

- **Nome do aluno:** Gustavo Domingues
- **Curso:** Informática
- **Unidade curricular:** Codificar aplicações para dispositivos móveis

O AppGaleria é um aplicativo Android em Kotlin e Jetpack Compose. Usuários podem criar conta, fazer login e acessar uma galeria protegida. As fotos podem vir da câmera ou da galeria, ser removidas e compartilhadas com outros aplicativos. A tela Sobre apresenta o estado da internet, latitude, longitude e altitude (mediante permissão), além de um tema escuro persistido no dispositivo.

## Executar no Android Studio

1. Abra esta pasta (`android-compose`) no Android Studio.
2. Aguarde a sincronização do Gradle.
3. Selecione um emulador ou aparelho com Android API 24 ou superior e clique em **Run**.
4. Conceda as permissões de câmera, imagens e localização quando o app solicitar.

## Identificação
- **Nome do aluno**: 
- **Curso**: 
- **Unidade Curricular**: 

## Explicação do Projeto
O AppGaleria é um aplicativo Android para organizar fotos no dispositivo. Ele oferece telas de login e cadastro, uma tela inicial protegida com grade de imagens, remoção de fotos, captura pela câmera, seleção na galeria e uma tela Sobre. As URIs das imagens são persistidas com Room e a sessão de login é armazenada com DataStore.
 
## Como Rodar
1. Clone o repositório.
2. Abra a pasta `android-compose` no Android Studio (versão Giraffe ou superior).
3. Aguarde o build do Gradle baixar as dependências.
4. Conecte um dispositivo físico ou inicie um emulador (API 24+).
5. Clique em "Run" (▶️).

## Critérios de Avaliação Atendidos
- Código organizado em camadas (MVVM).
- README claro e objetivo.
- Projeto roda perfeitamente no Android Studio (Emulador/Dispositivo).
- Solicita permissões de forma dinâmica.
- Gerencia o ciclo de vida das imagens com Room.

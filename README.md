# ![ic_pokedex_launcher (1)](https://github.com/user-attachments/assets/26767c2c-ad0f-4627-9b9f-b41fa3b8d438) Projeto Pokédex - Android

Listagem de pokémons da 1ª geração com a opção de favoritos (API [PokéApi](https://pokeapi.co/)).

## Descrição
- Aplicativo Android nativo
- 100% Kotlin
- MVI
- Arquitetura Limpa
- Modularização por feature/layer (seguindo princípios do [Guia para a arquitetura do app](https://developer.android.com/topic/architecture?hl=pt-br]), documentação oficial do Android)
- Offline-first (cache ativado para informações de pokémons e o carregamento de suas respectivas imagens)
- UI: Jetpack Compose
- Consumo de API: Ktor
- Banco de dados local: ROOM e DataStore
- Injeção de dependências: Koin
- Assincronismo: Flow e Coroutines

## Fluxograma de Desenvolvimento
![Arquitetura](https://github.com/user-attachments/assets/9b995abe-4ea7-4d5c-964d-ca942f28789d)
![Modularização](https://github.com/user-attachments/assets/541f87d9-bd6e-465f-a4f8-7f80cda354c2)

## Screenshots e Demos

### Splash
<p align="center">
  <img src="https://github.com/user-attachments/assets/1063dba6-49a6-4d98-8839-be6399f03663" width="240" height="480">
</p>

### Loading
<p align="center">
  <img src="https://github.com/user-attachments/assets/36954386-3129-441b-8a25-a5f592cfff76" width="240" height="480">
</p>

### Falha ao Sincronizar Pokémons
<p align="center">
  <img src="https://github.com/user-attachments/assets/0e36187f-5ac6-4211-90c3-a78b7a4c68f6" width="240" height="480">
</p>

### Listagem de Pokémons
<p align="center">
  <img src="https://github.com/user-attachments/assets/d3928b69-5483-495d-a68b-a9c6c3899c29" width="240" height="480">
</p>

### Detalhes de Pokémon
<p align="center">
  <img src="https://github.com/user-attachments/assets/3ab5a4ed-dbcd-420b-87c0-e85f82f5a3e4" width="240" height="480">
</p>

### Favoritos
<p align="center">
  <img src="https://github.com/user-attachments/assets/eef3719a-9968-4212-8b0d-38aa07ee0379" width="240" height="480">
</p>

### Demonstração

https://github.com/user-attachments/assets/c986bfe9-8785-45f4-94e4-0ef9a35e49b5

## Visão de Futuro

### Armazenamento de API keys de forma segura
- Caso necessário consumir outras APIs, uma boa escolha seria armazenar suas chaves de acesso de forma encriptada em um arquivo C++, utilizando o JNI e NDK no projeto, evitando brechas de segurança ao aplicar engenharia reversa no APK (como visto [aqui](https://github.com/BernardoSlailati/EncryptSensibleData)).

### Gerar versão de build release aplicando ofuscação e minificação
- Aplicar minificação e ofuscação de código ao ser gerada a versão de build release, sendo necess[ario criar o arquivo que dite as regras de ofuscação para o ProGuard.

### Módulo analytics apartado para acompanhamento de métricas
- Desenvolver módulo separado de analytics para tracking de eventos do usuário, logs gerais, etc. e que tenha uma interface agnóstica a ferramenta de análise escolhida (Firebase, Embrace, DataDog, UXCam, etc.)

### Segurança de dados sensíveis do usuário
- Ao implementar um fluxo de login e armazenamento local de dados do usuário, anonimizar dados pessoais sensíveis aplicando criptografia.

### Testabilidade
- Utilizar inicialmente JUnit e Mockk para a realização dos testes unitários mais básicos (envolvendo os DataSources locais e remotos - consumo de APIs e bancos de dados - e em seguida as estruturas de dados de Domain), criando mocks e fakes para simular o comportamento de tais classes
- Feito isso, poderiam ser feitos testes de snapshot mais simples com os Composables mais importantes de cada fluxo, devido a facilidade
- Na sequências, seguir com os testes unitários de Repository e ViewModels
- E para concluir, os testes de integração, que exijam um maior nível de complexidade para abranger determinados fluxos (ex: sincronização completa de pokémons)

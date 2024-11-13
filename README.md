<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>

Hi there! I hope you like this project!</br>



## Project assesment

| Feature/Project      | Android Native Project 
|----------------------|------------------------|
| Jetpack Compose      | ✓                      |
| Dependency Injection | ✓ Hilt                 |
| MVVM + Clean Arch    | ✓                      |
| Navigation Component | ✓                      |
| Coroutines + Flow    | ✓                      |
| RESTful API          | ✓ Retrofit2            |
| Unit Tests           | ✓                      |
| UI Tests             | ✓                      |
| Mockk                | ✓                      |
| Translations         | ✓ EN / PT              |
| Transition Animation | ✘                      |
| Infinite Scroll      | ✘                      |


## Project Overview

### Technologies & Libraries
1. **Jetpack Compose:** Used for building the UI declaratively. This enables efficient, responsive UI design with Kotlin-based components, making the codebase concise and easier to maintain.
2. **Dependency Injection with Hilt:** Hilt was chosen for dependency injection to manage dependencies efficiently across the application, improving modularity, testability, and ease of configuration.
3. **MVVM + Clean Architecture:** The project is organized according to MVVM architecture with Clean Architecture principles. This separation into layers (UI, Domain, Data) improves testability, scalability, and the reusability of components.
4. **Navigation Component:** Used for handling navigation across screens. It allows us to manage screens transitions easily, following the Single Activity pattern.
5. **Coroutines + Flow:** Asynchronous programming is handled using Coroutines and Flow. Coroutines provide a lightweight way to manage background tasks, while Flow allows for reactive stream-based data handling, improving responsiveness.
6. **Retrofit2:** Retrofit is used to perform network requests to the RESTful API. It’s efficient and integrates well with Gson for data serialization.
7. **Unit & UI Testing:** Unit tests ensure that core functionalities work as expected, while UI tests verify the proper rendering and behavior of UI elements. These tests follow the MVVM pattern, providing high test coverage.
8. **Mockk:** For mocking in unit tests, Mockk is used to simulate dependencies, allowing us to test individual units in isolation.
9. **Translations:** The app supports both English and Portuguese translations to cater to different locales.

## Limitations & Future Enhancements
1. **Transition Animation:** Currently, there are no transition animations implemented. In the future, adding animations could improve user experience.
2. **Infinite Scroll:** Paging or infinite scrolling is not supported, which may be a consideration for future feature enhancements if data volume increases.


## Instruções para Executar o Projeto

1. Preparação do Ambiente

- Certifique-se de ter o Android Studio (Arctic Fox ou versão mais recente) instalado.
- Instale o SDK Android necessário e as ferramentas de compilação compatíveis com a versão do projeto.

2. Importar o Projeto no Android Studio

- Baixe e extraia o arquivo .zip do projeto.
- Abra o Android Studio.
- Selecione a opção "Open an Existing Project" e navegue até a pasta onde o projeto foi extraído.
- Aguarde o Android Studio sincronizar o projeto e baixar as dependências necessárias.

3. Configurar o Dispositivo de Teste

- Use um dispositivo Android físico ou emulador com Android API nível 26 ou superior.
- Verifique se o dispositivo ou emulador está configurado e funcionando corretamente.

4. Executar o App

- No Android Studio, selecione a configuração de execução 'app' no menu de configurações de execução.
- Clique no botão Run (ou pressione Shift + F10).
- O Android Studio instalará o aplicativo no dispositivo/emulador e iniciará a execução.


## Instruções para Executar os Testes Automatizados

1. Testes Unitários

- No Android Studio, abra o painel Android e navegue até app/kotlin+java.
- Clique com o botão direito no pacote de teste marcado como (test) e selecione Run 'Tests in...' para executar os testes.
- Os resultados dos testes serão exibidos no painel Run.

2. Testes de Interface (UI)

- No Android Studio, abra o painel Android e navegue até app/kotlin+java.
- Clique com o botão direito no pacote de teste marcado como (androidTest) e selecione Run 'Tests in...'.
- Certifique-se de que o dispositivo de teste está conectado e ativo, pois esses testes precisam de um dispositivo físico ou emulador.
- Aguarde os testes serem executados e revise os resultados no painel Run.

</body>
</html>

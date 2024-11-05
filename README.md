
# Processador de Arquivos ZIP - MDIAS

Este projeto é uma aplicação em Java Swing para processamento de arquivos ZIP de rateios de telefonia, desenvolvida para a MDIAS. A aplicação permite que o usuário selecione um arquivo ZIP, processe seu conteúdo (gerando arquivos de saída), e então salve o arquivo processado no local desejado.

## Funcionalidades

1. **Interface Gráfica Simples**: Interface em Java Swing com um botão centralizado para seleção do arquivo ZIP.
2. **Processamento de Arquivos ZIP**: Extração e processamento dos arquivos `.txt` contidos no ZIP, gerando um arquivo de saída.
3. **Salvamento do Arquivo Processado**: Opção para salvar o arquivo de saída em um local personalizado.
4. **Feedback Visual**: O aplicativo exibe um diálogo de "Processando" enquanto realiza o processamento dos arquivos.

## Tecnologias Utilizadas

- Java Swing para interface gráfica
- Java NIO (New I/O) para manipulação de arquivos
- Maven para gerenciamento de dependências
- `SwingWorker` para execução em segundo plano durante o processamento

## Capturas de Tela

### Tela Inicial
A tela inicial da aplicação possui um botão para selecionar o arquivo ZIP que será processado.

![Tela Inicial](https://media.discordapp.net/attachments/1303405981857743010/1303406549439484035/image.png?ex=672ba371&is=672a51f1&hm=100a2dcbeccfab2e03853e74e7b7fdc9ab9d428f173347220965a13621cd98c2&=&format=webp&quality=lossless)

### Processando
Após selecionar o arquivo ZIP, o aplicativo exibe uma tela de "Processando", indicando que o processamento está em andamento.

![Processando](https://media.discordapp.net/attachments/1303405981857743010/1303406218890575902/image.png?ex=672ba322&is=672a51a2&hm=ae4e68785028ac20560948ae56344a312dbb3c88da41b2661fd05cc3248ba1fe&=&format=webp&quality=lossless)

### Arquivo Salvo
Depois que o processamento é concluído, o usuário pode escolher onde salvar o arquivo de saída gerado.

![Arquivo Salvo](https://media.discordapp.net/attachments/1303405981857743010/1303406224091648110/image.png?ex=672ba323&is=672a51a3&hm=63f4784180ecbc91772c10b1b3d1365cdf6e6bc552c6587852f93858b261a704&=&format=webp&quality=lossless)

## Como Usar

1. **Selecione o Arquivo ZIP**: Clique no botão central e selecione o arquivo ZIP que deseja processar.
2. **Aguarde o Processamento**: Um diálogo de "Processando" será exibido enquanto o aplicativo realiza o processamento dos arquivos.
3. **Salve o Arquivo Processado**: Após o processamento, escolha o local onde deseja salvar o arquivo de saída.

## Como Criar e Executar o .jar

1. **Pré-requisitos**:
   - Java 17
   - Maven para gerenciar as dependências

2. **Clonar o Repositório**:
   ```bash
   git clone <URL-do-repositorio>
   cd <nome-da-pasta>
   ```

3. **Construir o Projeto**:
   - Navegue até o diretório do projeto e execute o comando Maven para empacotar o `.jar`:
     ```bash
     mvn clean package -DskipTests
     ```

4. **Executar o Arquivo JAR**:
   - Após construir o projeto, execute o arquivo `.jar`:
     ```bash
     java -jar target/processador-mdias.jar
     ```


### Executando o `ProcessadorApplication` na IDE

1. **Abrir o Projeto na IDE**:
   - Importe o projeto para sua IDE preferida, como IntelliJ IDEA ou Eclipse. Certifique-se de que o projeto seja reconhecido como um projeto Maven.

2. **Configurar a Classe Principal**:
   - No menu de execução da IDE, escolha a classe principal `ProcessadorApplication` (esta deve estar localizada no pacote `com.mdiasbranco.processador`).
   - Verifique se a configuração de execução está usando o JDK 17 ou superior.

3. **Executar o Projeto**:
   - Na IntelliJ IDEA: clique com o botão direito na classe `ProcessadorApplication` e selecione **Run 'ProcessadorApplication'**.
   - No Eclipse: clique com o botão direito na classe `ProcessadorApplication`, vá até **Run As** e selecione **Java Application**.

4. **Pronto!**:
   - A aplicação deve iniciar e abrir a interface gráfica (JFrame) diretamente pela IDE, permitindo que você use a aplicação sem precisar compilar o `.jar`.

## Estrutura do Projeto

- `ProcessadorApplication`: Classe principal que exibe a interface gráfica.
- `ProcessadorService`: Realiza o processamento do arquivo ZIP.
- `ArquivoProcessor`: Classe que processa os arquivos `.txt` contidos no ZIP e gera o arquivo de saída.

## Autor

Feito por Luis Kaio.

# 📌 Sobre o Projeto

Este projeto foi desenvolvido durante o Hackathon 1000Devs pelo Grupo 5 e tem como objetivo facilitar o gerenciamento do calendário vacinal de uma família. O sistema permite registrar quais vacinas foram aplicadas a cada integrante da família e visualizar o calendário vacinal recomendado por idade.

## 🎯 Objetivo

Criar um software capaz de:

Exibir um calendário vacinal baseado na idade recomendada para cada vacina.

Cadastrar vacinas aplicadas para cada membro da família.

Oferecer uma API RESTful para acesso às funcionalidades.

Persistir os dados em um banco de dados MySQL.

## 🚀 Tecnologias Utilizadas

Back-end: Java com Spark Web (REST API)

Banco de Dados: MySQL (executado via Docker)

Front-end: HTML, CSS e JavaScript

Cliente API: Thunder Client para testes das APIs

Docker: Para a execução do banco MySQL

## 👥 Time de Desenvolvimento

### 💻 Back-end

Matheus Leal (Tech Lead)

Andrielly Araújo

Caio Souza

### 🎨 Front-end

José Victor Venetillo (Tech Lead)

Mirelly Santos

Jéssica Diniz

## 🏆 Conclusão

Este projeto foi um grande desafio e aprendizado para todos os envolvidos. Combinamos boas práticas de programação, arquitetura RESTful, persistência de dados e uma interface simples para visualização do calendário vacinal.

✨ Agradecemos à 1000Devs pela oportunidade de participar deste hackathon incrível! 🚀




# Documentação de Git

## Principais Comandos do Git

### Criando e Inicializando um Repositório.

- Clonar um repositório existente:
  ```sh
  git clone URL_DO_REPOSITORIO
  ```
- Iniciar um repositório local:
  ```sh
  git init
  ```

### -IMPORTANTE- Subindo as alterações (-nesta sequência-)

- Adicionar todos os arquivos modificados ao staging:

  ```sh
  git add .
  ```

- Criar um commit:

  ```sh
  git commit -m "feat: Mensagem descritiva"
  ```

  - Enviar alterações para o repositório remoto:

  ```sh
  git push origin nome-da-branch
  ```

### Trabalhando com Branches

- Criar uma nova branch:

  ```sh
  git branch nome-da-branch - Criar a branch de acordo com o nome da tarefa. Ex "git branch Mudar_Cor_Button"
  ```

- Mudar para outra branch:

  ```sh
  git checkout nome-da-branch
  ```

- Criar e mudar para uma nova branch:

  ```sh
  git checkout -b nome-da-branch
  ```

- Mesclar uma branch à branch atual:
  ```sh
  git merge nome-da-branch
  ```

### Trabalhando com Repositórios Remotos

- Adicionar um repositório remoto:

  ```sh
  git remote add origin URL_DO_REPOSITORIO
  ```

- Enviar alterações para o repositório remoto:

  ```sh
  git push origin nome-da-branch
  ```

- Atualizar o repositório local com as mudanças do remoto:
  ```sh
  git pull origin nome-da-branch
  ```

## Como tratar o fluxo de desenvolvimento ?

1. Mova o card que seja desenvolver de _Read_ para _In Progress_ e atribua o seu nome no campo _Assignees_ clicando no nome do card.
   ![](https://i.imgur.com/jj2oK9o.png)

![enter image description here](https://i.imgur.com/pI8DNCh.png)

2. No card, clique em **Convert to issue** e selecione o repositório do projeto, isso gerará uma mudança que será visível e gerenciada pelo líder do projeto, essencial para garantir o fluxo saudável do desenvolvimento.

3. Crie uma branch nome com o nome do card, por exemplo:
   Na aba de branchs, clique em **View all branchs**

   Clicando em **New branch**, você pode estar criando uma nova branch para o projeto.

Exemplo: se o card se chama "Adição da biblioteca de console", a branch se chamará "Adicao_da_biblioteca_de_console", e aponta-la para a branch **development** e clique em **Create new bran ch**, ou seja, a nova branch será criada espelhada na branch de desenvolvimento. Isto é necessário para que você trabalhe as suas modificações dentro da aplicação sem intervir nas outras modificações que estão sendo criadas.

4. No seu projeto, dê o **git pull** para atualizar a sua branch local e assim trazer a criação da sua branch remota, mude para ela e comece o desenvolvimento.

5. No término do desenvolvimento, faça commits usando Convensão de Commits

6. Quando o código estiver validado, testado localmente, crie um **Pull Request** a partir da sua branch de trabalho:

   o coordenador irá avaliar e testar junto com você a sua implementação.

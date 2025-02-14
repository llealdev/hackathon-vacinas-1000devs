# Documentação de Git

## Principais Comandos do Git

### Criando e Inicializando um Repositório

- Clonar um repositório existente:
  ```sh
  git clone URL_DO_REPOSITORIO
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

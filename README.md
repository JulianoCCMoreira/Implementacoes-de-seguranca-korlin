<p align="center">
  <img src="https://user-images.githubusercontent.com/66191563/171285697-d7fdf3f1-4d87-4566-b73b-452a5cd17041.png" />
 </p>
Este é um projeto que foi apresentado na segunda edição da Tech Week da Contabilizei! Onde apresentei sobre a importância da segurança em API's, mostrando conceitos básicos como implementação de Tokens JWT, CORS, criptografia e variáveis de ambiente.

## Sobre o projeto
O projeto foi desenvolvido com o framework Spring Boot e com a linguagem de programação Kotlin. Possui depedências para fazer a conexão com uma instância de banco de dados MySQL. 

### Como baixar o projeto
Para clonar o projeto basta executar o seguinte comando no seu terminal:
```sh
git clone https://github.com/JulianoCCMoreira/Spring-Security-Kotlin.git
```
Depois baixar as dependências com:
```
mvn clean package
```

### Como configurar o projeto
Basta ter uma instância do MySQL rodando em sua máquina e exportar as váriáveis de ambiente. Em minha máquina eu tenho uma base de dados MySQL com as seguintes configurações:
```
JWT_SECRET=2_TECHWEEK_CONTABILIZEI
DATABASE_URL=jdbc:mysql://localhost:3306/seguranca
DATABASE_USERNAME=root
DATABASE_PASSWORD=12345678
```
> Lembrando que o nome deve ser idêntico, o que muda é apenas o valor da váriavel.

## Apresentação
Segue os links relacionados a apresentação.

### Slides
Os slides utilizados durante a apresentação na Tech Week: [clique aqui para acessar.](https://www.canva.com/design/DAFBeMYtC18/ZDibTuZeFvx6IoGo0j08OA/view?utm_content=DAFBeMYtC18&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton)


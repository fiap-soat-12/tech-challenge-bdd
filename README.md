<div align="center">

# Tech Challenge - BDD

![GitHub Release Date](https://img.shields.io/badge/Release%20Date-Fevereiro%202025-yellowgreen)
![](https://img.shields.io/badge/Status-Em%20Desenvolvimento-yellowgreen)
<br>
![](https://img.shields.io/badge/Version-%20v2.0.0-brightgreen)
</div>

## 💻 Descrição

Este repositório é responsável por realizar o teste e2e entre toda a jornada de realização do pedido, interagindo com os microserviços Order, Payment e Cook.

## 🛠 Tecnologias Utilizadas

<div align="center">

![Java](https://img.shields.io/badge/java_21-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Cucumber](https://img.shields.io/badge/Cucumber-43B02A?style=for-the-badge&logo=cucumber&logoColor=white)
![JUnit5](https://img.shields.io/badge/JUnit5-25A162.svg?style=for-the-badge&logo=JUnit5&logoColor=white)
![Maven](https://img.shields.io/badge/Apache%20Maven-C71A36.svg?style=for-the-badge&logo=Apache-Maven&logoColor=white)
![JSON Org](https://img.shields.io/badge/JSON%20Org-000000.svg?style=for-the-badge&logo=JSON&logoColor=white)

</div>

## 📝 Arquitetura

### 📚 Gherkin

Gherkin segue uma arquitetura baseada em cenários de teste escritos em linguagem natural, como o estilo "Given-When-Then". 
Esses cenários são organizados em arquivos chamados de feature files, que descrevem o comportamento esperado de um sistema ou funcionalidade.
Os cenários compartilham um contexto com o mesmo OrderID que é retornado na criação do pedido e é utilizado como insumo para as demais requisições nos passos seguintes.

### 🧪 Cenários Mapeados
- Criação do pedido e evoluir todos os status até o status FINISHED
- Criação do pedido mas o pagamento é recusado, logo, deve ser finalizado com status FINISHED sem repassar ao microserviço Cook

## ⚙️ Configuração

### Pré-requisitos

1. É necessário executar a pipeline para criar o VPC no repositório: https://github.com/fiap-soat-12/tech-challenge-vpc
2. É necessário executar a pipeline para criar o SQS no repositório: https://github.com/fiap-soat-12/tech-challenge-queue
3. É necessário executar a pipeline para criar a imagem no ECR no repositório e a Infraestrutura da Cook API: https://github.com/fiap-soat-12/tech-challenge-cook-api
4. É necessário executar a pipeline para criar a imagem no ECR no repositório e a Infraestrutura da Order API: https://github.com/fiap-soat-12/tech-challenge-order-api
5. É necessário executar a pipeline para criar a imagem no ECR no repositório e a Infraestrutura da Payment API: https://github.com/fiap-soat-12/tech-challenge-payment-api
6. É necessário executar a pipeline para criar o k8s no repositório: https://github.com/fiap-soat-12/tech-challenge-k8s

### Desenvolvimento

- **[Cucumber](https://cucumber.io/)**: Site oficial do Cucumber.
- **[Junit 5](https://junit.org/junit5/)**: Documentação oficial do Junit 5.
- **[Apache Commons Math3](https://commons.apache.org/proper/commons-math/)**: Documentação oficial da Apache Commons Math3.
- **[JSON Org](https://www.json.org/json-en.html)**: Documentação oficial da JSON.org.
- **[Lombok](https://projectlombok.org/)**: Site oficial do Project Lombok.
- **[Owner](https://javadoc.io/static/org.aeonbits.owner/owner/1.0.5/index-all.html)**: Documentação da Aeonbits Owner.
- **[Rest Assured](https://rest-assured.io/)**: Documentação oficial da Rest Assured.
- **[Slf4j](https://www.slf4j.org/)**: Documentação oficial da Slf4j.

## 🚀 Execução

### Executando o BDD

  Caso deseje executar os testes BDD:

  1. Certificar que todos os microserviços estão deployados e sua saúde estável
  2. A aplicação espera 3 parâmetros 'env.baseUrl.order', 'env.baseUrl.cook', 'env.baseUrl.payment', caso a aplicação esteja sendo exposta com um gateway, é necessário passar a URL do gateway nestes três parâmetros, caso esteja sendo executada local, é necessário passar a Base URL Microserviço.
  3. Executar o comando 'mvn test'


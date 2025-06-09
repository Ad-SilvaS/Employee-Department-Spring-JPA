
# Employees - Departments

API RESTful desenvolvida em Java com Spring Boot para gerenciamento de funcionários e departamentos


## Visão Geral
O sistema permite:

- Cadastrar, listar, atualizar e excluir departamentos

- Cadastrar, listar, atualizar e excluir funcionários

- Associar funcionários a departamentos

- Realizar buscas por nome e e-mail de funcionários

- Realizar buscas por nome de departamentos

## Tecnologias

- Java 17+

- Spring Boot 3+

- Spring Data JPA

- Hibernate Validator

- Maven

- Banco de dados H2 (embutido para desenvolvimento)
## Endpoints

Departamentos (/departments)
```
Método	    Endpoint	                            Descrição
POST	  /departments	                      Cria um novo departamento
GET	      /departments	                      Lista todos os departamentos
GET	      /departments/{id}	                  Busca departamento por ID
GET	      /departments/name?name=nome	      Busca departamentos por nome (contendo)
PUT	      /departments/{id}/update-name	      Atualiza o nome de um departamento
DELETE	  /departments/{id}	            Remove um departamento (se não tiver funcionários)
```

Funcionários (/employees)
```
Método	    Endpoint	                             Descrição
POST	  /employees	                     Cria um novo funcionário
GET	      /employees	                     Lista todos os funcionários
GET	      /employees/{id}	                 Busca funcionário por ID
GET	      /employees/name?name=nome	         Busca funcionários por nome (contendo)
GET	      /employees/email?email=email	     Busca funcionários por e-mail (contendo)
PUT	      /employees/{id}/update-name	     Atualiza o nome de um funcionário
PUT	      /employees/{id}/update-email	     Atualiza o e-mail de um funcionário
PUT	      /employees/{id}/update-phone	     Atualiza o telefone de um funcionário
PUT	      /employees/{id}/alter-department	 Altera o departamento de um funcionário
DELETE	  /employees/{id}	                 Remove um funcionário
```
## Configuração e Execução

Pré-requisitos

- Java 17+

- Maven

- Banco de dados configurado (application.properties)

Como Executar:

- Clone o repositório:

- Execute o projeto com:
```
mvn spring-boot:run
```
## Tratamento de Erros

O sistema possui tratamento de erros personalizado para:
- Recursos não encontrados (404)
- Violações de integridade de dados (400)
- Exceções de banco de dados (400)
## Estrutura do Projeto

```
com.project
├── controllers
│   ├── DepartmentController.java
│   ├── EmployeeController.java
│   └── exception
│       ├── GlobalExceptionHandler.java
│       └── StandardError.java
├── DTO
│   ├── DepartmentDTO.java
│   ├── EmployeeDTO.java
│   └── EmployeeInputDTO.java
├── entities
│   ├── Department.java
│   └── Employee.java
├── repositories
│   ├── DepartmentRepository.java
│   └── EmployeeRepository.java
└── services
    ├── DepartmentService.java
    ├── EmployeeService.java
    └── exception
        ├── DatabaseException.java
        └── NotFoundException.java
```

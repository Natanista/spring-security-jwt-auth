
# Spring Secutiy JWT Pratice + tests

A simple RESTAPI to pratice a JWT authentication using Spring Security.




## API Reference

#### Signup 

```http
  POST /api/v1/signup
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `email` | `string` | user email |
| `senha` | `string` | user password |
| `confirmacaoSenha` | `string` | user password confirmation |
| `setor` | `string` | user business area |
| `cpf` | `string` | user cpf(Brazilian Id) |
| `dataNascimento` | `string` | user born date, ex: "1990-06-12" |
| `telefone` | `string` | user phone |


#### Login - *will return jwt token if user exists

```http
  POST /api/v1/bookings
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `email`      | `string` |user email |
| `password`      | `string` |user password |

#### Create a attendance

```http
  POST /api/v1/attendance
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `dataHora`      | `string` |user email |
| `Cliente`      | `string` |client object **find below |


#### GET all attendances

```http
  GET /api/v1/attendance
```

#### CLIENTE OBJECT

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `nome`      | `string` |client name|
| `cpf`      | `string` |client cpf(Brazilian ID) |









# Desafio Java

### Caracteristicas:

- [Banco de dados] - H2:mem
- [Url no Heroku] - https://appdesafio.herokuapp.com/

### Endpoints:

/users
Efetua o cadastro de usuarios, efetuando as validações necessárias
recebe campos e lista de objetos no seguinte formato:

{        
    "name": "João da Silva",
    "email": "joao@silva.org",
    "password": "hunter2",
    "phones": [ 
        {
            "number": "987654321",
            "ddd": "21"
        }
     ]     
} 

/login
Efetuar o login o usuario, recebe um objeto com email e senha e efetua as devidas validações.

{         
    "email": "joao@silva.org",
    "password": "hunter2",   
} 

/users/1
Acessa o perfil do usuario via token.
Token repassado no Header do documento.







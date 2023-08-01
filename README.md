# DSMovie
Desafio implementação de teste unitários com JUnit e Mockito e cobertura de código com Jacoco do curso Java Spring Ultimate.

## ESPECIFICAÇÃO - Sistema DSMovie:
Este é um projeto de filmes e avaliações de filmes. A visualização dos dados dos filmes é pública (não necessita login), porém as alterações de filmes (inserir, atualizar, deletar) são permitidas apenas para usuários ADMIN.
As avaliações de filmes podem ser registradas por qualquer usuário logado CLIENT ou ADMIN. A entidade Score armazena uma nota de 0 a 5 (score) que cada usuário deu a cada filme.
Sempre que um usuário registra uma nota, o sistema calcula a média das notas de todos usuários, e armazena essa nota média (score) na entidade Movie, juntamente com a contagem de votos (count). 


### Tecnologias utilizadas
-Java

-Spring Boot

-JPA / Hibernate

-Maven

-OAuth2

-JWT

-JUnit

-Mockito

Prompt para o Google Gemini

Contexto:
Tenho uma aplicação Java usando Maven e Spring Boot que precisa ter o Swagger/OpenAPI configurado para documentação da API REST.
Requisitos:

Adicionar dependências Maven necessárias para Springdoc OpenAPI no pom.xml
Criar classe de configuração do OpenAPI com:

Informações básicas da API (título, versão, descrição)
Configuração de segurança JWT (se aplicável, pois tenho JwtRequestFilter na aplicação)


Configurar Spring Security para permitir acesso público aos endpoints do Swagger:

/swagger-ui/**
/v3/api-docs/**
/swagger-ui.html


Adicionar configurações no application.properties ou application.yml se necessário
Fornecer exemplos de como documentar controllers usando anotações do OpenAPI:

@Tag
@Operation
@ApiResponse
@Parameter
@Schema


Listar as URLs para acessar:

Interface Swagger UI
JSON da documentação OpenAPI



Observações importantes:

Minha aplicação usa Spring Security com autenticação JWT
Preciso que a configuração seja compatível com Spring Boot 3.x (ou especifique se for Spring Boot 2.x)
Quero evitar erros 404 (recurso não encontrado) e erros 500 (falha na geração da documentação)
Preciso de exemplos práticos e completos, não apenas trechos de código

Por favor, forneça:

Código completo e funcional
Explicação de cada etapa
Boas práticas para documentar a API
Como testar se a instalação funcionou corretamente
Tentar novamente


#  Task Flow — Sistema Completo de Gestão de Tarefas

O **Task Flow** é uma plataforma completa para **gestão de tarefas, projetos e colaboração em equipe**, composta por **Front-end**, **Back-end**, **banco de dados em Docker (MySQL)** e **testes automatizados**.

---

##  Componentes do Sistema

###  Front-end  
- Interface web moderna, conectada diretamente à API.  
- Focada em produtividade, visualização em kanban e dashboards claros.

###  Back-end 
API REST robusta com:
- Autenticação e autorização via **JWT**
- Gestão completa de tarefas e projetos
- Comentários, notificações e logs de alterações
- Relatórios e dashboards
- Documentação via **Swagger**
- Arquitetura limpa (MVC, validação, JPA)

###  Docker & Banco de Dados (MySQL)
O projeto inclui configuração containerizada com:
- **MySQL rodando via Docker**

###  Testes Automatizados
- Testes unitários e de integração  
- Uso de mocks

###  Autorização & Perfis
Sistema com papéis bem definidos:
- **Administrador**
- **Gerente**
- **Colaborador**

Permissões aplicadas em toda a API.

###  Swagger
Documentação automática disponível via **Swagger UI**, permitindo:
- Testar endpoints no navegador  
- Visualizar contratos e modelos  
- Acelerar desenvolvimento front-end  

---

##  Funcionalidades Principais

- Registro, login e recuperação de senha  
- CRUD de tarefas e projetos  
- Visualização em lista e kanban  
- Busca e filtros avançados  
- Atribuição de responsáveis  
- Comentários e notificações  
- Histórico de alterações  
- Dashboard com métricas  
- Gerenciamento de usuários e permissões  

---

##  Stack Tecnológica

- **Back-end:** Java + Spring Boot  
- **Front-end:** *Definir tecnologia (React, Angular, etc.)*  
- **Banco:** **MySQL (via Docker)**  
- **Autenticação:** JWT  
- **ORM:** Spring Data JPA / Hibernate  
- **Build:** Maven  
- **Documentação:** Swagger  
- **Testes:** JUnit + Mockito  

---

##  Futuras Evoluções

- Relatórios avançados  
- Integrações externas (agenda, comunicação, etc.)  
- Workflows customizáveis  
- Permissões mais detalhadas  

---
















# Contribuidores:
[![Contributors](https://contrib.rocks/image?repo=arthurviniciusl/task-flow-back)](https://github.com/arthurviniciusl/task-flow-back/graphs/contributors)

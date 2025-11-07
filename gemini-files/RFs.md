
# Funcionalidades Principais (MVP – Versão Inicial)

Este documento descreve as principais funcionalidades do sistema, servindo como **guia de referência para integração e configuração**.  
Cada funcionalidade está detalhada com seus objetivos e escopo básico.

---

## 1. Autenticação e Perfis

**Descrição:**  
Gerencia o acesso de usuários ao sistema, permitindo autenticação segura e controle de perfis.

**Funcionalidades:**
- Cadastro e login de usuários (email/senha) com autenticação JWT.
- Perfis de usuário: **administrador**, **gerente** e **colaborador**.
- Recuperação de senha via e-mail.

---

## 2. Gestão de Tarefas

**Descrição:**  
Permite o controle completo de tarefas dentro dos projetos, incluindo criação, edição e atribuição.

**Funcionalidades:**
- Criar, editar, excluir e atribuir tarefas.
- Campos: **título**, **descrição**, **responsável**, **status**, **prioridade**, **data de entrega**.
- Visualização em **lista** e em **quadro (kanban simples)**.
- Filtro e busca por **status**, **responsável** e **prioridade**.

---

## 3. Gestão de Projetos

**Descrição:**  
Administra os projetos e suas tarefas associadas, oferecendo visão geral do progresso.

**Funcionalidades:**
- Criar projetos e associar tarefas.
- Definir **nome**, **descrição** e **equipe envolvida**.
- Painel de resumo do progresso (percentual concluído).

---

## 4. Colaboração

**Descrição:**  
Facilita a comunicação e o acompanhamento de atividades entre membros da equipe.

**Funcionalidades:**
- Comentários em tarefas.
- Notificações básicas (ex: “Tarefa atribuída a você”).
- Histórico de alterações (**log de atividade por tarefa**).

---

## 5. Dashboard e Relatórios

**Descrição:**  
Centraliza métricas e relatórios de desempenho de tarefas e projetos.

**Funcionalidades:**
- Painel principal com contagem de tarefas por status (**A Fazer / Em Progresso / Concluído**).
- Relatórios por **usuário** e por **projeto**.
- Exportação de dados (**CSV ou PDF**).

---

## 6. Configurações e Acesso

**Descrição:**  
Gerencia usuários, permissões e preferências visuais do sistema.

**Funcionalidades:**
- Gestão de usuários (somente para **administrador**).
- Permissões básicas por papel.
- Configurações de **idioma** e **tema** (opcional).
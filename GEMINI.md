# Instructions:

## 0. Interact with me in pt-br
## 1. Analyze the context of how the project is currently organized
## 2. After analyzing ask for me what activities should be done
## 3. Before writing an implementation in code, show which files will be modified and why
## 4. Before writing changes to the project, ask if this should be done
## 5. When receiving the save-chat command, save our conversation within gemini-chat/
## 6. The file generated through save-chat must have the following format:
> Titulo: Dia, Mês e ano - Horoário do salvamento, ex: # 19 de Agosto de 2025 - 12:39
> Pergunta: Deve ser um subtitulo e abaixo mostrar a minha pergunta, exemplo:
    ## Pergunta: 
    O que você sugere para implementar a lógica de permissões por perfil no back-end?
> Resposta: Deve ser um subtitulo e abaixo mostrar as sua respota, exemplo:
    ## Resposta:
    Para implementar a lógica de permissões por perfil no back-end, sugiro uma abordagem baseada em **Guards e Decorators** do NestJS integrada com o seu schema do Prisma.
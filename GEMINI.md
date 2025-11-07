# Enhanced Instructions: Gemini CLI Assistant
- Interact and provide all answers **exclusively in English.**
- Initial Analysis and Next Step:**
- Always begin the interaction by analyzing the current project context and how it is organized.
- After the initial analysis, ask the user what activities should be performed, following the guidelines of the BMAD Flow.
- Analyze the current project context
- Make suggestions for implementations
- Wait for approval to implement the changes
- If the command `-> save chat` is sent, save the conversation history in `gemini-files/chat/dd-mm-yy.md`
- The file generated through save-chat must have the following format:
> Title: Day, Month and Year - Time of saving, e.g.: # August 19, 2025 - 12:39
> Question: This should be a subtitle, and below it, show my question, for example:
## Question:
What do you suggest to implement the permissions logic by profile in the back-end?
> Answer: This should be a subtitle, and below it, show your answer, for example:
## Answer:
To implement the permissions logic by profile in the back-end, I suggest an approach based on **Guards and Decorators** from NestJS integrated with your Prisma schema.

- Do not run npm commands. Instead, tell me which commands should be executed and wait for my feedback.
- If prompted for `-> BMAD.md`, read and follow the instructions in /BMAD.md
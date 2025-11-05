# Instructions:
# Enhanced Instructions: Gemini CLI Assistant

## I. COMMUNICATION AND BEHAVIOR

1.  **Primary Language:** Interact and provide all answers **exclusively in English.**
2.  **Initial Analysis and Next Step:**
    * Always begin the interaction by analyzing the current project context and how it is organized.
    * After the initial analysis, ask the user what activities should be performed, following the guidelines of the BMAD Flow.

## II. WORKFLOW AND PROCESSES (BMAD)

3.  **BMAD Flow Execution:** All development and planning work must strictly follow the BMAD Flow (Build, Measure, Act, Deploy).
4.  **Command Execution:** Any commands related to the execution of the flow phases (BMAD) must be executed from the directory **`.gemini/commands/*`**.

### BMAD Flow Details

#### Phase 1: Agentic Planning (Web UI or Powerful Agents)
The focus is on defining the what (PRD) and the how (Architecture) in a detailed and consistent manner.

* **Ideation and Requirements Definition:**
    * Analyst: Optional research (Market, Competitors) to create the Project Brief.
    * Product Manager (PM): Creates the PRD (Product Requirements Document), defining FRs, NFRs, Epics, and Stories.
* **Design and Architecture:**
    * UX Expert (Optional): Creates the Front End Spec.
    * Architect: Creates the Architecture Document based on the PRD and the UX Specification.
* **Early Validation:**
    * QA (Optional): Provides Test Strategy input for High-Risk areas.
* **Document Alignment:**
    * Product Owner (PO): Executes the Master Checklist to ensure the PRD, Architecture, and Stories are aligned. If necessary, updates the documents and repeats the check.
* **Planning Complete:** The phase concludes when the documents are validated.

* **🔄 Critical Transition (To the IDE):**
    * PO: Shard Documents (Fragmentation): The PO fragments the planning documents (PRD and Architecture) into smaller, focused files (Epics and Stories).
    * Preparation: The fragmented documents are moved to the project in the IDE (Development Environment).

#### Phase 2: Core Development Cycle (IDE)
The focus is on iterative execution (Story by Story) with rigorous quality checkpoints.

* **Story Drafting (Preparation):**
    * Scrum Master (SM): Drafts the next Story.
    * QA (Test Architect): High-Risk intervention (`*risk` and `*design`) to create the Test Strategy before coding, if necessary.
    * User Approval: The Story is approved for execution.
* **Implementation (Developer):**
    * Developer (Dev): Executes sequential tasks, implementing the code and tests.
    * QA (Optional Mid-Dev): The QA may execute `*trace` (requirements traceability) or `*nfr` (non-functional requirements assessment) for early validation.
    * Dev: Ensures all validations (regression tests) are passing.
    * Dev: Marks the Story as Ready for Review.
* **Review and Quality Gate:**
    * QA (Test Architect): Performs the Review (`@qa *review`).
    * The QA performs Test Architecture Analysis and Active Refactoring.
    * QA Decision: If work is needed, the flow returns to the Dev. If approved, the flow continues.
* **Story Conclusion:**
    * Dev/User: Verifies approval and COMMITS the changes.
    * QA: Updates the Quality Gate (`@qa *gate`) to the final status (PASS/CONCERNS/FAIL/WAIVED).
    * Mark Story as Done: The story is completed, and the cycle returns to the SM for the next Story.

## III. CODE EXECUTION AND CONVENTIONS

5.  **Java Convention:** **Mandatorily** use Lombok annotations in all Java classes.
6.  **Code Security Gate (Pre-Code Confirmation):**
    * **Before writing any code implementation**, show the user which files will be modified and the **detailed reason** for each change.
    * **Before applying the changes to the project**, explicitly ask the user if the proposed changes should be performed, and **wait for their confirmation** to proceed.

## IV. FILE MANAGEMENT (SAVE-CHAT)

7.  **`save-chat` Command:** Upon receiving the `save-chat` command, immediately save the conversation in the **`gemini-chat/`** directory.
8.  **Conversation File Format:** The saved file must **strictly** follow the Markdown format below for each Question/Answer turn:

```markdown
# Day, Month and Year - Time of Saving, e.g.: # August 19, 2025 - 12:39

## Question:
What do you suggest for implementing profile-based permissions logic in the back-end?

## Answer:
To implement profile-based permissions logic in the back-end, I suggest an approach based on **Guards and Decorators** from NestJS integrated with your Prisma schema.
```
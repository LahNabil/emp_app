# Employee Management Application

This is an employee management application designed to simplify and enhance the process of managing employees, departments, attendance (pointage), and profiles. Additionally, it integrates AI-powered resume analysis and a chat system to help recruiters find the best candidate.

## Features

- **Employee & Department Management**: Efficiently manage employee data, departments, and attendance.
- **File Handling & CV Analysis**: Upload PDF resumes, which are processed by an AI component to extract relevant information.
- **AI-Powered Chat for Recruitment**: Utilizes RAG (Retrieval-Augmented Generation) for assisting in identifying top candidates.
- **Profile Management**: User-friendly interface for managing profiles and access permissions.

## Tech Stack

- **Backend**: Java Spring Boot
  - **Dependencies**: Spring Data JPA, Spring Security, JSON Web Tokens (JWT), ModelMapper, Lombok, Spring Validation
- **Frontend**: Angular
- **Database**: PostgreSQL with pgVector for advanced storage and vector-based querying
- **AI Integration**: Spring AI with OpenAI and RAG for resume processing
- **Containerization**: Docker Compose

## Project Structure

- `backend/` - Contains the Java Spring Boot backend code
- `frontend/` - Contains the Angular frontend code

## Installation & Setup

### Prerequisites

- Java 17 or higher
- Node.js and Angular CLI
- Docker and Docker Compose
- PostgreSQL


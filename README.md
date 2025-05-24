# 🎯 Microservices-Based Quiz Application

---

## 📝 Overview

This is a **Quiz Application** designed using a **microservices architecture** for improved scalability and maintainability. The application was initially developed as a monolithic system and later refactored into separate microservices:

- **Quiz Service** – Responsible for quiz creation and score calculation.
- **Question Service** – Manages questions and provides them to the Quiz Service.

---

## 🚀 Features & Modules


### Question Service
- Add, update, delete, and fetch questions

### Quiz Service
- Create a quiz by selecting a category and number of questions
- Fetch available quizzes
- Submit answers and receive a score

---

## 🛠 Technology Stack

| Component     | Technology                                                                                 |
|---------------|--------------------------------------------------------------------------------------------|
| Language      | Java 17                                                                                    |
| Framework     | Spring Boot                                                                               |
| Dependencies  | Spring Web, Spring Data JPA, PostgreSQL Driver, OpenFeign, Eureka Client          |
| Database      | PostgreSQL (managed via pgAdmin 4)                                                        |
| Architecture  | Microservices                                                                             |
| Tools         | Eureka (Service Discovery), Feign (Inter-service communication)                           |

---

## 🧩 Microservices Details

### Question Service

- **Endpoints:**
  - `getAllQuestions()`
  - `getQuestionByCategory(categoryName, numQuestions)`
  - `addQuestion()`

- **Responsibilities:**
  - Manage question database
  - Provide a list of random question IDs for a given category
  - Return question details (without revealing correct answers upfront)
  - Validate submitted answers and return scores

- **Data Privacy:**
  - Correct answers are only shared when required for scoring, never in general question fetch responses.

---

### Quiz Service

- **Endpoints:**
  - `createQuiz(categoryName, numQuestions)`
  - `getQuiz()`
  - `getScore()`

- **Responsibilities:**
  - Accept quiz creation requests specifying category and question count
  - Request question IDs from Question Service
  - Store quiz metadata and question IDs
  - Send user-submitted answers to Question Service for validation and scoring

---

## 🔄 Quiz Flow

1. **Create Quiz**
   - Client sends category and number of questions to Quiz Service.
   - Quiz Service calls Question Service to fetch a list of question IDs.
   - Question Service returns question IDs.
   - Quiz Service stores quiz details with these question IDs.

2. **Take Quiz**
   - User attempts the quiz.
   - Quiz Service collects answers and sends question IDs + user responses to Question Service.
   - Question Service validates answers and returns the score.

---

Feel free to reach out if you'd like to contribute or have any questions!


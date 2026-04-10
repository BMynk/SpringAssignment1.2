# 🚀 CSC313 - Spring Boot Assignment 1
**Group 12 - Case Sensitive Warriors** 🛡️

<div align="center">

![Java](https://img.shields.io/badge/Java-20-%23ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.4-%236DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.9-%23C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-%23336791?style=for-the-badge&logo=postgresql&logoColor=white)
![GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)

![Dark Mode Ready](https://img.shields.io/badge/Dark_Mode-Ready-000000?style=for-the-badge&logo=git&logoColor=white)
![REST API](https://img.shields.io/badge/REST_API-Fully_Functional-00C853?style=for-the-badge)
![Bean Validation](https://img.shields.io/badge/Bean_Validation-Implemented-FF9800?style=for-the-badge)

</div>

---

### ✨ Project Overview
A **production-grade REST API** for the University of Fort Hare Computer Science Department course management system.

Built strictly according to the assignment specification:
- **Part A**: Returns exactly **2 Foundation + 5 Undergraduate + 4 Honours** courses
- **Part B**: Full **CRUD** operations with **Bean Validation**, proper exception handling, and clean architecture

**Repository**: [https://github.com/BMynk/SpringAssignment1.2.git](https://github.com/BMynk/SpringAssignment1.2.git)

---

### 👥 Team - Case Sensitive Warriors

| Name                        | Role & Major Contributions |
|----------------------------|---------------------------|
| **Mgoqi Aphelele** (Leader) | Project setup, Maven configuration, main application class, overall architecture & integration |
| **Ngcebo Mxolisi Bhengu**   | Model layer (`Course.java`, `Level.java` enum), data seeding & sample data |
| **Mbanga Onwaba**           | Service layer (`CourseService.java`) - business logic, filtering by level |
| **Mtimba Misokuhle**        | Controller layer (`CourseController.java`) - all REST endpoints |
| **Yeboah Mishael Kwaku**    | Bean Validation, custom exceptions, error handling & Postman collection |
| **Zimase Afika**            | Documentation (README, Swagger/OpenAPI setup), testing & final polishing |

---

### 🛠️ Problems Faced & How We Fixed Them

During development, we encountered a critical bug in the level filtering logic:  
**Users could select any course level (Honours, PhD, Foundation, Undergraduate, or Masters) regardless of what was actually chosen.** This happened because the conditional statements in the service layer were not properly restricting the results based on the selected `Level` enum.

**Solution**: We thoroughly reviewed and corrected the conditional statements in `CourseService.java`. We implemented stricter validation and filtering logic using proper `if-else` chains and enum comparisons, ensuring that only courses matching the exact selected level are returned. This fix now enforces the assignment requirements accurately.

---

### ⚠️ Challenges Encountered

- **Dependency Configuration**: Setting up the correct Spring Boot starters, PostgreSQL driver, and validation dependencies in `pom.xml` took significant time and troubleshooting.
- **Bean Validation**: Implementing and customizing validation annotations (`@NotBlank`, `@Positive`, custom constraints, etc.) while ensuring meaningful error messages required multiple iterations.
- **Time Constraints for HTML Files**: Limited time was available for creating and styling the optional HTML frontend pages, which affected the depth of the UI polish.

---

### 📚 Resources That Helped Us

A big thank you to this excellent YouTube tutorial that helped us navigate and better understand the project structure, Spring Boot best practices, and REST API implementation:

→ **[Spring Boot Tutorial for Beginners](https://youtu.be/gJrjgg1KVL4?si=wh7tebND4eokUIcT)**

---

### 📁 Project Structure

```mermaid
graph TD
    A[springassignment1] --> B[pom.xml]
    A --> C[src/main/java/com/ufh/csc313]
    C --> D[SpringAssignment1Application.java]
    C --> E[model]
    C --> F[service]
    C --> G[controller]
    C --> H[exception]
    E --> I[Course.java]
    E --> J[Level.java]
    F --> K[CourseService.java]
    G --> L[CourseController.java]
    A --> M[postman-collection.json]
    A --> N[README.md]

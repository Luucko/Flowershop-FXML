# **Flower Shop (2024)**
## *Course: Object Oriented Architectures & Secure Development*
## *Grade: 17/20*
## *Tools used: Java, JavaFX, Gradle, JUnit, SQLite, GitLab, custom exceptions, Crypto utility*

This project was the final exam assignment for the **Object Oriented Architectures & Secure Development** course. The goal was to design and implement a secure and well-architected JavaFX desktop application using Gradle. The app simulates a flower shop where customers can register, log in, configure custom bouquets, and place orders.

### Application features:

* **User Registration & Authentication:**
  Users can securely register and log in using hashed passwords (via a provided `Crypto` class). All credentials are stored in an SQLite database, with secure hashing and custom error handling to prevent exposure of sensitive backend issues.

* **Bouquet Configuration & Ordering:**
  Users can configure bouquets by selecting a random set of flowers (between 5 and 25). The UI displays all flowers in the bouquet along with individual prices and a calculated total. Dissatisfied users can reconfigure bouquets as many times as needed.

* **Ordering Flow & Persistence:**
  Once a user confirms a bouquet, it is saved to a non-human-readable file unique to the user. On relaunching the app, all previously ordered bouquets are reloaded and displayed.

* **Personalized Experience:**
  All data is user-specific—customers can only see their own bouquet history, and all data is isolated using username-linked file storage.

* **Secure Coding Practices:**
  Implemented layered exception handling, password hashing, encrypted storage of user data, and proper error feedback for UI. Used custom exceptions to ensure no technical stack traces reach the user interface.

* **Code Quality & Testing:**
  Followed clean code practices with a clear separation of UI, logic, and data access layers. Included a JUnit test following the AAA pattern to demonstrate understanding of unit testing principles.

### Runtime functionality verified:

* Correct login/registration flow (e.g., for user `alice` with `welcome123`).
* Bouquet configuration and reconfiguration with dynamic price calculation.
* File-based order persistence that survives application restarts.
* Accurate reflection of user-specific order history and bouquet details.

This exam project tested the full spectrum of application development skills—**from architecture, secure coding, and database interaction to UI design and persistence**. Scoring 17/20 reflects solid implementation with strong adherence to software quality standards and secure development principles.

---

Let me know if you’d like a condensed version for a CV or portfolio!

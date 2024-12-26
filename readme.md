# Kata: Library Management System

This is a simple **Library Management System** built using **Java** and **Spring Boot**. The system allows users to perform basic library operations such as adding books, borrowing books, returning books, and viewing available books. The application uses **MongoDB** as its database.

## Features

1. **Add Books**
    - Add new books to the library with details such as id, title, author, and publication year.

2. **View Available Books**
    - View a list of books currently available for borrowing.

3. **Borrow Books**
    - Borrow a book by its id. The system ensures the book is available before allowing it to be borrowed.

4. **Return Books**
    - Return a borrowed book by its id. The system updates the availability of the book accordingly.

5. **Error Handling**
    - Custom exceptions like `BookNotFoundException`, `BookNotAvailableException`, and `InvalidBookException` provide meaningful error messages when invalid operations are attempted.

## Technologies Used

- **Java 17**
- **Spring Boot 3.x**
- **MongoDB** (NoSQL database)
- **Maven** (Build tool)
- **JUnit 5** (For test-driven development)

## Installation and Setup

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/a-j-a-y-c-p/Kata.git
   cd Kata
   ```

2. **Set Up Environment Variables**:

   - Create a `.env` file in the `\src\main\resources` directory of the project by copying `.env.example`.

3. **Build the Project**:

    ```bash
    mvn clean install
   ```

4. **Run the Application**:

    ```bash
    mvn spring-boot:run
    ```

5. **Access the Endpoints**:

    - The application runs on `http://localhost:8080`.
    - Use tools like **Postman** or **cURL** to interact with the API.

## API Endpoints

### Add a Book

- **POST** `/api/v1/library/`
- Request Body:
  ```json
    {
    "id": "12345",
    "title": "bookTitle",
    "author": "bookAuthor",
    "publicationYear": 2020
    }
  ```
- Response: `201 Created` or `400 Bad Request` if the book is null.

### View Available Books

- **GET** `/api/v1/library/`
- Response:
  ```json
  [
  {
    "id": "12345",
    "title": "bookTitle",
    "author": "bookAuthor",
    "publicationYear": 2020
  }
  ]
  ```

### Borrow a Book

- **POST** `/api/v1/library/borrow/{id}`
- Path Parameter: id of the book to borrow.
- Response: `200 OK` or `400 Bad Request` if the book is already borrowed.

### Return a Book

- **POST** `/api/v1/library/return/{id}`
- Path Parameter: id of the book to return.
- Response: `200 OK` or `400 Bad Request` if the book was not borrowed.
  
### Exception Handling

The application handles the following exceptions:

- `BookNotFoundException`: Thrown when a book with the specified ISBN is not found.
- `BookNotAvailableException`: Thrown when attempting to borrow a book that is already borrowed.
- `InvalidBookException`: Thrown when attempting to return a book that was not borrowed.

### Testing

Tests are written using **JUnit 5** to validate the application's core functionality. Test cases include scenarios for:

- Adding a book.
- Borrowing an available book.
- Attempting to borrow an unavailable book.
- Returning a borrowed book.
- Attempting to return a book that was not borrowed.

Test reports are also generated as part of the build process.

Run tests using:
   ```bash
   mvn test
   ```
mvn test


### Directory Structure

```plaintext
src/
├── main/
│   ├── java/
│   │   ├── com.librarymanagement/
│   │       ├── controller/
│   │       ├── dto/
│   │       ├── exception/
│   │       ├── model/
│   │       ├── repository/
│   │       ├── service/
│   ├── resources/
│       ├── application.properties
│       ├── .env.example
└── test/
├── pom.xml
├── surefire-test-reports
└── README.md
```
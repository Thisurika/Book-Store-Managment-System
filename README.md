# Book-Store-Managment-System
Little Book Heaven is a Java EE-based web app for managing a bookstore. It supports Manager and Cashier roles with features like book CRUD, category search using Merge Sort, and cart calculation. Data is stored in text files using FileHandler, with a clean and user-friendly JSP frontend.

# 📚 Little Book Heaven – Bookstore Management System

**Little Book Heaven** is a Java EE-based web application designed to manage a bookstore system with role-based access. Developed as part of my undergraduate studies at SLIIT, this project demonstrates strong use of Object-Oriented Programming, File Handling, and Data Structures (Merge Sort and Binary Search Tree).

---

## 🔧 Features

- **User Roles:**
  - 👤 **Manager**: Add and manage cashiers.
  - 👤 **Cashier**: Add, view, update, delete books; manage cart and search.

- **Book Management:**
  - Perform CRUD operations on books.
  - Store book data in text files using `FileHandler.java`.

- **Cart & Payment:**
  - Add books to cart, calculate total bill.

- **Search & Sort:**
  - Filter books by category using **Merge Sort**.
  - Store books in a **Binary Search Tree** for optimized search.

- **Frontend:**
  - Built with JSP, HTML, and CSS for a user-friendly interface.

---

## 🛠️ Tech Stack

| Technology    | Description                        |
|---------------|------------------------------------|
| Java EE       | Backend logic (Servlets, JSP)      |
| JSP/CSS/HTML  | Frontend views                     |
| Apache Tomcat | Deployment server (v10.1.x)        |
| File I/O      | Data persistence (no DB)           |
| Maven         | Build tool                         |
| DSA Used      | Merge Sort, Binary Search Tree     |

---

## 🗂️ Project Structure

project/
├── src/
│ ├── model/ # Book, Cashier classes (OOP)
│ ├── dao/ # FileHandler, BookDAO
│ └── servlet/ # Servlet classes
├── web/ # JSP, CSS, images
├── WEB-INF/ # web.xml
├── pom.xml
└── README.md # This file

yaml
Copy
Edit

---

## 🚀 Getting Started

1. **Clone the repository**  
   ```bash
   git clone https://github.com/your-username/LittleBookHaven.git
Import into IntelliJ or VS Code (with Java & Maven support)

Build the project

bash
Copy
Edit
mvn clean install
Run with Apache Tomcat 10.1.x
Deploy WAR file or run directly via IDE

Access via browser

arduino
Copy
Edit
http://localhost:8080/LittleBookHaven/
📦 System Requirements
Java JDK 17+

Apache Tomcat 10.1.x

Maven 3+

Web browser

🧑‍💻 Author
Thisuru
SLIIT Undergraduate – BSc (Hons) in Information Technology
📧 Email: your.email@example.com
🔗 LinkedIn: linkedin.com/in/yourprofile

📜 License
This project is open-source and free to use for educational purposes.

“First, solve the problem. Then, write the code.” – John Johnson

vbnet
Copy
Edit

---

✅ **Next Step**:  
- Paste this into a file named `README.md`.
- Upload it to your GitHub repo using the **"Add file" → "Create new file"** method or by dragging and dropping.

Let me know if you want me to send you this as a downloadable `.md` file!

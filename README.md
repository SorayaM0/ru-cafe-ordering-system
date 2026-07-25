# ☕ RU Cafe Ordering System

A desktop café ordering application built with **Java**, **JavaFX**, and **Maven**. This application allows users to customize menu items, manage a current order, place orders, and review completed orders through an interactive graphical user interface.

---

## 📌 Features

### 🍩 Donut Ordering
- Choose from multiple donut types:
  - Yeast
  - Cake
  - Donut Hole
  - Seasonal
- Select quantity
- Dynamic donut image updates based on the selected donut type

### ☕ Coffee Ordering
- Select cup size:
  - Short
  - Tall
  - Grande
  - Venti
- Add multiple add-ins
- Select quantity
- Dynamic coffee image updates based on the selected cup size

### 🥪 Sandwich Ordering
- Choose bread type
- Select protein
- Add optional toppings
- Select quantity

### 🛒 Current Order
- View all selected items
- Remove individual items
- Clear the entire order
- Automatically calculates:
  - Subtotal
  - Sales Tax
  - Total Price

### 📋 Placed Orders
- Place completed orders
- View all placed orders
- Cancel existing orders
- Export all orders to a text file

---

## 🖼️ User Interface

The application was developed using **JavaFX** and includes:

- Multiple tabs for different menu categories
- Responsive user interface
- Dynamic food and beverage images
- Interactive order management
- Styled using CSS

---

## 🛠️ Technologies Used

- Java 21
- JavaFX
- Maven
- FXML
- CSS
- Object-Oriented Programming (OOP)

---

## 📂 Project Structure

```
Project4
│
├── src
│   └── main
│       ├── java
│       │   ├── Project4
│       │   ├── controller
│       │   └── model
│       │
│       └── resources
│           ├── css
│           ├── view
│           └── view/img
│
├── pom.xml
├── README.md
└── module-info.java
```

---

## ▶️ Running the Application

Clone the repository:

```bash
git clone https://github.com/SorayaM0/ru-cafe-ordering-system.git
```

Navigate into the project:

```bash
cd ru-cafe-ordering-system
```

Run the application using Maven:

```bash
mvn clean javafx:run
```

---

## 📸 Screenshots

You can add screenshots here after uploading them to your repository.

### Home Screen

![Home](screenshots/home.png)

### Donut Ordering

![Donuts](screenshots/donuts.png)

### Coffee Ordering

![Coffee](screenshots/coffee.png)

### Current Order

![Current Order](screenshots/current-order.png)

### Placed Orders

![Placed Orders](screenshots/placed-orders.png)

---

## 🎯 Learning Objectives

This project demonstrates:

- JavaFX GUI development
- Event-driven programming
- MVC (Model-View-Controller) architecture
- Object-oriented design principles
- File input/output
- Collections and data management
- Maven project configuration

---

## 👩‍💻 Author

**Soraya M.**

GitHub: https://github.com/SorayaM0

---

## 📄 License

This project was developed for educational purposes as part of the Rutgers University Software Methodology course.

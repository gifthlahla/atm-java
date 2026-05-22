# 🏧 ATM Desktop App – Java Swing GUI

A fully functional ATM simulation desktop application built with Java Swing.  
This project started as a console-based task during my **Oasis Infobyte Java Development Internship**, then was rebuilt as a GUI application to practice desktop development, event handling, and UI design.

---

## ✨ Features

- 🔐 **Secure Login** – User ID and PIN (hardcoded sample users)
- 💰 **Check Balance** – View current account balance
- 📥 **Deposit Money** – Add funds with positive amount validation
- 📤 **Withdraw Cash** – Withdraw with insufficient funds check
- 📜 **Transaction History** – Scrollable list with **timestamps** (LocalDateTime) for every deposit and withdrawal
- ❌ **Exit** – Close the application safely

All data is stored **in-memory** using HashMap and ArrayList – no external database required.

---

## 🖥️ Tech Stack

- **Java 17** (or higher)
- **Swing** – GUI framework
- **java.time.LocalDateTime** – Timestamps for transaction history
- **OOP principles** – Encapsulation, separation of concerns
- **Apache NetBeans** – IDE used for development

---

## 🚀 How to Run

### Option 1: Run in NetBeans (Recommended)

1. Open **Apache NetBeans**.
2. Go to `File → Open Project` and select the `atm-java` folder.
3. Right-click the project and select **Run**.

### Option 2: Run from Command Line

1. Navigate to the project folder:
   ```bash
   cd atm-java
   ```
2. Compile the Java files:
   ```bash
   javac -d build/classes src/atminterface/*.java
   ```
3. Run the application:
   ```bash
   java -cp build/classes atminterface.LoginFrame

---

## 🔑 Sample Login Credentials

| User ID |	PIN	| Starting Balance |
|---------|-----|------------------|
| gift | 1234 |	$2500.00 |

---

## 📁 Project Structure (NetBeans Project)

```
atm-java/
├── build.xml              (NetBeans build script)
├── manifest.mf            (NetBeans manifest)
├── nbproject/             (NetBeans project configuration)
├── build/                 (Compiled .class files)
├── src/
│   └── atminterface/
│       ├── ATMGUI.java    (Main class)
│       ├── LoginScreen.java
│       ├── Dashboard.java
│       ├── User.java
│       └── (other .java files)
└── README.md
```

---

## 🙌 Acknowledgments
- Oasis Infobyte – For the internship that inspired this project. The original console-based ATM was Task 2 of the internship. This GUI version is a personal enhancement to explore Swing and improve user experience.

---

## 📄 License
- This project is for personal portfolio and learning purposes. Feel free to use or modify it for your own practice.

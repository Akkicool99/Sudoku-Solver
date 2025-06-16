# 🧠 Sudoku Solver (JavaFX)

A simple and interactive Sudoku Solver built using **Java 22** and **JavaFX**. This project allows users to input Sudoku puzzles through a graphical interface and solve them using a backtracking algorithm.

---

## 📸 Preview

![GUI Screenshot](https://github.com/Akkicool99/Sudoku-Solver/blob/3dfebfd8849d5af14a6d58d9602a25192f386264/Screenshot%202025-06-16%20174931.png)

After Pressing "Solve" Button

![GUI Screenshot](https://github.com/Akkicool99/Sudoku-Solver/blob/3dfebfd8849d5af14a6d58d9602a25192f386264/Screenshot%202025-06-16%20174953.png)
---

## ✨ Features

- ✅ 9x9 Sudoku grid input
- ✅ Highlights 3x3 subgrids for better visibility
- ✅ "Solve" button to trigger the solving algorithm
- ✅ "Clear" button to reset the board
- ✅ Easy to set initial puzzles via code
- ✅ JavaFX GUI for a clean visual experience

---

## 🔧 Tech Stack

- Java 22
- JavaFX SDK 24
- VS Code with JavaFX setup
- Git & GitHub
  
---
## 🧠 Algorithms Used

-Backtracking: The solver checks each cell recursively by trying all valid digits.
-Skips pre-filled cells.
-Stops once the board is fully solved

---

## 📁 Folder Structure

SudokuSolver/
- ├── lib/ # JavaFX SDK folder
- ├── src/
- │ ├── SudokuSolver.java # Solving logic (backtracking)
- │ └── SudokuGUI.java # JavaFX GUI class
- ├── .gitignore
- └── README.md


---

## ▶️ How to Run

### 1. Set up JavaFX SDK
Download from: https://openjfx.io/

### 2. Compile
```bash
javac --module-path lib/javafx-sdk-24/lib --add-modules javafx.controls,javafx.fxml -d out src/SudokuSolver.java src/SudokuGUI.java


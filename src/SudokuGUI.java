import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SudokuGUI extends Application {
    private TextField[][] fields = new TextField[9][9];
    private SudokuSolver solver = new SudokuSolver();

    // 👇 Change this to preload a puzzle (0 = empty)
    private int[][] presetBoard = {
        {5, 3, 0, 0, 7, 0, 0, 0, 0},
        {6, 0, 0, 1, 9, 5, 0, 0, 0},
        {0, 9, 8, 0, 0, 0, 0, 6, 0},
        {8, 0, 0, 0, 6, 0, 0, 0, 3},
        {4, 0, 0, 8, 0, 3, 0, 0, 1},
        {7, 0, 0, 0, 2, 0, 0, 0, 6},
        {0, 6, 0, 0, 0, 0, 2, 8, 0},
        {0, 0, 0, 4, 1, 9, 0, 0, 5},
        {0, 0, 0, 0, 8, 0, 0, 7, 9}
    };

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);

        // Create 9x9 text fields with styling and borders
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                TextField tf = new TextField();
                tf.setPrefHeight(50);
                tf.setPrefWidth(50);
                tf.setFont(Font.font(18));
                tf.setAlignment(Pos.CENTER);

                // Only allow digits 1–9
                tf.textProperty().addListener((obs, oldVal, newVal) -> {
                    if (!newVal.matches("[1-9]?")) {
                        tf.setText(oldVal);
                    }
                });

                // Preload values from presetBoard
                if (presetBoard[row][col] != 0) {
                    tf.setText(String.valueOf(presetBoard[row][col]));
                }

                fields[row][col] = tf;
                tf.setBorder(createCellBorder(row, col));
                grid.add(tf, col, row);
            }
        }

        // Solve Button
        Button solveBtn = new Button("Solve");
        solveBtn.setOnAction(e -> {
            int[][] board = new int[9][9];
            for (int row = 0; row < 9; row++) {
                for (int col = 0; col < 9; col++) {
                    String text = fields[row][col].getText();
                    board[row][col] = text.isEmpty() ? 0 : Integer.parseInt(text);
                }
            }

            if (solver.solve(board)) {
                for (int row = 0; row < 9; row++) {
                    for (int col = 0; col < 9; col++) {
                        fields[row][col].setText(String.valueOf(board[row][col]));
                    }
                }
            } else {
                System.out.println("No solution found.");
            }
        });

        // Clear Button
        Button clearBtn = new Button("Clear");
        clearBtn.setOnAction(e -> {
            for (int row = 0; row < 9; row++) {
                for (int col = 0; col < 9; col++) {
                    fields[row][col].clear();
                }
            }
        });

        // Layout buttons
        HBox buttonBox = new HBox(20, solveBtn, clearBtn);
        buttonBox.setAlignment(Pos.CENTER);

        VBox root = new VBox(20, grid, buttonBox);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 600, 700);
        primaryStage.setTitle("Sudoku Solver");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to add bold borders every 3 blocks
    private Border createCellBorder(int row, int col) {
        BorderStrokeStyle style = BorderStrokeStyle.SOLID;
        Color color = Color.BLACK;
        double thin = 1;
        double thick = 3;

        return new Border(new BorderStroke(
            color, color, color, color,
            style, style, style, style,
            CornerRadii.EMPTY,
            new BorderWidths(
                (row % 3 == 0) ? thick : thin,  // top
                (col % 3 == 2) ? thick : thin,  // right
                (row == 8) ? thick : ((row % 3 == 2) ? thick : thin),  // bottom
                (col % 3 == 0) ? thick : thin   // left
            ),
            null
        ));
    }

    public static void main(String[] args) {
        launch(args);
    }
}

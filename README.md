# Course "Artificial Life" Programming Assignments
Programming assignments done in Masters In Computer Science course "Artificial Life", using Java or C++.

## Programming Assignment: A

Implement a **1-dimensional cellular automaton** with the k = 2 states {0, 1}, with a neighborhood
radius of r = 1 or r = 2, and 84 cells.

The boundary cells j = 0, j = 1, j = 82, j = 83 shall be fixed to the content aj = 0. The
programm shall depict in every line the complete state of all 84 cells as text console ASCII
output.

Implement two possible starting conditions for the CA:
S: a seed (all cells are empty but cell no 42, ai=42 = 1), and
R: random starting condition, each cell is set with a probability of p = 0.5.
Let the user enter at runtime: the neighborhood radius r, the rule for the CA (Wolfram
Notation), and the starting condition (S or R).

Please use C, C++, or Java to implement your program.
Send an E-Mail to your tutor containing the documented source code, a description how
to compile and run your program (e.g. give the commands), and a file containing at
least 10 lines of result.

## Programming Assignment: B

Implement a two-dimensional (d=2) (Cellular) Automaton on a 101 × 82 sized, rectangular
grid. Use a torus toplogy to manage the boundary of the grid. If you use console output
(ASCII-art) take characters that are aligned with the content they represent and that are
easy to distinguish from each other.

You can choose if you would like to implement Langton’s Ant, Conway’s Game of Life or
the probabilistic Cellular Automaton called “Forest Fire”.

### Langton’s Ant:
Make the user choose the initial configuration of the grid from the following list of 5 possibilities:
all white, all black, checker board, horizontal stripes, random setting and make the
user chose the starting position and orientation for the ant.
Write the total number of cells living for each time step into a file (one ASCII value per line).

### Conway’s Game of Life:
Make the user choose the initial pattern to start with, from the following list of 5 possibilities:
blinker, glider, r-pentomino, Gosper’s Glider Gun, and a pattern (class 3 or 4) of your
own choice.
Write the total number of cells living for each time step into a file (one ASCII value per line).

### Forest Fire Model: (I implemented this)
Make the user set the probabilistic parameters p, f, q. Start with an empty field (all cells are
in state Ashes).
Write the number of cells for each state (Ashes, Tree, Fire) for each time step into a file
(three values per line, ASCII format, separated by blanks).

Implement your program using C, C++, or Java:
Make sure your program can be compiled and started from a console, or a terminal; using an
IDE is fine for developing, but make sure the final program can be operated without the IDE.
Send an E-Mail to your tutor containing the documented source code and a description
how to compile and run your program from the console (no IDE).

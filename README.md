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

## Programming Assignment: C 

Write a C, C++, or Java Programm, that implements a **Predator-Prey**, Activator-Inhibitor
System with an iterated function. Please notice, that it is reasonable to work with real
values (type double).

Find a set of starting conditions for x(0) and y(0) and a set of parameters a, b, c, d, e, f that
will yield an almost stable oscillation. Determine the mean population size ¯x, y¯ for prey and
predator when the system has reached an almost stable oscillation.

Increase the parameter a a bit. How is the influence of a on the mean values ¯x, y¯?

**x(i + 1) = x(i) + a ∗ x(i) + b ∗ y(i) + e ∗ x(i) ∗ x(i)**

**y(i + 1) = y(i) + c ∗ x(i) + d ∗ y(i) + f ∗ y(i) ∗ y(i)**

Draw the temporal development x(i), and x(i) of a stable oscillation with respect to the
iteration number i, and draw a so-called phase plot, where y(i) is plotted against x(i) in a
two dimensional diagram.

Use the program Gnuplot to draw this graph, and hand in the respective Gnuplot commands.
Extend the equations by two further terms, redo the steps above for the altered system and
describe some of the major differences.

**x(i + 1) = x(i) + a ∗ x(i) + b ∗ y(i) + e ∗ x(i) ∗ x(i) + g ∗ x(i) ∗ y(i)**

**y(i + 1) = y(i) + c ∗ x(i) + d ∗ y(i) + f ∗ y(i) ∗ y(i) + h ∗ x(i) ∗ y(i)**

Make sure your program is running correctly, and that you have sensible and resonable comments
in your sourcecode

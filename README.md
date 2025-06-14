# JWalker Examples
[![Apache-2.0](https://img.shields.io/badge/license-Apache--2.0-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0)
[![Java 11](https://img.shields.io/badge/Java-%3E=%2011-blue.svg)](https://adoptium.net/temurin/releases/?os=any&arch=any&version=11)

This repository contains example projects for [JWalker](https://github.com/epieffe/jwalker),
to show how it can be used to solve any search problem.

## 🖥️ How to run
Each example project can be executed with the Exec Maven Plugin, or it can be packaged into an executable jar.

#### Run with the Exec Maven Plugin
To run a project with the Exec Maven Plugin, supply its subproject name to the `-pl` option.

For example, to run the Maze project, run the following command:
```
./mvnw compile exec:java -pl maze
```

#### Package into an executable jar
The package goal produces an executable jar with all the necessary dependencies included.

For example, to create an executable jar for the N-Puzzle project, run the following command:
```
./mvnw clean package -pl npuzzle
```

Then, you can execute the previously created jar with the following command:
```
java -jar npuzzle/target/npuzzle.jar
```

## 📂 Example projects
Here we describe the example projects included in this repository.

#### Maze
The Maze project generates a random maze on a 2D grid, then it uses the *Best&#x2011;first&#160;Search* algorithm to find a path to the exit.

The generated maze and the path to the exit are printed to the console.

![Maze](https://github.com/user-attachments/assets/3a4eec11-1e4d-4211-acdf-4a8560106740)

#### N-Puzzle
The N&#x2011;Puzzle project generates a random [15&#x2011;Puzzle](https://en.wikipedia.org/wiki/15_puzzle) configuration, then it uses the *A** algorithm to find a reasonably short sequence of moves that solve the puzzle.

The sequence of moves to solve the puzzle from the initially generated configuration is printed to the console.

![N-Puzzle](https://github.com/user-attachments/assets/2dba24e2-ca56-47f7-bf44-f6f6f876141e)

#### N-Queens
The N&#x2011;Queens project uses the *Steepest&#160;descent* algorithm to solve the [8&#x2011;Queens](https://en.wikipedia.org/wiki/Eight_queens_puzzle) problem, which consists in placing eight chess queens on a 8x8 chess board so that no two queens threaten each other.

Note that the *Steepest descent* algorithm is not guaranteed to always find an optimal node. Sometimes it might happen that, in the found configuration, there are still some queens threatening each other.

The found configuration and its number of threats between queens are printed to the console.

![N-Queens](https://github.com/user-attachments/assets/2dc13d9e-4d42-418b-849e-24a4ae6b331a)

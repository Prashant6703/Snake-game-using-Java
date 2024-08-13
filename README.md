# Snake Game in Java

This project implements the classic Snake game using Java Swing for GUI and AWT for graphics. It features a playable snake that grows in length as it eats cherries while avoiding collisions with walls and itself.

## Features

- **Game Controls:** Use arrow keys (`UP`, `DOWN`, `LEFT`, `RIGHT`) to control the direction of the snake.
- **Score Tracking:** Keep track of the current score and best score achieved in the game session.
- **Game Status:** Includes game states such as running, paused, game over, and start screen.
- **Cherry Spawn:** Randomly spawns cherries for the snake to eat, increasing the score.
- **Graphics:** Uses basic shapes for drawing the snake, cherries, and game boundaries.

## How to Play

1. **Launch the Game:**
   - Run the `Main.java` file to start the game.
   ```bash
   java Main.java

## Game Controls:

Use arrow keys (UP, DOWN, LEFT, RIGHT) to navigate the snake.
Press P to pause/resume the game.
Press ENTER to restart the game when game over.


## Objective:

Control the snake to eat cherries (o or images) that appear randomly on the screen.
Avoid hitting the walls or colliding with the snake's own body.
Try to achieve the highest possible score.

## File Structure

Main.java: Contains the main class that initializes the game window (JFrame).
Game.java: Extends JPanel and handles the game logic, drawing, and user input.
Snake.java: Represents the snake entity with methods for movement, growth, and direction handling.
Point.java: Represents a point in the game grid used for snake and cherry positioning.
cherry.png: Image file for the cherry (optional, use default oval if image not loaded).


## Contributors
Prashant

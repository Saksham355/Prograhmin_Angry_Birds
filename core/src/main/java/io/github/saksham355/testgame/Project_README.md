# Angry Birds Game Implementation

## Introduction
This project is a Java-based implementation of the popular *Angry Birds* game using the *libGDX* framework. The game involves launching birds from a slingshot to destroy structures and eliminate pigs to progress through levels. It is developed as part of the *CSE 201: Advanced Programming* course.

The project demonstrates the application of *Object-Oriented Programming principles, incorporates **design patterns, implements **game physics, and includes **serialization* for saving/restoring game states.

---
### Core Gameplay
- *Birds and Slingshot*: Drag and launch birds from the slingshot. Birds follow realistic trajectories based on angle and speed.
- *Structures*: Buildings made of blocks (wood, glass, steel) break upon impact, depending on material durability.
- *Pigs*: Pigs have varying sizes and health. They are destroyed when hit by birds or collapsing blocks.
- *Win/Lose Conditions*:
    - *Win*: All pigs are destroyed.
    - *Lose*: All birds are used without eliminating all pigs.

### Levels
- *3 unique levels*, each featuring:
    - Defined sets of birds and different structures.
    - Pigs placed strategically to increase difficulty.
    - Block power: Wood:1,Glass:2,Rock:3
    - Bird power: Black>Yellow>Red

### Design Patterns
- *Factory Pattern*: For creating birds, pigs, and blocks dynamically.
- *Observer Pattern*: For updating game elements like score and UI in response to game events.

### Physics and Interactions
- Realistic bird trajectories using *Box2D* physics.
- Collisions between birds, blocks, and pigs, with cascading effects on block collapses.

### Serialization
- Custom classes for each level to save the existence and position of each physical body.
- These are saved when the user clicks the pause button and then the data is always deserialized for that level.
- The level can be reset by clicking the restart button in the pause menu

### JUnit Testing
- Testcases added for proper function of the serialization functions and deserialization functions
### Run
- Go to the Lwjg3Launcher (in lwjgl3 folder->src->java->io.github.saksham355.testgame.lwjgl3) and run the file

### Sources
- https://angrybirds.fandom.com/wiki/Angry_Birds_Wiki : For Assets of the game.
- www.libgdx.com : For libgdx tutorials.
- https://www.youtube.com/watch?v=_y1RvNWoRFU&list=PLD_bW3UTVsElsuvyKcYXHLnWb8bD0EQNI : Box2D tutorials.

---

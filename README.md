# ldts-project-assignment-g0601

In this remastering of the classic game of 1976, "Breakout", there'll be bricks aligned in the top of the board and your job is to destroy all of them. In order to do that,
you need to try to hit the bricks with a constantly moving ball, by controlling a paddle. 

This project was developed by António Ferreira (up202004735@fe.up.pt), João Maldonado (up202004244@fe.up.pt) and Tomás Gomes (up202004393@fe.up.pt) for LDTS 2021/2022.

### IMPLEMENTED FEATURES 

- **Moving Paddle** - The paddle will move to left/right when the left/right arrow key is pressed. (1)
- **Constantly Moving Ball** - The ball will have an autonomous and continuous movement, which is created by a thread. (2)
- **Hitting the Ball** - By moving the paddle, the player will try to hit the ball.
- **Destroying a Brick** - When you can destroy a brick by bouncing the ball on it, you will get a certain amount of points

(1)

![](https://i.imgur.com/prkkDuH.png)
![](https://i.imgur.com/9HpwUqQ.png)

(2)

![](https://i.imgur.com/kmFUiVX.png)

### PLANNED FEATURES  

- **Simple Main Menu** - A main menu that will let you start the game, read the instructions or close it. (1)
- **Player Lives** - The player will only have 3 lives to try and win the game. (2)

(1)

![](https://i.imgur.com/SleueHm.png)

(2)

![](https://i.imgur.com/UueNvJd.png)

### DESIGN  

-------

#### CREATING SIMILAR CLASSES

**Problem in Context**

In this project, we felt the need to create different objects, but with a lot in common, leading to many methods
that do the same (for example, a draw method). 

**The Pattern**

In order to solve the considered problem, we decided to apply the Factory Method pattern, since it allows us to define a general/abstract class that has all the common methods
shared by the majority of the classes. This way, we don't have to keep implementing the same methods over and over again.

**Implementation**

![](https://i.imgur.com/zightXA.png)

The following link shows how the pattern was introduced in our code:

 - [Element](https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g0601/blob/master/src/main/java/com/ldts/breakout/Element.java)

**Consequences**

The use of the Factory pattern in the current design allows us to have the following consequence:
- As we previously stated, when we create a new class, we already have, in this case, a draw method, since it extends the class Element. To make it specific to the class, we'll
only have to **Override** the draw method.

### KNOWN CODE SMELLS AND REFACTORING SUGGESTIONS

Although we identified various code smells, there's the need to point out that some will not be changed in the final version of the code, since we couldn't find any
solution for them.

------

#### DISPENSABLES

**Data Class**

- **Paddle**
- **Points**
- **Position**
- **Wall**

Even though they help us organize our code, these classes don't do anything special, they're basically data containers 
for other classes, due to the fact of not having any additional functionality.

A way to solve this problematic situation could be de implementation of the **Move Method**, where we include, for example, the points system in the Arena class.

#### BLOATERS

**Large Class**

While implementing the code for our game, we've come to realize that the size of our Arena class was growing quite rapidly, since it deals with a lot of features, like the
paddle movement, the creation of walls and bricks, the verification of the requirements for ending the game...

With this in mind, in order to minimize the size of this class, we could Extract this class seperate many of its methods into diferent components.

**Primitive Obsession**

At the start of this project, we were determined to use the minimum number of scattered values as possible. This way we used constants to have, through out all of our code,
values that stay constant and aren't modifiable. However, the number of constants used are quite high and many are defined by using other constants.

So, to solve this problem, we can only define the important constants and utilize the rest in all of their respective classes.

### TESTING

#### COVERAGE REPORT
![](https://i.imgur.com/o3fuFIc.png)

#### MUTATION TEST
You can check this [link](https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g0601/tree/master/src/test/pitest/202201070024) for the mutation test

### SELF-EVALUATION

All of the members participated equally in the process of making this project, giving it their best to try and develop a high quality project. Everyone collaborated by creating
classes, implementing methods or even doing commits.

- **António Ferreira** - 34%
- **João Maldonado** - 33%
- **Tomás Gomes** - 33%

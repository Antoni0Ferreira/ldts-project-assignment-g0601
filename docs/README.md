# ldts-project-assignment-g0601

In this remastering of the classic game of 1976, "Breakout", there'll be bricks aligned in the top of the board and your job is to destroy all of them. In order to do that,
you need to try to hit the bricks with a constantly moving ball, by controlling a paddle. 

This project was developed by António Ferreira (up202004735@fe.up.pt), João Maldonado (up202004244@fe.up.pt) and Tomás Gomes (up202004393@fe.up.pt) for LDTS 2021/2022.

### IMPLEMENTED FEATURES 

- **Moving Paddle** - The paddle will move to the left/right when the left/right arrow key is pressed.

![](https://i.imgur.com/pdbCog1.gif)

- **Constantly Moving Ball** - The ball will have an autonomous and continuous movement, which is created by a thread.

![](https://i.imgur.com/Fw9sTxW.gif)

- **Hitting the Ball** - By moving the paddle, the player will try to hit the ball.

![](https://i.imgur.com/GjSj3N5.gif)

- **Destroying a Brick** - When you destroy a brick by bouncing the ball on it, you will get a certain amount of points.

![](https://i.imgur.com/iEjteBz.gif)

- **Playing Lives** - You only get 3 lives to try and destroy all of the bricks. When you lose a life, the ball will reset to its original position

![](https://i.imgur.com/6btd2CU.gif)

- **Simple Main Menu** - A main menu that will let you start the game, read the instructions or exit.

![](https://i.imgur.com/Z8LTFNU.gif)

- **Simple Pause Menu** - When you're playing the game, you can hit 'q' on the keyboard to pause the game.

![](https://i.imgur.com/51KOW6K.gif)

### PLANNED FEATURES

Although we are quite happy with the finished product, there's still some features we would like to see implemented, especifically the introduction of
diferent levels or changes in the ball's speed.

### DESIGN  

At this stage of the project, we have implemented 4 different design patterns:

-------

#### CREATING SIMILAR CLASSES

**Problem in Context**

In this project, we felt the need to create different objects, but with a lot in common, leading to many methods
that do the same (for example, a draw method). 

**The Pattern**

In order to solve the considered problem, we decided to apply the Factory Method pattern, since it allows us to define a general/abstract class that has all the common methods
shared by the majority of the classes. This way, we don't have to keep implementing the same methods over and over again.

**Implementation**

![](https://i.imgur.com/VutqToB.png)

The following link shows how the pattern was introduced in our code:

 - [Element](https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g0601/blob/master/src/main/java/com/ldts/breakout/Element.java)

**Consequences**

The use of the Factory pattern in the current design allows us to have the following consequence:
- As we previously stated, when we create a new class, we already have, in this case, a draw method, since it extends the class Element. To make it specific to the class, we'll
only have to **Override** the draw method.

#### HAVING ONLY ONE INSTANCE OF THE CLASS 'GAME'

**Problem in Context**

For this project, we considered that it was good practice to avoid having multiple instances of the class 'Game'

**The Pattern**

With this in mind, our team decided to introduce the Singleton pattern, to only have one "Game".

**Implementation**

![]()

The following link shows how the pattern was introduced in our code:

- [Game](https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g0601/blob/master/src/main/java/com/ldts/breakout/Game.java)

**Consequences**

The use of the Singleton pattern in the current design allows us to have the following consequences:
- Even though this pattern could be caracterized as an "antipattern", since its use is on the decline, it ensures us that only one object of the class 'Game' will
be created and used through out the whole runtime of the program;
- However, this pattern has many disavantages, like an increase in difficulty in the testing of the code and the breaking of our code's modularity.

#### A BETTER WAY TO ORGANIZE OUR CODE

**Problem in Context**

While we were in the earlier stages of this project, our code was getting quite messy and we couldn't understand the overall flow of our methods (for example,
the draw method of the class 'Arena' was getting mixed up with the method that allowed the paddle to move).

**The Pattern**

This way, we came to the conclusion that we should restructure our classes and apply the Model-View-Controller (MVC) pattern, in order to divide our project into 3 different
main parts for a better organization of our code.

**Implementation**

![]()

The following link shows how the pattern was introduced in our code:

- [Model](https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g0601/tree/master/src/main/java/com/ldts/breakout/model)
- [Controller](https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g0601/tree/master/src/main/java/com/ldts/breakout/controller)
- [View](https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g0601/tree/master/src/main/java/com/ldts/breakout/viewer)

**Consequences**

The use of the MVC pattern in the current design allows us to have the following consequences:
- As we already stated, with this pattern, our project now has a better structure, where, for each element of our game, we created 3 different classes
that handle different tasks: the Model classes only represent our data, the View classes display the models and sends the player's actions to a controller and the Controller
classes provide data from the models to the viewers and interpret the player's actions.

#### PRESSING THE ARROW KEYS

**Problem in Context**

In this game, as we mentioned before, the player gets to control a paddle with the left/right arrow keys of its keyboard. Even though it worked, in the beginning stages
this project, we had a very simple and naïve method of moving this paddle. We also wanted to have a main menu where the player could chose an option by pressing diferent arrow
keys and the 'Enter' key.

**The Pattern**

To solve this issue, we applied the Observer pattern, where we created a keyboard observer in our class 'Game' whose job is to observe and be notified when the keys of the 
keyboard are being pressed. We also set a listener on our 'State' classes, which is the same keyboard observer from the game. In this case, the listener will have an
action associated to it, which determines what will happen in the game (play the game, read the instructions, quit the gane...) 

**Implementation**

![]()

The following link shows how the pattern was introduced in our code:

- [KeyBoardObserver](https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g0601/blob/master/src/main/java/com/ldts/breakout/gui/KeyBoardObserver.java)
- [LaternaGUI](https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g0601/blob/master/src/main/java/com/ldts/breakout/gui/LanternaGUI.java)
- [Game](https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g0601/blob/master/src/main/java/com/ldts/breakout/Game.java)
- [KeyBoardListener](https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g0601/blob/master/src/main/java/com/ldts/breakout/state/KeyBoardListener.java)
- [PlayingState](https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g0601/blob/master/src/main/java/com/ldts/breakout/state/PlayingState.java) (an example)

**Consequences**

The use of the Observer pattern in the current design allows us to have the following consequences:
- Now when we press a key, the classes that have features that depend on the keyboard won't keep asking, in this case, the class 'Game' if a key was pressed. Using the
keyboard observer and listener, the classes will be notified when a key is pressed.

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
- **Brick**
- **Button**
- **Arena**

Even though they help us organize our code, these classes don't do anything special, they're basically data containers 
for other classes, due to the fact of them not having any additional functionality.

A way to solve this problematic situation could be the implementation of the **Move Method**, where we include, for example, the points or the player lives system in the Arena
class.

**Duplicate Code**

In the 'Controller' classes that have a non-empty list of buttons, we introduced a method called 'getActiveButton()', which, as the name already states, will return the
index of the active button in class' buttons list. However, this method is practically identical in every class that it is implemented.

In this case, to solve this problem, we need to apply the **Extract Method**, followed by the **Pull up Method**, where we move the 'getActiveButton()' method
into the the 'GameController' abstract class.

#### BLOATERS

**Long Methods**

After restructuring our project, we've come to notice that the size of our 'keyPressed' methods are quite big, since they're only made up of if-elses/switch cases that
consider all of the possible actions or used keyboard keys in the game.

With this in mind, in order to minimize the size of these methods, we could apply the **Extract Method**, where we seperate many of their if-elses/switch cases into diferent
components and other methods/classes.

**Primitive Obsession**

At the start of this project, we were determined to use the minimum number of scattered values as possible. This way we used constants to have, through out all of our code,
values that stay constant and aren't modifiable. However, the number of constants used are quite high and many are defined by using other constants.

So, to solve this problem, we can only define the important constants and utilize the rest in all of their respective classes.

### TESTING

#### COVERAGE REPORT
![](https://i.imgur.com/OzsLgL2.png)
- Controller

![](https://i.imgur.com/v7aZDyN.png)

- GUI

![](https://i.imgur.com/LHqpE6I.png)

- Model

![](https://i.imgur.com/AFJq8HQ.png)
![](https://i.imgur.com/G0l4xP9.png)
![](https://i.imgur.com/LcJfCvn.png)

- State

![](https://i.imgur.com/XwwE5FR.png)

- Viewer

![](https://i.imgur.com/8MgzKng.png)
![](https://i.imgur.com/9Wxuj3O.png)

#### MUTATION TEST
You can check this [link](https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g0601/tree/master/src/test/pitest/202201221447) for the mutation test.

### SELF-EVALUATION

All of the members participated equally in the process of making this project, giving it their best to try and develop a high quality game. Everyone collaborated by creating
classes, implementing methods or even doing commits.

- **António Ferreira** - 34%
- **João Maldonado** - 33%
- **Tomás Gomes** - 33%

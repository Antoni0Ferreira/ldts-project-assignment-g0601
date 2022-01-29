# ldts-project-assignment-g0601

In this remastering of the classic game of 1976, "Breakout", there'll be bricks aligned on the top of the board and your job is to destroy all of them. In order to do that,
you need to try to hit the bricks with a constantly moving ball, by controlling a paddle. 

This project was developed by António Ferreira (up202004735@fe.up.pt), João Maldonado (up202004244@fe.up.pt) and Tomás Gomes (up202004393@fe.up.pt) for LDTS 2021/2022.

### IMPLEMENTED FEATURES 

- **Moving Paddle** - The paddle will move to the left/right when the left/right arrow key is pressed.

![](https://i.imgur.com/pdbCog1.gif)

- **Constantly Moving Ball** - The ball will have an autonomous and continuous movement.

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
different levels or changes in the ball's speed.

### DESIGN  

At this stage of the project, we have implemented 6 different design patterns:

-------

#### CREATING SIMILAR CLASSES

**Problem in Context**

In this project, we felt the need to create different objects, but with a lot in common, leading to many methods
that do the same. 

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

![](https://i.imgur.com/CjqlSWB.png)

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
the method that made the ball constantly move was getting mixed up with the method that allowed the paddle to move).

**The Pattern**

This way, we came to the conclusion that we should restructure our classes, and overall arquitecture of our project, and apply the Model-View-Controller (MVC) pattern, 
in order  to divide our project into 3 different main parts for a better organization of our code.

**Implementation**

![](https://i.imgur.com/PvKetih.png)

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
of this project, we had a very simple and naïve method of moving this paddle. On the other hand, we also wanted to have a main menu where the player could chose an option 
by pressing  diferent arrow keys and the 'Enter' key.

**The Pattern**

To solve this issue, we applied the Observer pattern, where we created a keyboard observer in our class 'Game' whose job is to observe and be notified when the keys of the 
keyboard are being pressed. We also set a listener on our 'State' classes, which is the same keyboard observer from the game. In this case, the listener will have an
action associated to it, which determines what will happen in the game (play the game, read the instructions, quit the gane...) 

**Implementation**

![](https://i.imgur.com/pMQK2Eu.png)

The following links shows how the pattern was introduced in our code:

- [KeyBoardObserver](https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g0601/blob/master/src/main/java/com/ldts/breakout/gui/KeyBoardObserver.java)
- [LaternaGUI](https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g0601/blob/master/src/main/java/com/ldts/breakout/gui/LanternaGUI.java)
- [Game](https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g0601/blob/master/src/main/java/com/ldts/breakout/Game.java)
- [KeyBoardListener](https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g0601/blob/master/src/main/java/com/ldts/breakout/state/KeyBoardListener.java)
- [PlayingState](https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g0601/blob/master/src/main/java/com/ldts/breakout/state/PlayingState.java) (an example)

**Consequences**

The use of the Observer pattern in the current design allows us to have the following consequences:
- Now when we press a key, the classes that have features that depend on the keyboard won't keep asking, in this case, the class 'Game', if a key was pressed. Using the
keyboard observer and listener, the classes will be notified when a key is pressed. 
In other words, we have an abstract coupling between a subject (Keyboard) and an observer (Game).

#### HAVING A MAIN MENU/DIFFERENT SCREENS

**Problem in Context**

When we normally load up a game, it usually starts on a menu where you can do many things. That being said,
the team decided to introduce this concept and let the player start the game in a main menu, that lets him read the instructions, play the game or simply quit. We also wanted
to print a simple message when the game ended, letting the player know if he had lost or won the game, and introduce a pause menu, allowing the user to stop the game.

**The Pattern**

The option we had for this concept was to implement the State pattern. For all of the possible states we created different classes, which extend an abstract class called
'GameState'. These states represent different moments in the game (we have classes like 'MenuState', 'PlayingState', 'PauseState', etc.).

**Implementation**

![](https://i.imgur.com/g2c2V3k.png)

The following link shows how the pattern was introduced in our code:

- [GameState](https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g0601/blob/master/src/main/java/com/ldts/breakout/state/GameState.java)
- [MenuState](https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g0601/blob/master/src/main/java/com/ldts/breakout/state/MenuState.java)
- [PlayingState](https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g0601/blob/master/src/main/java/com/ldts/breakout/state/PlayingState.java)
- [PauseState](https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g0601/blob/master/src/main/java/com/ldts/breakout/state/PlayingState.java)
- [InstructionsState](https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g0601/blob/master/src/main/java/com/ldts/breakout/state/InstructionsState.java)
- [EndGameState](https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g0601/blob/master/src/main/java/com/ldts/breakout/state/EndGameState.java)

**Consequences**

The use of the State pattern in the current design leads us in to have the following consequences:
- Bigger number of classes
- Depending on the context, we'll have an active state that leads the program to only focus on the methods related to the state in question
- The localization and partitioning behavior for the different states.

#### CHANGING FROM ONE STATE TO ANOTHER

**Problem in Context**

When we decided to introduce a menu to our game, we had the idea to implement buttons, 
which have the main job of letting the player travel from the main menu into the actual game, for example.

**The Pattern**

In order to do this task, we implemented the Command Pattern, where for every button created we associated a Menu Button Command that can be executed with the pressing of a
specific keyboard key (like the 'Enter' key). When executed, the command in question will change the current state into another.

**Implementation**

![](https://i.imgur.com/SeKmyGz.png)

The following link shows how the pattern was introduced in our code:

- [Command](https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g0601/tree/master/src/main/java/com/ldts/breakout/model/command)
- [MenuButtonCommand](https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g0601/blob/master/src/main/java/com/ldts/breakout/model/command/MenuButtonCommand.java)
- [Button](https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g0601/blob/master/src/main/java/com/ldts/breakout/model/Button.java)

**Consequences**

The use of the Command pattern in the current design allows us to have the following consequences:
- With this pattern, we can transition between states, allowing our game the flow properly.
- The commands can be extended and manipulated like any other object. An example of this is that these command are parameters of the Button constructor
- To add a new command is fairly easy.

### KNOWN CODE SMELLS AND REFACTORING SUGGESTIONS

Although, during this project, we came across many different code smells, there's the need to point out that some weren't refactored in the 
final version of the code, since we couldn't find any solution for them.

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
index of the active button in class' buttons list. However, this method is practically identical in every class that is implemented.

In this case, to solve this problem, we need to apply **Pull up Method**, where we move the 'getActiveButton()' method
into the the 'GameController' abstract class.

#### BLOATERS

**Long Methods**

After restructuring our project, we've come to notice that the size of our 'keyPressed' methods are quite big, since they're only made up of if-elses/switch cases that
consider all of the possible actions or used keyboard keys in the game.

With this in mind, in order to minimize the size of these methods, we could apply the **Extract Method**, where we seperate many of their if-elses/switch cases into diferent
newo methods.

**Primitive Obsession**

At the start of this project, we were determined to use the minimum number of scattered values as possible. This way we used constants to have, through out all of our code,
values that stay constant and aren't modifiable. However, the number of constants used are quite high and many are defined by using other constants.

So, to solve this problem, we can only define the important constants and utilize the rest in all of their respective classes.

#### COUPLERS

**Message Chains**

Once we implemented the MCV pattern, we realized that we introduced a frequent message chain between our State, Controller and Viewer classes (for example, MenuState.step() ->
MenuController.step() -> menuViewer.draw()).

That being taken into consideration, to solve this code smell, we could implement the **Hide Delegate** method, where we create, in our Controller classes, for example,
a method that delegates the call to our Viewer objects.

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

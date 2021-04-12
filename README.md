# Autonomous Computer-controlled Game Characters

## Game Design
In my application, I create the ghosts in GameModel through the 
addGameCharacters () method. This creates instances of all the ghosts and sets 
their health and sharpness as local variables within the instances. Then the 
Call () method of characterTask calls the checkDistance () method to get the 
distance between the player and ghost to give it to ghostProcess (). In 
ghostProcess () the health, sharpness and distance are passed into either the 
neural network or the fuzzy logic network to decide if the ghost should run or 
attack.

I also display the players health in the application window so you can easily see 
the player healing and getting damaged along side with the game controls.

## Interactions
In my application, I have a class called Interactions that handles all interactions 
between the player and the enemies. It is a utility class that calculates and 
returns values based on the player and the ghost’s values.
The main function in this class is CheckDistance(). It takes in the player and the 
ghost’s position and a ghost object. Using these it decides whether to attack or 
heal. This class is called from the call () method in character task if the ghost is
alive.

## Neural Network Ghost and FuzzyLogic Ghost
These two classes are quite like each other except one uses fuzzy logic and the 
other uses a neural network to decide what to do based on the health, 
sharpness, and the players position in relation to the ghost. They also have 
smaller methods such as heal (), takeDamage () and other getters and setters.My Neural Network
My neural network uses a sigmoid activator and has 3 inputs, 2 hidden layers 
and 2 output layers

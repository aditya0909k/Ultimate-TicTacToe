<img width="544" alt="Screen Shot 2022-04-30 at 11 41 15 PM" src="https://user-images.githubusercontent.com/85547196/166133164-a5e9db9c-e85b-42e1-a2fd-d1786c5129d6.png">

This is a working version of the Ultimate Tic-Tac-Toe Game, where the TTT game consists of 9 individual boards.

In this game, I implemented a version of the Ultimate TTT Game using an array of 9 boards. 

These boards are the type of IBoard, where it could be referred to as "Interface Board", meaning that the Ultimate TTT Game can accept any type of board that the user would want to use, whether it be a one dimensional array of an object, two dimensional array of characters, or anything else. In terms of the IBoard that UltimateTTT uses by default, we will be using the Board class which consists of an array of Boxes. Each box essentially acts like a String/Character, but with a few modifications such as variables indicating the row and column, and ease of availability checking that is coded into the isAvailable method of the Box class. In our demo, the IBoard could also accept OtherBoardWrapper, which is a wrapper class for OtherBoard, which is implemented as a 2D character array. The OtherBoard is in the project used to demonstrate that the game works using the IBoard interface, rather than only accepting the Board.

I also made it so that the game accepts a player, called APlayer. This abstract type can be extended by any child, like a computer player, human player, or any other type of player possible. By default, our TTT game will set the players to computers, which will make moves automatically through generated integers in the range of rowSize\*colSize of the game using Math.Random. However in the case that a user would like to play, a setPlayers method can be used to set either both or one player as a human, so that the user can participate in the game.  

The main logic of the Ultimate TTT Game relies on checking through the Ultimate Board and its individual boards. The logic for the Ultimate Board is coded specifially towards itself, whereas the logic for the IBoard array is coded towards the IBoard type.

Specific things about my project that I am proud about are the fact that I made the UltimateTTT Game to work with IBoard, rather than simply going with Board. The project requirements don't detail that we must use an interface board type, but I went the extra step to do that in order to have the effect of this being a product that could be shipped to a customer. I did the same thing in terms of printing my board, since I could have stopped at making sure it  printed correctly at only a 3x3 ultimate board. Rather, I made sure that the ultimate board can be printed for any rowSize\*colSize, however in most cases this will never be used since playing a 4x4 game with 4x4 boards or more would be very time consuming and most likely end up in a tie game... Things I know I didn't implement was having the logic of the ultimate board adaptable to being a one dimensional array of IBoards, two dimensional array of IBoards, or more. Currently, at this state, the UltimateTTT Game will only accept a one dimensional array of IBoards.

What I learned through this game was the importance of abstract types. The use of interface IBoard and abstract APlayer makes the UltimateTTT game an actual game, rather than something that is hardcoded. The use of abstract types allows us to control elements of the game without actually changing anything regarding the code within the game itself, which is how it should be made if this was a product being shipped to a customer. I think that learning how software engineering concepts apply to the real world of software development was critical knowledge I gained by working through this project alongside the original TTT activites we did. Furthermore, the use of the IBoard interface forced me to think about how I coded my logic solutions to the game. Originally, I had coded the check row/col/diagonal methods specifially towards the Board class, that uses a one dimensional array. Once I tried to use the two dimensional character array introduced by OtherBoard, I quickly realized that hardcoding towards a specific type isn't a viable option when creating a project that could be used in the real world. Through this, I learned that not only must I make the standard methods applicable to multiple types, but the logic as well should be code that is generalized.

If I were to redo this game from scratch, I think I would do a couple things differently. For one, I would definitely draw my UML diagram BEFORE I coded the project, as I think outlining how the project should work would make coding it much easier. Additionally, I would have made the ultimate board's check row/col/diagonal methods generalized towards any type of ultimate board, such as a two dimensional array and one dimensional array. Overall, I feel as though I've taken the right steps towards making my game fully functioning, and there isn't much I would do differently other than have a complete idea of how my parts of my game, such as the board and player, would interact before actually coding it.

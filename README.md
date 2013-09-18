scrabblah
=========

Scrabblah, a Scrabble Clone - UAB CIS466 Games Seminar

Design Goals:  
The game logic shall be totally encapsulated away from the UI.  
There shall be 2 to 4 players.  
One user shall be able to play against AI players.  
One user shall be able to play against another user on the same terminal.  
The game rules shall mimic Scrabble in "most" ways.  
  
Order of Play:  
1) 1 or 2 (or 3 or 4) players?  
2) Determine who goes first  
3) Players choose tiles at random  
4) Players take turns until the game ends  
  
A turn consists of the following actions:  
A) Pass  
	1) Player does not place tiles  
	2) Turn Ends  
  
B) Swap   
	1) Player removes n tiles from his rack  
	2) Player draws n tiles from the bag  
	3) Turn Ends  
  
C) Play a Word  
	1) Player places tiles on the gameBoard
	2) Player submits current placement of tiles  
	3) Game checks for valid tile placement & valid words  
	4) Score is computed and added to Player's score  
	5) Player replenishes tiles  
	6) Endgame Check  
	7) Turn Ends  
  
D) Resign  
	1) Player Quits  
	2) Game Ends  

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package reversi;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 *
 * @author eral
 */



public class Board {
        
    int heuristic=0;
    int depth=0;
    
	public final byte size = 8;
    Cell[][] BoardA2D = new Cell[8][8] ;
	enum directionEnuType {
							right, upperright, up, upperleft, left, downleft, down, downright
						  };
						  
	directionEnuType directionEnu;
	enum playerEnuType { black, white};
        playerEnuType playerEnum;
	
	boolean playerTurn = false;     //false= black,  true = white
	
	Board() {
            //[[# set initial board configuration
		BoardA2D = new Cell [size][size];
		
		for(int i = 0 ; i < size; i++)	{
			for (int j = 0; j < size; j++) {
				BoardA2D [i][j] = new Cell();
				BoardA2D [i][j]. rowIndex = i;
				BoardA2D [i][j]. colIndex = j;
			}
		}
		BoardA2D [3][3].cellEnu =  1; // white
		BoardA2D [3][4].cellEnu = -1; // black
		BoardA2D [4][3].cellEnu = -1; // black
		BoardA2D [4][4].cellEnu =  1; // white
	}
	
        
        public Board(Board copied)     // copy constructor         
        {      
          
          for (int i=0;i<8;i++){
              for (int j=0; j<8;j++){
                  BoardA2D[i][j] = new Cell(copied.BoardA2D[i][j]);
              }
		  }
		  
          depth = copied.depth;
          heuristic = copied.heuristic;
          directionEnu=copied.directionEnu;
          playerEnum=copied.playerEnum;
          playerTurn=copied.playerTurn;
        }
	//------------------------------------------------------------------------------------
	boolean isValidCoord(int a) {
		return a >=0 && a < 8;
	 }
	 
	//-------------------------------------------------------------------------------------	
	public Cell getNeighbour(Cell currentCell, directionEnuType directionEnu){
	
            //[[## this method returns neighbour of the input cell in the specified direction , if exist 
	
		switch(directionEnu)
		{
			case right:
				if(isValidCoord(currentCell.colIndex + 1))
					return BoardA2D [currentCell.rowIndex][currentCell.colIndex + 1];
				else 
					return null;
				
			case upperright:
			if(isValidCoord(currentCell.rowIndex - 1) && isValidCoord(currentCell.colIndex + 1))
					return BoardA2D [currentCell.rowIndex - 1][currentCell.colIndex + 1];
				else 
					return null;
			
			case up:
				if(isValidCoord(currentCell.rowIndex - 1))
					return BoardA2D [currentCell.rowIndex -1][currentCell.colIndex];
				else 
					return null;
			
			case upperleft:
				if(isValidCoord(currentCell.rowIndex - 1) && isValidCoord(currentCell.colIndex - 1))
					return BoardA2D [currentCell.rowIndex - 1][currentCell.colIndex - 1];
				else 
					return null;
				
			
			case left:
				if(isValidCoord(currentCell.colIndex - 1))
					return BoardA2D [currentCell.rowIndex][currentCell.colIndex-1];
				else 
					return null;
			
			case downleft:
				if(isValidCoord(currentCell.rowIndex + 1) && isValidCoord(currentCell.colIndex - 1))
					return BoardA2D [currentCell.rowIndex + 1][currentCell.colIndex - 1];
				else 
					return null;
			
			case down:
				if(isValidCoord(currentCell.rowIndex + 1) && isValidCoord(currentCell.colIndex ))
					return BoardA2D [currentCell.rowIndex + 1][currentCell.colIndex ];
				else 
					return null;
			
			case downright:
				if(isValidCoord(currentCell.rowIndex + 1) && isValidCoord(currentCell.colIndex + 1))
					return BoardA2D [currentCell.rowIndex + 1][currentCell.colIndex + 1];
				else 
					return null;
			
			default:
				return null;
		}
	}
	
	//----------------------------------------------------------------------------------------
	public void putDisk(int rowIndex, int colIndex, /*playerEnuType playerEnu*/ int playerturn) {
	
		if(playerturn == 0)
			BoardA2D [rowIndex][colIndex].cellEnu = -1; // black
		else
			BoardA2D [rowIndex][colIndex].cellEnu =  1; // white
	}
	
	//----------------------------------------------------------------------------------------
	public void flipBetween(Cell A, Cell B) {
		
            //[[# given two cells of same color, this method flips all cells between.
            // note all the cell between must be of opposite color, thus before calling this method
            // one should callthe method  isBetweenAllenemy() and if yes , should call this function
		
		// A, B vertical
		if(A.colIndex == B.colIndex) {
			for(int i = Math.min(A.rowIndex,B.rowIndex); i < Math.max(A.rowIndex,B.rowIndex) -1 ; i++) {
				BoardA2D [i +1][A.colIndex].flip();
			}
		}
		// A,B horizantal
		else if(A.rowIndex == B.rowIndex) {
			for(int j = Math.min(A.colIndex,B.colIndex); j < Math.max(A.colIndex,B.colIndex)-1; j++) {
				BoardA2D [A.rowIndex][j + 1].flip();
			}
		}
		// A,B diagonal
		else if(Math.abs(A.rowIndex - B.rowIndex) == Math.abs(A.colIndex - B.colIndex)) {
			
			int min_rowIndex = Math.min(A.rowIndex, B.rowIndex);
			int max_rowIndex = Math.max(A.rowIndex, B.rowIndex);
			int min_colIndex = Math.min(A.colIndex,B.colIndex);
			int max_colIndex = Math.max(A.colIndex,B.colIndex);
			int slope = (A.rowIndex - B.rowIndex)/(A.colIndex - B.colIndex);
			
			for(int i = 1; i < max_colIndex - min_colIndex; i++) { 
				if(slope == 1) {
					BoardA2D [max_rowIndex- i][max_colIndex - i].flip();
				}
				else if(slope == -1) {
					BoardA2D [min_rowIndex + i][max_colIndex - i].flip();
				}
			}
		}
	}	
	
	//----------------------------------------------------------------------------------------------
	public void printBoard(Cell [][] pb) {
		
		System.out.printf("\n");
		for(int i = 0; i<size; i++) {
			for(int j = 0; j<size; j++) {
                            if (pb [i][j].cellEnu == 0)
				System.out.printf("00   ");
                            else if (pb [i][j].cellEnu == -1)
				System.out.printf("-1   ");
                            else if (pb [i][j].cellEnu == 1)
				System.out.printf("+1   ");
			}
			System.out.printf("\n");
		}
		System.out.printf("\n");
	}
	
	//---------------------------------------------------------------------------------------------
	public boolean isBetweenAllEnemy(Cell A, Cell B) {
	
		boolean retval = true;

		// A, B vertical
		if(A.colIndex == B.colIndex) {
			for(int i = Math.min(A.rowIndex,B.rowIndex); i < Math.max(A.rowIndex,B.rowIndex)-1; i++) {
				if(BoardA2D [i +1][A.colIndex].cellEnu == A.cellEnu || BoardA2D [i +1][A.colIndex].cellEnu == 0 ){
					retval = false;
					break;
				}
			}
                        if(Math.abs(A.rowIndex -B.rowIndex) ==1) {
                            retval = false;
                            
                        }
		}
		// A,B horizantal
		else if(A.rowIndex == B.rowIndex) {
			for(int j = Math.min(A.colIndex,B.colIndex); j < Math.max(A.colIndex,B.colIndex)-1; j++) {
				if(BoardA2D [A.rowIndex][j + 1].cellEnu == A.cellEnu || BoardA2D [A.rowIndex][j + 1].cellEnu == 0) {
					retval = false;
					break;
				}
			}
                        
                        if(Math.abs(A.colIndex -B.colIndex) ==1) {
                            retval = false;
                            
                        }
		}
		// A,B diagonal
		else if(Math.abs(A.rowIndex - B.rowIndex) == Math.abs(A.colIndex - B.colIndex)) {
			
			int min_rowIndex = Math.min(A.rowIndex, B.rowIndex);
			int max_rowIndex = Math.max(A.rowIndex, B.rowIndex);
			int min_colIndex = Math.min(A.colIndex,B.colIndex);
			int max_colIndex = Math.max(A.colIndex,B.colIndex);
			int slope = (A.rowIndex - B.rowIndex)/(A.colIndex - B.colIndex);
			
			for(int i = 1; i < max_colIndex - min_colIndex; i++) { 
				if(slope == 1) {
					if(BoardA2D [min_rowIndex + i][min_colIndex + i].cellEnu == A.cellEnu 
						|| BoardA2D [min_rowIndex + i][min_colIndex + i].cellEnu == 0) {
						
							retval = false;
							break;
					}
				}
				else if(slope == -1) {
					if(BoardA2D [min_rowIndex + i][max_colIndex - i].cellEnu == A.cellEnu
						|| BoardA2D [min_rowIndex + i][max_colIndex - i].cellEnu == 0)	{
						
						retval = false;
						break;
					}
				}
			}
                        if(Math.abs(A.colIndex -B.colIndex) ==1) {
                            retval = false;
                            
                        }
		}
		
		return retval;
	}
	public Cell findClosestFriend(Cell currentCell, directionEnuType directionEnu) {
	
            //[[# Given any cell of any of two color(black or white), this method returns the 
            //closest cell of the same color with input cell in the specified direction
	
		switch(directionEnu)
		{
			case right :
				if(currentCell.cellEnu == -1) { // black
					while(getNeighbour(currentCell,directionEnu.right) != null
						&&getNeighbour(currentCell,directionEnu.right).cellEnu != -1) {
					currentCell = getNeighbour(currentCell,directionEnu.right);
					}
				}
				else if(BoardA2D [currentCell.rowIndex][currentCell.colIndex].cellEnu == 1) { // white
					while(getNeighbour(currentCell,directionEnu.right) != null
						&&getNeighbour(currentCell,directionEnu.right).cellEnu != 1) {
					currentCell = getNeighbour(currentCell,directionEnu.right);
					}
				}
				currentCell = getNeighbour(currentCell,directionEnu.right);
				break;
			
				case upperright :
				if(currentCell.cellEnu == -1) { // black
					while(getNeighbour(currentCell,directionEnu.upperright) != null
						&& getNeighbour(currentCell,directionEnu.upperright).cellEnu != -1) {
					currentCell = getNeighbour(currentCell,directionEnu.upperright);
					}
				}
				else if(currentCell.cellEnu == 1) { // white
					while(getNeighbour(currentCell,directionEnu.upperright) != null
						&& getNeighbour(currentCell,directionEnu.upperright).cellEnu != 1) {
					currentCell = getNeighbour(currentCell,directionEnu.upperright);
					}
				}
				currentCell = getNeighbour(currentCell,directionEnu.upperright);
				break;
				
				case up :
				if(currentCell.cellEnu == -1) { // black
					while(getNeighbour(currentCell,directionEnu.up) != null
						&& getNeighbour(currentCell,directionEnu.up).cellEnu != -1) {
					currentCell = getNeighbour(currentCell,directionEnu.up);
					}
				}
				else if(currentCell.cellEnu == 1) { // white
					while(getNeighbour(currentCell,directionEnu.up) != null
						&& getNeighbour(currentCell,directionEnu.up).cellEnu != 1) {
					currentCell = getNeighbour(currentCell,directionEnu.up);
					}
				}
				currentCell = getNeighbour(currentCell,directionEnu.up);
				break;
				
				case upperleft :
				if(currentCell.cellEnu == -1) { // black
					while(getNeighbour(currentCell,directionEnu.upperleft) != null
						&& getNeighbour(currentCell,directionEnu.upperleft).cellEnu != -1) {
					currentCell = getNeighbour(currentCell,directionEnu.upperleft);
					}
				}
				else if(currentCell.cellEnu == 1) { // white
					while(getNeighbour(currentCell,directionEnu.upperleft) != null
						&& getNeighbour(currentCell,directionEnu.upperleft).cellEnu != 1) {
					currentCell = getNeighbour(currentCell,directionEnu.upperleft);
					}
				}
				currentCell = getNeighbour(currentCell,directionEnu.upperleft);
				break;
				
				case left :
				if(currentCell.cellEnu == -1) { // black
					while(getNeighbour(currentCell,directionEnu.left) != null
						&& getNeighbour(currentCell,directionEnu.left).cellEnu != -1) {
					currentCell = getNeighbour(currentCell,directionEnu.left);
					}
				}
				else if(currentCell.cellEnu == 1) { // white
					while(getNeighbour(currentCell,directionEnu.left) != null
						&& getNeighbour(currentCell,directionEnu.left).cellEnu != 1) {
					currentCell = getNeighbour(currentCell,directionEnu.left);
					}
				}
				currentCell = getNeighbour(currentCell,directionEnu.left);
				break;
				
				case downleft :
				if(currentCell.cellEnu == -1) { // black
					while(getNeighbour(currentCell,directionEnu.downleft) != null
						&& getNeighbour(currentCell,directionEnu.downleft).cellEnu != -1) {
					currentCell = getNeighbour(currentCell,directionEnu.downleft);
					}
				}
				else if(currentCell.cellEnu == 1) { // white
					while(getNeighbour(currentCell,directionEnu.downleft) != null
						&& getNeighbour(currentCell,directionEnu.downleft).cellEnu != 1) {
					currentCell = getNeighbour(currentCell,directionEnu.downleft);
					}
				}
				currentCell = getNeighbour(currentCell,directionEnu.downleft);
				break;
				
				case down :
				if(currentCell.cellEnu == -1) { // black
					while(getNeighbour(currentCell,directionEnu.down) != null
						&& getNeighbour(currentCell,directionEnu.down).cellEnu != -1) {
					currentCell = getNeighbour(currentCell,directionEnu.down);
					}
				}
				else if(currentCell.cellEnu == 1) { // white
					while(getNeighbour(currentCell,directionEnu.down) != null
						&& getNeighbour(currentCell,directionEnu.down).cellEnu != 1) {
					currentCell = getNeighbour(currentCell,directionEnu.down);
					}
				}
				currentCell = getNeighbour(currentCell,directionEnu.down);
				break;
				
				case downright :
				if(currentCell.cellEnu == -1) { // black
					while(getNeighbour(currentCell,directionEnu.downright) != null
						&& getNeighbour(currentCell,directionEnu.downright).cellEnu != -1) {
					currentCell = getNeighbour(currentCell,directionEnu.downright);
					}
				}
				else if(currentCell.cellEnu == 1) { // white
					while(getNeighbour(currentCell,directionEnu.downright) != null
						&& getNeighbour(currentCell,directionEnu.downright).cellEnu != 1) {
					currentCell = getNeighbour(currentCell, directionEnu.downright);
					}
				}
				currentCell = getNeighbour(currentCell,directionEnu.downright);
				break;
				
				default:
				break;
		}
		
		return currentCell;
	}
	
	public void markCandidates(boolean turn)  {
	
            //[[## given player turn, marks possible empty cells for putting
            // the disc using current layout of the game area(state)- BoardA2D
	
		if(turn==false) {    //black player
			for(int i = 0; i < size; i++) {
				for(int j = 0; j < size; j++) {
					if(BoardA2D [i][j].cellEnu == 0) {
						BoardA2D [i][j].cellEnu = -1; // black
						if(isPlaceble(BoardA2D [i][j])) {
							BoardA2D [i][j].candidateB00 = true;
						}
                                                else    BoardA2D [i][j].candidateB00 = false;
						BoardA2D [i][j].cellEnu = 0;
					}
				}
			}
		}
		else if(turn==true) {   //white player
			for(int i = 0; i < size; i++) {
				for(int j = 0; j < size; j++) {
					if(BoardA2D [i][j].cellEnu == 0) {
						BoardA2D [i][j].cellEnu = 1; // white
						if(isPlaceble(BoardA2D [i][j])) {
							BoardA2D [i][j].candidateB00 = true;
						}
                                                else    BoardA2D [i][j].candidateB00 = false;
						BoardA2D [i][j].cellEnu = 0;
					}
				}
			}
		}
	}
	
	boolean isPlaceble(Cell C) {
            
        //[[# given empty input cell, this method determines if the given empty cell is
        // legal to put to disc by checking if putting disc there will result in flipping 
        // other cell(s) . Note that player turn is not important here, thus its caller routine
        // responsibility to check for player turn. 
        // This routine immediatelly returns true  when hits the flipping situation in any direction.
        // Returns false if there is no flips after checking all directions
		
		Cell tempC = null;
		if((tempC = findClosestFriend(C,directionEnu.right)) != null) {
			if(isBetweenAllEnemy(C,tempC)) {
				return true;
			}
		}
				
		if((tempC = findClosestFriend(C,directionEnu.upperright)) != null) {
			if(isBetweenAllEnemy(C,tempC)) {
				return true;
			}
		}
		
		if((tempC = findClosestFriend(C,directionEnu.up)) != null) {
			if(isBetweenAllEnemy(C,tempC)) {
				return true;
			}
		}
		
		if((tempC = findClosestFriend(C,directionEnu.upperleft)) != null) {
			if(isBetweenAllEnemy(C,tempC)) {
				return true;
			}
		}
		
		if((tempC = findClosestFriend(C,directionEnu.left)) != null) {
			if(isBetweenAllEnemy(C,tempC)) {
				return true;
			}
		}
		
		if((tempC = findClosestFriend(C,directionEnu.downleft)) != null) {
			if(isBetweenAllEnemy(C,tempC)) {
				return true;
			}
		}
		
		if((tempC = findClosestFriend(C,directionEnu.down)) != null) {
			if(isBetweenAllEnemy(C,tempC)) {
				return true;
			}
		}
		
		if((tempC = findClosestFriend(C,directionEnu.downright)) != null) {
			if(isBetweenAllEnemy(C,tempC)) {
				return true;
			}
		}
		
		return false;
	}
		
        ArrayList<Board> get_successors()  {
            
            //[[## this routine finds all possible next states of the game(i.e. board layout)
            // using the curent board layout and player turn as an input. It plays all possible candidates 
            // and stores resulting states in ArrayList then returns.
            
            ArrayList<Board> Successors = new ArrayList<Board>();
            Cell temp_cell = new Cell();
            Board temp_board = new Board(this);

            
            temp_board.markCandidates(temp_board.playerTurn);
            for(int i=0;i<size;i++)
            {
                for(int j=0; j<size;j++)
                {
                    if(temp_board.BoardA2D[i][j].candidateB00==true)
                    {
                        if(temp_board.playerTurn == false)
                            temp_board.BoardA2D[i][j].cellEnu = -1;
                        else
                            temp_board.BoardA2D[i][j].cellEnu = 1;
                        
                        temp_cell=temp_board.findClosestFriend(temp_board.BoardA2D[i][j], directionEnu.right);
                        if(temp_cell!=null)
                        {
                            if(temp_board.isBetweenAllEnemy(temp_board.BoardA2D[i][j], temp_cell))
                            {
                                temp_board.flipBetween(temp_board.BoardA2D[i][j], temp_cell);                                                             
                            }
                        }

                        temp_cell=temp_board.findClosestFriend(temp_board.BoardA2D[i][j], directionEnu.upperright);
                        if(temp_cell!=null)
                        {
							if(temp_board.isBetweenAllEnemy(temp_board.BoardA2D[i][j], temp_cell))
							{
								temp_board.flipBetween(temp_board.BoardA2D[i][j], temp_cell);                               
							}
                        }


                        temp_cell=temp_board.findClosestFriend(temp_board.BoardA2D[i][j], directionEnu.up);
                        if(temp_cell!=null)
                        {
                            if(temp_board.isBetweenAllEnemy(temp_board.BoardA2D[i][j], temp_cell))
                            {
                                temp_board.flipBetween(temp_board.BoardA2D[i][j], temp_cell);                               
                            }
                        }

                        temp_cell=temp_board.findClosestFriend(temp_board.BoardA2D[i][j], directionEnu.upperleft);
                        if(temp_cell!=null)
                        {
                            if(temp_board.isBetweenAllEnemy(temp_board.BoardA2D[i][j], temp_cell))
                            {
                                temp_board.flipBetween(temp_board.BoardA2D[i][j], temp_cell);                               
                            }
                        }

                        temp_cell=temp_board.findClosestFriend(temp_board.BoardA2D[i][j], directionEnu.left);
                        if(temp_cell!=null)
                        {
                            if(temp_board.isBetweenAllEnemy(temp_board.BoardA2D[i][j], temp_cell))
                            {
                                temp_board.flipBetween(temp_board.BoardA2D[i][j], temp_cell);                               
                            }
                        }

                        temp_cell=temp_board.findClosestFriend(temp_board.BoardA2D[i][j], directionEnu.downleft);
                        if(temp_cell!=null)
                        {
                            if(temp_board.isBetweenAllEnemy(temp_board.BoardA2D[i][j], temp_cell))
                            {
                                temp_board.flipBetween(temp_board.BoardA2D[i][j], temp_cell);                               
                            }
                        }

                        temp_cell=temp_board.findClosestFriend(temp_board.BoardA2D[i][j], directionEnu.down);
                        if(temp_cell!=null)
                        {
                            if(temp_board.isBetweenAllEnemy(temp_board.BoardA2D[i][j], temp_cell))
                            {
                                temp_board.flipBetween(temp_board.BoardA2D[i][j], temp_cell);                               
                            }
                        }

                        temp_cell=temp_board.findClosestFriend(temp_board.BoardA2D[i][j], directionEnu.downright);
                        if(temp_cell!=null)
                        {
                            if(temp_board.isBetweenAllEnemy(temp_board.BoardA2D[i][j], temp_cell))
                            {
                                temp_board.flipBetween(temp_board.BoardA2D[i][j], temp_cell);                               
                                                              
                            }
                        }
                        
                        temp_board.BoardA2D [i][j].candidateB00 = false;
                        // Check for player turn is changed
                        if (temp_board.hasLegalMoves(!(temp_board.playerTurn)))
                            temp_board.playerTurn=!(temp_board.playerTurn);
                        
                        temp_board.depth=temp_board.depth+1;                         
                        Successors.add(temp_board);
                        temp_board = new Board(this);
                        temp_board.markCandidates(temp_board.playerTurn);
                    }
                }
              }
            
            return Successors;
        }
        
        
        public void delete_candidates()
        {
             for (int i=0;i<8;i++)
            {
              for (int j=0; j<8;j++)
                { 
                    if(this.BoardA2D[i][j].candidateB00==true)
                        this.BoardA2D[i][j].candidateB00=false;
                }
            }
             
        }
       
        public boolean isGameOver()
        {
           //[[## game is over if both players have no placable cells to put disc

            boolean hasMoveBlack;
            boolean hasMoveWhite;
            // Check for legal moves of black
            hasMoveBlack = hasLegalMoves(false);            
            
            // Check for legal moves of white
            hasMoveWhite = hasLegalMoves(true); 
            
            if (!hasMoveBlack && !hasMoveWhite)
                return true;
            else 
                return false;            
        }
        
        public boolean hasLegalMoves(boolean playTurn)
        {
            // Check for legal moves of black
            this.markCandidates(playTurn);            
            for (int i=0;i<8;i++)
            { 
                for (int j=0; j<8;j++)
                { 
                   if(this.BoardA2D[i][j].candidateB00==true)
                   {
                       this.delete_candidates();
                       return true;
                   }                      
                }  
            }
            return false;
        }
        
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package reversi;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;

/**
 *
 * @author geral
 */
public class gamePlay {
    
    gamePlay(){}
    
    String gameHistory = "";
    boolean isOpeningB00 = true; 
    boolean humanTurnB00 = true;
    boolean comtocomPlayB00 = false;
	boolean playervscpuB00 = false; 
	boolean cpuvscpuB00 = false;
    Board gameBoard = new Board();
	Cell HumanCell = new Cell();
	
	void setHumanCell(int rowindex, int colindex){
		HumanCell.rowIndex = rowindex;
		HumanCell.colIndex = colindex;
	}

    
    public void forwardGameOneStep(){
        
        if(!comtocomPlayB00){
		
			if(humanTurnB00)
			{	
				playHumanMove(HumanCell);
			}
			else
			{
				getMiniMaxMoves();
			}
        }
        else
        {
            if(isOpeningB00)
			{  //[[# use opening methods at the game start
				getOpeningMoves();
			}
			else
			{ //[[# use minimax(alpha-beta)  at game proceeds
				getMiniMaxMoves();
			}
                  
        }
    }
	
    public void getOpeningMoves()	{   
	
		//[[# Method to find where to put disc at the beginning states  of the game by loooking  the opening loop up table,
		// taking opponent,s move into account. After opening strings (defined in openings.java) consumed, alpha-beta pruning
		// minimax strategy gets its turn at the rest of the game
	
        Random rand = new Random();
        int randomMove2;
        Cell openingCell = new Cell();
        String oneMoveString = "";
        openings possibleOpeningsA1D = new openings();
        possibleOpeningsA1D.defineOpenings();
        ArrayList<Board> sucStates = new ArrayList<Board>();	
        sucStates = this.gameBoard.get_successors();
        
        if(this.gameHistory.length() == 0)
        {	//[[# choose move to be taken at the game start
            int blackFirstMovesA1D[] = {0,49,98,147};           
            int randomMove = rand.nextInt(4);
            //this first move takes one of the four alternatives of black player, that is C4,D3,...
            oneMoveString = possibleOpeningsA1D.openingMovesArr[blackFirstMovesA1D[randomMove]].moveString.substring(0, 2);  
            openingCell = turnCodeToRow(oneMoveString);
            this.gameHistory = oneMoveString;
        }
        else
        {
            int possibleMovesArr[] = new int[196];
            int indexPoss = 0;
                        
            for (int j = 0; j<196; j++ )
            {
                if (this.gameHistory.length()<possibleOpeningsA1D.openingMovesArr[j].moveString.length())
                {
                    String tempString = possibleOpeningsA1D.openingMovesArr[j].moveString.substring(0,this.gameHistory.length());
                    if (tempString.matches(this.gameHistory))
                    {
                        possibleMovesArr[indexPoss] = j;
                        indexPoss++;
                    }
                }
            } 
            if(indexPoss == 0)
            {
                this.isOpeningB00 = false;
                oneMoveString="";
            }
            else if (indexPoss==1)
            {
                randomMove2 = 0;
                oneMoveString = possibleOpeningsA1D.openingMovesArr[possibleMovesArr[randomMove2]].moveString.substring(this.gameHistory.length(),this.gameHistory.length()+2);  
            }
            else
            { 
                randomMove2 = rand.nextInt(indexPoss-1);
                oneMoveString = possibleOpeningsA1D.openingMovesArr[possibleMovesArr[randomMove2]].moveString.substring(this.gameHistory.length(),this.gameHistory.length()+2);
            }
            if (oneMoveString.length() != 0)
            {
                openingCell = turnCodeToRow(oneMoveString);
                this.gameHistory += oneMoveString;         
            }
        }
        
        if (oneMoveString.length() != 0)
        {
            //[[#  To find the successor state resulting from our move according the opening we chose,
			// we simply check which of these states has the location occupied at the coordinate where we put the disc in prev state.
			
            for (int i=0; i<sucStates.size(); i++)
            {
                if(sucStates.get(i).BoardA2D[openingCell.rowIndex][openingCell.colIndex].cellEnu != 0)
                {
                    this.gameBoard = sucStates.get(i);
                    break;
                }
            }
        }
    }
	
    public void getMiniMaxMoves()
    {
		//[[# methods used at the proceeding states of the with different heuristics in action at different parts
		
        Board coord_history = new Board();
        minimax playMinimax = new minimax();
        int gameHeurVal = 0;
        int bestMoveIndex = 0;
        gameHeurVal = playMinimax.alphabeta(this.gameBoard,this.gameBoard.depth, 2, -10000, 10000, this.gameBoard.playerTurn);
        bestMoveIndex=playMinimax.moveCache.get(gameHeurVal);
        coord_history = this.gameBoard;
        this.gameBoard=this.gameBoard.get_successors().get(bestMoveIndex);
        compareBoards(coord_history,this.gameBoard);
        playMinimax.clearCache();
    }
    
    
    public void compareBoards(Board coord_history_s, Board gameBoard_s)
    {
        for(int i=0;i<8;i++)
        {
            for(int j=0;j<8;j++)
            {
                if(((coord_history_s.BoardA2D[i][j].cellEnu==0) && (gameBoard_s.BoardA2D[i][j].cellEnu!=0))
                        || (((coord_history_s.BoardA2D[i][j].cellEnu!=0) && (gameBoard_s.BoardA2D[i][j].cellEnu==0))))
                    turnToCode2(i,j);      
            }
        }
        
    }
    public void displayGameState()
    {
        this.gameBoard.printBoard(this.gameBoard.BoardA2D);
      
    }
    
    public void turnToCode2 (int i, int j)
    {
        String oneMoveString ="";
        String element1 ="";
        
        switch (j)
       {
           case 0: element1="A";
                     break;
           case 1: element1="B";
                     break;
           case 2: element1="C";
                     break;
           case 3: element1="D";
                     break;
           case 4: element1="E";
                     break;
           case 5: element1="F";
                     break;
           case 6: element1="G";
                     break;
           case 7: element1="H";
                     break;
           default : break; 
       }
        

        oneMoveString = element1 + Integer.toString(i+1);
        this.gameHistory=this.gameHistory+oneMoveString;
  
    }
    
    public Cell turnCodeToRow(String goneMoveString){
	
	   //[[# determine row and col index of a Cell having coordinates 
	   // of the form num-letter
	   
       Cell returnCell = new Cell();
       returnCell.rowIndex=Integer.parseInt(goneMoveString.substring(1, 2))-1;
       String rowLabelIndicator= goneMoveString.substring(0,1);
       
       switch (rowLabelIndicator)
       {
           case "A": returnCell.colIndex=0;
                     break;
           case "B": returnCell.colIndex=1;
                     break;
           case "C": returnCell.colIndex=2;
                     break;
           case "D": returnCell.colIndex=3;
                     break;
           case "E": returnCell.colIndex=4;
                     break;
           case "F": returnCell.colIndex=5;
                     break;
           case "G": returnCell.colIndex=6;
                     break;
           case "H": returnCell.colIndex=7;
                     break;
           default : break; 
       }
       return returnCell;
        
    }
	
	void playHumanMove(Cell hCell){
	
		ArrayList<Board> sucs = new ArrayList<Board>();
		sucs = this.gameBoard.get_successors();
	

		for (int i=0; i<sucs.size(); i++)
        {
            if(sucs.get(i).BoardA2D[hCell.rowIndex][hCell.colIndex].cellEnu != 0)
            {
                this.gameBoard = sucs.get(i);
                turnToCode2(hCell.rowIndex,hCell.colIndex);
                
                break;
            }
        }
	}
}

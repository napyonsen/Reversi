/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 
 /*
01 function alphabeta(node, depth, α, β, maximizingPlayer)
02      if depth = 0 or node is a terminal node
03          return the heuristic value of node
04      if maximizingPlayer
05          for each child of node
06              α := max(α, alphabeta(child, depth - 1, α, β, FALSE))
07              if β ≤ α
08                  break (* β cut-off *)
09          return α
10      else
11          for each child of node
12              β := min(β, alphabeta(child, depth - 1, α, β, TRUE))
13              if β ≤ α
14                  break (* α cut-off *)
15          return β
*/

package reversi;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author MUSTAFAERAL
 */
public class minimax 
{
	HashMap<Integer, Integer> moveCache = new HashMap<Integer, Integer>();        
	heuristic_methods hm = new heuristic_methods();

	public int alphabeta(Board inBoard,int boardDepth, int searchDepth, int inAlpha, int inBeta, boolean playerTurn)	
        {	
            ArrayList<Board> sucs = new ArrayList<Board>();	
            sucs = inBoard.get_successors();

            if(inBoard.playerTurn == playerTurn)	
            {	
                if(sucs.isEmpty() || searchDepth == 0)
                {
                   return hm.getWeightedHeuristic(inBoard, playerTurn);
                }

                for(int i = 0; i < sucs.size(); i++)	
                {
                    int tempAlpha = alphabeta(sucs.get(i),boardDepth,searchDepth-1, inAlpha, inBeta,playerTurn);
                    inAlpha = Math.max(tempAlpha, inAlpha);
                    
                    if (sucs.get(i).depth == boardDepth + 1)
                    {
                           if(!moveCache.containsKey(tempAlpha))  
                            moveCache.put(tempAlpha, i);
                    }
                                               
                    if(inBeta <= inAlpha)
                    break;
                }
                
                return inAlpha;
            }

            else 
            {
                if(sucs.isEmpty() || searchDepth == 0)
                {
                    return hm.getWeightedHeuristic(inBoard, playerTurn);
                }

                for(int i = 0; i < sucs.size(); i++)	
                {
                    inBeta = Math.min(alphabeta(sucs.get(i),boardDepth,searchDepth-1, inAlpha, inBeta,playerTurn), inBeta);
                    if(inBeta <= inAlpha)
                    break;
                }
                return inBeta;
            }
	}  
        
        public void clearCache()
        {
            moveCache.clear();
        }
}

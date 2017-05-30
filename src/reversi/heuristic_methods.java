/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package reversi;


/**
 *
 * @author eral
 */
public class heuristic_methods {
    
glbIntCls init_our_var = new glbIntCls();

int B_maxDiscWeightEarly = init_our_var.B_maxDiscWeightEarly;
int B_mobilityWeigthEarly = init_our_var.B_mobilityWeigthEarly;
int B_stableDiscWeightEarly =init_our_var.B_stableDiscWeightEarly;
int B_frontierWeightEarly = init_our_var.B_frontierWeightEarly;

int B_maxDiscWeightMid =init_our_var.B_maxDiscWeightMid;
int B_mobilityWeigthMid = init_our_var.B_mobilityWeigthMid;
int B_stableDiscWeightMid =  init_our_var.B_stableDiscWeightMid;
int B_frontierWeightMid = init_our_var.B_frontierWeightMid;

int B_maxDiscWeightEnd = init_our_var.B_maxDiscWeightEnd;
int B_mobilityWeigthEnd =init_our_var.B_mobilityWeigthEnd;
int B_stableDiscWeightEnd =init_our_var.B_stableDiscWeightEnd;
int B_frontierWeightEnd = init_our_var.B_frontierWeightEnd;


int W_maxDiscWeightEarly = init_our_var.W_maxDiscWeightEarly;
int W_mobilityWeigthEarly =init_our_var.W_mobilityWeigthEarly;
int W_stableDiscWeightEarly = init_our_var.W_stableDiscWeightEarly;
int W_frontierWeightEarly = init_our_var.W_frontierWeightEarly;

int W_maxDiscWeightMid = init_our_var.W_maxDiscWeightMid;
int W_mobilityWeigthMid = init_our_var.W_mobilityWeigthMid;
int W_stableDiscWeightMid = init_our_var.W_stableDiscWeightMid;
int W_frontierWeightMid = init_our_var.W_frontierWeightMid;

int W_maxDiscWeightEnd =init_our_var.W_maxDiscWeightEnd;
int W_mobilityWeigthEnd = init_our_var.W_mobilityWeigthEnd;
int W_stableDiscWeightEnd = init_our_var.W_stableDiscWeightEnd;
int W_frontierWeightEnd = init_our_var.W_frontierWeightEnd;
    
    Board.directionEnuType directionEnu;
    enum playerEnuType { black, white};
public  heuristic_methods()
{}
    
public int maximumDisc(Board b, boolean playTurn)
{
    //[[# Given input board and player turn, this methods returns 
    // 5 * #cells in players color
    
    int heurWhite=0;
    int heurBlack=0;

    for (int i=0;i<8;i++)
    {
        for (int j=0; j<8;j++)
        { 
            if (b.BoardA2D[i][j].cellEnu == 1)      // white disc
                heurWhite +=5;
            else if (b.BoardA2D[i][j].cellEnu == -1)
                heurBlack +=5;
        }
    }
    if (playTurn == false) //black
        return heurBlack;
    else
        return heurWhite;
}


public int mobility (Board b, boolean playTurn)  
{      
    //[[#  given input board and player turn, this method returns heuristic value as
    // (5) * # placable empty cells
    
    int retval = 0;
    
    b.markCandidates(playTurn);

    for (int i=0;i<8;i++)
    { 
        for (int j=0; j<8;j++)
        { 
           if(b.BoardA2D[i][j].candidateB00==true)
               retval += 5;
        }  
    }
    b.delete_candidates();
    
    return retval;
}

public int stableDisc (Board b, boolean playTurn)
{
    //[[# this function evaluates heuristics based on # of disc on the corners and
    // edges of the playing ares , given player turn . Having disc of the same color is
    // good while having disc on the corners is even better
    
    int heur_val=0;
    int cellEn=0;
    
    Cell badTopLeft[]={ new Cell ( 1 , 0 ) , new Cell ( 1 , 1 ) ,new Cell ( 0 , 1 )};
    Cell badTopRight[]={ new Cell ( 0 , 6 ) , new Cell ( 1 , 6 ) ,new Cell ( 1 , 7 )};
    Cell badDownRight[]={ new Cell ( 6 , 6 ) , new Cell ( 6 , 7 ) ,new Cell ( 7 , 6 )};
    Cell badDownLeft[]={ new Cell ( 6 , 0 ) , new Cell ( 6 , 1 ) ,new Cell ( 7 , 1 )};
    Cell Sides[]={new Cell ( 0 , 2 ) , new Cell ( 0 , 3 ) ,new Cell ( 0 , 4 ) , new Cell ( 0 , 5 ) , 
                  new Cell ( 7 , 2 ) , new Cell ( 7 , 3 ) ,new Cell ( 7 , 4 ) , new Cell ( 7 , 5 ) ,
                  new Cell ( 2 , 0 ) , new Cell ( 3 , 0 ) ,new Cell ( 4 , 0 ) , new Cell ( 5 , 0 ) , 
                  new Cell (2 , 7 ) , new Cell ( 3 , 7 ) ,new Cell ( 4 , 7 ) , new Cell ( 5 , 7 )};
    
    Cell Corners[]={new Cell(0,0), new Cell(0,7), new Cell(7,0), new Cell(7,7)};
    
    if (playTurn == false)
        cellEn=-1;
    else   
        cellEn=1;
    
   //[[# evaluate heuristics values at corners. Surrounded by color same with current player, if it
   // has same color then good, increase heuristic val by 10, else if empty cell then bad, decrease heurisitc val by 10
   // having a corner brings +20 points 
    
    for (int i=0; i<3; i++)
    {
        if((b.BoardA2D[0][0].cellEnu==0)&&(b.BoardA2D[badTopLeft[i].rowIndex][badTopLeft[i].colIndex].cellEnu==cellEn))   
            heur_val=heur_val-10;
        else if ((b.BoardA2D[0][0].cellEnu==cellEn)&&(b.BoardA2D[badTopLeft[i].rowIndex][badTopLeft[i].colIndex].cellEnu==cellEn)) 
            heur_val=heur_val+10;

        if((b.BoardA2D[0][7].cellEnu==0)&&(b.BoardA2D[badTopRight[i].rowIndex][badTopRight[i].colIndex].cellEnu==cellEn))   
            heur_val=heur_val-10; 
        else if ((b.BoardA2D[0][7].cellEnu==cellEn)&&(b.BoardA2D[badTopRight[i].rowIndex][badTopRight[i].colIndex].cellEnu==cellEn)) 
            heur_val=heur_val+10;

        if((b.BoardA2D[7][0].cellEnu==0)&&(b.BoardA2D[badDownLeft[i].rowIndex][badDownLeft[i].colIndex].cellEnu==cellEn))   
            heur_val=heur_val-10; 
        else if ((b.BoardA2D[7][0].cellEnu==cellEn)&&(b.BoardA2D[badDownLeft[i].rowIndex][badDownLeft[i].colIndex].cellEnu==cellEn)) 
            heur_val=heur_val+10;

        if((b.BoardA2D[7][7].cellEnu==0)&&(b.BoardA2D[badDownRight[i].rowIndex][badDownRight[i].colIndex].cellEnu==cellEn))   
            heur_val=heur_val-10;  
        else if ((b.BoardA2D[7][7].cellEnu==cellEn)&&(b.BoardA2D[badDownRight[i].rowIndex][badDownRight[i].colIndex].cellEnu==cellEn)) 
            heur_val=heur_val+10;
    
        if(b.BoardA2D[Corners[i].rowIndex][Corners[i].colIndex].cellEnu==cellEn)   
            heur_val=heur_val+20; 
    }
    
    //[[# calculate heuristic at sides (4*4)
    // having same color of player turn  brings +5 points
    for (int i=0; i<16; i++)
    {
        if(b.BoardA2D[Sides[i].rowIndex][Sides[i].colIndex].cellEnu==cellEn)   
            heur_val=heur_val+5; 
    }
    
   return heur_val; 
}

public int frontier (Board b, boolean playTurn)
{
    //[[# this method calculates heuristic considering cells which have empty neighbour
    // at any direction (frontier cell) . The less frontier cell the player has, the less
    // the player gives change to its opponent to flip its cells.
    
    int heur_val=0;
    int cellEn=0;
    
    if (playTurn == false)
        cellEn=-1;
    else   
        cellEn=1;
    
    for (int i=0;i<8;i++)
    { 
        for (int j=0; j<8;j++)
        { 
          if(b.BoardA2D[i][j].cellEnu == cellEn && isFrontier(b,b.BoardA2D[i][j]))
          {
              heur_val -=10;
          }
        }
    }
    return heur_val;
}

public boolean isFrontier(Board brd, Cell cll)
{
   if(brd.getNeighbour(brd.BoardA2D[cll.rowIndex][cll.colIndex],directionEnu.right) != null 
           &&(brd.getNeighbour(brd.BoardA2D[cll.rowIndex][cll.colIndex],directionEnu.right).cellEnu==0))
       return true;
   if(brd.getNeighbour(brd.BoardA2D[cll.rowIndex][cll.colIndex],directionEnu.upperright) != null 
           &&(brd.getNeighbour(brd.BoardA2D[cll.rowIndex][cll.colIndex],directionEnu.upperright).cellEnu==0))
       return true;
   if(brd.getNeighbour(brd.BoardA2D[cll.rowIndex][cll.colIndex],directionEnu.up) != null 
           &&(brd.getNeighbour(brd.BoardA2D[cll.rowIndex][cll.colIndex],directionEnu.up).cellEnu==0))
       return true;
   if(brd.getNeighbour(brd.BoardA2D[cll.rowIndex][cll.colIndex],directionEnu.upperleft) != null 
           &&(brd.getNeighbour(brd.BoardA2D[cll.rowIndex][cll.colIndex],directionEnu.upperleft).cellEnu==0))
       return true;
   if(brd.getNeighbour(brd.BoardA2D[cll.rowIndex][cll.colIndex],directionEnu.left) != null 
           &&(brd.getNeighbour(brd.BoardA2D[cll.rowIndex][cll.colIndex],directionEnu.left).cellEnu==0))
       return true;
   if(brd.getNeighbour(brd.BoardA2D[cll.rowIndex][cll.colIndex],directionEnu.downleft) != null 
           &&(brd.getNeighbour(brd.BoardA2D[cll.rowIndex][cll.colIndex],directionEnu.downleft).cellEnu==0))
       return true;
   if(brd.getNeighbour(brd.BoardA2D[cll.rowIndex][cll.colIndex],directionEnu.down) != null 
           &&(brd.getNeighbour(brd.BoardA2D[cll.rowIndex][cll.colIndex],directionEnu.down).cellEnu==0))
       return true;
   if(brd.getNeighbour(brd.BoardA2D[cll.rowIndex][cll.colIndex],directionEnu.downright) != null 
           &&(brd.getNeighbour(brd.BoardA2D[cll.rowIndex][cll.colIndex],directionEnu.downright).cellEnu==0))
       return true;
   return false;
}

public int getWeightedHeuristic(Board b, boolean playTurn)
{
    //[[# this function averages all heuristics by taking game depth into account. 
        
    int heuristicValue=0;
    
    int heurMaxDisc = maximumDisc(b,playTurn);
    int heurMobility = mobility(b,playTurn);
    int heurStableDisc = stableDisc(b, playTurn);
    int heurFrontier = frontier(b, playTurn);
    
    if(playTurn==false)
    {
    if( b.depth+4 < 16)                             // First 16 Moves
        heuristicValue = heurMaxDisc*B_maxDiscWeightEarly + heurMobility*B_mobilityWeigthEarly + heurStableDisc*B_stableDiscWeightEarly + heurFrontier*B_frontierWeightEarly;
    else if( b.depth+4 > 15 && b.depth+4 < 44)      // 28 Moves for early stage
        heuristicValue = heurMaxDisc*B_maxDiscWeightMid + heurMobility*B_mobilityWeigthMid + heurStableDisc*B_stableDiscWeightMid + heurFrontier*B_frontierWeightMid;        
    else                                            // 16 moves for end stage
        heuristicValue = heurMaxDisc*B_maxDiscWeightEnd + heurMobility*B_mobilityWeigthEnd + heurStableDisc*B_stableDiscWeightEnd + heurFrontier*B_frontierWeightEnd;
   
    }
    
    else
    {
     if( b.depth+4 < 16)                             // First 16 Moves
        heuristicValue = heurMaxDisc*W_maxDiscWeightEarly + heurMobility*W_mobilityWeigthEarly + heurStableDisc*W_stableDiscWeightEarly + heurFrontier*W_frontierWeightEarly;
    else if( b.depth+4 > 15 && b.depth+4 < 44)      // 28 Moves for early stage
        heuristicValue = heurMaxDisc*W_maxDiscWeightMid + heurMobility*W_mobilityWeigthMid + heurStableDisc*W_stableDiscWeightMid + heurFrontier*W_frontierWeightMid;        
    else                                            // 16 moves for end stage
        heuristicValue = heurMaxDisc*W_maxDiscWeightEnd + heurMobility*W_mobilityWeigthEnd + heurStableDisc*W_stableDiscWeightEnd + heurFrontier*W_frontierWeightEnd;
   
    }
   return heuristicValue;  
}

}
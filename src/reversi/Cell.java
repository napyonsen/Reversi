/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package reversi;


public class Cell {

	public int rowIndex;
	public int colIndex;
	int cellEnu;
        boolean candidateB00 =false;
	
        public Cell()
        {}
        
        public Cell(int row, int col)
        {
            rowIndex=row;
            colIndex=col;
        }
        public Cell(Cell copied) 
        {
            rowIndex = copied.rowIndex;
            colIndex = copied.colIndex;
            cellEnu = copied.cellEnu;
            candidateB00 = copied.candidateB00;
        }
	public void flip() { 
		if(cellEnu == -1) cellEnu = 1;
		else if (cellEnu == 1) cellEnu = -1;
	}
}

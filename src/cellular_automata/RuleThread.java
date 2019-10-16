/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cellular_automata;

import cellular_automata.neighbourhood.NeighbourhoodStrategy;

/**
 *
 * @author Logan Warner zdv5950 17991274
 */
public class RuleThread implements Runnable{
    private Cell[][] grid;
    private int posX;
    private int posY;
    private int[] birthString;
    private int[] surviveString;
    private NeighbourhoodStrategy neighbourhood;
    
    public RuleThread(Cell[][] grid, int posX, int posY, int[] birthString, int[] surviveString, NeighbourhoodStrategy neighbourhood) {
        this.grid = grid;
        this.posX = posX;
        this.posY = posY;
        this.birthString = birthString;
        this.surviveString = surviveString;
        this.neighbourhood = neighbourhood;
    }
    
     private void rule() {
        int neighbourCount = neighbourhood.neighbourhood(grid, posX, posY);
        int currentCell = grid[posX][posY].state;
        int nextCellState = Cell.DEAD;
        
        if(currentCell == Cell.DEAD) {
            for(int i=0; i < birthString.length; i++) {
                if(neighbourCount == birthString[i]) {
                    nextCellState = Cell.ALIVE;
                    break;
                }
            }
        } else if(currentCell == Cell.ALIVE) {
            for(int i=0; i < surviveString.length; i++) {
                if(neighbourCount == surviveString[i]) {
                    nextCellState = Cell.ALIVE;
                    break;
                }
            }
        }
        grid[posX][posY].setState(nextCellState);
    }
    @Override
    public void run() {
        rule();
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cellular_automata.neighbourhood;

import cellular_automata.Cell;

/**
 *
 * @author Logan Warner zdv5950 17991274
 */
public class HexagonalNeighbourhood implements NeighbourhoodStrategy{

    /* Neighbourhood:
     *  ## 
     *  #O#
     *   ##
    */
    @Override
    public int neighbourhood(Cell[][] grid, int posX, int posY) {
        int neighbourCount = 0;

        // Top Row
        try {
            if(grid[posX - 1][posY - 1].prevState == Cell.ALIVE) {
                neighbourCount++;
            }
        }
        catch(IndexOutOfBoundsException e) {
            neighbourCount++;
        }
        try {
            if(grid[posX][posY - 1].prevState == Cell.ALIVE) {
                neighbourCount++;
            }
        }
        catch(IndexOutOfBoundsException e) {
            neighbourCount++;
        }

        // Middle Row
        try {
            if(grid[posX - 1][posY].prevState == Cell.ALIVE) {
                neighbourCount++;
            }
        }
        catch(IndexOutOfBoundsException e) {
            neighbourCount++;
        }
        try {
            if(grid[posX + 1][posY].prevState == Cell.ALIVE) {
                neighbourCount++;
            }
        }
        catch(IndexOutOfBoundsException e) {
            neighbourCount++;
        }

        // Bottom Row
        try {
            if(grid[posX][posY + 1].prevState == Cell.ALIVE) {
                neighbourCount++;
            }
        }
        catch(IndexOutOfBoundsException e) {
            neighbourCount++;
        }
        try {
            if(grid[posX + 1][posY + 1].prevState == Cell.ALIVE) {
                neighbourCount++;
            }
        }
        catch(IndexOutOfBoundsException e) {
            neighbourCount++;
        }
        return neighbourCount;
    }
    
    public String toString() {
        return "Hexagonal";
    }
}

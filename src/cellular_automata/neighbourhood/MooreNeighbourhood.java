/* Neighbourhood:
 *  ###
 *  #O#
 *  ###
 */
package cellular_automata.neighbourhood;

import cellular_automata.Cell;

/**
 *
 * @author Logan Warner zdv5950 17991274
 */
public class MooreNeighbourhood implements NeighbourhoodStrategy{   
    //public MooreNeighbourhood(){}
    @Override
    public int neighbourhood(Cell[][] grid, int posX, int posY) {
        int neighbourCount = 0;
        
        for(int x = -1; x <= 1; x++) {
            for(int y=-1; y <= 1; y++) {
                try {
                    if(!(x == 0 && y == 0)) {
                        if(grid[posX + x][posY + y].prevState == Cell.ALIVE) {
                            neighbourCount++;
                        }
                    }
                } catch(IndexOutOfBoundsException e) {
                    neighbourCount++;
                }
                
            }
        }
        //System.out.println(neighbourCount);
        /*
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
        try {
            if(grid[posX + 1][posY - 1].prevState == Cell.ALIVE) {
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
            if(grid[posX - 1][posY + 1].prevState == Cell.ALIVE) {
                neighbourCount++;
            }
        }
        catch(IndexOutOfBoundsException e) {
            neighbourCount++;
        }
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
*/
        return neighbourCount;        
    }

    public String toString() {
        return "Moore";
    }
}

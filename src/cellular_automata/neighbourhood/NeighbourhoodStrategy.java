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
public interface NeighbourhoodStrategy {
    public int neighbourhood(Cell[][] grid, int posX, int posY);
}

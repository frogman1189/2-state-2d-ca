/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cellular_automata;

import cellular_automata.neighbourhood.NeighbourhoodStrategy;
import cellular_automata.neighbourhood.MooreNeighbourhood;
import java.util.Random;

/**
 *
 * @author Logan Warner zdv5950 17991274
 */
public class CellularAutomata {
    private Cell[][] grid;
    private int width;
    private int height;
    private int generation;
    private Random rand;
    private double seedPercent;
    public CellularAutomata(int width, int height) {
        this.width = width;
        this.height = height;
        generation = 0;
        grid = new Cell[width][height];
        rand = new Random();
        seedPercent = 0.5;
    }
    public Cell[][] getGrid() {
        return grid;
    }
    public void setSeedPercent(double percent) {
        seedPercent = percent;
    }
    public void seedMap(long seed) {
        rand = new Random(seed);
        seedMap();
    }
    public void seedMap() {
        int fillIterations = (int)(width * height * seedPercent);
        for(int x=0; x<width; x++) {
            for(int y=0; y < height; y++) {
                grid[x][y] = new Cell();
            }
        }
        for(int iter=0; iter < fillIterations; iter++) {
            int x = rand.nextInt(width);
            int y = rand.nextInt(height);
            grid[x][y].setState(Cell.ALIVE);
        }
    }
    public void iterate() {
        int[] birthString = {5,6,7,8};
        int[] surviveString = {4,5,6,7,8};
        iterate(birthString, surviveString);
    }
    public void iterate(int[] birthString, int[] surviveString) {
        NeighbourhoodStrategy neighbourhood = new MooreNeighbourhood();
        iterate(birthString, surviveString, neighbourhood);
    }
    public void iterate(int[] birthString, int[] surviveString, NeighbourhoodStrategy neighbourhood) {
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                grid[x][y].setState(grid[x][y].state);
            }
        }
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                rule(x, y, birthString, surviveString, neighbourhood);
            }
        }
        generation++;
    }
    public int getGeneration() {
        return generation;
    }
    private void rule(int posX, int posY, int[] birthString, int[] surviveString, NeighbourhoodStrategy neighbourhood) {
        int neighbourCount = neighbourhood.neighbourhood(grid, posX, posY);
        int currentCell = grid[posX][posY].state;
        int nextCellState = Cell.DEAD;
        
        //grid[posX][posY].state = Cell.DEAD;
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
    /*private void rule(int posX, int posY, int[] birthString, int[] surviveString, NeighbourhoodStrategy neighbourhood) {
        int neighbourCount = neighbourhood.neighbourhood(grid, posX, posY);
        int currentCell = grid[posX][posY].state;
        int nextCellState = Cell.DEAD;
        
        if(neighbourCount > 4) {
            nextCellState = Cell.ALIVE;
        }
        else if(neighbourCount == 4 && currentCell == Cell.ALIVE) {
            nextCellState = Cell.ALIVE;
        }
        grid[posX][posY].setState(nextCellState);
    }*/
    public void iterateGenerations(int iterationCount, int[] birthString, int[] surviveString, NeighbourhoodStrategy neighbourhood) {
        for(int i=0; i < iterationCount; i++) {
            iterate(birthString, surviveString, neighbourhood);
        }
    }
    public void iterateGenerations(int iterationCount, int[] birthString, int[] surviveString) {
        for(int i=0; i < iterationCount; i++) {
            iterate(birthString, surviveString);
        }
    }
    public void iterateGenerations(int iterationCount) {
        for(int i=0; i < iterationCount; i++) {
            iterate();
        }
    }
    
    public String toString() {
        String output = "";
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                if(grid[x][y].state == Cell.ALIVE) {
                    output += "#";
                }
                else {
                    output += " ";
                }
            }
            output += "\n";
        }
        return output;
    }
    
    public static void main(String[] args) {
        CellularAutomata cave = new CellularAutomata(50,20);
        cave.setSeedPercent(0.6);
        cave.seedMap();
        System.out.println("===Initial Configuration===");
        System.out.println(cave);
        
        //int[] conwayBirthSt 3,= {3};
        //int[] conwaySurviveString = {2, 3};
        
        //int[] caveBirthString = {5,6,7,8};
        //int[] caveSurviveString = {4,5,6,7,8};
        
        int[] hexBirthString = {6};
        int[] hexSurviveString = {3,4,5,6};
        for(int i=0; i < 20; i++) {
            //if(i % 2 == 1) {
            //    cave.iterate(conwayBirthString, conwaySurviveString);
            //}
            //else {
                cave.iterate(hexBirthString, hexSurviveString);
            //}
            System.out.println("===Generation " + cave.getGeneration() + "===");
            System.out.println(cave);
        }
        
    }
}

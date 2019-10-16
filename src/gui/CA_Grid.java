/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import cellular_automata.Cell;
import cellular_automata.CellularAutomata;
import cellular_automata.neighbourhood.MooreNeighbourhood;
import cellular_automata.neighbourhood.NeighbourhoodStrategy;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;

/**
 *
 * @author Logan Warner zdv5950 17991274
 */
public class CA_Grid extends JComponent{
    private CellularAutomata ca;
    private int[] birthString;
    private int[] surviveString;
    private NeighbourhoodStrategy neighbourhood;
    public CA_Grid() {
        super();
        ca = new CellularAutomata(100, 100);
        ca.setSeedPercent(0.6);
        ca.seedMap();
        birthString = new int[]{ 5,6,7,8 };
        surviveString = new int[]{4,5,6,7,8};
        neighbourhood = new MooreNeighbourhood();
        this.setPreferredSize(new Dimension(600,600));
    }
    public void seed() {
        ca = new CellularAutomata(100, 100);
        ca.setSeedPercent(0.6);
        ca.seedMap();
        repaint(); 
    }
    public void seed(int width, int height) {
        ca = new CellularAutomata(width, height);
        ca.setSeedPercent(0.6);
        ca.seedMap();
        repaint(); 
    }
    public void seed(int width, int height, long seed) {
        ca = new CellularAutomata(width, height);
        ca.setSeedPercent(0.6);
        ca.seedMap(seed);
        repaint(); 
    }
    public void setNeighbourhood(NeighbourhoodStrategy neighbourhood) {
        this.neighbourhood = neighbourhood;
    }
    public void setBirthString(int[] birthString) {
        this.birthString = birthString;
    }
    public void setSurviveString(int[] surviveString) {
        this.surviveString = surviveString;
    }
    public void iterate() {
        ca.iterate(birthString, surviveString, neighbourhood);
        //System.out.println("===Generation " + ca.getGeneration() + "===");
        //System.out.println(ca);
        repaint();
    }
    @Override
    public void paintComponent(Graphics g) {
        Cell[][] grid = ca.getGrid();
        int gridWidth = grid.length;
        int gridHeight = grid[0].length;
        int cellWidth = getWidth() / gridWidth;
        int cellHeight = getHeight()/gridHeight;
        // theres a gap due to integer division so find offset to center
        int offsetX = (getWidth() - (cellWidth * gridWidth)) / 2;
        int offsetY = (getHeight() - (cellHeight * gridHeight)) / 2;
        int countAlive = 0;
        int countDead = 0;
        
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        g.setColor(Color.black);
        for(int x = 0; x < gridWidth; x++) {
            for(int y = 0; y < gridHeight; y++) {
                if(grid[x][y].state == Cell.ALIVE) {
                    g.setColor(Color.black);
                    g.fillRect(offsetX + x * cellWidth, offsetY + y * cellHeight,
                            offsetX + x * cellWidth + cellWidth, offsetY + y * cellHeight + cellHeight);
                    
                }
                else {
                    g.setColor(Color.white);
                    g.fillRect(offsetX + x * cellWidth, offsetY + y * cellHeight,
                            offsetX + x * cellWidth + cellWidth, offsetY + y * cellHeight + cellHeight);
                }
            }
        }
        g.setColor(Color.white);
        g.fillRect(0, cellHeight * gridHeight + offsetY, getWidth(), getHeight());
        g.fillRect(cellWidth * gridWidth + offsetX, 0, getWidth(), getHeight());
    }
}

package cellular_automata;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Logan Warner zdv5950 17991274
 */
public class Cell {
    public static final int ALIVE=1;
    public static final int DEAD=0;
    public int state;
    public int prevState;
    public Cell() {
        state = DEAD;
        prevState = DEAD;
    }
    public Cell(int state) {
        this.state = state;
        prevState = DEAD;
    }
    public void setState(int state) {
        prevState = this.state;
        this.state = state;
    }
}

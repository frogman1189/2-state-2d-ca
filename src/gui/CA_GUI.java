/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import cellular_automata.neighbourhood.HexagonalNeighbourhood;
import cellular_automata.neighbourhood.MooreNeighbourhood;
import cellular_automata.neighbourhood.NeighbourhoodStrategy;
import cellular_automata.neighbourhood.VonNeumannNeighbourhood;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

/**
 *
 * @author Logan Warner zdv5950 17991274
 */
public class CA_GUI extends JPanel implements ActionListener{
    private JButton iterate;
    private JButton play;
    private JButton stop;
    private JButton seed;
    private Timer timer;
    private JTextField birthString;
    private JTextField surviveString;
    private JComboBox<NeighbourhoodStrategy> neighbourhood;
    private CA_Grid caGrid;
    public CA_GUI() {
        super(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        
        iterate = new JButton("Step");
        iterate.addActionListener(this);
        buttonPanel.add(iterate);
        
        play = new JButton("Play");
        play.addActionListener(this);
        buttonPanel.add(play);
        
        stop = new JButton("Stop");
        stop.addActionListener(this);
        buttonPanel.add(stop);
        
        seed = new JButton("Seed");
        seed.addActionListener(this);
        buttonPanel.add(seed);
        
        buttonPanel.add(new JLabel("B"));
        birthString = new JTextField("5,6,7,8");
        birthString.setPreferredSize(new Dimension(100, 20));
        birthString.addActionListener(this);
        buttonPanel.add(birthString);
        
        buttonPanel.add(new JLabel("S"));
        surviveString = new JTextField("4,5,6,7,8");
        surviveString.setPreferredSize(new Dimension(100, 20));
        surviveString.addActionListener(this);
        buttonPanel.add(surviveString);
        
        NeighbourhoodStrategy[] neighbourhoodStrategyArray = new NeighbourhoodStrategy[3];
        neighbourhoodStrategyArray[0] = new MooreNeighbourhood();
        neighbourhoodStrategyArray[1] = new HexagonalNeighbourhood();
        neighbourhoodStrategyArray[2] = new VonNeumannNeighbourhood();
        
        neighbourhood = new JComboBox(neighbourhoodStrategyArray);
        neighbourhood.addActionListener(this);
        
        buttonPanel.add(neighbourhood);
        
        add(buttonPanel, BorderLayout.SOUTH);
        
        timer = new Timer(500, this);
        caGrid = new CA_Grid();
        add(caGrid, BorderLayout.CENTER);
        
        neighbourhood.setSelectedIndex(0);
    }
    
    public static void main(String[] args)
    { 
        JFrame frame = new JFrame("Cellular Automata");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new CA_GUI());
        frame.pack();
        // position the frame in the middle of the screen
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenDimension = tk.getScreenSize();
        Dimension frameDimension = frame.getSize();
        frame.setLocation((screenDimension.width-frameDimension.width)/2,
            (screenDimension.height-frameDimension.height)/2);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == iterate) {
            caGrid.iterate();
        }
        else if(ae.getSource() == play) {
            timer.start();
        }
        else if(ae.getSource() == stop) {
            timer.stop();
        }
        else if(ae.getSource() == timer) {
            caGrid.iterate();
        }
        else if(ae.getSource() == seed) {
            int width = 0;
            int height = 0;
            long seed = 0;
            try {
                String widthInput = JOptionPane.showInputDialog("Width[100]:");
                if(widthInput.length() > 0) {
                    width = Integer.parseInt(widthInput);
                } else {
                    width = 100;
                }
                String heightInput = JOptionPane.showInputDialog("Height[100]:");
                if(heightInput.length() > 0) {
                    height = Integer.parseInt(heightInput);
                } else {
                    height = 100;
                }
                String seedInput = JOptionPane.showInputDialog("Seed[None]:");
                if(seedInput.length() > 0) {
                    seed = Long.parseLong(seedInput);
                    caGrid.seed(width, height, seed);
                } else {
                    caGrid.seed(width, height);
                }
                //caGrid.seed();
            } catch(NumberFormatException e) {
                JOptionPane.showMessageDialog(this, e);
            }
        }
        else if(ae.getSource() == neighbourhood) {
            if(neighbourhood.getSelectedItem() instanceof NeighbourhoodStrategy) {
                caGrid.setNeighbourhood((NeighbourhoodStrategy)neighbourhood.getSelectedItem());
            }
        }
        else if(ae.getSource() == birthString) {
            String bString = birthString.getText();
            if(bString.length() > 0) {
                String[] bStringArray = bString.split(",");
                int[] bArray = new int[bStringArray.length];
                for(int i = 0; i < bStringArray.length; i++) {
                    try {
                        bArray[i] = Integer.parseInt(bStringArray[i]);
                    }catch(NumberFormatException e){}
                }
                caGrid.setBirthString(bArray);
            }
            else {
                int[] b = new int[]{};
                caGrid.setBirthString(b);
            }
            
        }
        else if(ae.getSource() == surviveString) {
            String sString = surviveString.getText();
            if(sString.length() > 0) {
                String[] sStringArray = sString.split(",");
                int[] sArray = new int[sStringArray.length];
                for(int i = 0; i < sStringArray.length; i++) {
                    try {
                        sArray[i] = Integer.parseInt(sStringArray[i]);
                    } catch(NumberFormatException e) {}
                }
                caGrid.setSurviveString(sArray);
            }
            else {
                int[] s = new int[]{};
                caGrid.setSurviveString(s);
            }
        }
    }
}

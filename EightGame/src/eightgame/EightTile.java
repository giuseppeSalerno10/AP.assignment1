/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Beans/Bean.java to edit this template
 */
package eightgame;

import eightgame.Events.ResetListener;
import eightgame.Events.UpdateTileListener;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.*;
import javax.swing.JButton;
import javax.swing.Timer;

/**
 *
 * @author Giuse
 */
public class EightTile extends JButton implements UpdateTileListener, ResetListener {
    
    private int label; //This property identifies the text to be displayed
    private int position; //This property define the position inside the tile array
    private VetoableChangeSupport vetoableChangeSupport = new VetoableChangeSupport(this);
    
    public EightTile(){}
    public EightTile(int label, int position) {
        this.position = position;

        updateLabelAndText(label);
    }
    
    public void addVetoableChangeListener(VetoableChangeListener listener){
        vetoableChangeSupport.addVetoableChangeListener(listener);
    }
    public void removeVetoableChangeListener(VetoableChangeListener listener){
        vetoableChangeSupport.removeVetoableChangeListener(listener);
    }
    
    public int getPosition(){
        return position;
    }

    public int getLbl(){
        return label;
    }
    public void setLbl(int label) throws InterruptedException{
        try{
            vetoableChangeSupport.fireVetoableChange("label", this.label, label);
            updateLabelAndText(label);
        }
        catch(PropertyVetoException e){
            Color currentColor = getBackground();
            setBackground(Color.RED);
            Timer blinkTimer = new Timer(500, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    setBackground(currentColor);
                    ((Timer) e.getSource()).stop();
                }
            });
            blinkTimer.start();

        }
    }
    
    private void setTileColor(){
        if(label == 9){
            setBackground(Color.GRAY);
        }
        else if(label == position + 1){
            setBackground(Color.GREEN);
        }
        else{
            setBackground(Color.YELLOW);
        }
    }
    private void updateLabelAndText(int label){
        this.label = label;
        setText(label == 9 ? "" : String.valueOf(label));
        setTileColor();
    }
    
    @Override
    public void update(int label) {
        updateLabelAndText(label);
    }

    @Override
    public void reset(int[] permutation) {
        updateLabelAndText(permutation[position]);
    }

}

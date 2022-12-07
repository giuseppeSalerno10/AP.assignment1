/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Beans/Bean.java to edit this template
 */
package eightgame;

import java.awt.Color;
import java.beans.*;
import javax.swing.JButton;

/**
 *
 * @author Giuse
 */
public class EightTile extends JButton {
    
    private int label;
    private int position;
    private VetoableChangeSupport vetoableChangeSupport = new VetoableChangeSupport(this);
    
    public EightTile(){
    }
    public EightTile(int label){
        this.label = label;
        setText(String.valueOf(label));
    }
    public EightTile(int label, int position) {
        this.label = label;
        setText(String.valueOf(label));
        
        setPosition(position);
    }

    public void setPosition(int position){
        try{
            vetoableChangeSupport.fireVetoableChange("position", this.position, position);
            this.position = position;
            
            setTileColor();
        }
        catch(Exception e){
            
        }
    }
    public int getPosition(){
        return position;
    }

    public int getLbl(){
        return label;
    }
    
    private void setTileColor(){
        if(label == 9){
            setBackground(Color.GRAY);
        }
        else if(label == position){
            setBackground(Color.GREEN);
        }
        else{
            setBackground(Color.YELLOW);
        }
    }

}

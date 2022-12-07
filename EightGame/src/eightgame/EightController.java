/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Beans/Bean.java to edit this template
 */
package eightgame;

import eightgame.Events.ResetListener;
import eightgame.Events.UpdateTileListener;
import java.awt.Point;
import java.beans.*;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JLabel;

/**
 *
 * @author Giuse
 */
public class EightController extends JLabel implements VetoableChangeListener, ResetListener {
    
    private ArrayList<UpdateTileListener> updateListeners = new ArrayList<UpdateTileListener>();
    private EightTile[] tiles;
    public EightController(){}
    public EightController(EightTile[] tiles) {
        this.tiles = tiles;
        for(int i = 0; i < tiles.length; i++){
            tiles[i].addVetoableChangeListener(this);
            updateListeners.add(tiles[i]);
        }
    }

    
    @Override
    public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
        switch(evt.getPropertyName()){
            case "label":
                int newLabel =(int) evt.getNewValue();
                int oldLabel = (int) evt.getOldValue();

                if(oldLabel == 9){
                    throw new PropertyVetoException("The hole cannot move", evt);
                }
                if(newLabel != 9){
                    throw new PropertyVetoException("A tile can only move into the hole", evt);
                }

                int newPosition = findTilePositionByLabel(newLabel);
                updateListeners.get(newPosition).update(oldLabel);
                break;       
        }
    }

    @Override
    public void reset(int[] permutation) {
        //Tiles works by references, i don't need to update nothing
    }

    public void switchTiles(int positionA, int positionB) {
        int oldLabel = tiles[positionA].getLbl();
        updateListeners.get(positionA).update(tiles[positionB].getLbl());
        updateListeners.get(positionB).update(oldLabel);

    }
    private int findTilePositionByLabel(int label){
        for(EightTile tile : tiles){
            if(tile.getLbl() == label){
                return tile.getPosition();
            }
        }
        
        return -1;
    }
}

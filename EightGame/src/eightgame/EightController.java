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
import java.util.HashMap;
import javax.swing.JLabel;

/**
 *
 * @author Giuse
 */
public class EightController extends JLabel implements VetoableChangeListener, ResetListener {
    
    private HashMap<Integer, Integer[]> validMoves;
    private ArrayList<UpdateTileListener> updateListeners = new ArrayList<UpdateTileListener>();
    private EightTile[] tiles;
    
    public EightController(){
        setText("Start");
    }
    private HashMap<Integer, Integer[]> generateValidMoves(int tilesNumber){
        return new HashMap<Integer, Integer[]>()
        {{
            put(0, new Integer[] {1,3});
            put(1, new Integer[] {0,2,4});  // 0 1 2
            put(2, new Integer[] {1,5});    // 3 4 5
            put(3, new Integer[] {0,4,6});  // 6 7 8
            put(4, new Integer[] {1,3,5,7});
            put(5, new Integer[] {2,4,8});
            put(6, new Integer[] {3,7});
            put(7, new Integer[] {4,6,8});
            put(8, new Integer[] {5,7});
        }};
    }
    public void setTiles(EightTile[] tiles){
        this.tiles = tiles;
        for(int i = 0; i < tiles.length; i++){
            tiles[i].addVetoableChangeListener(this);
            updateListeners.add(tiles[i]);
        }
        validMoves = generateValidMoves(tiles.length);
    }
    private Boolean isMoveValid(int newPosition, int  newLabel, int oldPosition, int oldLabel){
        
        for(int position : validMoves.get(oldPosition)){
            if(position == newPosition){
                return true;
            }
        }
        
        return false;
    }
    @Override
    public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
        switch(evt.getPropertyName()){
            case "label":
                int newLabel =(int) evt.getNewValue();
                int oldLabel = (int) evt.getOldValue();
                int newPosition = findTilePositionByLabel(newLabel);
                int oldPosition = findTilePositionByLabel(oldLabel);

                if(!isMoveValid(newPosition, newLabel, oldPosition, oldLabel)){
                    setText("Invalid Move!");
                    throw new PropertyVetoException("Invalid Move!", evt);
                }

                setText("OK");
                updateListeners.get(newPosition).update(oldLabel);
                break;       
        }
    }

    @Override
    public void reset(int[] permutation) {
        setText("Start");
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

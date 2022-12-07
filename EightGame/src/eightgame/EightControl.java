/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Beans/Bean.java to edit this template
 */
package eightgame;

import java.awt.Point;
import java.beans.*;
import java.io.Serializable;
import javax.swing.JLabel;

/**
 *
 * @author Giuse
 */
public class EightControl extends JLabel implements VetoableChangeListener {
    private EightTile[] tiles;
    public EightControl() {
    }
    
    public EightTile[] getTiles(){
        return tiles;
    }
    public EightTile getTile(int index){
        return(tiles[index]);
    }
    public void setTiles(EightTile[] tiles){
        this.tiles = tiles; 
        for(int i = 0; i < tiles.length; i++){
            tiles[i].addVetoableChangeListener(this);
        }
    }
    
    public void switchTiles(int sourceIndex, int destinationIndex){
        int tempPosition = tiles[sourceIndex].getPosition();
        Point tempLocation = tiles[sourceIndex].getLocation();
        
        tiles[sourceIndex].setPosition(tiles[destinationIndex].getPosition());
        tiles[sourceIndex].setLocation(tiles[destinationIndex].getLocation());
        tiles[destinationIndex].setPosition(tempPosition);
        tiles[destinationIndex].setLocation(tempLocation);
        
        EightTile tempTile = tiles[sourceIndex];
        tiles[sourceIndex] = tiles[destinationIndex];
        tiles[destinationIndex] = tempTile;
    }
    public void shuffleTiles(EightTile[] tiles, int[] permutation ){
        //1. Cerco il tile che ha label = permutazione[i]
        //2. Sostituisco il tile trovato (con indice j) con il tile in posizione i
        
        for(int i = 0; i < tiles.length; i++){
            if(tiles[i].getLbl() == permutation[i]){
                //Tile is already in the right position
                continue;
            }
            
            for(int j = 0; j < tiles.length; j++){
                if(tiles[j].getLbl() == permutation[i]){
                    switchTiles(i,j);
                }
            }
        }
    }
    public void resetBoard(int[] permutation){
        shuffleTiles(tiles,permutation);
    }
    public int findNearestHolePosition(int sourcePosition){
        int holePosition = -1;
        holePosition = sourcePosition + 1;
        return holePosition;
    }

    @Override
    public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
        int newPosition =(int) evt.getNewValue();
        int oldPosition = (int) evt.getOldValue();
        
        if(oldPosition == 9){
            throw new PropertyVetoException("The hole cannot move", evt);
        }
        if(newPosition != 9){
            throw new PropertyVetoException("The hole cannot move", evt);
        }
    }
}

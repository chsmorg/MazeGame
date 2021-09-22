/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmm5b7finalproject;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

/**
 *
 * @author chase
 */
public class Tile extends Pane{
        private int x;
        private int y;
        private Pane pane;
        private boolean tileVisited;
        private Circle circle;
        private boolean end;
        
        // north, south,east ,and west walls for each tile
        public boolean n;
        public boolean s;
        public boolean e;
        public boolean w;
        
        
        //creates a tile object with an x and y coordinate
        public Tile(int x, int y){
            this.x=x;
            this.y=y;
            this.pane = new Pane();
            this.tileVisited=false;
            this.n=true;
            this.s=true;
            this.e=true;
            this.w=true;
            this.circle = new Circle();
            this.end=false;
        }
        //returns x
        public int getX(){
            return this.x;
        }
        //returns y
        public int getY(){
            return this.y;
        }
        //checks if visited
        public boolean getVisited(){
            return this.tileVisited;
        }
        //sets if visited
        public void setVisited(boolean visit){
            this.tileVisited= visit;
        }
        //returns circle for tile
        public Circle getCircle(){
            return this.circle;
        }
        //returns if tile is an end tile
        public boolean getEnd(){
            return this.end;
        }
        //sets tile as an end tile
        public void setEnd(boolean end){
            this.end=end;
        }
       
        
       
        
                
          
        
    
}

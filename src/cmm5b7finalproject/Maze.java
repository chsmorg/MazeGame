/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmm5b7finalproject;

import java.util.ArrayList;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 *
 * @author chase
 */

public final class Maze {
     public boolean stop = false;
     int size;
     ArrayList<Tile> tiles = new ArrayList<>();
     int h;
     int w;
     Tile currentTile;
     GridPane grid;
     ArrayList<Tile> path = new ArrayList<>();
     //creates a maze with a given size
     //adds tile objects in every slot of the gridpane along with sizes for the circles
    public Maze(int size) {
        this.size=size;
        this.grid = new GridPane();
        this.grid.setPrefSize(450, 450);
        this.grid.setHgap(0);
        this.grid.setVgap(0);
        //this.grid.setGridLinesVisible(true);
        int conSize = 450 / size;
        this.h = this.w = conSize;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Tile tile = new Tile(i, j);
                tile.setPrefSize(this.w, this.h);
                this.grid.add(tile, i, j);
                this.tiles.add(tile);
                tile.getCircle().setCenterX(this.h/2);
                tile.getCircle().setCenterY(this.h/2);
                tile.getCircle().setRadius(this.h/3);
                tile.getCircle().setFill(Color.AQUA);
            }
        }
            this.paintGrid();
            this.createMaze();
            this.drawWalls();
        

        
        
       
    }
    
    //sets colors for all tiles 
    //sets red and green for start and end tiles
    private void paintGrid() {
        for(Tile tile:this.tiles){
            tile.setStyle("-fx-background-color: #5555");
            
            if (tile.getX() == 0 && tile.getY() == 0) {
                tile.setStyle("-fx-background-color: red");
               
                this.currentTile = tile;
            }
            if(tile.getX()==(int)this.size/2 && tile.getY()==(int)this.size/2){
                tile.setEnd(true);
                tile.setStyle("-fx-background-color: lime");
            }
            
        }
        

    }
    //creates maze walls recursively and randomly
    //there will always be an unvisted neighbor in the recursive chain for the entire maze
    private void createMaze(){
                Tile tile = this.currentTile;
                //finds all 4 neighboring tiles
                Tile tileU = findTile(tile.getX(),tile.getY()-1,this.tiles); 
                Tile tileD = findTile(tile.getX(),tile.getY()+1,this.tiles); 
                Tile tileR=findTile(tile.getX()+1,tile.getY(),this.tiles); 
                Tile tileL=findTile(tile.getX()-1,tile.getY(),this.tiles); 
                //checks if neighbors are null visted or not visited
                int uN = mazeTileCheck(tileU);
                int dN = mazeTileCheck(tileD);
                int rN = mazeTileCheck(tileR);
                int lN = mazeTileCheck(tileL);
                
               
                // sets the current tile to visited  
	        tile.setVisited(true); 
        
	    // while tile(i,j) has an unvisited neighbor 
	    while (uN==0||dN ==0||rN==0|| lN ==0){
                uN = mazeTileCheck(tileU);
                dN = mazeTileCheck(tileD);
                rN = mazeTileCheck(tileR);
                lN = mazeTileCheck(tileL);
	    {
	         
	         double r = Math.random();
                 //System.out.println(r);
                 //moves up
	         if (r <= 0.25 && uN==0) 
	         {
                     if(!tileU.getVisited()){
                         tileU.s=false;
	        	 tile.n=false;
	        	 this.currentTile=tileU;
                         createMaze();
                     }
	        	 
	        	 
	        	 
	        	 
	        	 
	         }  
                 //moves down
	         else if (0.25 <= r && r < 0.50 && dN==0) 
	         {
	        	if(!tileD.getVisited())
                        {
                         tileD.n=false;  
	        	 tile.s=false;  
	        	 this.currentTile=tileD;
                         createMaze();
                        } 
                     
	        	 
	         }
                 //moves right
	         else if (0.50 <= r && r < 0.75 && rN==0) 
	         {
	        	 if(!tileR.getVisited()){
                             tileR.w=false;
                             tile.e=false; 
                             this.currentTile=tileR;
                             createMaze();
                         }
                         
	        	 
	        	 
	         }
                 //moves left
	         else if (0.75 <= r && r < 1.0 && lN ==0) 
	         {
	        	if(!tileL.getVisited()){
                         tileL.e=false; 
	        	 tile.w=false; 
                         this.currentTile=tileL;
	        	 createMaze(); 
                        } 
	         }
                
            }
        }
            
    
        
    }
    //draws the wall on the tiles for the user to see if they can move to a tile or not
    //checks a tile for its wall and adds a line object if there is a wall
    private void drawWalls(){
        for(Tile tile:this.tiles){
            tile.setVisited(false);
            if(tile.getX()==0 && tile.getY()==0){
                this.currentTile=tile;
                tile.setVisited(true);
            }
            if(tile.s){
                Line line= new Line(0,this.h,this.w,this.h);
                tile.getChildren().add(line);
            }
            if(tile.n){
                Line line= new Line(0,0,this.w,0);
                tile.getChildren().add(line);
                
            }
            if(tile.e){
                Line line= new Line(this.w,0,this.w,this.h);
                tile.getChildren().add(line);
                
            }
            if(tile.w){
                Line line= new Line(0,0,0,this.h);
                tile.getChildren().add(line);
                
            }
        }
    }
    
    //finds a tile with a given x,y coordinate
    public Tile findTile(int i, int j, ArrayList<Tile> t){
        
        for(Tile tile: t){
            if (tile.getX() == i && tile.getY() == j){
                return tile;
            }
            
        }
        return null;
        
        
        
    }
    //retuns 2 if tile is null 0 if tile is not null and is unvisited and 1
    //if tile is not null and is visited
    private int mazeTileCheck(Tile tile){
        if(tile != null){
            if(!tile.getVisited()){
                return 0;
            }
            else{
                return 1;
            }
        }
        return 2;
    
    }

    
}

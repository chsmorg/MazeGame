/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmm5b7finalproject;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author chase
 * references: 
 * https://stackoverflow.com/questions/18549704/create-a-new-line-in-javas-filewriter
 * https://www.w3schools.com/java/java_files_create.asp
 * https://stackoverflow.com/questions/5757202/how-would-i-print-all-values-in-a-treemap
 * https://www.geeksforgeeks.org/split-string-java-examples/#:~:text=The%20string%20split()%20method,of%20the%20given%20regular%20expression.&text=Parameters%3A%20regex%20%2D%20a%20delimiting%20regular,by%20splitting%20the%20given%20string.
 * https://www.w3schools.com/java/java_files_read.asp
 * https://www.w3schools.com/java/java_files_create.asp
 * 
 * 
 */
public class Record {
    private final String FILE;
    private TreeMap<Double, String> times;
    private Entry<Double,String> first = null;
    
     
    
    
    public Record(String file){
        this.FILE=file;
        this.times = fillMap(file);
        if(!this.times.isEmpty()){
            this.first=this.times.firstEntry();
        }
        
        
    }
    //fills a tree map of record times from loaded file sorting the times automatically
    //returns an empty map if no records are loaded in
    private TreeMap<Double,String> fillMap(String file){
        TreeMap<Double,String> map = new TreeMap<>();
        File f = new File(file);
        try{
            if(f.createNewFile()){
                return map;
            }
            else{
                try (Scanner scan = new Scanner(f)) {
                    while(scan.hasNextLine()){
                        String[] line = scan.nextLine().split(",");
                        
                        
                       //System.out.println(Integer.parseInt(line[0]));
                        map.put(Double.parseDouble(line[0]),line[1]);
                        
                    }
                }
                return map;
                
            }
        }
        catch(IOException  e){
           return map; 
        }
            
        
    }
    //gets first value in the map is also the highscore
    public Entry<Double,String>  getFirst(){
        return this.first;
    }
    //returns the treemap of saved times
    public TreeMap<Double,String> getTimes(){
        return this.times;
    }
    //saves a new time into the treemap and writes it into the file
    //also updates the first entry time
    public void saveTime(double time,String name){
        this.times.put(time,name);
        if(!this.times.isEmpty()){
            this.first=this.times.firstEntry();
        }
        try{
            try (PrintStream writer = new PrintStream(FILE)) {
                this.times.entrySet().forEach((i) -> {
                    writer.println(i.getKey()+","+i.getValue());
                });
            }
            
        }
        catch(IOException e){
            
        }
        
    }

}

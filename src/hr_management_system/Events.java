/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr_management_system;

/**
 *
 * @author ABS
 */
public class Events {
    private String eventName;
    private String eventDuration;
    private String eventType;
    Connector c= new Connector();


    public Events(String eventName, String eventDuration, String eventType) {
        this.eventName = eventName;
        this.eventDuration = eventDuration;
        this.eventType = eventType;
        
    }
    
    public void insert(){
        String query = "insert into Event (Event_Name, Event_duration, Event_type )values('" + this.eventName + "','" + this.eventDuration + "' ,'" + this.eventType+ "' )";
        c.runDML(query);

        System.out.println("Added");
    }
    public void update(int id){
        try{
            
            
            c.runDML("update Event set Event_Name='"+this.eventName+"',Event_duration='"+this.eventDuration+"',Event_type='"+this.eventType+"'where ID= "+id);
        }catch(Exception e){e.printStackTrace();}
    }
    public void Delete(int id){
        String query = "DELETE FROM Event WHERE ID = "+ id;
        c.runDML(query);
    
    }
    
    
    
  
    
    
}

package kz.mirinda.trainer.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 *  workouts
 *
 * @author mirinda
 */
public class Workout implements Serializable {
    private List<Drill> drills=new ArrayList<Drill>();  // TODO MAP in future(DrillName : Drill)
    private Date date;

    public Workout(){
        date = new Date();
    }

    public List<Drill> getDrills() {
        return drills;
    }
    public void setDrills(List<Drill> drills) {
        this.drills = drills;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public Drill getDrill(String drillName){
        Iterator<Drill> iterator = drills.iterator();
        for(;iterator.hasNext();){
            Drill drill= iterator.next();
            if(drill.getDrillName().equals(drillName)) return drill;
        }
        return null; // TODO think null is bad >_<
    }
    public void add(Drill drill){
        drills.add(drill);
    }
    public void add(String drillName,int i){
        Drill drill= new Drill(drillName,i);
        drills.add(drill);
    }
    
    @Override
    public String toString(){
        String drillString="";
        Iterator<Drill> iterator= drills.iterator();
        for(;iterator.hasNext();){
            drillString+="\t"+iterator.next().toString()+"\n";
        }
        return date.toString()+"\n"+drillString;
    }

    /*public static void main(String[] args){
        Workout workout = new Workout();
        workout.add("push_up",100);
        workout.add("pull_up",100);
        workout.add("press",200);
        System.out.println(workout.toString());
    }*/
}

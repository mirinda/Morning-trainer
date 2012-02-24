package kz.mirinda.trainer.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * drills or exercises
 *
 * @author mirinda
 */
public class Drill implements Serializable {
    private String drillName;
    private List<Integer> numbers = new ArrayList<Integer>();;

    public Drill(String drillName){
        this.drillName=drillName;
    }

    public Drill(String drillName,int i){
        this.drillName = drillName;
        add(i);
    }
    
    public String getDrillName() {
        return drillName;
    }

    public void setDrillName(String drillName) {
        this.drillName = drillName;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }
    public void add(int i) {
        numbers.add(new Integer(i));
    }
    @Override
    public String toString(){
        String numString="";
        Iterator<Integer> iterator= numbers.iterator();
        for (;iterator.hasNext();){
            Integer i= iterator.next();
            numString+=i.toString();
            numString+=iterator.hasNext()?",":"";
        }
        return drillName+":"+numString+".";
    }
}

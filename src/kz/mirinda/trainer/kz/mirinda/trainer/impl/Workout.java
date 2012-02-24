package kz.mirinda.trainer.kz.mirinda.trainer.impl;

import java.util.Date;
import java.util.List;

/**
 *  new class
 *
 * @author mirinda
 */
public class Workout {
    private List<Drill> drills;
    private Date date;

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
}

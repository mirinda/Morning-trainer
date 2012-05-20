package kz.mirinda.trainer.impl;

import java.io.Serializable;

/**
 * new class
 *
 * @author mirinda
 */
public class DrillModel implements Serializable{
    public String getDrillName() {
        return drillName;
    }

    public void setDrillName(String drillName) {
        this.drillName = drillName;
    }

    private String drillName;

	@Override
	public String toString() {
		return  drillName ;
	}
}

package kz.mirinda.trainer.kz.mirinda.trainer.impl;

import java.util.List;

/**
 * new class
 *
 * @author mirinda
 */
public class Drill {
    private String drillName;
    private List<Integer> numbers;

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
}

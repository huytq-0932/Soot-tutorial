package dev.navids.soottutorial.hellosoot;

import soot.Unit;

public class UnitState {
    private int index;
    private int totalVisitedTimes;

    public UnitState(int index, int totalVisitedTimes) {
        this.index = index;
        this.totalVisitedTimes = totalVisitedTimes;
    }

    public UnitState(int index) {
        this.index = index;
        this.totalVisitedTimes = 1;
    }

    public int getTotalVisitedTimes() {
        return totalVisitedTimes;
    }

    public int getIndex() {
        return index;
    }

    boolean isVisitable() {
        return totalVisitedTimes > 0;
    }

    int decreaseVisitTimes() {
        --totalVisitedTimes;
        return totalVisitedTimes;
    }

    int increaseVisitTimes() {
        ++totalVisitedTimes;
        return totalVisitedTimes;
    }
}

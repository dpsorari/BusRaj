package com.chaos.busraj.BusInfo;

import java.util.ArrayList;
import java.util.Date;

public class BusStatus {

    //unique id for each bus
    private String busID;
//    private String busName;
    private ArrayList<String> city;

    public BusStatus(String busID, ArrayList<String> city) {
        this.busID = busID;
        this.city = city;
    }

    Date getExpectedTime(String source) {return null;}

    //Expected time of arrival for all buses(having same id) at a particular stop
    ArrayList<Date> getExpectedTimeForAllBus(String source) {return null;}
}

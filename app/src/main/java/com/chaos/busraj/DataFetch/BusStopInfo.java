package com.chaos.busraj.DataFetch;

import com.chaos.busraj.BusInfo.BusStatus;

import java.util.ArrayList;

//information related to a bus stop
public class BusStopInfo {

    //used to get all the stand names of a particular city
    public ArrayList<String> getAllStops(String city) {
        return null;
    }

    //all Buses that arrive or depart from particular stop
    public ArrayList<Object> getAllBusAtStop(String city, String stopName) {
        return null;
    }

    //get all the stops of a bus
    public ArrayList<String> getRoute(BusStatus busStatus) {
        return null;
    }
}

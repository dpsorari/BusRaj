package com.chaos.busraj.DataFetch;

import com.chaos.busraj.BusInfo.BusStatus;

import java.util.ArrayList;

//information related to a bus stop
public interface BusStopInfo {

    //used to get all the stand names of a particular city
    ArrayList<String> getAllStops(String city);

    //all Buses that arrive or depart from particular stop
    ArrayList<Object> getAllBusAtStop(String city, String stopName);

    //get all the stops of a bus
    ArrayList<String> getRoute(BusStatus busStatus);
}

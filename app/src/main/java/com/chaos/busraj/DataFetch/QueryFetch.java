package com.chaos.busraj.DataFetch;

import android.support.annotation.NonNull;

import com.chaos.busraj.BusInfo.BusStatus;

import java.util.ArrayList;

//information related to a bus stop and cities
public final class QueryFetch {

    //used to get all the stand names of a particular city
    public static ArrayList<String> getAllStops(@NonNull final String city) {
        //dummy data currently
        ArrayList<String> stopNames = new ArrayList<String>();

        stopNames.add("MI ROAD");
        stopNames.add("MNIT");
        stopNames.add("GOLCHA");
        stopNames.add("TRITON");
        stopNames.add("TONK PHATAK");
        stopNames.add("BRAHMAPURI");
        stopNames.add("Chandpole");
        stopNames.add("Choti Chaupar");
        stopNames.add("Badi Chaupar");
        stopNames.add("Laxmi Mandir");
        stopNames.add("Sanganeri Gate");
        stopNames.add("Goshala");

        return stopNames;
    }

    //all Buses that arrive or depart from particular stop
    public static ArrayList<Object> getAllBusAtStop(@NonNull final String city,@NonNull final String stopName) {
        return null;
    }

    //get all the stops of a bus
    public static ArrayList<String> getRoute(@NonNull final BusStatus busStatus) {
        return null;
    }

    //city of a particular state
    public static ArrayList<String> getCitiesOfState(@NonNull final String state) {
        //dummy data currently
        ArrayList<String> cityNames = new ArrayList<String>();

        cityNames.add("Jaipur");
        cityNames.add("Kota");
        cityNames.add("Jodhpur");
        cityNames.add("Bikaner");
        cityNames.add("Udaipur");
        cityNames.add("Bassi");
        cityNames.add("Jaiselmer");
        cityNames.add("Bundi");
        cityNames.add("Sambhar");
        cityNames.add("Chomu");
        cityNames.add("Ajmer");
        cityNames.add("Bharatpur");
        cityNames.add("Pushkar");

        return cityNames;
    }
}

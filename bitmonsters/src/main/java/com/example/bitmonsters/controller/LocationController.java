package com.example.bitmonsters.controller;
import com.example.bitmonsters.model.Location;
import com.example.bitmonsters.model.LocationTracking;
import com.example.bitmonsters.repository.LocationRepository;
import com.example.bitmonsters.repository.LocationTrackingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class LocationController {
    @Autowired
    LocationRepository locationRepository;

    @Autowired
    LocationTrackingRepository locationTrackingRepository;

    // Get All Locations
    @GetMapping("/getAllLocation")
    public List<Location> getAllLocation() {
        return locationRepository.findAll();
    }

    // Get a Single Location
    @GetMapping("/getlocation/{id}")
    public Location getLocationById(@PathVariable(value = "id") Long locationId) {
        return locationRepository.findById(locationId).get();
    }

    //Get the Foot Traffic For Today
    @GetMapping("/getFootTrafficlocation")
    public TreeMap<Long, List<Integer>> getFootTrafficForToday() {
        //Get Today's Date
        LocalDateTime today = LocalDateTime.now().minusDays(3);

        TreeMap<Long, List<Integer>> toReturn = new TreeMap<>();

        List<LocationTracking> locationTracking = locationTrackingRepository.findAll();
        //List<Integer> fixedList = Arrays.asList(new Integer[11]);

        for(int i =0; i< locationTracking.size(); i++){
            //Check if today, add into list.
            LocationTracking thisTracking = locationTracking.get(i);
//            System.out.println("DATE" + thisTracking.getDatetime().toLocalDate().getDayOfMonth());
//            System.out.println("THE DATE IS " + today);
            //System.out.println("DATE" + thisTracking.getDatetime());
            //System.out.println(today.getDayOfMonth());
            //System.out.println(thisTracking.getDatetime().getDayOfMonth());
            if(thisTracking.getDatetime().getDayOfMonth() == today.getDayOfMonth()){
                Long thisLocationID = thisTracking.getLocation_id();
//                System.out.println("Tracking Location ID" + thisLocationID);
                //If locationID does not exist, put into Hashmap.
                if(!toReturn.containsKey(thisLocationID)){
                    List<Integer> fixedList = Arrays.asList(new Integer[11]);
                    fixedList.set(0, 0);
                    fixedList.set(1, 0);
                    fixedList.set(2, 0);
                    fixedList.set(3, 0);
                    fixedList.set(4, 0);
                    fixedList.set(5, 0);
                    fixedList.set(6, 0);
                    fixedList.set(7, 0);
                    fixedList.set(8, 0);
                    fixedList.set(9, 0);
                    fixedList.set(10, 0);
                    toReturn.put(thisLocationID , fixedList);

                }else{
                    //If between 11am - 12pm
                    List<Integer> x = toReturn.get(thisLocationID);

                    if(thisTracking.getDatetime().getHour() == 11 ){
                        int count = toReturn.get(thisLocationID).get(0);
                        x.set(0, ++count);
                    }

                    //If between 12am - 1pm, send notification for promo codes.
                    if(thisTracking.getDatetime().getHour() == 12 ){
                        int count = toReturn.get(thisLocationID).get(1);
                        x.set(1, ++count);
                    }

                    //If between 1pm - 2pm
                    if(thisTracking.getDatetime().getHour() == 13 ){
                        int count = toReturn.get(thisLocationID).get(2);
                        x.set(2, ++count);
                    }


                    //If between 11am - 12pm
                    if(thisTracking.getDatetime().getHour() == 14 ){
                        int count = toReturn.get(thisLocationID).get(3);
                        x.set(3,++count);
                    }

                    //If between 11am - 12pm
                    if(thisTracking.getDatetime().getHour() == 15 ){
                        int count = toReturn.get(thisLocationID).get(4);
                        x.set(4,++count);
                    }

                    //If between 11am - 12pm
                    if(thisTracking.getDatetime().getHour() == 16 ){
                        int count = toReturn.get(thisLocationID).get(5);
                        x.set(5,++count);
                    }

                    //If between 11am - 12pm
                    if(thisTracking.getDatetime().getHour() == 17 ){
                        int count = toReturn.get(thisLocationID).get(6);
                        x.set(6,++count);
                    }

                    //If between 11am - 12pm
                    if(thisTracking.getDatetime().getHour() == 18 ){
                        int count = toReturn.get(thisLocationID).get(7);
                        x.set(7,++count);
                    }

                    //If between 11am - 12pm
                    if(thisTracking.getDatetime().getHour() == 19 ){
                        int count = toReturn.get(thisLocationID).get(8);
                        x.set(8,++count);
                    }

                    //If between 11am - 12pm
                    if(thisTracking.getDatetime().getHour() == 20 ){
                        int count = toReturn.get(thisLocationID).get(9);
                        x.set(9,++count);
                    }

                    //If between 11am - 12pm
                    if(thisTracking.getDatetime().getHour() == 21 ){
                        int count = toReturn.get(thisLocationID).get(10);
                        x.set(10,++count);
                    }

                }

            }
        }

        //If after promocode has been blasted and foot traffic does not increase, increase the spawn rate of the Boss Monster.
//        int before = fixedList.get(1);
//        int after = fixedList.get(2);

        //If the after does not increase the footfall by at least 30%, increase the boss monster ratio. Cap the boss monster ratio at 50%
//        if(after/before * 100 <=30 ){
//            increaseSpawnRates(thisLocationID);
//        }
        return toReturn;
    }

    //Get the Foot Traffic For Today
    @GetMapping("/getFootTrafficForPast7Days")
    public TreeMap<String, List<Integer>> getFootTrafficForPast7Days() {
        //<Date, [20,30,40,10]
        List<Location> allLocations = getAllLocation();
        LocalDate now = LocalDateTime.now().toLocalDate();
        LocalDate weekAgo = LocalDateTime.now().toLocalDate().minusDays(7);
        List<LocationTracking> locationTracking = locationTrackingRepository.findAll();

        TreeMap<String, List<Integer> > toReturn = new TreeMap<>();


        for(int i =0; i< locationTracking.size(); i++){
            //If After 7 Days ago and Before Today
            if(locationTracking.get(i).getDatetime().toLocalDate().isBefore(now) &&
                    locationTracking.get(i).getDatetime().toLocalDate().isAfter(weekAgo)){

                //If doesn't exist
                if(!toReturn.containsKey(locationTracking.get(i).getDatetime().toLocalDate() + " " +locationTracking.get(i).getDatetime().toLocalDate().getDayOfWeek())){

                    List<Integer> fixedList = Arrays.asList(new Integer[allLocations.size()]);
                    toReturn.put(locationTracking.get(i).getDatetime().toLocalDate() + " " +locationTracking.get(i).getDatetime().toLocalDate().getDayOfWeek(), fixedList);

                    for(int j =0; j<allLocations.size(); j++){
                        fixedList.set(j , 0);
                    }
                }

                int count = toReturn.get(locationTracking.get(i).getDatetime().toLocalDate()+ " " +locationTracking.get(i).getDatetime().toLocalDate().getDayOfWeek()).get(locationTracking.get(i).getLocation_id().intValue() -1);
                toReturn.get(locationTracking.get(i).getDatetime().toLocalDate()+ " " +locationTracking.get(i).getDatetime().toLocalDate().getDayOfWeek()).set(locationTracking.get(i).getLocation_id().intValue() -1,++count);
            }

        }

        //Actionable Insight
        //Friday was the most popular day over the past week, and location 6 was the most popular location.
        int max = 0;
        String day = "";
        int maxlocation = 0;
        String location = "";
        List<Integer> fixedList = Arrays.asList(new Integer[allLocations.size()]);

        for(int j =0; j<allLocations.size(); j++){
            fixedList.set(j , 0);
        }

        for (Map.Entry<String, List<Integer>> entry : toReturn.entrySet()) {
            String key = entry.getKey();
            List<Integer> value = entry.getValue();
            int total = value.stream().mapToInt(i -> i).sum();

            if(total > max){
                max = total;
                day = key;
            }

            for(int i =0; i< value.size(); i++){
                int thisValue = fixedList.get(i);
                int x = thisValue + value.get(i);
                fixedList.set(i, x);
            }
        }


        for(int x = 0; x<fixedList.size(); x++){
            if(fixedList.get(x) > max){
                maxlocation = fixedList.get(x);
                location = x + 1 + "";
            }
        }

        String actionableInsight = "The Most Popular Day is " + day + ", and The Most Popular Location is Location" + location  + " With a Foot" +
                "Traffic of " + max + ".Suggestion: Put More Campaigns Along This Location" ;
        toReturn.put(actionableInsight, fixedList);

        //Location Names
        String locationNames = "";
        List<Location> locationList = locationRepository.findAll();
        for(int x = 0; x< locationList.size(); x++){
            locationNames += locationList.get(x).getLocation_description() + ",";
        }
        toReturn.put(locationNames, fixedList);

        return toReturn;
    }

    //Get the Foot Traffic For Today
    @GetMapping("/getAverageTimeSpentPerLocation")
    public TreeMap<Long, List<Integer>> getAverageTimeSpentPerLocation() {
        //1: [30,40,100]
        //2: [40,60,50]
        //3: [50,70,100]

        //Get Today's Date
        LocalDateTime today = LocalDateTime.now();

        TreeMap<Long, List<Integer>> toReturn = new TreeMap<>();

        List<LocationTracking> locationTracking = locationTrackingRepository.findAll();


        for(int i=0; i<locationTracking.size(); i++){
            //Check if today.
            LocationTracking thisTracking = locationTracking.get(i);
            if(thisTracking.getDatetime().getDayOfMonth() == today.getDayOfMonth()){
//                System.out.println(thisTracking.getDatetime().getDayOfMonth() );
//                System.out.println(today.getDayOfMonth() );
                long mbId = thisTracking.getMb_id();
                if(!toReturn.containsKey(mbId)){
                    List<Integer> fixedList = Arrays.asList(new Integer[getAllLocation().size()]);
                    for(int j =0; j<getAllLocation().size(); j++){
                        fixedList.set(j , 0);
                    }
                    toReturn.put(mbId, fixedList);
                }

                long locationID = thisTracking.getLocation_id();
                long count = toReturn.get(mbId).get(Math.toIntExact(locationID) -1 );
                List<Integer> x = toReturn.get(mbId);
                long newCount = count +3;
                x.set(Math.toIntExact(locationID)-1, Math.toIntExact(newCount));
                toReturn.put(mbId, x);


            }
        }


        return toReturn;
    }

    // Get a Single PromoCode
    public void updateSpawnRates() {
        //Tier 1 Spawn Rate
        String spawnrate = "0.4,0.3,0.25,0.15,0.1";
        //Tier 2 Spawn Rate

        //TIer 3 Spawn Rate
    }

    @PutMapping("/increaseSpawnRates")
    public Location increaseSpawnRates( Long location_id) {
        Location location = locationRepository.findById(location_id).get();
        //Increased Spawn Rate
        String newRate = "0.3,0.2,0.1,0.1,0.3";
        location.setSpawn_rate(newRate);
        Location updatedLocation = locationRepository.save(location);
        return updatedLocation;
    }

    @PutMapping("/decreaseSpawnRates")
    public Location decreaseSpawnRates( Long location_id) {
        Location location = locationRepository.findById(location_id).get();
        //Decrease Spawn Rate
        String newRate = "0.3,0.25,0.2,0.15,0.1";
        location.setSpawn_rate(newRate);
        Location updatedLocation = locationRepository.save(location);
        return updatedLocation;
    }



}


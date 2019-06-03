package com.example.bitmonsters.controller;
import com.example.bitmonsters.model.LocationTracking;
import com.example.bitmonsters.model.PromoCode;
import com.example.bitmonsters.model.PromoCodeUsage;
import com.example.bitmonsters.model.User;
import com.example.bitmonsters.repository.LocationTrackingRepository;
import com.example.bitmonsters.repository.PromoCodeListRepository;
import com.example.bitmonsters.repository.PromoCodeUsageRepository;
import com.example.bitmonsters.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class PromoCodeController {
    @Autowired
    PromoCodeListRepository promoCodeListRepository;

    @Autowired
    PromoCodeUsageRepository promoCodeUsageRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    LocationTrackingRepository locationTrackingRepository;

    // Get All PromoCodes
    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    @GetMapping("/getAllPromoCode")
    public List<PromoCode> getAllPromoCode() {
        return promoCodeListRepository.findAll();
    }

    // Get a Single PromoCode
    public PromoCode getPromoCode(String promo_code_id) {
        return promoCodeListRepository.findByPromoCodeId(promo_code_id);
    }

    // Get PromoCodeUsage
    @GetMapping("/getPromoCodeUsage")
    public TreeMap<String, Integer> getPromoCodeUsage() {
        //Get Today's Date
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime weekAgo = LocalDateTime.now().minusDays(7);
        List<PromoCodeUsage> promoCodeUsages = promoCodeUsageRepository.findAll();
        TreeMap<String, Integer> toReturn = new TreeMap<>();

        int max = 0;
        int min = 1000000;
        String maxCategory = "";
        String minCategory = "";

        for(int i =0; i< promoCodeUsages.size(); i++){
            //If After 7 Days ago and Before Today
            if(promoCodeUsages.get(i).getDatetime().isBefore(now) &&
                    promoCodeUsages.get(i).getDatetime().isAfter(weekAgo)){
                String promoCodeID = promoCodeUsages.get(i).getPromo_code_id();
                System.out.println(promoCodeID);
                PromoCode thisPromoCode = getPromoCode(promoCodeID);
                System.out.println(thisPromoCode);
                String category = thisPromoCode.getCategory();
                System.out.println(category);
                //Check the promo code category
                //If doesn't exist
                if(!toReturn.containsKey(category)){
                    toReturn.put(category, 0);
                }

                int count = toReturn.get(category);
                count = count + promoCodeUsages.get(i).getCount();
                toReturn.put(category, count);

                if(count > max){
                    max = count;
                    maxCategory = category;
                }

                if(count < min){
                    min = count;
                    minCategory = category;
                }

                }

            }
        //Actionable Insight
        String actionableInsight = "/The Most Popular Coupons Are From The " + maxCategory + " Category " +
                "And The Least Popular Are From The " + minCategory + " Category. Suggestion: Increase The Incentives For The Coupons In The " + minCategory + "For The Next Round";
        toReturn.put(actionableInsight, 0);
        return toReturn;
    }

    @GetMapping("/getAgeRange")
    public TreeMap<String, Integer> getAgeRange() {

        List<User> userAgeRange = userRepository.findAll();
        TreeMap<String, Integer> toReturn   =
                new TreeMap<>();
//        HashMap<String, Integer> toReturn = new HashMap<>();
        toReturn.put("18-20",0);
        toReturn.put("21-30",0);
        toReturn.put("31-40",0);
        toReturn.put("41-50",0);
        toReturn.put("51-60",0);

        for(int i =0; i< userAgeRange.size(); i++){
            User user = userAgeRange.get(i);
            int age = user.getAge();

            if(age >=18 && age<=20){
                int count = toReturn.get("18-20");
                toReturn.put("18-20", ++count);
            }

            if(age >=21 && age <=30){
                int count = toReturn.get("21-30");
                toReturn.put("21-30", ++count);
            }

            if(age >=31 && age <=40){
                int count = toReturn.get("31-40");
                toReturn.put("31-40", ++count);
            }

            if(age >=41 && age <=50){
                int count = toReturn.get("41-50");
                toReturn.put("41-50", ++count);
            }

            if(age >=51 && age <=60){
                int count = toReturn.get("51-60");
                toReturn.put("51-60", ++count);
            }

        }

        return toReturn;
    }

    @PostMapping("/adduser")
    public User createUser(@Valid @RequestBody User user) {
        LocalDateTime now = LocalDateTime.now();
        user.setDatetime(now);
        return userRepository.save(user);
    }

    @PostMapping("/addPromoCodeUsage")
    public PromoCodeUsage createPromoCodeUsage(@Valid @RequestBody PromoCodeUsage promoCodeUsage) {
        LocalDateTime now = LocalDateTime.now();
        promoCodeUsage.setDatetime(now);
        return promoCodeUsageRepository.save(promoCodeUsage);
    }

    @GetMapping("/getDetails")
    public TreeMap<String, Integer> getDetails() {
        //Get Today's Date
        LocalDateTime now = LocalDateTime.now();
        List<User> users = userRepository.findAll();
        List<LocationTracking> locationTracking = locationTrackingRepository.findAll();

        int numOfUsers = 0;
        int numOfSpawnedMonsters = 0;
        TreeMap<String, Integer> toReturn = new TreeMap<>();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getDatetime().getDayOfMonth() == now.getDayOfMonth()) {
                ++numOfUsers;
            }

        }

        for (int i = 0; i < locationTracking.size(); i++) {
            if (locationTracking.get(i).getDatetime().getDayOfMonth() == now.getDayOfMonth()) {
                ++numOfSpawnedMonsters;
            }

        }

        toReturn.put("Number Of Bitcatchers Loaned", numOfUsers);
        toReturn.put("Number Of BitMonsters Spawned", numOfSpawnedMonsters);

        return toReturn;
    }



}


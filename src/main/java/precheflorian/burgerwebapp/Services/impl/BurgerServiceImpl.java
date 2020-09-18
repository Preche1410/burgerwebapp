package precheflorian.burgerwebapp.Services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import precheflorian.burgerwebapp.Models.Burger.BurgerResponse;
import precheflorian.burgerwebapp.Models.Burger.BurgerVenueInfo;
import precheflorian.burgerwebapp.Models.Venue.Venue;
import precheflorian.burgerwebapp.Models.Venue.VenueResult;
import precheflorian.burgerwebapp.Services.BurgerRecognizeService;
import precheflorian.burgerwebapp.Services.BurgerService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;

@Service
public class BurgerServiceImpl implements BurgerService {
    private static final String TARTU_BURGER_VENUES_URL="https://api.foursquare.com/v2/venues/search?client_id=JGUYO04FCR5YRTWJM1YMFJUQWFDZ3WXX5A3ALZEG12PMHHPK&client_secret=P2PJH3K2OAB14GFPEO2WUD4Q20DJN3JVOA4VNC2XOVL4YNEI&v=20200918&near=tartu&intent=browse&radius=2000&query=burger%20joint&limit=10";
    private static final String BASE_PLACE_API_URL = "https://foursquare.com/v/burger/";
    @Autowired
    BurgerService burgerService;
    @Autowired
    private BurgerRecognizeService burgerRecognizeService;
    @Override
    public VenueResult getVenues(){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(TARTU_BURGER_VENUES_URL,VenueResult.class);
    }

    @Override
    public List<String> getImageUrls(String url){
        List<String> imageUrls = new ArrayList<>();
   try {
       Document doc = Jsoup.connect(url).get();
       doc.select("img").forEach(Link -> {
           if (Link.toString().contains("600x600")){
               String startUrl = Link.toString()
                       .substring(Link.toString().indexOf("\"")+1);
           String fullUrl= startUrl.substring(0,startUrl.indexOf("\""));
            imageUrls.add(fullUrl);
           }
       });
   }catch (Exception e){
       e.printStackTrace();
   }
   return imageUrls;
   }
   @Override
    public List<BurgerVenueInfo>
   getBurgerVenuesInfo(VenueResult venueResult,
                       List<BurgerVenueInfo>burgerVenues){
        for (Venue venue : venueResult.getResponse().getVenues()){
            String url = BASE_PLACE_API_URL + venue.getId() +"/" +"image";
           List<String> imageUrls = burgerService.getImageUrls(url);
           BurgerResponse response = burgerRecognizeService.getUrlWithBurger(imageUrls);
           if (response!=null){
               BurgerVenueInfo burgerVenueInfo= BurgerVenueInfo.of(venue.getName(),
                       venue.getLocation().getAddress(),
                       response.getUrlWithBurger());
               burgerVenues.add(burgerVenueInfo);
           }
        }
        return burgerVenues;
   }
}


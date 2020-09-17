package precheflorian.burgerwebapp.Services;

import precheflorian.burgerwebapp.Models.Burger.BurgerVenueInfo;
import precheflorian.burgerwebapp.Models.Venue.VenueResult;

import java.util.List;

public interface BurgerService {
    VenueResult getVenues();
    List<String> getImageUrls(String url);
    List<BurgerVenueInfo>getBurgerVenuesInfo(VenueResult venueResult,
                                             List<BurgerVenueInfo> burgerVenues);
}

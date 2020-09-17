package precheflorian.burgerwebapp.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import precheflorian.burgerwebapp.Models.Burger.BurgerVenueInfo;
import precheflorian.burgerwebapp.Models.Venue.VenueResult;
import precheflorian.burgerwebapp.Services.BurgerService;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class BurgerController {
    @Autowired
    BurgerService burgerService;
    @GetMapping
    public ResponseEntity<List<BurgerVenueInfo>>getBurgerJointsInTartu(){
        VenueResult venueResult = burgerService.getVenues();
        List<BurgerVenueInfo> burgerVenueInfos= new ArrayList<>();
        return new ResponseEntity<>(burgerService
                .getBurgerVenuesInfo(venueResult,
                        burgerVenueInfos), HttpStatus.OK);
    }

}

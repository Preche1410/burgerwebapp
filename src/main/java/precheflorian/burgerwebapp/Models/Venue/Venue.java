package precheflorian.burgerwebapp.Models.Venue;

import lombok.Data;
import precheflorian.burgerwebapp.Models.Venue.location.Location;

@Data
public class Venue {
    private String id;
    private String name;
    private Location location;
}

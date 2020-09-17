package precheflorian.burgerwebapp.Models.Venue;

import lombok.Data;

import java.util.List;

@Data
public class VenuesResponse {
    private List<Venue> venues;
}

package precheflorian.burgerwebapp.Models.Burger;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class RecognizeBodyPayload {
    private Map<String,List<String>> urls;
}

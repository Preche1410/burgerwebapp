package precheflorian.burgerwebapp.Models.Burger;

import lombok.Data;

import java.util.List;
@Data
public class RecognizeBodyPayload {
    private List<String> urls;
}

package precheflorian.burgerwebapp.Services;

import precheflorian.burgerwebapp.Models.Burger.BurgerResponse;

import java.util.List;

public interface BurgerRecognizeService {
    BurgerResponse getUrlWithBurger(List<String> photoUrls);
}

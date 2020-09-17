package precheflorian.burgerwebapp.Services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import precheflorian.burgerwebapp.Models.Burger.BurgerResponse;
import precheflorian.burgerwebapp.Models.Burger.RecognizeBodyPayload;
import precheflorian.burgerwebapp.Services.BurgerRecognizeService;

import java.util.List;

@Service
public class BurgerRecognizeServiceImpl implements BurgerRecognizeService {
    private static final String BURGER_FINDER_URL=
            "https://pplkdijj76.execute-api.eu-west-1.amazonaws.com/prod/recognize";
    RestTemplate restTemplate = new RestTemplate();
    @Autowired
    private RestTemplateAutoConfiguration restTemplateAutoConfiguration;
    @Override
    public BurgerResponse getUrlWithBurger(List<String> imageUrls)
    {
        RecognizeBodyPayload body = new RecognizeBodyPayload();
        BurgerResponse burgerResponse=null;
        if (imageUrls.size()>0){
            body.setUrls(imageUrls);
            try {

                burgerResponse = restTemplate.postForObject(BURGER_FINDER_URL,body,BurgerResponse.class);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return burgerResponse;
    }
}

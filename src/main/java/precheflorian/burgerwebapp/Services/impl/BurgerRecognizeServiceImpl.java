package precheflorian.burgerwebapp.Services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import precheflorian.burgerwebapp.Models.Burger.BurgerResponse;
import precheflorian.burgerwebapp.Services.BurgerRecognizeService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
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
        Map<String,List<String>> payloadMap = new HashMap<>();
        BurgerResponse burgerResponse=null;
        if (imageUrls.size()>0){
            payloadMap.put("urls",imageUrls);
            try {

                burgerResponse = restTemplate.postForObject(BURGER_FINDER_URL,payloadMap,BurgerResponse.class);
            }catch (Exception e){
                log.info("No burger response received",e);
            }
        }
        return burgerResponse;
    }
}

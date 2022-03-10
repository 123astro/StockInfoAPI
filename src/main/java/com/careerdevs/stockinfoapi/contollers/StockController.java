package com.careerdevs.stockinfoapi.contollers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/query") // endpoint

public class StockController {

    @Autowired
    private Environment env;

    private String alpha_Vantage_Endpoint = "https://www.alphavantage.co/query?function=OVERVIEW" +
            "&symbol=";

    @GetMapping("/")
    public Object getStock(RestTemplate restTemplate,
                           //@PathVariable("stock") String stockTicker, // path example
                           @RequestParam("name") String name  // request example
    ) {
        System.out.println(name);
        String apikey = env.getProperty("ALPHA_KEY");
        return restTemplate.getForObject(alpha_Vantage_Endpoint + name + "&interval=5min&outputsize" +
                        "=full&apikey=" + apikey,
                Object.class);
    }
}

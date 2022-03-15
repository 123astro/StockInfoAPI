package com.careerdevs.stockinfoapi.contollers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/stocks/") // endpoint

public class StockController {

    @Autowired
    private Environment env;

    private String alpha_Vantage_Endpoint = "https://www.alphavantage.co/query?function=OVERVIEW" +
            "&symbol=";

    @GetMapping("overview")
    public Object getStock(RestTemplate restTemplate,
                         //  @PathVariable("name") String name // path example
                          @RequestParam("symbol") String symbol// request example

    ) {
        String apikey = env.getProperty("ALPHA_KEY");
        return restTemplate.getForObject(alpha_Vantage_Endpoint + symbol + "&interval=5min&" +
                        "=full&apikey=" + apikey,
                Object.class);
    }
}

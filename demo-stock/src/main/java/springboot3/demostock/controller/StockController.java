package springboot3.demoorder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {
    @Autowired
    RestTemplate restTemplate;

    public String add(){
        System.out.println("下单成功");
        String msg = restTemplate.getForObject("http://localhost:8082/add", String.class);
        return "hello word" + msg;
    }
}

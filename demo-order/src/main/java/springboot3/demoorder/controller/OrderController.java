package springboot3.demoorder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import springboot3.demoorder.service.DemoStockService;

@RestController
@RequestMapping("/order")
@RefreshScope
public class OrderController {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    DemoStockService demoStockService;
    @Value("${author}")
    String author;

    @GetMapping("/add")
    public String add(){
        String msg = restTemplate.getForObject("http://demo-stock/stock/add", String.class);
        return author + "hello word" + msg;
    }

    @GetMapping("/reduce")
    public String reduce(){
        String msg = demoStockService.reduce();
        return author + "hello word" + msg;
    }
}

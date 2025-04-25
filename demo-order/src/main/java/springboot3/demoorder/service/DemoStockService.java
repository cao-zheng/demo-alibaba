package springboot3.demoorder.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "demo-stock",path = "/stock")
public interface DemoStockService {
    @GetMapping("/reduce")
    String reduce();
}
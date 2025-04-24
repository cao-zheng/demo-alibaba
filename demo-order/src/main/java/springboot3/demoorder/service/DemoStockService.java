package springboot3.demoorder.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "demo-stock",path = "/reduce")
public interface DemoStock {
    @RequestMapping("/reduce")
    String reduce();
}

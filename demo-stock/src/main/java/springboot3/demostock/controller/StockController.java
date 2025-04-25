package springboot3.demostock.controller;

import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/stock")
@RefreshScope
public class StockController {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    RestTemplate restTemplate;
    AtomicInteger sum = new AtomicInteger(50);
    @Value("${author}")
    String author;
    @GetMapping("/add")
    public String add(){
        int current = sum.incrementAndGet();
        jdbcTemplate.update("insert into demo_stock values (?,?)",current,"123");
        System.out.println("添加库存成功");
        return author + "剩余货物" + current;
    }

    @GetMapping("/reduce")
    public String reduce(){
        int current = sum.decrementAndGet();
        jdbcTemplate.update("delete from demo_stock where id = ?",current);
        System.out.println("扣减库存成功");
        return author + "剩余货物" + current;
    }
}

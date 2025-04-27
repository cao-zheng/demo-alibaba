package springboot3.demoorder.controller;

import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import springboot3.demoorder.service.DemoStockService;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/order")
@RefreshScope
public class OrderController {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    DemoStockService demoStockService;
    @Value("${author}")
    String author;
    AtomicInteger ordernum = new AtomicInteger(0);

    @GetMapping("/add")
    @GlobalTransactional
    public String add(){
        //订单 + 1
        String sql = "insert into demo_order values ( " + ordernum.get() + ",'xxx')";
        jdbcTemplate.update(sql);
        //库存 - 1
        String msg = demoStockService.reduce();
        ordernum.incrementAndGet();
        return author + "hello word" + msg;
    }

    @GetMapping("/reduce")
    @GlobalTransactional
    public String reduce(){
        //订单 - 1
        String sql = "delete from demo_order where id = " + ordernum.get();
        jdbcTemplate.update(sql);
        //库存 + 1
        String msg = restTemplate.getForObject("http://demo-stock/stock/add", String.class);
        ordernum.decrementAndGet();
        return author + "hello word" + msg;
    }

    @GetMapping("/sentinelTest")
    public String sentinelTest() throws Exception{
        Thread.sleep(2000);
        return author + "hello word";
    }
}

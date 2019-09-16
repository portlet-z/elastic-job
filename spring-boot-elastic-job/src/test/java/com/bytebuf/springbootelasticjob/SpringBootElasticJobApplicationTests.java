package com.bytebuf.springbootelasticjob;

import com.bytebuf.springbootelasticjob.model.Order;
import com.bytebuf.springbootelasticjob.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootElasticJobApplicationTests {

    @Autowired
    private OrderService orderService;

    @Test
    public void testOrder() {
        orderService.insertOrder();
    }

    @Test
    public void testGetOrder() {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.SECOND, -30);

        List<Order> orders = orderService.getOrder(now, 2, 1);
        System.out.println(orders);
    }

    @Test
    public void contextLoads() {
    }

}

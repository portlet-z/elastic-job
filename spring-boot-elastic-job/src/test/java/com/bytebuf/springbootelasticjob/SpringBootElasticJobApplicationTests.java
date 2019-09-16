package com.bytebuf.springbootelasticjob;

import com.bytebuf.springbootelasticjob.dao.JdOrderMapper;
import com.bytebuf.springbootelasticjob.dao.TmallOrderMapper;
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

    @Autowired
    private JdOrderMapper jdOrderMapper;

    @Autowired
    private TmallOrderMapper tmallOrderMapper;

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
    public void testThirdOrder() {
        orderService.produceThirdOrder();
    }

    @Test
    public void fetchJdOrder() {
        System.out.println(jdOrderMapper.getNotFetchedOrder(5));
    }

    @Test
    public void fetchTmallOrder() {
        System.out.println(tmallOrderMapper.getNotFetchedOrder(5));
    }

    @Test
    public void contextLoads() {
    }

}

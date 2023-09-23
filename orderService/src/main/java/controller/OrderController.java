package controller;

import dto.Order;
import dto.OrderEvent;
import kafka.OrderProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

public class OrderController {
    @Autowired
    private OrderProducer orderProducer;

//    public OrderController(OrderProducer orderProducer) {
//        this.orderProducer = orderProducer;
//    }

    @RequestMapping("/orders")
    public String placeOrder(@RequestBody Order order){
        order.setOrderId(Integer.parseInt(UUID.randomUUID().toString()));

        OrderEvent orderEvent=new OrderEvent();
        orderEvent.setStatus("PENDING");
        orderEvent.setMessage("order status is still pending ");
        orderEvent.setOrder(order);

        orderProducer.sendMesage(orderEvent);
        return  "successfully order is placed";


    }
}

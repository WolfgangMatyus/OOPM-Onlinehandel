package Onlinehandel.util;

import Onlinehandel.entities.Order;
import Onlinehandel.provided.Matcher;

public class OnRouteMatcher extends Object implements Matcher<Order> {


    @Override
    public boolean matches(Order order) {
        return (order.isCollected() && !order.isDelivered());
    }
}

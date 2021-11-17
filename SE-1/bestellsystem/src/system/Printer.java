package system;

import datamodel.Order;

public interface Printer {

    StringBuffer printOrders(Iterable<Order> orders);
    StringBuffer printOrder(Order order);
    Formatter createFormatter();
}

package system;

import datamodel.Order;
import datamodel.TAX;

public interface Calculator {
    long calculateValue(Iterable<Order> orders);
    long calculateValue(Order orders);
    long calculateIncludedVAT(Iterable<Order> orders);
    long calculateIncludedVAT(Order orders);
    long calculateIncludedVAT(long price, TAX taxRate);

}

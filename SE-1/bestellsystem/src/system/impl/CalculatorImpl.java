package system.impl;

import datamodel.Order;
import datamodel.OrderItem;
import datamodel.TAX;
import system.Calculator;

 public class CalculatorImpl implements Calculator {

    @Override
    public long calculateValue(Iterable<Order> orders) {
        long value = 0;
        for (Order o : orders) {
            value += calculateValue(o);
        }
        return value;
    }

    @Override
    public long calculateValue(Order order) {
        long value = 0;        // hard-coded return value
        for (OrderItem oi : order.getItemsAsArray()) {
            value += oi.getArticle().getUnitPrice() * oi.getUnitsOrdered();
        }
        return value;
    }

    @Override
    public long calculateIncludedVAT(Iterable<Order> orders) {
        return 0;
    }

    @Override
    public long calculateIncludedVAT(Order order) {
        long vat = 0;
        for (OrderItem oi : order.getItemsAsArray()) {
            vat += calculateIncludedVAT(oi.getArticle().getUnitPrice()* oi.getUnitsOrdered(), oi.getArticle().getTax()) ;
        }
        return vat;
    }

    @Override
    public long calculateIncludedVAT(long price, TAX taxRate) {
        double vat =( price - (  price/(1.0 + (taxRate.rate()/100) )  )     );
        return Math.round(vat);
    }
}

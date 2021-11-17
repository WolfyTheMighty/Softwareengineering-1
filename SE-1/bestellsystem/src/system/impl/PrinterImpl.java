package system.impl;

import application.Application_C1;
import datamodel.Currency;
import datamodel.Order;
import system.Calculator;
import system.Formatter;
import system.Printer;

import java.util.HashMap;

public class PrinterImpl implements Printer {

    private final Calculator calculator;
    Formatter formatter = createFormatter();

    public PrinterImpl(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public StringBuffer printOrders(Iterable<Order> orders) {
        HashMap<Long, Integer> oq = new HashMap<>();
        Formatter otfmt = new Formatter.OrderTableFormatter(new Object[][]{
                // five column table with column specs: width and alignment ('[' left, ']' right)
                {12, '['}, {20, '['}, {36, '['}, {10, ']'}, {10, ']'}
        })
                .liner("+-+-+-+-+-+")        // print table header
                .hdr("||", "Order-Id", "Customer", "Ordered Items", "Order", "MwSt.")
                .hdr("||", "", "", "", "Value", "incl.")
                .liner("+-+-+-+-+-+")
                .liner("||");


        for (Order order : orders) {

            if (!oq.containsKey(order.getCustomer().getId())) {
                oq.put(order.getCustomer().getId(), 1);
            } else {
                long id = order.getCustomer().getId();
//                System.out.println(id);
                int x = oq.get(id);
//                System.out.println(x);
                oq.put(id, x + 1);
            }


            printOrder(otfmt, order, oq);
            otfmt
                    .liner("| | |-|-|-|")
                    .line("", "", "total:", calculator.calculateValue(order), calculator.calculateIncludedVAT(order))
                    .liner("| | | |=|=|")
                    .liner("| | | | | |");
        }

        long totalAllOrders = calculator.calculateValue(orders);        // calculate value of all orders
        long totalVAT = calculator.calculateIncludedVAT(orders);        // calculate compound VAT (MwSt.) for all orders

        otfmt.lineTotal(totalAllOrders, totalVAT, Currency.EUR);
        otfmt.print();    // output table
        oq.clear();
    }

    @Override
    public StringBuffer printOrder(Order order) {
        return null;
    }

    @Override
    public Formatter createFormatter() {
        return new FormatterImpl();
    }
}

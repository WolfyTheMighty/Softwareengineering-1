package system.impl;

import datamodel.Currency;
import datamodel.Order;
import system.Calculator;
import system.Formatter;
import system.Printer;

import java.io.*;
import java.util.HashMap;

class PrinterImpl implements Printer {

    private final Calculator calculator;
    Formatter formatter = createFormatter();
    HashMap<Long, Integer> oq = new HashMap<>();
    Formatter.OrderTableFormatter otfmt;

    public PrinterImpl(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public void printOrdersToFile(Iterable<Order> orders, String filepath) throws IOException {
//        File file = new File("filepath");
//        FileWriter fw = new FileWriter(file);
        Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(filepath), "utf-8"));

        writer.write(printOrders(orders).toString());

        writer.close();
    }

    @Override
    public StringBuffer printOrders(Iterable<Order> orders) {

        otfmt = new OrderTableFormatterImpl(formatter, new Object[][]{
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


            printOrder(order);
            otfmt
                    .liner("| | |-|-|-|")
                    .line("", "", "total:", calculator.calculateValue(order), calculator.calculateIncludedVAT(order))
                    .liner("| | | |=|=|")
                    .liner("| | | | | |");
        }

        long totalAllOrders = calculator.calculateValue(orders);        // calculate value of all orders
        long totalVAT = calculator.calculateIncludedVAT(orders);        // calculate compound VAT (MwSt.) for all orders

        otfmt.lineTotal(totalAllOrders, totalVAT, Currency.EUR);
        //otfmt.print();    // output table
        oq.clear();
        return formatter.getBuffer();
    }

    @Override
    public StringBuffer printOrder(Order order) {
        for (int i = 0; i < order.getItemsAsArray().length; i++) {
            int x = order.getItemsAsArray()[i].getUnitsOrdered();

            long vat = (calculator.calculateIncludedVAT(order.getItemsAsArray()[i].getArticle().getUnitPrice(), order.getItemsAsArray()[i].getArticle().getTax())) * x;
//            System.out.println(vat);
            String quantity = order.getItemsAsArray()[i].getUnitsOrdered() > 1 ? order.getItemsAsArray()[i].getUnitsOrdered() + "x" : "";

            String id = i == 0 ? order.getId() : "";
            String name = i == 0 ? order.getCustomer().getFirstName() + "'s" : "";

            if (oq.containsKey(order.getCustomer().getId()) && i == 0) {
                switch ((int) oq.get(order.getCustomer().getId()) % 10) {
                    case 1 -> name += " " + oq.get(order.getCustomer().getId()) + "st order";
                    case 2 -> name += " " + oq.get(order.getCustomer().getId()) + "nd order";
                    case 3 -> name += " " + oq.get(order.getCustomer().getId()) + "rd order";
                    default -> name += " " + oq.get(order.getCustomer().getId()) + "th order";
                }
            }
            otfmt.line(id,
                    name,
                    order.getItemsAsArray()[i].toString() + ", " + quantity + " " + String.format("%.2f", (double) order.getItemsAsArray()[i].getArticle().getUnitPrice() / 100),
                    (order.getItemsAsArray()[i].getArticle().getUnitPrice()) * (order.getItemsAsArray()[i].getUnitsOrdered()),
                    vat);

        }
        return formatter.getBuffer();
    }

    @Override
    public Formatter createFormatter() {
        return new FormatterImpl();
    }
}

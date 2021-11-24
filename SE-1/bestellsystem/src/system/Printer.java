package system;

import datamodel.Order;

import java.io.IOException;

public interface Printer {

    /**
     * Print orders as table to a file.
     *
     * Conditions:
     *  ‐ creates new file or overwrites an existing file.
     *  ‐ not existing parts of the path are created, throws IOException
     *    if this is not possible.
     *
     * @param orders list of orders to print.
     * @param filepath path and name of the output file.
     * @throws IOException for errors.
     */
    void printOrdersToFile( Iterable<Order> orders, String filepath ) throws IOException;

    StringBuffer printOrders(Iterable<Order> orders);
    StringBuffer printOrder(Order order);
    Formatter createFormatter();
}

package system;

import datamodel.Order;

import java.io.IOException;

public interface Printer {

    /**
     * Print orders as table to a file.
     *
     * Conditions:
     *  - creates new file or overwrites an existing file.
     *  - not existing parts of the path are created, throws IOException
     *    if this is not possible.
     *
     * @param orders list of orders to print.
     * @param filepath path and name of the output file.
     * @throws IOException for errors.
     */
    void printOrdersToFile( Iterable<Order> orders, String filepath ) throws IOException;

    /**
     * Format all orders to a table
     * @param orders
     * @return
     */
    StringBuffer printOrders(Iterable<Order> orders);

    /**
     * Format table part of a single order
     * @param order order to be formatted to table
     * @return formatted table line
     */
    StringBuffer printOrder(Order order);

    /**
     * Creates a Formatter object which can apply the formatting functions
     * @return Formatter object
     */
    Formatter createFormatter();
}

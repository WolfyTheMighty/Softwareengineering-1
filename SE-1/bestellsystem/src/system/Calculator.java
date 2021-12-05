package system;

import datamodel.Order;
import datamodel.TAX;

/**
 * Public Interface of a Calculator component that provides methods to calculate prices and sales/VAT taxes
 *
 * @author Arpad Horvath
 * @version {@value package_info#Version}
 * @since "0.1.0"
 */
public interface Calculator {

    /**
     * Calculates the compound value of all orders
     * @param orders List of orders to calculate value of
     * @return total price
     */
    long calculateValue(Iterable<Order> orders);

    /**
     * Calculates  value of one orders
     * @param orders order to calculate value of
     * @return  value of the order
     */
    long calculateValue(Order orders);

    /**
     * Calculates the compound VAT of all orders
     * @param orders List of orders to calculate VAT of
     * @return total VAT
     */
    long calculateIncludedVAT(Iterable<Order> orders);

    /**
     * Calculates the  VAT of one orders
     * @param orders order to calculate VAT of
     * @return  VAT
     */
    long calculateIncludedVAT(Order orders);

    /**
     * Calculates the included VAT based on price and TAX rate
     * @param price price
     * @param taxRate tax rate
     * @return included VAT
     */
    long calculateIncludedVAT(long price, TAX taxRate);

}

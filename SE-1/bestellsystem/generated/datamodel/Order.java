
import java.util.*;

/**
 * Class for entity type Order. Order represents a contractual relation between a Customer and a selling business for purchasing (ordered) items.
 * 
 * @since 1.0
 * @version {@value package_info#Version}
 * @author sgra64
 */
public class Order {

    /**
     * Default constructor
     */
    public Order() {
    }

    /**
     * id attribute, null: invalid, can be set only once
     */
    private String id = null;

    /**
     * reference to owning Customer, final, is never null
     */
    private final Customer customer;

    /**
     * date/time order was created
     */
    private final Date creationDate;

    /**
     * items ordered as part of this order
     */
    private final List<OrderItem> items;


    /**
     * 
     */
    public Customer owns;





    /**
     * Constructor with customer owning the order as argument.
     * @param customer owner of order, customer who placed that order, throws IllegalArgumentException if customer is null
     */
    public void Order(Customer customer) {
        // TODO implement here
    }

    /**
     * Id getter. Id can only be set once since id are immutable after assignment.
     * @return order id, may be invalid (null) if id is still unassigned
     */
    public String getId() {
        // TODO implement here
        return "";
    }

    /**
     * Id setter. Id can only be set once since id is immutable after assignment.
     * @param id assignable id ( != null ) if id attribute is still unassigned (null)
     * @return chainable self-reference
     */
    public Order setId(String id) {
        // TODO implement here
        return null;
    }

    /**
     * customer getter.
     * @return customer who owns the order, cannot be null
     */
    public Customer getCustomer() {
        // TODO implement here
        return null;
    }

    /**
     * creationDate getter, returns the time/date when the oder was created.
     * @return time/date when order was created
     */
    public Date getCreationDate() {
        // TODO implement here
        return null;
    }

    /**
     * creationDate setter.
     * @param dateAsLong dateAsLong time/date when order was created (provided as long value in milliseconds since 01/01/1970)
     * @return chainable self-reference
     */
    public Order setCreationDate(long dateAsLong) {
        // TODO implement here
        return null;
    }

    /**
     * Return number of items that are part of the order.
     * @return number of ordered items
     */
    public int itemsCount() {
        // TODO implement here
        return 0;
    }

    /**
     * Ordered items getter (as {@code Iterable<OrderItem>}).
     * @return ordered items as {@code Iterable<OrderItem>}
     */
    public Iterable<OrderItem> getItems() {
        // TODO implement here
        return null;
    }

    /**
     * Ordered items getter (as {@code OrderItem[]}).
     * @return ordered items as {@code OrderItem[]}
     */
    public OrderItem[ ] getItemsAsArray() {
        // TODO implement here
        return null;
    }

    /**
     * Ordered items getter (as {@code Stream<OrderItem>}).
     * @return ordered items as {@code Stream<OrderItem>}
     */
    public Stream<OrderItem> getItemsAsStream() {
        // TODO implement here
        return null;
    }

    /**
     * Create new item and add to order.
     * @param article ordered article
     * @param units units of articles ordered
     * @return chainable self-reference
     */
    public Order addItem(Article article, int units) {
        // TODO implement here
        return null;
    }

    /**
     * Delete i-th item with constraint: {@code i >= 0 && i < items.size()}, otherwise the method has no effect.
     * @param i index in order items
     */
    public void deleteItem(int i) {
        // TODO implement here
    }

    /**
     * Delete all ordered items.
     */
    public void deleteAllItems() {
        // TODO implement here
    }

}
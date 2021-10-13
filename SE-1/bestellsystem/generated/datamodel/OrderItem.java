
import java.util.*;

/**
 * Class that describes an ordered item as part of an Order. Orders may have multiple order items.
 * 
 * @since 1.0
 * @version {@value package_info#Version}
 * @author sgra64
 */
public class OrderItem {

    /**
     * Default constructor
     */
    public OrderItem() {
    }

    /**
     * ordered article, immutable, final
     */
    private final Article article;

    /**
     * number of articles ordered
     */
    private int unitsOrdered;




    /**
     * Constructor with article and units arguments.
     * @param article ordered article, throws IllegalArgumentException if article is null
     * @param unitsOrdered number of articles ordered
     */
    public void OrderItem(Article article, int unitsOrdered) {
        // TODO implement here
    }

    /**
     * article getter. Attribute article cannot be changed has therefore has no setter (immutable attribute).
     * @return reference to the ordered article
     */
    public Article getArticle() {
        // TODO implement here
        return null;
    }

    /**
     * unitsOrdered getter.
     * @return number of article ordered
     */
    public int getUnitsOrdered() {
        // TODO implement here
        return 0;
    }

    /**
     * unitsOrdered setter with constraint: {@code units >= 0}, otherwise the method has no effect.
     * @param units updated number of articles ordered
     */
    public void setUnitsOrdered(int units) {
        // TODO implement here
    }

}
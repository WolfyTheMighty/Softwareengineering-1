package datamodel;

import java.util.*;

/**
 * Class that describes an ordered item as part of an Order. Orders may have multiple order items.
 *
 * @author Arpad Horvath
 * @version {@value package_info#Version}
 * @since 1.0
 */
public class OrderItem {
    /**
     * ordered article, immutable, final
     */
    private final Article article;

    /**
     * number of articles ordered
     */
    private int unitsOrdered;

    /**
     * Default constructor
     */
    public OrderItem() {
        article = null;
    }


    /**
     * Constructor with article and units arguments.
     *
     * @param article      ordered article, throws IllegalArgumentException if article is null
     * @param unitsOrdered number of articles ordered
     */
    public OrderItem(Article article, int unitsOrdered) {
        this.article = article;
        this.unitsOrdered = unitsOrdered;
    }

    /**
     * article getter. Attribute article cannot be changed has therefore has no setter (immutable attribute).
     *
     * @return reference to the ordered article
     */
    public Article getArticle() {
        return article;
    }

    /**
     * unitsOrdered getter.
     *
     * @return number of article ordered
     */
    public int getUnitsOrdered() {

        return unitsOrdered;
    }

    /**
     * unitsOrdered setter with constraint: {@code units >= 0}, otherwise the method has no effect.
     *
     * @param units updated number of articles ordered
     */
    public void setUnitsOrdered(int units) {
        if (units >= 0) this.unitsOrdered = units;
    }

    @Override
    public String toString() {
        return unitsOrdered + " " +getArticle().getDescription() + " (" + getArticle().getId() +")"
                ;
    }
}
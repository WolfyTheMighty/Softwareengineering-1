
import java.util.*;

/**
 * Class for entity type Article. An article is an entity that can be  referenced in order items.
 * 
 * @since 0.1.0
 * @version {@value package_info#Version}
 * @author sgra64
 */
public class Article {

    /**
     * Default constructor
     */
    public Article() {
    }

    /**
     * id attribute, null: invalid, can be set only once
     */
    private String id = null;

    /**
     * article description, never null, mapped to ""
     */
    private String description = "";

    /**
     * price in cent per article (unit); negative values are mapped to 0
     */
    private long unitPrice = 0;

    /**
     * currency in which price is quoted, EUR is the default currency
     */
    private Currency currency = Currency.EUR;

    /**
     * tax rate applicable to article, German regular MwSt is the default tax rate
     */
    private TAX tax = TAX.GER_VAT;




    /**
     * Default constructor.
     */
    public void Article() {
        // TODO implement here
    }

    /**
     * Public constructor with description and price arguments.
     * @param descr descriptive text for article
     * @param unitPrice unitPrice price (in cent) for one unit of the article
     */
    public void Article(String descr, long unitPrice) {
        // TODO implement here
    }

    /**
     * Id getter. Id can only be set once since id are immutable after assignment.
     * @return article id, may be invalid (null) if still unassigned
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
    public Article setId(String id) {
        // TODO implement here
        return null;
    }

    /**
     * description getter.
     * @return descriptive text for article
     */
    public String getDescription() {
        // TODO implement here
        return "";
    }

    /**
     * description setter.
     * @param descr descriptive text for article
     * @return chainable self-reference
     */
    public Article setDescription(String descr) {
        // TODO implement here
        return null;
    }

    /**
     * unitPrice getter.
     * @return price in cent for one article unit
     */
    public long getUnitPrice() {
        // TODO implement here
        return 0;
    }

    /**
     * unitPrice setter.
     * @param unitPrice price in cent for one article unit
     * @return chainable self-reference
     */
    public Article setUnitPrice(long unitPrice) {
        // TODO implement here
        return null;
    }

    /**
     * currency getter.
     * @return currency in which unitPrice is quoted
     */
    public Currency getCurrency() {
        // TODO implement here
        return null;
    }

    /**
     * currency setter.
     * @param currency currency in which unitPrice is quoted
     * @return chainable self-reference
     */
    public Article setCurrency(Currency currency) {
        // TODO implement here
        return null;
    }

    /**
     * tax getter.
     * @return tax rate applicable for article
     */
    public TAX getTax() {
        // TODO implement here
        return null;
    }

    /**
     * tax setter.
     * @param tax tax rate applicable for article
     * @return chainable self-reference
     */
    public Article setTax(TAX tax) {
        // TODO implement here
        return null;
    }

}
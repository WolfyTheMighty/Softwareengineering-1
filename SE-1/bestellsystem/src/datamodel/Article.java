package datamodel;

import java.util.*;

/**
 * Class for entity type Article. An article is an entity that can be  referenced in order items.
 *
 * @author Arpad Horvath
 * @version {@value package_info#Version}
 * @since 0.1.0
 */
public class Article {


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
    public Article() {
    }

    /**
     * Public constructor with description and price arguments.
     *
     * @param descr     descriptive text for article
     * @param unitPrice unitPrice price (in cent) for one unit of the article
     */
    public Article(String descr, long unitPrice) {
        setDescription(descr);
        setUnitPrice(unitPrice);
    }

    /**
     * Id getter. Id can only be set once since id are immutable after assignment.
     *
     * @return article id, may be invalid (null) if still unassigned
     */
    public String getId() {
        return id;
    }

    /**
     * Id setter. Id can only be set once since id is immutable after assignment.
     *
     * @param id assignable id ( != null ) if id attribute is still unassigned (null)
     * @return chainable self-reference
     */
    public Article setId(String id) {
        if (this.id == null && !id.isEmpty()) this.id = id;
        return this;
    }

    /**
     * description getter.
     *
     * @return descriptive text for article
     */
    public String getDescription() {

        return description;
    }

    /**
     * description setter.
     *
     * @param descr descriptive text for article
     * @return chainable self-reference
     */
    public Article setDescription(String descr) {
        if (!descr.isEmpty()) this.description = descr;
        return this;
    }

    /**
     * unitPrice getter.
     *
     * @return price in cent for one article unit
     */
    public long getUnitPrice() {

        return unitPrice;
    }

    /**
     * unitPrice setter.
     *
     * @param unitPrice price in cent for one article unit
     * @return chainable self-reference
     */
    public Article setUnitPrice(long unitPrice) {
        if (unitPrice >= 0) this.unitPrice = unitPrice;
        return this;
    }

    /**
     * currency getter.
     *
     * @return currency in which unitPrice is quoted
     */
    public Currency getCurrency() {

        return currency;
    }

    /**
     * currency setter.
     *
     * @param currency currency in which unitPrice is quoted
     * @return chainable self-reference
     */
    public Article setCurrency(Currency currency) {
        if (Arrays.asList(Currency.values()).contains(currency)) this.currency = currency;
        return this;
    }

    /**
     * tax getter.
     *
     * @return tax rate applicable for article
     */
    public TAX getTax() {
        return tax;
    }

    /**
     * tax setter.
     *
     * @param tax tax rate applicable for article
     * @return chainable self-reference
     */
    public Article setTax(TAX tax) {
        if (Arrays.asList(TAX.values()).contains(tax)) this.tax = tax;
        return this;
    }


}
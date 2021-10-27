package datamodel;

/**
 * Type to enumerate tax rates applicable for articles.
 * 
 * A selling business is obligated to collect taxes from sales based on a tax rate. German sales tax (Umsatzsteuer) is collected as value-added tax (VAT, Mehrwertsteuer, MwSt.) at different rates. A regular rate of 19% applies to all articles, except for those with a reduced rate of 7% such as food, health care items and books.
 * 
 * A VAT-system includes taxes in prices. Each business collects taxes and reclaims paid taxes. End-customers cannot reclaim taxes. This creates a system where taxes are collected from suppliers and distributors based on their "added value" and by end-customers based on the final purchase price.
 * 
 * German MwSt. was ~243 Mrd, ~1/3 of total tax revenue of ~800 Mrd (2019).
 * See: <a href="https://de.wikipedia.org/wiki/Umsatzsteuer_(Deutschland)">Umsatzsteuer (Deutschland)</a>.
 * 
 * @since 1.0
 * @version {@value package_info#Version}
 * @author Arpad Horvath
 */
public enum TAX {
    TAXFREE,
    GER_VAT,
    GER_VAT_REDUCED
}
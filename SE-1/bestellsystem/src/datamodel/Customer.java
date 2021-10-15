package datamodel;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class for entity type Customer. Customer is an individual (not a business) who creates and owns orders in the system.
 *
 * @author Arpad Horvath
 * @version {@value package_info#Version}
 * @since 0.1.0
 */
public class Customer {


    /**
     * id attribute, {@code < 0} invalid, can be set only once
     */
    private long id = -1;

    /**
     * none-surname name parts, never null, mapped to ""
     */
    private String firstName = "";

    /**
     * surname, never null, mapped to ""
     */
    private String lastName = "";

    /**
     * contact information, multiple contacts are possible
     */
    private final List<String> contacts = new ArrayList<String>();

    public Customer() {

    }

    /**
     * Public constructor with name argument.
     *
     * @param name single-String Customer name, e.g. "Eric Meyer"
     */
    public Customer(String name) {
        this.id = -1;
        setName(name);
    }

    /**
     * Id getter.
     *
     * @return customer id, may be invalid {@code < 0} if unassigned
     */
    public long getId() {
        return id;
    }

    /**
     * Id setter. Id can only be set once, id is immutable after assignment.
     *
     * @param id assignment if id is valid {@code >= 0} and id attribute is still unassigned {@code < 0}
     * @return chainable self-reference
     */
    public Customer setId(long id) {
        if (this.id < 0) {
            this.id = id;
        }
        return this;
    }

    /**
     * Getter that returns single-String name from lastName and firstName attributes in format: {@code "lastName, firstName"} or {@code "lastName"} if {@code firstName} is empty.
     *
     * @return single-String name
     */
    public String getName() {
        if (firstName.isEmpty()) {
            return lastName;
        } else {
            return lastName + ", " + firstName;
        }
    }

    /**
     * firstName getter.
     *
     * @return value of firstName attribute, never null, mapped to ""
     */
    public String getFirstName() {
        if (firstName == null) return "";
        return firstName;
    }

    /**
     * lastName getter.
     *
     * @return value of lastName attribute, never null, mapped to ""
     */
    public String getLastName() {
        if (lastName == null) return "";
        return lastName;
    }

    /**
     * Setter that splits single-String name (e.g. "Eric Meyer") into first- and lastName parts and assigns parts to corresponding attributes.
     *
     * @param name single-String name to split into first- and lastName parts
     * @return chainable self-reference
     */
    public Customer setName(String name) {
        if (name == null) return this;
        splitName(name);
        return this;
    }

    /**
     * firstName, lastName setter. Method maintains that both attributes are never null; null-arguments are ignored and don't change attributes.
     *
     * @param first assigned to firstName attribute
     * @param last  assigned to lastName attribute
     * @return chainable self-reference
     */
    public Customer setName(String first, String last) {
        if (first == null) return this;
        if (last == null) return this;
        this.firstName = first;
        this.lastName = last;
        return this;
    }

    /**
     * Return number of contacts.
     *
     * @return number of contactsContacts getter (as {@code String[]}).
     */
    public int contactsCount() {

        return contacts.size();
    }

    /**
     * Contacts getter (as {@code String[]}).
     *
     * @return contacts (as {@code String[]})
     */
    public String[] getContacts() {
        return contacts.toArray(new String[0]);
    }

    /**
     * Add new contact for Customer. Duplicate entries are ignored.
     *
     * @param contact added when not already present
     * @return chainable self-reference
     */
    public Customer addContact(String contact) {
        if (contact == null || contact.equals("")) return this;
        if (!contacts.contains(contact)) {
            contacts.add(contact);
        }
        return this;
    }

    /**
     * Delete i-th contact with constraint: {@code i >= 0} and {@code i < contacts.size()}, otherwise method has no effect.
     *
     * @param i index in contacts
     */
    public void deleteContact(int i) {
        if (i < contacts.size() && (i >= 0)) {
            contacts.remove(i);
        }

    }

    /**
     * Delete all contacts.
     */
    public void deleteAllContacts() {
        contacts.clear();
    }

    /**
     * Split single-String name into first- and last name.
     *
     * @param name single-String name split into first- and last name
     */
    private void splitName(String name) {
        if (name == null) return;
//        this.firstName = name.split(" |; |, ")[0];
        if ((name.contains(", ") || name.contains("; "))) {
//            System.out.println("here");
            name = new StringBuilder(name).reverse().toString();
            String temp = "";
            for (String s : name.split(" ")) {
                temp = temp + " " + new StringBuilder(s).reverse().toString();
            }
            name = temp;
        }
        String split = "";
        Pattern pattern = Pattern.compile(" |; |, ");
        Matcher matcher = pattern.matcher(name);
        if (matcher.find()) {
            split = matcher.group();
        }
        this.lastName = name.substring(name.lastIndexOf(split)).replace(",", "").trim();
        name = name.replace(lastName, "");
        this.firstName = name.replace(",","").trim();
//        new StringBuilder(name).reverse().toString().split(" |; |, ")[0];
    }

}
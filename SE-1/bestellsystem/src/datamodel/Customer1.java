package datamodel;

import java.util.ArrayList;
import java.util.List;

public class Customer1 {

    private long id;
    private String firstName;
    private String lastName;
    private final List<String> contacts = new ArrayList<String>();

    public Customer1() {
        this.id = -1;
        this.firstName = "";
        this.lastName = "";

    }

    public Customer1(String name) {
        this.id = -1;
        splitName(name);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        if (id > 0) {
            this.id = id;
        }
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setName(String name) {
        splitName(name);
    }

    public void setName(String first, String last) {
        this.firstName = first;
        this.lastName = last;
    }

    public int contactsCount() {
        return contacts.size();
    }

    public String[] getContacts() {
        return (String[]) contacts.toArray();
    }

    public void addContact(String contact) {
        this.contacts.add(contact);
    }

    public void deleteContact(int i) {
        contacts.remove(i);
    }

    public void deleteAllContact() {
        contacts.clear();
    }

    private void splitName(String name) {
        this.firstName = name.split(" ")[0];
        this.lastName = name.split(" ")[1];
    }


}

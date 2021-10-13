package datamodel;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String[] getContacts() {
        return (String[]) contacts.toArray();
    }

    public String getName(){
        return firstName + " " +lastName;
    }

    public void setName(String name){

    }

    public void setName(String first, String last){

    }

    private long id;
    private String firstName;
    private String lastName;
    private final List<String> contacts =new ArrayList<String>();

    public Customer(){
        this.id = -1;
        this.firstName ="";
        this.lastName ="";

    }

    public Customer(String name){
        this.id = -1;
        splitName(name);
    }

    public int contactsCount(){
        return contacts.size();
    }

    public void addContact(String contact){

    }

    public void deleteContact(int i){

    }
    public void deleteAllContact(){

    }

    private void splitName(String name){
        this.firstName =name.split(" ")[0];
        this.lastName =name.split(" ")[1];;
    }


}

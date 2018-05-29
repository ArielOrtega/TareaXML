
package domain;

import java.util.ArrayList;


public class Person {
    private String id;
    private String name;
    private String lastname;
    private String SecondLastname;
    private String birthDate;
    private String nationality;
    private String fathersId;
    private ArrayList<Person> children;

    public Person(String id, String name, String lastname, String SecondLastname, String birthDate, String nationality, String fathersId) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.SecondLastname = SecondLastname;
        this.birthDate = birthDate;
        this.nationality = nationality;
        this.fathersId = fathersId;
        this.children = new ArrayList<>();
    }
    
    public Person() {
        this.id = "";
        this.name = "";
        this.lastname = "";
        this.SecondLastname = "";
        this.birthDate = "";
        this.nationality = "";
        this.fathersId = "";
        this.children = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSecondLastname() {
        return SecondLastname;
    }

    public void setSecondLastname(String SecondLastname) {
        this.SecondLastname = SecondLastname;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getFathersId() {
        return fathersId;
    }

    public void setFathersId(String fathersId) {
        this.fathersId = fathersId;
    }

    public ArrayList getChild() {
        return children;
    }
    
    public String getChildrens(){
        String result = "";
        for (int i = 0; i < children.size(); i++) {
            result += children.get(i).toString();
        }
        return result;
    }
    
    public Person getChild(int n){
        return children.get(n);
    }
    
    public void setChild(int n, Person p){
        children.set(n, p);
    }

    public void addChild(Person child) {
        this.children.add(child);
    }
    
    

    @Override
    public String toString() {
        return name;
    }
    
    
}

import java.util.Calendar;
public class Person {
    private String id;
    private String firstName;
    private String lastName;
    private String title;
    private int yearOfBirth;

    public Person(String id, String firstName, String lastName, String title, int yearOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.yearOfBirth = yearOfBirth;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", title='" + title + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                '}';
    }

    public String toCSVDataString() {
        return id + ", " + firstName + ", " + lastName + ", " + title + ", " + yearOfBirth;
    }

    public String getFullName(){
        return firstName + " " + lastName;
    }

    public String getFormalName(){
        return title + " " + getFullName();
    }

    public int getAge(int yearOfBirth){
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return currentYear - yearOfBirth;
    }


    public String toXmlDataString() {
        return "<Person>" +
                "<id>" + id + "</id>" +
                "<firstName>" + firstName + "</firstName>" +
                "<lastName>" + lastName + "</lastName>" +
                "<title>" + title + "</title>" +
                "<yearOfBirth>" + yearOfBirth + "</yearOfBirth>" +
                "</Person>";

    }

    public String toJSONDataString() {
        return "{" +
                "\"id\":\"" + id + "\", " +
                "\"firstName=\":\"" + firstName + "\", " +
                "\" lastName=\":\"" + lastName + "\", " +
                "\" title=\":\"" + title + "\", " +
                "\" yearOfBirth=\":\"" + yearOfBirth +
                "}";
    }
}
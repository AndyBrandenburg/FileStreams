import java.util.Calendar;
public class Person {
    private String id;
    private String firstName;
    private String lastName;
    private String title;
    private int yearOfBirth;

    public static final int ID_LENGTH = 6;
    public static final int FIRSTNAME_LENGTH = 35;
    public static final int LASTNAME_LENGTH = 35;
    public static final int TITLE_LENGTH = 35;
    public static final int YEAR_OF_BIRTH_LENGTH = 4;

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

    public String getPaddedID(){
        return String.format("%-" + ID_LENGTH + "s", id);
    }
    public String getPaddedFirstName(){
        return String.format("%-" + FIRSTNAME_LENGTH + "s", firstName);
    }
    public String getPaddedLastName(){
        return String.format("%-" + LASTNAME_LENGTH + "s", lastName);
    }
    public String getPaddedTitle(){
        return String.format("%-" + TITLE_LENGTH + "s", title);
    }
    public String getPaddedYearOfBirth(){
        return String.format("%-" + YEAR_OF_BIRTH_LENGTH + "s", yearOfBirth);
    }

    public String toRecordString(){
        return getPaddedID() + getPaddedFirstName() + getPaddedLastName() + getPaddedTitle() + getPaddedYearOfBirth();
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
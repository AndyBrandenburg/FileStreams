import java.io.Serializable;

public class Product implements Serializable {
    public String id;
    public String name;
    public String description;
    public double cost;
    public static final int ID_LENGTH = 6;
    public static final int NAME_LENGTH = 35;
    public static final int DESCRIPTION_LENGTH = 75;

    public Product(String id, String name, String description, double cost) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cost = cost;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getPaddedID(){
        return String.format("%-" + ID_LENGTH + "s", id);
    }
    public String getPaddedName(){
        return String.format("%-" + NAME_LENGTH + "s", name);
    }
    public String getPaddedDescription(){
        return String.format("%-" + DESCRIPTION_LENGTH + "s", description);
    }

    public String toRecordString(){
        return getPaddedID() + getPaddedName() + getPaddedDescription() + String.format("%8.2f", cost);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                '}';
    }

    public String toCSVDataString() {
        return id + ", " + description + ", " + cost;
    }

    public String toXmlDataString() {
        return "<Product>" +
                "<id>" + id + "</id>" +
                "<name>" + name + "</name>" +
                "<description>" + description + "</description>" +
                "<cost>" + cost + "</cost>" +
                "</Product>";

    }

    public String toJSONDataString() {
        return "{" +
                "\"id\":\"" + id + "\", " +
                "\" name=\":\"" + name + "\", " +
                "\" description=\":\"" + description + "\", " +
                "\" cost=\":\"" + cost +
                "}";
    }
}

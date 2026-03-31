import java.util.Scanner;
public class Main {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        int age;

        age = SafeInput.getInt(in, "What is your age?");

        System.out.println("Age is " + age);
    }
}

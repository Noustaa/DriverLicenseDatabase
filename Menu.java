import java.util.Scanner;

public class Menu {
    void ShowMenu()
    {
        System.out.println("What do you want to do ?:\n1) Search ID license\n2) Search Name license");
        Scanner scan = new Scanner(System.in);  
        String choice = scan.nextLine(); 
        System.out.println(choice);
        scan.close();
    }
}
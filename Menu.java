import java.util.Scanner;

public class Menu {
    void ShowMenu()
    {
        System.out.println(
        "What do you want to do ?:\n"+
        "1) Add a new driver/license\n"+
        "2) Remove a driver/license\n"+
        "3) Change a driver insurance policy");
        Scanner scan = new Scanner(System.in);  
        String choice = scan.nextLine(); 
        System.out.println(choice);
        scan.close();
    }
}
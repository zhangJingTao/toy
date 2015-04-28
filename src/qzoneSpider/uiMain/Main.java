package uiMain;

import java.util.Scanner;

/**
 * Created by admin on 2015/4/28.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("press 'Q'---EXIT");
        System.out.println("输入QQ...");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            //exit
            String command = scanner.nextLine();
            if (command.toLowerCase().equals("q")){
                System.exit(0);
            }



        }
    }
}

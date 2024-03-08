package view;

import utils.TableUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MainView {
   private final static List<String> mainMenu = new ArrayList<>(List.of(
            "Add New Person ",
            "Add All Persons ",
            "Update Person ",
            "Delete Person",
            "Show Person Information",
            "Search Person Information",
            "Exit"));
   private final static  List<String> showMenu = new ArrayList<>(List.of(
            "Show Original Order",
            "Show Descending Order (ID)",
            "Show Descending Order (name) ",
            "Exit"));
   private final static List<String> Search=new ArrayList<>(List.of(
           "Search By ID",
           "Search By Gender",
           "Search By Country",
           "Exit"
   ));
   public static int renderMain(Scanner input){
       TableUtils.renderMenu(mainMenu, "Person Management System");
       System.out.print("Enter your option : ");
       return input.nextInt();
   }
   public static int renderShow(Scanner input)
   {
       TableUtils.renderMenu(showMenu,"Show Person");
       System.out.print("Enter your option : ");
       return input.nextInt();
   }
    public static int renderSearch(Scanner input)
    {
        TableUtils.renderMenu(Search,"Search Person");
        System.out.print("Enter your option : ");
        return input.nextInt();
    }
}

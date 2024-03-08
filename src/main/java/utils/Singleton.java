package utils;
import model.Person;
import model.SystemUser;
import controller.Controller;
import service.PersonService;

import java.util.*;
public class Singleton {
    private static Scanner scanner;
    private static Person person;
    private static SystemUser systemUser;
    private static DatabasePopulationUtils databasePopulationUtils;
    private static PersonService personService;
    private static Controller controller;
    public static Controller controller()
    {
        if(controller==null)
        {
            controller=new Controller();
        }
        return controller;
    }
    public static DatabasePopulationUtils databasePopulationUtils()
    {
        if(databasePopulationUtils==null)
        {
            databasePopulationUtils=new DatabasePopulationUtils();
        }return databasePopulationUtils;
    }
    public static Scanner scanner() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        return scanner;
    }
    public static SystemUser systemUser()
    {
        if(systemUser==null)
        {
            systemUser=new SystemUser();
        }return systemUser;
    }
    public static Person person()
    {
        if(person==null)
        {
            person=new Person();
        }
        return person;
    }

}

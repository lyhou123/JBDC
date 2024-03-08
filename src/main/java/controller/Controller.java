package controller;

import model.Person;
import repository.PersonRepository;
import service.PersonService;
import utils.DatabasePopulationUtils;
import utils.Singleton;
import utils.TableUtils;
import view.MainView;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Controller {
    private static Scanner input;
    private static DatabasePopulationUtils dbUtils = new DatabasePopulationUtils();
    private static final List<Person> fakePersons = dbUtils.getAllPerson();
    private  static final PersonService personService = new PersonService(new PersonRepository());
    public Controller() {
        dbUtils = Singleton.databasePopulationUtils();
        input = Singleton.scanner();
    }
    public void MainMenu() {
        int option;
        do {
            option = MainView.renderMain(input);
            switch (option) {
                case 1: {
                    input.nextLine();
                    System.out.println(
                            personService.createPerson(input) > 0 ?
                                    "Successfully Created a New Person"
                                    : ""
                    );

                }
                case 2: {
                    personService.addAllPersons(fakePersons);
                    break;
                }
                case 3: {
                    System.out.println(
                            personService
                                    .updatePerson(input) > 0 ?
                                    "Successfully Update Person Info"
                                    : ""
                    );
                }
                break;
                case 4: {
                    System.out.println(
                            personService
                                    .deletePersonByID(input) > 0 ?
                                    "Successfully Remove the Person"
                                    : "");

                    break;
                }
                case 5:{
                    showPerson();
                    break;
                }
                case 6:{
                    searchPerson();
                    break;
                }
            }
        }while (option!=7);
    }
    public void searchPerson()
    {
        int search ;
        do {
             search = MainView.renderSearch(input);
            switch (search) {
                case 1:
                    int searchID = 0;
                    System.out.println("Enter Person ID to search:");
                    searchID = input.nextInt();
                    int finalSearchID = searchID;
                    try {
                        Person optionalPerson =
                                personService.getAllPerson()
                                        .stream()
                                        .filter(person -> person.getId() == finalSearchID)
                                        .findFirst()
                                        .orElseThrow(() -> new ArithmeticException("Whatever exception!! "));
                        TableUtils.renderObjectToTable(
                                Collections.singletonList(optionalPerson));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        System.out.println("There is no element with ID=" + searchID);
                    }
                    break;
                case 2: {
                    input.nextLine();
                    System.out.print("Enter Gender for search=");
                    String gender = input.nextLine();
                    List<Person> personList = personService.getAllPerson().stream().filter(pro -> pro.getGender().equalsIgnoreCase(gender)).toList();
                    TableUtils.renderObjectToTable(personList);
                    break;
                }
                case 3: {
                    input.nextLine();
                    System.out.print("Enter Country for search=");
                    String country = input.nextLine();
                    List<Person> personList = personService.getAllPerson().stream().filter(pro -> pro.getAddress().equalsIgnoreCase(country)).toList();
                    TableUtils.renderObjectToTable(personList);
                    break;

                }
            }
        }while (search!=4);
    }
    public void showPerson()
    {
        int showOption;
        do {
            showOption =MainView.renderShow(input);

            switch (showOption) {
                case 1:
                    TableUtils.renderObjectToTable(personService.getAllPerson());
                    break;
                case 2:
                    // descending id
                    TableUtils.renderObjectToTable(
                            personService.getAllPersonDescendingByID()
                    );
                    break;
                case 3:
                    // descending name
                    TableUtils.renderObjectToTable(
                            personService.getAllPersonDescendingByName()
                    );
                    break;
            }
        } while (showOption !=4);
    }
}

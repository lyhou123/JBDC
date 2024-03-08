
import controller.Controller;
import repository.SystemUserRepository;
import service.SystemUserService;
import java.util.*;
public class Main {
    private final static Controller controller=new Controller();
    private static final SystemUserService systemUserService=new SystemUserService(new SystemUserRepository());
    public void run()
    {
        Scanner input = new Scanner(System.in);
        systemUserService.check(input);
        controller.MainMenu();
    }
  public static void main(String[] args) {
     new Main().run();
    }
}

package service;
import model.SystemUser;
import repository.SystemUserRepository;
import java.util.Scanner;
public class SystemUserService {
    private final SystemUserRepository systemUserRepository;
    public SystemUserService(SystemUserRepository systemUserRepository)
    {
        this.systemUserRepository =new SystemUserRepository();
    }
    public void check(Scanner scanner)
    {
        System.out.println("~".repeat(90));
        systemUserRepository.Login(new SystemUser().input(scanner));
        System.out.println("~".repeat(90));
    }
}

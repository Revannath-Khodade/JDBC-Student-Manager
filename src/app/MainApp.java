package app;

import model.Student;
import service.StudentService;
import service.StudentServiceImpl;

import java.util.List;
import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StudentService service = new StudentServiceImpl();

        while (true) {

            System.out.println("\n1. Insert");
            System.out.println("2. Update Name");
            System.out.println("3. Update Age");
            System.out.println("4. Delete By ID");
            System.out.println("5. Delete By Name");
            System.out.println("6. Display");
            System.out.println("7. Exit");

            int choice = sc.nextInt();

            switch (choice) {

                case 1 -> {
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Age: ");
                    int age = sc.nextInt();

                    service.addStudent(new Student(id, name, age));
                    System.out.println("Inserted Successfully ✅");
                }

                case 2 -> {
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter New Name: ");
                    String name = sc.nextLine();
                    service.updateName(id, name);
                }

                case 3 -> {
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    System.out.print("Enter New Age: ");
                    int age = sc.nextInt();
                    service.updateAge(id, age);
                }

                case 4 -> {
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    service.deleteById(id);
                }

                case 5 -> {
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    service.deleteByName(name);
                }

                case 6 -> {
                    List<Student> list = service.getAllStudents();
                    list.forEach(s ->
                            System.out.println(s.getId()+" "+s.getName()+" "+s.getAge()));
                }

                case 7 -> System.exit(0);
            }
        }
    }
}
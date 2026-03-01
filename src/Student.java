import java.sql.*;
import java.util.Scanner;

public class Student {
    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try (
                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/mydb",
                        "root",
                        "your_password_here"           // ⚠ Actual password
                );
                Scanner sc = new Scanner(System.in);
        ) {

            while (true){

                // ================= INSERT =================
                System.out.print("Do you Want To Insert Data? (Y/N) :");
                String yesNo1 = sc.next();

                if (yesNo1.equalsIgnoreCase("Y")) {

                    String sql1 = "insert into student values(?,?,?)";
                    try (PreparedStatement ps1 = con.prepareStatement(sql1)) {

                        while (true) {

                            System.out.print("Enter Student ID :");
                            int id = sc.nextInt();

                            sc.nextLine();

                            System.out.print("Enter Student Name :");
                            String name = sc.nextLine();

                            System.out.print("Enter Student age :");
                            int age = sc.nextInt();

                            ps1.setInt(1, id);
                            ps1.setString(2, name);
                            ps1.setInt(3, age);

                            ps1.executeUpdate();

                            System.out.print("Enter More Student Data (Y/N) :");
                            String str = sc.next();

                            if (str.equalsIgnoreCase("N")) break;
                            System.out.println();
                        }
                    }
                    System.out.println("Data Inserted Successfully ✅");
                } else {
                    System.out.println("No Data Inserted.");
                }

                System.out.println("-------------------------------------------------------------------");

                // ================= UPDATE =================
                System.out.print("Do You Want To Update Data? (Y/N) :");
                String yesNo2 = sc.next();

                if (yesNo2.equalsIgnoreCase("Y")) {

                    System.out.print("Update Name(1), Update Age(2) (1/2) :");
                    String yesNo3 = sc.next();

                    String sql2 = "";
                    switch (yesNo3) {
                        case "1" -> sql2 = "update student set name = ? where id = ?";
                        case "2" -> sql2 = "update student set age = ? where id = ?";
                        default -> {
                            System.out.println("Invalid Option");
                            return;
                        }
                    }

                    try (PreparedStatement ps2 = con.prepareStatement(sql2)) {

                        while (true) {

                            System.out.print("Enter Student ID :");
                            int id = sc.nextInt();

                            if (yesNo3.equals("1")) {

                                sc.nextLine();
                                // ✅ BUG FIX: nextInt() ke baad newline consume

                                System.out.print("Enter New Name :");
                                String newName = sc.nextLine();
                                // ✅ FIXED: next() → nextLine() (full name allow karega)

                                ps2.setString(1, newName);
                                ps2.setInt(2, id);

                            } else if (yesNo3.equals("2")) {

                                System.out.print("Enter New Age :");
                                int newAge = sc.nextInt();

                                ps2.setInt(1, newAge);
                                ps2.setInt(2, id);
                            }

                            int rows = ps2.executeUpdate();

                            if (rows > 0) {
                                System.out.println("Data Updated Successfully ✅");
                            } else {
                                System.out.println("No Record Found.");
                            }

                            System.out.print("\nUpdate More Student Data (Y/N) :");
                            String str1 = sc.next();

                            if (str1.equalsIgnoreCase("N")) break;
                        }
                    }
                } else {
                    System.out.println("No Data Updated.");
                }

                System.out.println("-------------------------------------------------------------------");

                // ================= DELETE =================
                System.out.print("Do You Want To Delete Data? (Y/N) :");
                String yesNo4 = sc.next();

                if (yesNo4.equalsIgnoreCase("Y")) {

                    System.out.print("Delete ID(1), Delete Name(2) (1/2) :");
                    String yesNo5 = sc.next();

                    String sql3 = "";

                    switch (yesNo5) {
                        case "1" -> sql3 = "delete from student where id = ?";
                        case "2" -> sql3 = "delete from student where name = ?";
                        default -> {
                            System.out.println("Invalid Option");
                            return;
                        }
                    }

                    try (PreparedStatement ps3 = con.prepareStatement(sql3)) {

                        while (true) {

                            if (yesNo5.equals("1")) {

                                System.out.print("Enter Delete Student ID :");
                                int id = sc.nextInt();
                                ps3.setInt(1, id);

                            } else if (yesNo5.equals("2")) {

                                sc.nextLine();

                                System.out.print("Enter Delete Student Name :");
                                String name = sc.nextLine();

                                ps3.setString(1, name);
                            }

                            con.setAutoCommit(false);

                            try {
                                int rows = ps3.executeUpdate();

                                if(rows > 0){

                                    System.out.print("Confirm Delete? (Y/N): ");
                                    String confirm = sc.next();

                                    if (confirm.equalsIgnoreCase("Y")) {
                                        con.commit();
                                        System.out.println("Deleted Successfully ✅");
                                    } else {
                                        con.rollback();
                                        System.out.println("Delete Cancelled ❌");
                                    }

                                } else {
                                    System.out.println("No Record Found.");
                                    con.rollback();
                                }

                            } catch (Exception e) {
                                con.rollback();
                                System.out.println("Error occurred. Transaction rolled back ❌");
                            }

                            con.setAutoCommit(true);

                            System.out.print("\nDelete More Student Data (Y/N) :");
                            String str2 = sc.next();

                            if (str2.equalsIgnoreCase("N")) break;
                        }
                    }
                } else {
                    System.out.println("No Data Deleted.");
                }

                System.out.println("-------------------------------------------------------------------");

                // ================= DISPLAY =================
                System.out.print("Do You Want To Display Data? (Y/N) :");
                String yesNo6 = sc.next();

                if (yesNo6.equalsIgnoreCase("Y")) {

                    String sql4 = "select * from student";

                    try (PreparedStatement ps4 = con.prepareStatement(sql4);
                         ResultSet rs = ps4.executeQuery()) {

                        System.out.println("\n------ Student Records ------");

                        while (rs.next()) {

                            int id = rs.getInt("id");
                            String name = rs.getString("name");
                            int age = rs.getInt("age");

                            System.out.println(
                                    "ID: " + id + ", Name: " + name + ", Age: " + age
                            );
                        }
                    }

                } else {
                    System.out.println("No Data Displayed.");
                }

                System.out.println("-------------------------------------------------------------------");

                System.out.print("Do You Perform More CRUD Operation? (Y/N) ");
                String str4 = sc.next();

                if(str4.equalsIgnoreCase("N")) break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
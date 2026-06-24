import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private int id;
    private String name;
    private String department;
    private double cgpa;
    private boolean placed;

    public Student(int id, String name, String department, double cgpa) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.cgpa = cgpa;
        this.placed = false;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getCgpa() {
        return cgpa;
    }

    public boolean isPlaced() {
        return placed;
    }

    public void setPlaced(boolean placed) {
        this.placed = placed;
    }

    @Override
    public String toString() {
        return "Student ID : " + id +
                "\nName       : " + name +
                "\nDepartment : " + department +
                "\nCGPA       : " + cgpa +
                "\nPlaced     : " + (placed ? "Yes" : "No");
    }
}

class Company {
    private int companyId;
    private String companyName;
    private String role;
    private double minCGPA;

    public Company(int companyId, String companyName,
                   String role, double minCGPA) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.role = role;
        this.minCGPA = minCGPA;
    }

    public int getCompanyId() {
        return companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getRole() {
        return role;
    }

    public double getMinCGPA() {
        return minCGPA;
    }

    @Override
    public String toString() {
        return "Company ID : " + companyId +
                "\nCompany    : " + companyName +
                "\nRole       : " + role +
                "\nMin CGPA   : " + minCGPA;
    }
}

class Placement {
    private Student student;
    private Company company;

    public Placement(Student student, Company company) {
        this.student = student;
        this.company = company;
    }

    @Override
    public String toString() {
        return "Student : " + student.getName() +
                " | Company : " + company.getCompanyName() +
                " | Role : " + company.getRole();
    }
}

public class PlacementManagementSystem {

    static ArrayList<Student> students = new ArrayList<>();
    static ArrayList<Company> companies = new ArrayList<>();
    static ArrayList<Placement> placements = new ArrayList<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n================================");
            System.out.println(" PLACEMENT MANAGEMENT SYSTEM");
            System.out.println("================================");
            System.out.println("1. Add Student");
            System.out.println("2. Add Company");
            System.out.println("3. View Students");
            System.out.println("4. View Companies");
            System.out.println("5. Place Student");
            System.out.println("6. View Placements");
            System.out.println("7. Search Student");
            System.out.println("8. Delete Student");
            System.out.println("9. Exit");
            System.out.print("Enter Choice : ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addStudent(sc);
                    break;

                case 2:
                    addCompany(sc);
                    break;

                case 3:
                    viewStudents();
                    break;

                case 4:
                    viewCompanies();
                    break;

                case 5:
                    placeStudent(sc);
                    break;

                case 6:
                    viewPlacements();
                    break;

                case 7:
                    searchStudent(sc);
                    break;

                case 8:
                    deleteStudent(sc);
                    break;

                case 9:
                    System.out.println("Thank You!");
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }

    static void addStudent(Scanner sc) {

        System.out.print("Enter Student ID : ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Name : ");
        String name = sc.nextLine();

        System.out.print("Enter Department : ");
        String dept = sc.nextLine();

        System.out.print("Enter CGPA : ");
        double cgpa = sc.nextDouble();

        students.add(new Student(id, name, dept, cgpa));

        System.out.println("Student Added Successfully!");
    }

    static void addCompany(Scanner sc) {

        System.out.print("Enter Company ID : ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Company Name : ");
        String name = sc.nextLine();

        System.out.print("Enter Role : ");
        String role = sc.nextLine();

        System.out.print("Enter Minimum CGPA : ");
        double cgpa = sc.nextDouble();

        companies.add(new Company(id, name, role, cgpa));

        System.out.println("Company Added Successfully!");
    }

    static void viewStudents() {

        if (students.isEmpty()) {
            System.out.println("No Students Available!");
            return;
        }

        System.out.println("\n------ STUDENT LIST ------");

        for (Student s : students) {
            System.out.println(s);
            System.out.println("-------------------------");
        }
    }

    static void viewCompanies() {

        if (companies.isEmpty()) {
            System.out.println("No Companies Available!");
            return;
        }

        System.out.println("\n------ COMPANY LIST ------");

        for (Company c : companies) {
            System.out.println(c);
            System.out.println("-------------------------");
        }
    }

    static void placeStudent(Scanner sc) {

        System.out.print("Enter Student ID : ");
        int sid = sc.nextInt();

        System.out.print("Enter Company ID : ");
        int cid = sc.nextInt();

        Student student = null;
        Company company = null;

        for (Student s : students) {
            if (s.getId() == sid) {
                student = s;
                break;
            }
        }

        for (Company c : companies) {
            if (c.getCompanyId() == cid) {
                company = c;
                break;
            }
        }

        if (student == null) {
            System.out.println("Student Not Found!");
            return;
        }

        if (company == null) {
            System.out.println("Company Not Found!");
            return;
        }

        if (student.isPlaced()) {
            System.out.println("Student Already Placed!");
            return;
        }

        if (student.getCgpa() < company.getMinCGPA()) {
            System.out.println("Student Not Eligible!");
            return;
        }

        student.setPlaced(true);
        placements.add(new Placement(student, company));

        System.out.println("Placement Successful!");
    }

    static void viewPlacements() {

        if (placements.isEmpty()) {
            System.out.println("No Placements Yet!");
            return;
        }

        System.out.println("\n------ PLACEMENT RECORDS ------");

        for (Placement p : placements) {
            System.out.println(p);
        }
    }

    static void searchStudent(Scanner sc) {

        System.out.print("Enter Student ID : ");
        int id = sc.nextInt();

        for (Student s : students) {
            if (s.getId() == id) {
                System.out.println("\nStudent Found");
                System.out.println(s);
                return;
            }
        }

        System.out.println("Student Not Found!");
    }

    static void deleteStudent(Scanner sc) {

        System.out.print("Enter Student ID : ");
        int id = sc.nextInt();

        for (Student s : students) {
            if (s.getId() == id) {
                students.remove(s);
                System.out.println("Student Deleted Successfully!");
                return;
            }
        }

        System.out.println("Student Not Found!");
    }
}
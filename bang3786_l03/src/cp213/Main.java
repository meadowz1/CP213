package cp213;

import java.time.LocalDate;

/**
 * Tests the Student class.
 *
 * @author Mike Bangar
 * @version 2024-10-04
 */
public class Main {

    public static void main(String[] args) {
	final String line = "-".repeat(40);
	int id = 123456;
	String surname = "Brown";
	String forename = "David";
	LocalDate birthDate = LocalDate.parse("1962-10-25");
	Student student = new Student(id, surname, forename, birthDate);

	System.out.println("New Student:");
	System.out.println(student);

	System.out.println(line);
	System.out.println("Test Getters");
	System.out.println("ID: " + student.getId());
	System.out.println("Surname: " + student.getSurname());
	System.out.println("Forename: " + student.getForename());
	System.out.println("Birthdate: " + student.getBirthDate());
	System.out.println(line);

	// call getters here

	System.out.println(line);
	System.out.println("Test Setters");
	student.setId(654321);
	student.setSurname("Smith");
	student.setForename("John");
	student.setBirthDate(LocalDate.parse("1990-01-01"));

	// call setters here

	System.out.println("Updated Student:");
	System.out.println(student);
	System.out.println(line);
	System.out.println("Test compareTo");
	Student student1 = new Student(123456, "Brown", "David", LocalDate.of(1962, 10, 25));
	Student student2 = new Student(111111, "Brown", "Matthew", LocalDate.of(1962, 10, 25));
	Student student3 = new Student(123456, "Brown", "Alice", LocalDate.of(1970, 5, 15));

	// create new Students - call comparisons here
	System.out.println("Comparing student1 to student2: " + student1.compareTo(student2));
	System.out.println("Comparing student1 to student3: " + student1.compareTo(student3));
	System.out.println("Comparing student2 to student3: " + student2.compareTo(student3));
    }

}

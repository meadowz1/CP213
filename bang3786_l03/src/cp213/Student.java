package cp213;

import java.time.LocalDate;

/**
 * Student class definition.
 *
 * @author Mike Bangar
 * @version 2024-10-04
 */
public class Student implements Comparable<Student> {

    // Attributes
    private LocalDate birthDate = null;
    private String forename = "";
    private int id = 0;
    private String surname = "";

    /**
     * Instantiates a Student object.
     *
     * @param id        student ID number
     * @param surname   student surname
     * @param forename  name of forename
     * @param birthDate birthDate in 'YYYY-MM-DD' format
     */
    public Student(int id, String surname, String forename, LocalDate birthDate) {

	this.id = id;
	this.surname = surname;
	this.forename = forename;
	this.birthDate = birthDate;

	return;
    }

    /**
     * Gets the student ID.
     * 
     * @return the student ID
     */
    public int getId() {
	return id;
    }

    /**
     * Sets the student ID.
     * 
     * @param id the student ID to set
     */
    public void setId(int id) {
	this.id = id;
    }

    /**
     * Gets the student's surname.
     * 
     * @return the surname
     */
    public String getSurname() {
	return surname;
    }

    /**
     * Sets the student's surname.
     * 
     * @param surname the surname to set
     */
    public void setSurname(String surname) {
	this.surname = surname;
    }

    /**
     * Gets the student's forename.
     * 
     * @return the forename
     */
    public String getForename() {
	return forename;
    }

    /**
     * Sets the student's forename.
     * 
     * @param forename the forename to set
     */
    public void setForename(String forename) {
	this.forename = forename;
    }

    /**
     * Gets the student's birthdate.
     * 
     * @return the birthdate
     */
    public LocalDate getBirthDate() {
	return birthDate;
    }

    /**
     * Sets the student's birthdate.
     * 
     * @param birthDate the birthdate to set
     */
    public void setBirthDate(LocalDate birthDate) {
	this.birthDate = birthDate;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString() Creates a formatted string of student data.
     */
    @Override
    public String toString() {
	String string = "Name:    " + surname + ", " + forename + "\n" + "ID:      " + id + "\n" + "Birthdate: "
		+ birthDate;
	return string;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(final Student target) {
	int result = 0;

	if (result == 0) {
	    result = this.forename.compareTo(target.forename);
	}

	if (result == 0) {
	    result = Integer.compare(this.id, target.id);
	}

	return result;
    }

    // getters and setters defined here

}

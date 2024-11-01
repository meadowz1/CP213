package cp213;

/**
 * Inherited class in simple example of inheritance / polymorphism.
 *
 * @author Mike Bangar
 * @version 2024-11-01
 */
public class IA extends Student {

    private String course;

    public IA(String lastName, String firstName, String id, String course) {
	super(lastName, firstName, id);
	this.course = course;
    }

    public String getCourse() {
	return this.course;
    }

    @Override
    public String toString() {
	return super.toString() + "\nCourse: " + this.course;
    }

}

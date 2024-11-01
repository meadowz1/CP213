package cp213;

/**
 * Inherited class in simple example of inheritance / polymorphism.
 *
 * @author Mike Bangar
 * @version 2024-11-01
 */
public class CAS extends Professor {

    private String term;

    public CAS(String lastName, String firstName, String department, String term) {
	super(lastName, firstName, department);
	this.term = term;
    }

    public String getTerm() {
	return this.term;
    }

    @Override
    public String toString() {
	return super.toString() + "\nTerm: " + this.term;
    }

}

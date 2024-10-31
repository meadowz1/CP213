package cp213;

/**
 * DO NOT CHANGE THE CONTENTS OF THIS CLASS.
 *
 * The individual node of a linked structure that stores <code>T</code> objects.
 * This is a singly linked node. The node link can be updated, but not the node
 * object, in order to avoid copying or moving values between nodes. Data
 * structures must be updated by moving nodes, not by copying or moving object.
 *
 * @author David Brown
 * @version 2024-09-01
 */
public final class SingleNode<T> {

    /**
     * The generic object stored in the node.
     */
    private T object = null;
    /**
     * Link to the next Node.
     */
    private SingleNode<T> next = null;

    /**
     * Creates a new node with object and link to next node. Not copy safe as it
     * accepts a reference to the object rather than a copy of the object.
     *
     * @param object the object to store in the node.
     * @param next   the next node to link to.
     */
    public SingleNode(final T object, final SingleNode<T> next) {
	this.object = object;
	this.next = next;
    }

    /**
     * Returns the node object. Not copy safe as it returns a reference to the
     * object, not a copy of the object.
     *
     * @return The object portion of the node.
     */
    public T getObject() {
	return this.object;
    }

    /**
     * Returns the next node in the linked structure.
     *
     * @return The node that follows this node.
     */
    public SingleNode<T> getNext() {
	return this.next;
    }

    /**
     * Links this node to the next node.
     *
     * @param next The new node to link to.
     */
    public void setNext(final SingleNode<T> next) {
	this.next = next;
    }
}

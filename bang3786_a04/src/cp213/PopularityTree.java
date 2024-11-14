package cp213;

/**
 * Implements a Popularity Tree. Extends BST.
 *
 * @author your name here
 * @author David Brown
 * @version 2024-10-15
 */
public class PopularityTree<T extends Comparable<T>> extends BST<T> {

    /**
     * Auxiliary method for valid. May force node rotation if the retrieval count of
     * the located node data is incremented.
     *
     * @param node The node to examine for key.
     * @param key  The data to search for. Count is updated to count in matching
     *             node data if key is found.
     * @return The updated node.
     */
    private TreeNode<T> retrieveAux(TreeNode<T> node, final CountedData<T> key) {

	if (node == null) {
	    return null;
	}

	int compare = node.getData().compareTo(key);
	this.comparisons++;

	if (compare == 0) {
	    node.getData().incrementCount();
	    return node;
	} else if (compare > 0) {
	    TreeNode<T> foundNode = this.retrieveAux(node.getLeft(), key);
	    if (foundNode != null) {
		if (node.getLeft().getData().getCount() < foundNode.getData().getCount()) {
		    node.setLeft(foundNode);
		}
		if (node.getData().getCount() < foundNode.getData().getCount()) {
		    TreeNode<T> newRoot = this.rotateRight(node);
		    if (node.getData().compareTo(this.root.getData()) == 0) {
			this.root = newRoot;
		    }

		    node.updateHeight();
		    newRoot.updateHeight();

		}
	    }

	    return foundNode;

	} else {
	    TreeNode<T> foundNode = this.retrieveAux(node.getRight(), key);

	    if (foundNode != null) {
		if (node.getRight().getData().getCount() < foundNode.getData().getCount()) {
		    node.setRight(foundNode);
		}
		if (node.getData().getCount() < foundNode.getData().getCount()) {
		    TreeNode<T> newRoot = this.rotateLeft(node);
		    if (node.getData().compareTo(this.root.getData()) == 0) {
			this.root = newRoot;
		    }

		    node.updateHeight();
		    newRoot.updateHeight();
		}
	    }

	    return foundNode;
	}
    }

    /**
     * Performs a left rotation around node.
     *
     * @param parent The subtree to rotate.
     * @return The new root of the subtree.
     */
    private TreeNode<T> rotateLeft(final TreeNode<T> parent) {

	TreeNode<T> pivot = parent.getRight();
	parent.setRight(pivot.getLeft());
	pivot.setLeft(parent);
	pivot.updateHeight();
	parent.updateHeight();

	return pivot;
    }

    /**
     * Performs a right rotation around {@code node}.
     *
     * @param parent The subtree to rotate.
     * @return The new root of the subtree.
     */
    private TreeNode<T> rotateRight(final TreeNode<T> parent) {

	TreeNode<T> pivot = parent.getLeft();

	if (pivot == null) {
	    return parent;
	}
	parent.setLeft(pivot.getRight());
	pivot.setRight(parent);
	pivot.updateHeight();
	parent.updateHeight();

	return pivot;
    }

    /**
     * Replaces BST insertAux - does not increment count on repeated insertion.
     * Counts are incremented only on retrieve.
     */
    @Override
    protected TreeNode<T> insertAux(TreeNode<T> node, final CountedData<T> data) {

	if (node == null) {
	    node = new TreeNode<T>(data);
	    this.size++;
	} else {
	    final int result = node.getData().compareTo(data);

	    if (result > 0) {
		node.setLeft(this.insertAux(node.getLeft(), data));
	    } else if (result < 0) {
		node.setRight(this.insertAux(node.getRight(), data));
	    } else {
	    }
	}
	node.updateHeight();
	return node;

    }

    /**
     * Auxiliary method for valid. Determines if a subtree based on node is a valid
     * subtree. An Popularity Tree must meet the BST validation conditions, and
     * additionally the counts of any node data must be greater than or equal to the
     * counts of its children.
     *
     * @param node The root of the subtree to test for validity.
     * @return true if the subtree base on node is valid, false otherwise.
     */
    @Override
    protected boolean isValidAux(final TreeNode<T> node, TreeNode<T> minNode, TreeNode<T> maxNode) {

	if (node == null) {
	    return true;
	}

	int leftHeight = node.getLeft() != null ? node.getLeft().getHeight() : 0;
	int rightHeight = node.getRight() != null ? node.getRight().getHeight() : 0;

	int leftCount = node.getLeft() != null ? node.getLeft().getData().getCount() : 0;
	int rightCount = node.getRight() != null ? node.getRight().getData().getCount() : 0;

	if (((minNode != null && node.getData().compareTo(minNode.getData()) <= 0)
		|| (maxNode != null && node.getData().compareTo(maxNode.getData()) >= 0))
		|| (node.getHeight() != Math.max(leftHeight, rightHeight) + 1)
		|| (node.getData().getCount() < leftCount) || (node.getData().getCount() < rightCount)) {
	    return false;
	}

	boolean isLeftValid = isValidAux(node.getLeft(), minNode, node);
	boolean isRightValid = isValidAux(node.getRight(), node, maxNode);

	return isLeftValid && isRightValid;
    }

    /**
     * Determines whether two PopularityTrees are identical.
     *
     * @param target The PopularityTree to compare this PopularityTree against.
     * @return true if this PopularityTree and target contain nodes that match in
     *         position, data, count, and height, false otherwise.
     */
    public boolean equals(final PopularityTree<T> target) {
	return super.equals(target);
    }

    /**
     * Very similar to the BST retrieve, but increments the data count here instead
     * of in the insertion.
     *
     * @param key The key to search for.
     */
    @Override
    public CountedData<T> retrieve(CountedData<T> key) {

	TreeNode<T> foundNode = this.retrieveAux(this.root, key);

	if (foundNode != null) {
	    return foundNode.getData();
	}

	return null;
    }

}

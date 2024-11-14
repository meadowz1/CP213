package cp213;

/**
 * Implements an AVL (Adelson-Velsky Landis) tree. Extends BST.
 *
 * @author your name here
 * @author David Brown
 * @version 2024-10-15
 */
public class AVL<T extends Comparable<T>> extends BST<T> {

    /**
     * Returns the balance data of node. If greater than 1, then left heavy, if less
     * than -1, then right heavy. If in the range -1 to 1 inclusive, the node is
     * balanced. Used to determine whether to rotate a node upon insertion.
     *
     * @param node The TreeNode to analyze for balance.
     * @return A balance number.
     */
    private int balance(final TreeNode<T> node) {

	if (node == null) {
	    return 0;
	}
	int leftHeight;
	int rightHeight;
	if (node.getLeft() == null) {
	    leftHeight = 0;
	} else {
	    leftHeight = node.getLeft().getHeight() + 1;

	}

	if (node.getRight() == null) {
	    rightHeight = 0;
	} else {
	    rightHeight = node.getRight().getHeight() + 1;
	}

	return leftHeight - rightHeight;
    }

    /**
     * Rebalances the current node if its children are not balanced.
     *
     * @param node the node to rebalance
     * @return replacement for the rebalanced node
     */
    private TreeNode<T> rebalance(TreeNode<T> node) {

	int balanced = balance(node);

	if (balanced > 1) {
	    if (balance(node.getLeft()) >= 0) {
		node = rotateRight(node);

	    }

	    else {
		node.setLeft(rotateLeft(node.getLeft()));
		node = rotateRight(node);
	    }

	}

	else if (balanced < -1) {
	    if (balance(node.getRight()) <= 0) {
		node = rotateLeft(node);
	    }

	    else {
		node.setRight(rotateRight(node.getRight()));
		node = rotateLeft(node);
	    }
	}

	return node;
    }

    /**
     * Performs a left rotation around node.
     *
     * @param node The subtree to rotate.
     * @return The new root of the subtree.
     */
    private TreeNode<T> rotateLeft(final TreeNode<T> node) {

	TreeNode<T> pivot = node.getRight();

	if (pivot == null) {
	    return node;
	}
	node.setRight(pivot.getLeft());
	pivot.setLeft(node);
	pivot.updateHeight();
	node.updateHeight();

	return pivot;
    }

    /**
     * Performs a right rotation around node.
     *
     * @param node The subtree to rotate.
     * @return The new root of the subtree.
     */
    private TreeNode<T> rotateRight(final TreeNode<T> node) {

	TreeNode<T> pivot = node.getRight();

	if (pivot == null) {
	    return node;
	}
	node.setRight(pivot.getLeft());
	pivot.setLeft(node);
	pivot.updateHeight();
	node.updateHeight();

	return pivot;
    }

    /**
     * Auxiliary method for insert. Inserts data into this AVL. Same as BST
     * insertion with addition of rebalance of nodes.
     *
     * @param node The current node (TreeNode).
     * @param data Data to be inserted into the node.
     * @return The inserted node.
     */
    @Override
    protected TreeNode<T> insertAux(TreeNode<T> node, final CountedData<T> data) {

	if (node == null) {
	    node = new TreeNode<T>(data);
	    node.getData().incrementCount();
	    this.size++;
	} else {
	    final int result = node.getData().compareTo(data);

	    if (result > 0) {
		node.setLeft(this.insertAux(node.getLeft(), data));
		if (this.balance(node) > 1) {
		    if (data.compareTo(node.getLeft().getData()) < 0) {
			node = this.rotateRight(node);
		    } else {
			node.setLeft(this.rotateLeft(node.getLeft()));
			node = this.rotateRight(node);
		    }
		}
	    } else if (result < 0) {
		node.setRight(this.insertAux(node.getRight(), data));
		if (this.balance(node) < -1) {
		    if (data.compareTo(node.getRight().getData()) > 0) {
			node = this.rotateLeft(node);
		    } else {
			node.setRight(this.rotateRight(node.getRight()));
			node = this.rotateLeft(node);
		    }
		}
	    } else {
		node.getData().incrementCount();
	    }
	}
	node.updateHeight();

	return node;
    }

    /**
     * Auxiliary method for valid. Determines if a subtree based on node is a valid
     * subtree. An AVL must meet the BST validation conditions, and additionally be
     * balanced in all its subtrees - i.e. the difference in height between any two
     * children must be no greater than 1.
     *
     * @param node The root of the subtree to test for validity.
     * @return true if the subtree base on node is valid, false otherwise.
     */
    @Override
    protected boolean isValidAux(final TreeNode<T> node, TreeNode<T> minNode, TreeNode<T> maxNode) {

	boolean isValid = true;

	if (node != null) {
	    int leftHeight = node.getLeft() != null ? node.getLeft().getHeight() : 0;
	    int rightHeight = node.getRight() != null ? node.getRight().getHeight() : 0;

	    if ((minNode != null && node.getData().compareTo(minNode.getData()) <= 0)
		    || (maxNode != null && node.getData().compareTo(maxNode.getData()) >= 0)
		    || (node.getHeight() != Math.max(leftHeight, rightHeight) + 1)) {
		isValid = false;
	    } else {
		boolean isLeftValid = isValidAux(node.getLeft(), minNode, node);
		boolean isRightValid = isValidAux(node.getRight(), node, maxNode);
		isValid = isLeftValid && isRightValid;
	    }
	}

	return isValid;

    }

    /**
     * Determines whether two AVLs are identical.
     *
     * @param target The AVL to compare this AVL against.
     * @return true if this AVL and target contain nodes that match in position,
     *         data, count, and height, false otherwise.
     */
    public boolean equals(final AVL<T> target) {
	return super.equals(target);
    }

    /**
     * Auxiliary method for remove. Removes data from this BST. Same as BST removal
     * with addition of rebalance of nodes.
     *
     * @param node The current node (TreeNode).
     * @param data Data to be removed from the tree.
     * @return The replacement node.
     */
    @Override
    protected TreeNode<T> removeAux(TreeNode<T> node, final CountedData<T> data) {

	if (node == null) {
	    return null;
	}

	TreeNode<T> resultNode = node;

	final int result = data.compareTo(node.getData());

	if (result < 0) {
	    resultNode.setLeft(removeAux(node.getLeft(), data));
	} else if (result > 0) {
	    resultNode.setRight(removeAux(node.getRight(), data));
	} else {
	    if (node.getLeft() == null) {
		resultNode = node.getRight();
	    } else if (node.getRight() == null) {
		resultNode = node.getLeft();
	    } else {
		TreeNode<T> alpha = node;
		TreeNode<T> beta = node.getRight();

		while (beta.getLeft() != null) {
		    alpha = beta;
		    beta = beta.getLeft();
		}

		if (alpha != node) {
		    alpha.setLeft(beta.getRight());
		    beta.setRight(node.getRight());
		}

		beta.setLeft(node.getLeft());
		resultNode = beta;
	    }
	}

	resultNode.updateHeight();
	return resultNode;

    }

}

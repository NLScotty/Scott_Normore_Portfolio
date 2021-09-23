package sait.sll.utility;

import java.io.Serializable;

public class Node implements Serializable{
	private Object element;
	private Node nextNode;
	
	public Node(Object element, Node nextNode) {
		super();
		this.element = element;
		this.nextNode = nextNode;
	}
	
	public Node(Object element) {
		super();
		this.element = element;
		this.nextNode = null;
	}

	public Object getElement() {
		return element;
	}

	public void setElement(Object element) {
		this.element = element;
	}

	public Node getNext() {
		return nextNode;
	}

	public void setNext(Node nextNode) {
		this.nextNode = nextNode;
	}
}
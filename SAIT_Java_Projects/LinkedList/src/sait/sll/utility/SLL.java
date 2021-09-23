package sait.sll.utility;

import java.io.Serializable;

public class SLL implements LinkedListADT, Serializable{

	private Node head;
	private Node tail;
	
	private int size;
	
	public SLL(){
		this.head=null;
		this.tail=null;
		this.size=0;
	}
	
	@Override
	public boolean isEmpty() {
		if(this.size==0) {
			return true;
		}
		return false;
	}

	@Override
	public void clear() {
		this.head = null;
		this.tail = null;
		this.size = 0;
		
	}

	//might need error handling
	@Override
	public void append(Object data) {
		if(size==0) {
			prepend(data);
		}else {
			Node newNode = new Node(data);
			this.tail.setNext(newNode);
			this.tail = newNode;
			size++;
		}
	}

	@Override
	public void prepend(Object data) {
		Node newNode = new Node(data);
		newNode.setNext(this.head);
		this.head = newNode;
		if(this.tail==null) {
			this.tail=this.head;
		}
		size++;
	}


	@Override
	public void insert(Object data, int index) throws IndexOutOfBoundsException {
		if(index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		
		//Maybe not needed
		else if(index == 0) {
			this.prepend(data);
		}
		else if(index == size-1) {
			this.append(data);
		}
		
		else {
			Node current = this.head;
			for(int i=1; i<index; i++) {
				current = current.getNext();
			}
			Node newNode = new Node(data,current.getNext());
			current.setNext(newNode);
		}
		size++;
	}

	@Override
	public void replace(Object data, int index) throws IndexOutOfBoundsException {
		if(index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		else if(index == size-1) {
			Node current = this.head;
			for(int i=0; i<index-1; i++) {
				current = current.getNext();
			}
			Node newNode = new Node(data);
			current.setNext(newNode);
			this.tail = newNode;
		}
		else {
			Node current = this.head;
			for(int i=0; i<index; i++) {
				current = current.getNext();
			}
			current.setElement(data);
		}
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public void delete(int index) throws IndexOutOfBoundsException {
		if(index >= this.size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		if(index==0) {
			this.head=this.head.getNext();
		}
		else {
			Node current = this.head;
			for(int i=1; i<index; i++) {
				current=current.getNext();
			}
			if(index==size-1){
				this.tail=current;
			}else {
				Node toBeDeleted = current.getNext();
				current.setNext(toBeDeleted.getNext());
			}
		}
		size--;
	}

	@Override
	public Object retrieve(int index) throws IndexOutOfBoundsException {
		if(index >= this.size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		Node current = this.head;
		for(int i=0; i<index; i++) {
			current = current.getNext();
		}
		return current.getElement();
	}

	@Override
	public int indexOf(Object data) {
		Node current=this.head;
		for(int i=0; i<this.size; i++) {
			if(current.getElement().equals(data)) {
				return i;
			}
			else {
			    current = current.getNext();
			}
		}
		return -1;
	}

	@Override
	public boolean contains(Object data) {
		if(indexOf(data) > 0){
			return true;
		}
		return false;
	}
	/*
	public void print() {
		for(Node temp = head; temp != null; temp = temp.getNext()) {
			System.out.println(temp.getElement().toString());
		}
	}
	*/
}
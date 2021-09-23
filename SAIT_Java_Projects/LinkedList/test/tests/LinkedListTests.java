package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import sait.sll.utility.*;


/**
 * @author Nick Hamnett
 * @author Maryam Moussavi
 * @version 2020-03-24
 *
 */
class LinkedListTests {
	/**
	 * Contains the linked list that is manipulated in each test.
	 */
	private LinkedListADT linkedList;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		// Create your concrete linked list class and assign to to linkedList.
		this.linkedList = new SLL();
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		this.linkedList.clear();
	}

	/**
	 * Test the linked list is empty.
	 */
	@Test
	void testIsEmpty() {
		assertTrue(this.linkedList.isEmpty());
		assertEquals(0, this.linkedList.size());
	}
	
	/**
	 * Tests appending elements to the linked list.
	 */
	@Test
	void testAppendNode() {
		this.linkedList.append("a");
		this.linkedList.append("b");
		this.linkedList.append("c");
		this.linkedList.append("d");
		
		/**
		 * Linked list should now be:
		 * 
		 * a -> b -> c -> d
		 */
		
		// Test the linked list is not empty.
		assertFalse(this.linkedList.isEmpty());
		
		// Test the size is 4
		assertEquals(4, this.linkedList.size());

		// Test the first node value is a
		assertEquals("a", this.linkedList.retrieve(0));

		// Test the second node value is b
		assertEquals("b", this.linkedList.retrieve(1));
		
		// Test the third node value is c
		assertEquals("c", this.linkedList.retrieve(2));
		
		// Test the fourth node value is d
		assertEquals("d", this.linkedList.retrieve(3));
	}

	/**
	 * Tests prepending nodes to linked list.
	 */
	@Test
	void testPrependNodes() {
		this.linkedList.prepend("a");
		this.linkedList.prepend("b");
		this.linkedList.prepend("c");
		this.linkedList.prepend("d");
		
		/**
		 * Linked list should now be:
		 * 
		 * d -> c -> b -> a
		 */
		
		// Test the linked list is not empty.
		assertFalse(this.linkedList.isEmpty());
		
		// Test the size is 4
		assertEquals(4, this.linkedList.size());

		// Test the first node value is a
		assertEquals("d", this.linkedList.retrieve(0));

		// Test the second node value is b
		assertEquals("c", this.linkedList.retrieve(1));
		
		// Test the third node value is c
		assertEquals("b", this.linkedList.retrieve(2));
		
		// Test the fourth node value is d
		assertEquals("a", this.linkedList.retrieve(3));
	}
	
	/**
	 * Tests inserting node at valid index.
	 */
	@Test
	void testInsertNode() {
		this.linkedList.append("a");
		this.linkedList.append("b");
		this.linkedList.append("c");
		this.linkedList.append("d");
		
		this.linkedList.insert("e", 2);
		
		/**
		 * Linked list should now be:
		 * 
		 * a -> b -> e -> c -> d
		 */
		
		// Test the linked list is not empty.
		assertFalse(this.linkedList.isEmpty());
		
		// Test the size is 4
		assertEquals(5, this.linkedList.size());

		// Test the first node value is a
		assertEquals("a", this.linkedList.retrieve(0));

		// Test the second node value is b
		assertEquals("b", this.linkedList.retrieve(1));
		
		// Test the third node value is e
		assertEquals("e", this.linkedList.retrieve(2));
		
		// Test the third node value is c
		assertEquals("c", this.linkedList.retrieve(3));
		
		// Test the fourth node value is d
		assertEquals("d", this.linkedList.retrieve(4));
	}
	
	/**
	 * Tests replacing existing nodes data.
	 */
	@Test
	void testReplaceNode() {
		this.linkedList.append("a");
		this.linkedList.append("b");
		this.linkedList.append("c");
		this.linkedList.append("d");
		
		this.linkedList.replace("e", 2);
		
		/**
		 * Linked list should now be:
		 * 
		 * a -> b -> e -> d
		 */
		
		// Test the linked list is not empty.
		assertFalse(this.linkedList.isEmpty());
		
		// Test the size is 4
		assertEquals(4, this.linkedList.size());

		// Test the first node value is a
		assertEquals("a", this.linkedList.retrieve(0));

		// Test the second node value is b
		assertEquals("b", this.linkedList.retrieve(1));
		
		// Test the third node value is e
		assertEquals("e", this.linkedList.retrieve(2));
		
		// Test the fourth node value is d
		assertEquals("d", this.linkedList.retrieve(3));
		
		this.linkedList.replace("f",0);
		
		/**
		 * Linked list should now be:
		 * 
		 * f -> b -> e -> d
		 */
		
		// Test the linked list is not empty.
		assertFalse(this.linkedList.isEmpty());
		
		// Test the size is 4
		assertEquals(4, this.linkedList.size());
		
		// Check the Contents
		assertEquals("f", this.linkedList.retrieve(0));
		assertEquals("b", this.linkedList.retrieve(1));	
		assertEquals("e", this.linkedList.retrieve(2));
		assertEquals("d", this.linkedList.retrieve(3));	
		
		//Checks to see if head is the correct node
		/**
		 * Linked list should now be:
		 * 
		 * g -> f -> b -> e -> d
		 */
		this.linkedList.prepend("g");
		assertEquals("g", this.linkedList.retrieve(0));
		assertEquals("f", this.linkedList.retrieve(1));	
		assertEquals("b", this.linkedList.retrieve(2));
		assertEquals("e", this.linkedList.retrieve(3));
		assertEquals("d", this.linkedList.retrieve(4));
		
		this.linkedList.replace("h",4);
		
		/**
		 * Linked list should now be:
		 * 
		 * g -> f -> b -> e -> h
		 */
		
		// Test the linked list is not empty.
		assertFalse(this.linkedList.isEmpty());
		
		// Test the size is 5
		assertEquals(5, this.linkedList.size());
		
		// Check the Contents
		assertEquals("g", this.linkedList.retrieve(0));
		assertEquals("f", this.linkedList.retrieve(1));	
		assertEquals("b", this.linkedList.retrieve(2));
		assertEquals("e", this.linkedList.retrieve(3));	
		assertEquals("h", this.linkedList.retrieve(4));	
		
		//Checks to see if tail is the correct node
		/**
		 * Linked list should now be:
		 * 
		 * g -> f -> b -> e -> h ->i
		 */
		this.linkedList.append("i");
		// Check the Contents
		assertEquals("g", this.linkedList.retrieve(0));
		assertEquals("f", this.linkedList.retrieve(1));	
		assertEquals("b", this.linkedList.retrieve(2));
		assertEquals("e", this.linkedList.retrieve(3));
		assertEquals("h", this.linkedList.retrieve(4));
		assertEquals("i", this.linkedList.retrieve(5));
	}
	
	/**
	 * Tests deleting node from linked list.
	 */
	@Test
	void testDeleteNode() {
		this.linkedList.append("a");
		this.linkedList.append("b");
		this.linkedList.append("c");
		this.linkedList.append("d");
		
		this.linkedList.delete(2);
		
		/**
		 * Linked list should now be:
		 * 
		 * a -> b -> d
		 */
		
		// Test the linked list is not empty.
		assertFalse(this.linkedList.isEmpty());
		
		// Test the size is 4
		assertEquals(3, this.linkedList.size());

		// Test the first node value is a
		assertEquals("a", this.linkedList.retrieve(0));

		// Test the second node value is b
		assertEquals("b", this.linkedList.retrieve(1));
		
		// Test the fourth node value is d
		assertEquals("d", this.linkedList.retrieve(2));
		
		//test for the deleting tail node
		
		this.linkedList.delete(2);
		this.linkedList.append("f");
		
		/**
		 * Linked list should now be:
		 * 
		 * a -> b -> f
		 */
		
		// Test the linked list is not empty.
		assertFalse(this.linkedList.isEmpty());
		
		// Test the size is 4
		assertEquals(3, this.linkedList.size());
		
		assertEquals("b", this.linkedList.retrieve(1));
		assertEquals("f", this.linkedList.retrieve(2));
		
		// Test deleting the head node
		this.linkedList.delete(0);
		/**
		 * Linked list should now be:
		 * 
		 * b -> f
		 */
		
		// Test the linked list is not empty.
		assertFalse(this.linkedList.isEmpty());	
		// Test the size is 4
		assertEquals(2, this.linkedList.size());
		
		assertEquals("b", this.linkedList.retrieve(0));
		assertEquals("f", this.linkedList.retrieve(1));		
	}
	
	/**
	 * Tests finding and retrieving node in linked list.
	 */
	@Test
	void testFindNode() {
		this.linkedList.append("a");
		this.linkedList.append("b");
		this.linkedList.append("c");
		this.linkedList.append("d");
		
		/**
		 * Linked list should now be:
		 * 
		 * a -> b -> c -> d
		 */
		
		boolean contains = this.linkedList.contains("b");
		assertTrue(contains);
		
		int index = this.linkedList.indexOf("b");
		assertEquals(1, index);
		
		String value = (String) this.linkedList.retrieve(1);
		assertEquals("b", value);
	}
	
	//Group written tests
	@Test
	void testSize() {
		this.linkedList.append("a");
		this.linkedList.append("b");
		this.linkedList.append("c");
		this.linkedList.append("d");
		this.linkedList.append("d");
		this.linkedList.append("d");
		
		assertEquals(6, this.linkedList.size());
	}
	
	@Test
	void testIndexOf() {
		this.linkedList.append("a");
		this.linkedList.append("b");
		this.linkedList.append("c");
		this.linkedList.append("d");
		
		/**
		 * Linked list should now be:
		 * 
		 * a -> b -> c -> d
		 */
		// Check the Contents
		assertEquals("a", this.linkedList.retrieve(0));
		assertEquals("b", this.linkedList.retrieve(1));	
		assertEquals("c", this.linkedList.retrieve(2));
		assertEquals("d", this.linkedList.retrieve(3));
		
		// Test the size is 4
		assertEquals(4, this.linkedList.size());
		
		// Tests the use of the indexOf method
		assertEquals(0, this.linkedList.indexOf("a"));
		assertEquals(1, this.linkedList.indexOf("b"));
		assertEquals(2, this.linkedList.indexOf("c"));
		assertEquals(3, this.linkedList.indexOf("d"));
		assertEquals(-1, this.linkedList.indexOf("f"));
	}
	@Test
	void testClear() {
		this.linkedList.append("a");
		this.linkedList.append("b");
		this.linkedList.append("c");
		this.linkedList.append("d");
		/**
		 * Linked list should now be:
		 * 
		 * a -> b -> c -> d
		 */
		// Check the Contents
		assertEquals("a", this.linkedList.retrieve(0));
		assertEquals("b", this.linkedList.retrieve(1));	
		assertEquals("c", this.linkedList.retrieve(2));
		assertEquals("d", this.linkedList.retrieve(3));
		// Test the size is 4
		assertEquals(4, this.linkedList.size());
		this.linkedList.clear();
		
		//check the size of the linked list
		assertEquals(0, this.linkedList.size());
		assertEquals(-1, this.linkedList.indexOf("a"));
		assertEquals(-1, this.linkedList.indexOf("d"));
		assertFalse(linkedList.contains("c"));
	}
	@Test
	void testRetreive() {
		this.linkedList.append("a");
		this.linkedList.append("b");
		this.linkedList.append("c");
		this.linkedList.append("d");
		
		/**
		 * Linked list should now be:
		 * 
		 * a -> b -> c -> d
		 */
		// Check the Contents
		assertEquals("a", this.linkedList.retrieve(0));
		assertEquals("b", this.linkedList.retrieve(1));	
		assertEquals("c", this.linkedList.retrieve(2));
		assertEquals("d", this.linkedList.retrieve(3));
	}
	void testContains() {
		this.linkedList.append("a");
		this.linkedList.append("b");
		this.linkedList.append("c");
		this.linkedList.append("d");
		
		/**
		 * Linked list should now be:
		 * 
		 * a -> b -> c -> d
		 */
		// Check the Contents
		assertEquals("a", this.linkedList.retrieve(0));
		assertEquals("b", this.linkedList.retrieve(1));	
		assertEquals("c", this.linkedList.retrieve(2));
		assertEquals("d", this.linkedList.retrieve(3));
		
		//Tests the use of the contains method
		assertTrue(linkedList.contains("a"));
		assertTrue(linkedList.contains("b"));
		assertTrue(linkedList.contains("c"));
		assertTrue(linkedList.contains("d"));
		assertFalse(linkedList.contains("e"));
	}
	//tests our methods out of bounds exceptions
	@Test
	void testOutOfBounds(){
		this.linkedList.append("a");
		this.linkedList.append("b");
		this.linkedList.append("c");
		this.linkedList.append("d");
		
		try {
			this.linkedList.insert("f", 4);
			fail();
		}catch (IndexOutOfBoundsException ex){
			
		}
		try {
			this.linkedList.delete(4);
			fail();
		}catch (IndexOutOfBoundsException ex){
			
		}
		try {
			this.linkedList.retrieve(-2);
			fail();
		}catch (IndexOutOfBoundsException ex){
			
		}
		try {
			this.linkedList.replace("f",-4);
			fail();
		}catch (IndexOutOfBoundsException ex){
			
		}
	}
}

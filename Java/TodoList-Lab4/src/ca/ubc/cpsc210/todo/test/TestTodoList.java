package ca.ubc.cpsc210.todo.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import ca.ubc.cpsc210.todo.model.TodoItem;
import ca.ubc.cpsc210.todo.model.TodoList;
import ca.ubc.cpsc210.todo.model.UnscheduledTodoItem;

public class TestTodoList {

	private TodoList todoList;
	
	@Before
	public void setUp() {
		todoList = new TodoList();
	}
	
	@Test
	public void testGetAllTodoItems() {
		Date currentDateTime = new Date(); // Default date constructor creates a date object of current date/time
		todoList.addTodoItem(new UnscheduledTodoItem("Groceries", "Pick up groceries at the UBC farmer's market.", false));
		todoList.addTodoItem(new UnscheduledTodoItem("Books on Magic", "Ask Twilight for recommendations for books on magic.", false));
		todoList.addTodoItem(new UnscheduledTodoItem("Vancouver Aquarium", "Visit the new Penguin Walk feature at the aquarium!", true));
		
		List<TodoItem> todoItems = todoList.getAllTodoItems();
		assertEquals(todoItems.size(), 3);
		assertEquals(todoItems.get(0).getTitle(), "Groceries");
		assertEquals(todoItems.get(1).getTitle(), "Books on Magic");
		assertEquals(todoItems.get(2).getTitle(), "Vancouver Aquarium");
	}
	
	@Test
	public void testGetTodoItemByIndex() {
		Date currentDateTime = new Date(); // Default date constructor creates a date object of current date/time
		todoList.addTodoItem(new UnscheduledTodoItem("Fish Food", "Fish flakes or pellets?", true));
		todoList.addTodoItem(new UnscheduledTodoItem("Apple Cider", "Drop by Applejack's farm for cider season.", false));
		todoList.addTodoItem(new UnscheduledTodoItem("Present", "Buy birthday present for sister.", true));
		
		TodoItem todoItem0 = todoList.getTodoItemByIndex(0);
		assertEquals(todoItem0.getTitle(), "Fish Food");
		
		TodoItem todoItem1 = todoList.getTodoItemByIndex(1);
		assertEquals(todoItem1.getTitle(), "Apple Cider");
		
		TodoItem todoItem2 = todoList.getTodoItemByIndex(2);
		assertEquals(todoItem2.getTitle(), "Present");

	}
	
	@Test
	public void testGetTodoItemById() {
		Date currentDateTime = new Date(); // Default date constructor creates a date object of current date/time
		todoList.addTodoItem(new UnscheduledTodoItem("Tablet", "Buy a screen protector at the night market.", false));
		todoList.addTodoItem(new UnscheduledTodoItem("Potions", "Visit Zecora for some potions.", false));
		todoList.addTodoItem(new UnscheduledTodoItem("Quiz", "Study for upcoming quiz.", true));
		
		List<TodoItem> todoItems = todoList.getAllTodoItems();
		assertEquals(todoItems.size(), 3);
		
		
		for (TodoItem item: todoList.getAllTodoItems()) {
			TodoItem todoItem = todoList.getTodoItemById(item.getId());
			assertEquals(todoItem.getTitle(), item.getTitle());
		}
		
		TodoItem item = todoList.getTodoItemById(UUID.randomUUID().toString());
		assertEquals(item, null);

	}
	
	@Test
	public void testAddTodoItem() {
		assertEquals(todoList.getAllTodoItems().size(), 0);
		todoList.addTodoItem(new UnscheduledTodoItem("Plan Courses", "Plan next year's courses.", true));
		assertEquals(todoList.getAllTodoItems().size(), 1);
		assertEquals(todoList.getTodoItemByIndex(0).getTitle(), "Plan Courses");
	}


	@Test
	public void testFilterTodoItemsBySearchTerm() {
		assertEquals(todoList.getAllTodoItems().size(), 0);
		
		todoList.addTodoItem(new UnscheduledTodoItem("FIX BUGS", "Find out why I'm getting null pointer errors", true));
		todoList.addTodoItem(new UnscheduledTodoItem("Research Flying", "How do you create a sonic rainboom?", false));
		todoList.addTodoItem(new UnscheduledTodoItem("Trip", "Why am I flying to LA again?", true));
		
		assertEquals(todoList.getAllTodoItems().size(), 3);	
		
		List<TodoItem> filteredList = todoList.filterTodoItemsBySearchTerm("fix bugs");
		assertEquals(filteredList.size(), 1);	
		
		filteredList = todoList.filterTodoItemsBySearchTerm("flying");
		assertEquals(filteredList.size(), 2);	

		filteredList = todoList.filterTodoItemsBySearchTerm("rainboom");
		assertEquals(filteredList.size(), 1);	

		filteredList = todoList.filterTodoItemsBySearchTerm("computer");
		assertEquals(filteredList.size(), 0);	
		
		filteredList = todoList.filterTodoItemsBySearchTerm("Why");
		assertEquals(filteredList.size(),2);
		
	}
	

	@Test
	public void testFilterTodoItemsByIncompletion() {
		assertEquals(todoList.getAllTodoItems().size(), 0);
		
		todoList.addTodoItem(new UnscheduledTodoItem("Buy Textbooks", "Why are textbooks so expensive?", false));
		todoList.addTodoItem(new UnscheduledTodoItem("Tutor", "Give Scootaloo some flying lessons!", true));
		todoList.addTodoItem(new UnscheduledTodoItem("Attend Class", "You shouldn't need to be reminded of that.", true));
		
		assertEquals(todoList.getAllTodoItems().size(), 3);	
		todoList.getTodoItemByIndex(0).setCompleted(true);
		
		List<TodoItem> filteredList = todoList.filterTodoItemsByIncompletion();
		assertEquals(filteredList.size(), 2);	

		todoList.getTodoItemByIndex(1).setCompleted(true);
		
		filteredList = todoList.filterTodoItemsByIncompletion();
		assertEquals(filteredList.size(), 1);	

		todoList.getTodoItemByIndex(2).setCompleted(true);
		
		filteredList = todoList.filterTodoItemsByIncompletion();
		assertEquals(filteredList.size(), 0);	
		
	}
	
}

package ca.ubc.cpsc210.todo.test;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import ca.ubc.cpsc210.todo.model.TodoItem;
import ca.ubc.cpsc210.todo.model.TodoList;

public class TestTodoList {

	private TodoList todoList;

	@Before
	public void setUp() {
		todoList = new TodoList();
	}

	@Test
	public void testGetAllTodoItems() {
		Date currentDateTime = new Date(); // Default date constructor creates a
											// date object of current date/time
		todoList.addTodoItem("Groceries",
				"Pick up groceries at the UBC farmer's market.",
				currentDateTime);
		todoList.addTodoItem("Books on Magic",
				"Ask Twilight for recommendations for books on magic.",
				currentDateTime);
		todoList.addTodoItem("Vancouver Aquarium",
				"Visit the new Penguin Walk feature at the aquarium!",
				currentDateTime);

		List<TodoItem> todoItems = todoList.getAllTodoItems();
		assertEquals(3, todoItems.size());
		assertEquals("Groceries", todoItems.get(0).getTitle());
		assertEquals("Books on Magic", todoItems.get(1).getTitle());
		assertEquals("Vancouver Aquarium", todoItems.get(2).getTitle());
	}

	@Test
	public void testGetTodoItemByIndex() {
		Date currentDateTime = new Date(); // Default date constructor creates a
											// date object of current date/time
		todoList.addTodoItem("Fish Food", "Fish flakes or pellets?",
				currentDateTime);
		todoList.addTodoItem("Apple Cider",
				"Drop by Applejack's farm for cider season.", currentDateTime);
		todoList.addTodoItem("Present", "Buy birthday present for sister.",
				currentDateTime);

		TodoItem todoItem0 = todoList.getTodoItemByIndex(0);
		assertEquals("Fish Food", todoItem0.getTitle());

		TodoItem todoItem1 = todoList.getTodoItemByIndex(1);
		assertEquals("Apple Cider", todoItem1.getTitle());

		TodoItem todoItem2 = todoList.getTodoItemByIndex(2);
		assertEquals("Present", todoItem2.getTitle());

	}

	@Test
	public void testGetTodoItemById() {
		Date currentDateTime = new Date(); // Default date constructor creates a
											// date object of current date/time
		todoList.addTodoItem("Tablet",
				"Buy a screen protector at the night market.", currentDateTime);
		todoList.addTodoItem("Potions", "Visit Zecora for some potions.",
				currentDateTime);
		todoList.addTodoItem("Quiz", "Study for upcoming quiz.",
				currentDateTime);

		List<TodoItem> todoItems = todoList.getAllTodoItems();
		assertEquals(3, todoItems.size());

		for (TodoItem item : todoList.getAllTodoItems()) {
			TodoItem todoItem = todoList.getTodoItemById(item.getId());
			assertEquals(item.getTitle(), todoItem.getTitle());
		}

		TodoItem item = todoList.getTodoItemById(UUID.randomUUID().toString());
		assertEquals(null, item);

	}

	@Test
	public void testAddTodoItem() {
		assertEquals(todoList.getAllTodoItems().size(), 0);
		todoList.addTodoItem("Plan Courses", "Plan next year's courses.", null);
		assertEquals(1, todoList.getAllTodoItems().size());
		assertEquals("Plan Courses", todoList.getTodoItemByIndex(0).getTitle());
	}

	@Test
	public void testUpdateTodoItem() {
		assertEquals(0, todoList.getAllTodoItems().size());

		todoList.addTodoItem("Buy Phone Charger", "Pick up a micro-USB cable",
				null);
		assertEquals(1, todoList.getAllTodoItems().size());

		TodoItem item = todoList.getTodoItemByIndex(0);
		assertEquals("Buy Phone Charger", item.getTitle());

		Date currentDateTime = new Date();
		todoList.updateTodoItem(item.getId(), "Buy Android Charger",
				"Purchase a micro-USB cable", currentDateTime);

		assertEquals(1, todoList.getAllTodoItems().size());
		item = todoList.getTodoItemByIndex(0);
		assertEquals("Buy Android Charger", item.getTitle());
		assertEquals("Purchase a micro-USB cable", item.getDescription());
		assertEquals(currentDateTime, item.getDueDate());
	}

	@Test
	public void testFilterTodoItemsBySearchTerm() {
		assertEquals(todoList.getAllTodoItems().size(), 0);

		todoList.addTodoItem("FIX BUGS",
				"Find out why I'm getting null pointer errors", null);
		todoList.addTodoItem("Research Flying",
				"How do you create a sonic rainboom?", null);
		todoList.addTodoItem("Trip", "Why am I flying to LA again?", null);

		assertEquals(3, todoList.getAllTodoItems().size());

		List<TodoItem> filteredList = todoList
				.filterTodoItemsBySearchTerm("fix bugs");
		assertEquals(1, filteredList.size());

		filteredList = todoList.filterTodoItemsBySearchTerm("flying");
		assertEquals(2, filteredList.size());

		filteredList = todoList.filterTodoItemsBySearchTerm("rainboom");
		assertEquals(1, filteredList.size());

		filteredList = todoList.filterTodoItemsBySearchTerm("computer");
		assertEquals(0, filteredList.size());

		filteredList = todoList.filterTodoItemsBySearchTerm("Why");
		assertEquals(2, filteredList.size());

	}

	@Test
	public void testFilterTodoItemsByIncompletion() {
		assertEquals(0, todoList.getAllTodoItems().size());

		todoList.addTodoItem("Buy Textbooks",
				"Why are textbooks so expensive?", null);
		todoList.addTodoItem("Tutor", "Give Scootaloo some flying lessons!",
				null);
		todoList.addTodoItem("Attend Class",
				"You shouldn't need to be reminded of that.", null);

		assertEquals(3, todoList.getAllTodoItems().size());
		todoList.getTodoItemByIndex(0).setCompleted(true);

		List<TodoItem> filteredList = todoList.filterTodoItemsByIncompletion();
		assertEquals(2, filteredList.size());

		todoList.getTodoItemByIndex(1).setCompleted(true);

		filteredList = todoList.filterTodoItemsByIncompletion();
		assertEquals(1, filteredList.size());

		todoList.getTodoItemByIndex(2).setCompleted(true);

		filteredList = todoList.filterTodoItemsByIncompletion();
		assertEquals(0, filteredList.size());

	}

}

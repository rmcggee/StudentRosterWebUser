import java.util.List;
import java.util.Scanner;

import controller.NewListItemHelper;
import model.NewListItem;

public class NewStartProgram {


	static Scanner in = new Scanner(System.in);
	static NewListItemHelper lih = new NewListItemHelper();

	private static void addAnStudent() {
		// Capture first and last name of new record
		System.out.print("Enter a first name: ");
		String fname = in.nextLine();
		System.out.print("Enter a last name: ");
		String lname = in.nextLine();
		NewListItem	toAdd	=	new	NewListItem(fname,	lname);
		lih.insertItem(toAdd);
	}

	private static void deleteAnStudent() {
		// Capture first and last names to identify record to delate
		System.out.print("Enter the first name to delete: ");
		String fname = in.nextLine();
		System.out.print("Enter the last name to delete: ");
		String lname = in.nextLine();
		NewListItem	toDelete	=	new	NewListItem(fname,	lname);
		lih.deleteItem(toDelete);
	}

	private static void editAnStudent() {

		System.out.println("How would you like to search? ");
		System.out.println("1 : Search by first name");
		System.out.println("2 : Search by last name");
		int searchBy = in.nextInt();
		in.nextLine();
		List<NewListItem> foundItems;
		if (searchBy == 1) {
			System.out.print("Enter the first name: ");
			String fname = in.nextLine();
			foundItems	=	lih.searchForItemByFirstName(fname);
		} else {
			System.out.print("Enter the last name: ");
			String lname = in.nextLine();

			foundItems	=	lih.searchForItemByLastName(lname);
		}

		if (!foundItems.isEmpty()) {
			System.out.println("Found Results.");
			for (NewListItem l : foundItems) {
				System.out.println(l.getId() + " : " + l.toString());
			}
			System.out.print("Which ID to edit: ");
			int idToEdit = in.nextInt();

			NewListItem toEdit = lih.searchForItemById(idToEdit);
			System.out.println("Retrieved " + toEdit.getLastName() + " from " + toEdit.getFirstName());
			System.out.println("1 : Update first name");
			System.out.println("2 : Update last name");
			int update = in.nextInt();
			in.nextLine();

			if (update == 1) {
				System.out.print("New First Name: ");
				String newFirstName= in.nextLine();
				toEdit.setFirstName(newFirstName);
			} else if (update == 2) {
				System.out.print("New Last Name: ");
				String newLastName = in.nextLine();
				toEdit.setLastName(newLastName);
			}

			lih.updateItem(toEdit);

		} else {
			System.out.println("---- No results found");
		}

	}

	public static void main(String[] args) {
		try {
			runMenu();
		}

		catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	public static void runMenu() {
		boolean goAgain = true;
		System.out.println("--- Welcome to our awesome student roster! ---");
		while (goAgain) {
			System.out.println("Select a number :");
			System.out.println("1: Add student");
			System.out.println("2: Edit student");
			System.out.println("3: Delete student");
			System.out.println("4: View student list");
			System.out.println("5: Exit the awesome program");
			System.out.print("Your selection: ");
			int selection = in.nextInt();
			in.nextLine();

			if (selection == 1) {
				addAnStudent();
			} else if (selection == 2) {
				editAnStudent();
			} else if (selection == 3) {
				deleteAnStudent();
			} else if (selection == 4) {
				viewTheList();
			} else {
				lih.cleanUp();
				System.out.println("   Thanks for updating the roster!   Goodbye!   ");
				goAgain = false;
			}

		}

	}

	private static void viewTheList() {
		//print out the results	
		List<NewListItem>	allItems = lih.showAllItems();
		for(NewListItem singleItem :	allItems){
			System.out.println(singleItem.returnItemDetails());
		}

	}

}

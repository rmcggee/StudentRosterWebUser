package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.NewListItem;

/**
 * Servlet implementation class EditItemServlet
 */
@WebServlet("/editItemServlet")
public class EditItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditItemServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		NewListItemHelper dao = new NewListItemHelper();
		String fName = request.getParameter("firstName");
		String lName = request.getParameter("lastName");
		Integer tempId = Integer.parseInt(request.getParameter("id"));
		NewListItem itemToUpdate = dao.searchForItemById(tempId);
	
		itemToUpdate.setFirstName(fName);
		itemToUpdate.setLastName(lName);
		
		dao.updateItem(itemToUpdate);
		getServletContext().getRequestDispatcher("/viewAllItemsServlet").forward(request, response);
		
	}

}

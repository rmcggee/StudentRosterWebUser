package controller;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.NewListItem;

public class NewListItemHelper {

	static	EntityManagerFactory	emfactory = Persistence.createEntityManagerFactory("StudentRoster");	

	public void insertItem(NewListItem li) {
		EntityManager em	= emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(li);
		em.getTransaction().commit();
		em.close();
	};

	public	List<NewListItem>	showAllItems(){
		EntityManager em = emfactory.createEntityManager();
		List<NewListItem> allItems	= em.createQuery("SELECT i FROM	NewListItem i").getResultList();
		return	allItems;
	}

	public	void	deleteItem(NewListItem	toDelete)	{
		EntityManager	em	=	emfactory.createEntityManager();
		em.getTransaction().begin();
		System.out.println(toDelete.getFirstName());
		System.out.println(toDelete.getLastName());
		TypedQuery<NewListItem> typedQuery	= em.createQuery("select st from NewListItem st where st.firstName = :selectedFirstName and st.lastName = :selectedLastName",	NewListItem.class);
		// These two lines will substitute with the variable values
		typedQuery.setParameter("selectedFirstName", toDelete.getFirstName());
		typedQuery.setParameter("selectedLastName",	toDelete.getLastName());
		//keep only one
		typedQuery.setMaxResults(1);
		//get	the	result	and	save	it	into	a	new	list	item
		NewListItem	result	=	typedQuery.getSingleResult();
		// result will be removed
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}

	public NewListItem searchForItemById(int idToEdit)	{
		EntityManager	em	=	emfactory.createEntityManager();
		em.getTransaction().begin();
		NewListItem found	=	em.find(NewListItem.class,	idToEdit);
		em.close();
		return	found;
	}

	public	void	updateItem(NewListItem	toEdit)	{
		EntityManager	em	=	emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}

	public List<NewListItem> searchForItemByFirstName(String myFirstName){
		EntityManager	em	=	emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<NewListItem>	typedQuery	=	em.createQuery("select	st from NewListItem st where st.firstName =	:selectedFirstName",	NewListItem.class);
		typedQuery.setParameter("selectedFirstName", myFirstName);
		List<NewListItem>	foundItems	=	typedQuery.getResultList();
		em.close();
		return	foundItems;

	}
	public	List<NewListItem>	searchForItemByLastName(String	myLastName)	{

		EntityManager em	=	emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<NewListItem>	typedQuery	=	em.createQuery("select st from	NewListItem	st where st.lastName = :selectedLastName",	NewListItem.class);
		typedQuery.setParameter("selectedLastName",	myLastName);
		List<NewListItem>	foundItems	=	typedQuery.getResultList();
		em.close();
		return	foundItems;
	}

	public	void	cleanUp(){
		emfactory.close();
	}

}

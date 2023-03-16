package com.anudip.BookManagementSystem;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 *
 */
public class App 
{
	public static SessionFactory createConfiguration() {
		Configuration conf = new Configuration().configure().addAnnotatedClass(Book.class);
		SessionFactory ss = conf.buildSessionFactory();
		return ss;
	}
	public static void createBook(Session ss, Transaction t) {

		Book b1 = new Book(101, " In Search of Lost Time ", 900," Marcel Proust"," National Library");
		Book b2 = new Book(102, " War and Peace", 540, " Leo Tolstoy"," National Library");
		Book b3 = new Book(103, " A Suitable Boy", 800, " Vikram Seth"," National Library");
		Book b4 = new Book(104, " The Great Indian Novel", 690, " Shashi Tharoor"," central Library");
		Book b5 = new Book(105, " The House of Blue Mangoes", 910, " David Davidar’s"," central Library");
		ss.save(b1);
		ss.save(b2);
		ss.save(b3);
		ss.save(b4);
		ss.save(b5);
		t.commit();
	}
	public static void getAllBook(Session ss, Transaction t) {
		Query query = ss.createQuery("from Book");

		List<Book> books = query.list();
		for (Book book : books) {
			System.out.println(book.getBookId()+book.getBookName()+book.getBookPrice()+book.getAuthorName()+book.getLibraryName());
		}
	}
	public static void updateBook(Session ss, Transaction t) {
		t.begin();
		Book b = ss.find(Book.class, 2);
		b.setBookName("Hamlet");
		ss.save(b);
		t.commit();

	}
	public static void deleteBook(Session ss, Transaction t) {
		t.begin();
		Book b = ss.find(Book.class, 2);

		ss.delete(b);
		t.commit();

	}
		public static void main(String[] args)

		{
			SessionFactory sessionfactory = createConfiguration();
			Session session = sessionfactory.openSession();
			Transaction t = session.beginTransaction();
			createBook(session, t);
			updateBook(session,t);
			deleteBook(session,t);
			getAllBook(session, t);
			System.out.println("Done...");
		}
	}

	

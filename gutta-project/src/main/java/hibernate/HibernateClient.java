package hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import model.Person;

public class HibernateClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = null;
		// configures settings from hibernate.cfg.xml
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate-cfg.xml").build();
		try {
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		List<Person> result = session.createQuery("from Person", Person.class).list();

		result.forEach(person -> {
			System.out.println(person.getId() + " " + person.getFirstName() + " " + person.getLastName());
		});

		session.getTransaction().commit();
		session.close();
	}

}

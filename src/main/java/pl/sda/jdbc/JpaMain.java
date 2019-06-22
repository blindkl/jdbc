package pl.sda.jdbc;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("sdajpa");

    public static void main(String[] args) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        createCustomer(entityManager);
        findCustomersByLastName(entityManager, "Kowalski");
    }

    private static List<Customer> findCustomersByLastName(EntityManager entityManager, String lastName) {
        entityManager.createQuery("select c from Customer c where lastName = :lastName", Customer.class);
    }

    public static Customer createCustomer(EntityManager entityManager) {
        Customer customer = new Customer();
        customer.setFirstName("Jan");
        customer.setLastName("Kowalski");
        customer.setAge(35);
        customer.setCity("Łódź");
        customer.setPostalCode("91-001");
        entityManager.getTransaction().begin();
        entityManager.persist(customer);
        entityManager.getTransaction().commit();
        return customer;
    }
}

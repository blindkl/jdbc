package pl.sda.jdbcjpa.jpa;

import com.google.common.collect.Lists;
import pl.sda.jdbcjpa.JpaDao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManager entityManager = JpaDao.getEMF().createEntityManager();

//        createCustomer(entityManager);
//        findCustomersByLastName(entityManager, "Kowalski");
//        System.out.println(findCustomerByPesel("123"));

        createCustomerWithOrders(entityManager);
        Customer customerByPesel = findCustomerByPesel("124");
        addNewBook("Pan Tadeusz - twarda oprawa", new Book());
        addNewBook("Pan Tadeusz - ebook", new Ebook());

        System.out.println(basicNamedQuery("Jan"));

        entityManager.close();
        JpaDao.getEMF().close();
    }

    private static List<Customer> basicNamedQuery(String name) {
        EntityManager entityManager = JpaDao.getEMF().createEntityManager();

        TypedQuery<Customer> findByFirstName =
                entityManager.createNamedQuery("findByFirstName", Customer.class);
        findByFirstName.setParameter("fn", name);
        return findByFirstName.getResultList();
    }

    private static void addNewBook(String title, Product book) {
        book.setProductName(title);
        //EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager(); //zastąpione przez JpaDao.saveEntity
//        entityManager.getTransaction().begin();
//        entityManager.persist(book);
//        entityManager.getTransaction().commit();
        JpaDao.saveEntity(book);
    }

    private static List<Customer> findCustomersByLastName(EntityManager entityManager, String xxx) {
        TypedQuery<Customer> query = entityManager.createQuery(
                "select c from Customer c where lastName = :ln",
                Customer.class
        );
        query.setParameter("ln", xxx);
        List<Customer> resultList = query.getResultList();
        System.out.println(resultList);
        return resultList;

    }

    public static Customer createCustomer(EntityManager entityManager) {

        Customer customer = new Customer();
        customer.setFirstName("Jan");
        customer.setLastName("Kowalski");
        customer.setAge(35);
        customer.setPesel("123");
        customer.setCustomerStatus(CustomerStatus.ACTIVATED);
        customer.getNickname().add("adaś");
        entityManager.getTransaction().begin();
        entityManager.persist(customer);
        entityManager.getTransaction().commit();
        return customer;
    }

    public static Customer findCustomerByPesel(String pesel) {
        EntityManager entityManager = JpaDao.getEMF().createEntityManager();
        TypedQuery<Customer> query = entityManager.createQuery(
                "select c from Customer c " +
                        "where pesel = :pp", Customer.class
        );
        query.setParameter("pp", pesel);
        return query.getSingleResult();
    }

    public static Customer createCustomerWithOrders(EntityManager entityManager) {

        Customer customer = new Customer();
        customer.setFirstName("Jan");
        customer.setLastName("Nowak");
        customer.setAge(35);
        customer.setPesel("124");
        customer.setCustomerStatus(CustomerStatus.ACTIVATED);
        customer.getNickname().add("tomek");
        OrderLine ol1 = new OrderLine(null, "Rower", BigDecimal.valueOf(1500.9));
        OrderLine ol2 = new OrderLine(null, "Oswietlenie", BigDecimal.valueOf(120.8));

        Order o1 = new Order(BigDecimal.valueOf(500),
                customer.getLastName(),
                Lists.newArrayList(ol1),
                customer);
        Order o2 = new Order(
                BigDecimal.valueOf(1500),
                customer.getLastName(),
                Lists.newArrayList(ol2),
                customer);
        ol1.setOrderHeader(o1);
        ol2.setOrderHeader(o2);
//fixme
        customer.setOrders(Lists.newArrayList(o1, o2));

        Cart cart = new Cart();
        cart.setCust(customer);
        customer.setCart(cart);

        entityManager.getTransaction().begin();
//        entityManager.persist(o1);
//        entityManager.persist(o2);
//        entityManager.persist(cart); //moglismy z tego zrezygnowac z racji wybrania operacji cascade PERSIST
        entityManager.persist(customer);
        entityManager.getTransaction().commit();
        return customer;
    }
}

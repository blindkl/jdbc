package pl.sda.jdbcjpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaDao {

    public static final EntityManagerFactory ENTITY_MANAGER_FACTORY =
            Persistence.createEntityManagerFactory("yyy"); //yyy -> tak sie nazywa konfiguracja dostepu do bazy danych

    private static EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();

    public static <E extends BaseEntity> void saveEntity(E entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    public static EntityManagerFactory getEMF() {
        return ENTITY_MANAGER_FACTORY;
    }

    public static EntityManager getEM() {
        return entityManager;
    }
}

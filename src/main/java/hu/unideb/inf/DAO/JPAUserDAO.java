package hu.unideb.inf.DAO;

import hu.unideb.inf.Modell.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class JPAUserDAO implements UserDAO {

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public void saveUser(User user) {
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteUser(User user) {
        entityManager.getTransaction().begin();
        entityManager.remove(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateUser(User user) {saveUser(user);}

    @Override
    public List<User> getUsers() {
        TypedQuery<User> query = entityManager.createQuery("SELECT user FROM User user", User.class);
        return query.getResultList();
    }

    public boolean validate(String userName, String password) {

        TypedQuery<User> query = entityManager.createQuery("from User u where u.username = :userName and u.password = :password",User.class).setParameter("userName", userName);
        query.setParameter("userName",userName);
        query.setParameter("password",password);
        try {
            User user = query.getSingleResult();
            return true;
        } catch( javax.persistence.NoResultException e ){
            return false;
        }
    }

    public boolean usernameAlreadyExists(String userName){
        TypedQuery<User> query = entityManager.createQuery("from User u where u.username = :userName",User.class).setParameter("userName", userName);
        query.setParameter("userName",userName);

        List<User> ret = query.getResultList();

        return ret.size() > 0;
    }

    public boolean emailAlreadyExists(String email){
        TypedQuery<User> query = entityManager.createQuery("from User u where u.email = :email",User.class).setParameter("email", email);
        query.setParameter("email",email);

        List<User> ret = query.getResultList();

        return ret.size() > 0;
    }

    @Override
    public void close() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }
}

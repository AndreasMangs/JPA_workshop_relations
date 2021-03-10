package se.lexicon.jpa_workshop.data;

import se.lexicon.jpa_workshop.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;

@Repository
public class AppUserDAORepository implements AppUserDAO {

    private EntityManager em;

    @Autowired
    public AppUserDAORepository(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional(readOnly = true)
    public AppUser findById(int appUserId) {
        return em.find(AppUser.class, appUserId);

    }

    @Override
    @Transactional(readOnly = true)
    public Collection<AppUser> findAll() {
        return em.createQuery("SELECT appuser FROM AppUser appuser", AppUser.class).getResultList();
    }

    @Override
    @Transactional
    public AppUser create(AppUser appUser) {
        em.persist(appUser);
        return appUser;
    }

    @Override
    @Transactional
    public AppUser update(AppUser appUser) {
        em.merge(appUser);
        return appUser;
    }

    @Override
    @Transactional
    public void delete(int appUserId) {
        AppUser theUser = findById(appUserId);
        em.remove(theUser);
    }
}

package se.lexicon.jpa_workshop.data;

import se.lexicon.jpa_workshop.model.AppUser;
import se.lexicon.jpa_workshop.model.Details;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;

@Repository
public class DetailsDAORepository implements DetailsDAO{

    private EntityManager em;

    @Autowired
    public DetailsDAORepository(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional(readOnly = true)
    public Details findById(int detailsId) {
        return em.find(Details.class, detailsId);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Details> findAll() {
        return em.createQuery("SELECT details FROM Details details", Details.class).getResultList();
    }

    @Override
    @Transactional
    public Details create(Details details) {
        em.persist(details);
        return details;
    }

    @Override
    @Transactional
    public Details update(Details details) {
        em.merge(details);
        return details;
    }

    @Override
    @Transactional
    public void delete(int detailsId) {
        Details theDetails = findById(detailsId);
        em.remove(theDetails);
    }
}

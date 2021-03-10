package se.lexicon.jpa_workshop.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import se.lexicon.jpa_workshop.model.BookLoan;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Collection;

@Repository
public class BookLoanDAORepository implements BookLoanDAO{

    public final EntityManager em;

    @Autowired
    public BookLoanDAORepository(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional(readOnly = true)
    public BookLoan findById(int bookLoanId) {
        return em.find(BookLoan.class, bookLoanId);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<BookLoan> findAll() {
        return em.createQuery("SELECT bookloan FROM BookLoan bookloan", BookLoan.class).getResultList();
    }

    @Override
    @Transactional
    public BookLoan create(BookLoan bookLoan) {
        em.persist(bookLoan);
        return bookLoan;
    }

    @Override
    @Transactional
    public BookLoan update(BookLoan bookLoan) {
        em.merge(bookLoan);
        return bookLoan;
    }

    @Override
    @Transactional
    public void delete(int bookLoanId) {
        BookLoan theBookLoan = em.find(BookLoan.class, bookLoanId);
        em.remove(theBookLoan);
    }
}

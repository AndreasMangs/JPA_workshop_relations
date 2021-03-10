package se.lexicon.jpa_workshop.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import se.lexicon.jpa_workshop.model.Book;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
//import javax.transaction.Transactional;
import java.util.Collection;

@Repository
public class BookDAORepository implements BookDAO{

    public final EntityManager em;

    @Autowired
    public BookDAORepository(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional(readOnly = true)
    public Book findById(int bookId) {
        return em.find(Book.class, bookId);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Book> findAll() {
        return em.createQuery("SELECT book FROM Book book", Book.class).getResultList();
    }

    @Override
    @Transactional
    public Book create(Book book) {
        em.persist(book);
        return book;
    }

    @Override
    @Transactional
    public Book update(Book book) {
        em.merge(book);
        return book;
    }

    @Override
    @Transactional
    public void delete(int bookId) {
        Book theBookId = findById(bookId);
        em.remove(theBookId);
    }
}

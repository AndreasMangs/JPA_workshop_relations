package se.lexicon.jpa_workshop.data;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.jpa_workshop.model.Book;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureTestEntityManager
@Transactional
@DirtiesContext
class BookDAORepositoryTest {

    @Autowired private BookDAO testObject;
    @Autowired private TestEntityManager em;

    private Book savedBook;


    @BeforeEach
    void setUp() {
        Book unsaved = new Book(null, "abc123", "Testboken", 60 );
        savedBook = em.persistAndFlush(unsaved);
    }

    @AfterEach
    void tearDown() {
        em.flush();
    }

    @Test
    void findById() {
        int id = savedBook.getBookId();
        Book result = testObject.findById(id);

        assertNotNull(result);
        assertEquals(result,savedBook);
    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
        Book book = new Book(null,"AAA111", "NewBook", 30);

        Book result = testObject.create(book);

        assertNotNull(result);
        assertNotNull(result.getBookId());
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}
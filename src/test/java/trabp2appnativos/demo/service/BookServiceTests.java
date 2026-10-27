package trabp2appnativos.demo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import trabp2appnativos.demo.domain.ReadingStatus;
import trabp2appnativos.demo.dto.BookRequest;
import trabp2appnativos.demo.dto.BookResponse;
import trabp2appnativos.demo.exception.BookException;
import trabp2appnativos.demo.repository.BookRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceTests {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll();
    }

    private BookRequest sampleRequest() {
        return new BookRequest("Dom Casmurro", "Machado de Assis", "Romance", 1899, ReadingStatus.QUERO_LER);
    }

    @Test
    void shouldCreateBook() {
        BookResponse created = bookService.create(sampleRequest());

        assertNotNull(created.id());
        assertEquals("Dom Casmurro", created.title());
        assertEquals(ReadingStatus.QUERO_LER, created.readingStatus());
    }

    @Test
    void shouldListBooks() {
        bookService.create(sampleRequest());
        bookService.create(new BookRequest("1984", "George Orwell", "Distopia", 1949, ReadingStatus.LIDO));

        assertEquals(2, bookService.findAll().size());
    }

    @Test
    void shouldFindBookById() {
        BookResponse created = bookService.create(sampleRequest());

        BookResponse found = bookService.findById(created.id());

        assertEquals(created, found);
    }

    @Test
    void shouldUpdateBook() {
        BookResponse created = bookService.create(sampleRequest());

        BookResponse updated = bookService.update(
                created.id(),
                new BookRequest("Dom Casmurro", "Machado de Assis", "Romance", 1899, ReadingStatus.LIDO)
        );

        assertEquals(created.id(), updated.id());
        assertEquals(ReadingStatus.LIDO, updated.readingStatus());
    }

    @Test
    void shouldDeleteBook() {
        BookResponse created = bookService.create(sampleRequest());

        bookService.delete(created.id());

        assertThrows(BookException.NotFound.class, () -> bookService.findById(created.id()));
    }

    @Test
    void shouldThrowNotFoundForUnknownId() {
        assertThrows(BookException.NotFound.class, () -> bookService.findById(9999L));
        assertThrows(BookException.NotFound.class, () -> bookService.update(9999L, sampleRequest()));
        assertThrows(BookException.NotFound.class, () -> bookService.delete(9999L));
    }
}

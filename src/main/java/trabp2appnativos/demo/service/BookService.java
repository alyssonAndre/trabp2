package trabp2appnativos.demo.service;

import org.springframework.stereotype.Service;
import trabp2appnativos.demo.domain.Book;
import trabp2appnativos.demo.dto.BookRequest;
import trabp2appnativos.demo.dto.BookResponse;
import trabp2appnativos.demo.exception.BookException;
import trabp2appnativos.demo.repository.BookRepository;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public BookResponse create(BookRequest request) {

        Book book = new Book();

        applyRequest(book, request);

        Book savedBook = bookRepository.save(book);

        return toResponse(savedBook);
    }


    public List<BookResponse> findAll() {

        return bookRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }


    public BookResponse findById(Long id) {

        Book book = bookRepository.findById(id)
                .orElseThrow(BookException.NotFound::new);

        return toResponse(book);
    }


    public BookResponse update(Long id, BookRequest request) {

        Book book = bookRepository.findById(id)
                .orElseThrow(BookException.NotFound::new);

        applyRequest(book, request);

        Book updatedBook = bookRepository.save(book);

        return toResponse(updatedBook);
    }


    public void delete(Long id) {

        Book book = bookRepository.findById(id)
                .orElseThrow(BookException.NotFound::new);

        bookRepository.delete(book);
    }

    private void applyRequest(Book book, BookRequest request) {

        book.setTitle(request.title());
        book.setAuthor(request.author());
        book.setGenre(request.genre());
        book.setPublicationYear(request.publicationYear());
        book.setReadingStatus(request.readingStatus());
    }

    private BookResponse toResponse(Book book) {

        return new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getGenre(),
                book.getPublicationYear(),
                book.getReadingStatus()
        );
    }

}

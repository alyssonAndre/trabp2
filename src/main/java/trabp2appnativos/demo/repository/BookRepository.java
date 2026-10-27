package trabp2appnativos.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import trabp2appnativos.demo.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}

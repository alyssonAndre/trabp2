package trabp2appnativos.demo.dto;

import trabp2appnativos.demo.domain.ReadingStatus;

public record BookResponse(
        Long id,
        String title,
        String author,
        String genre,
        Integer publicationYear,
        ReadingStatus readingStatus
) {
}

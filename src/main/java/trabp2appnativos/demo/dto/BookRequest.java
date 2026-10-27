package trabp2appnativos.demo.dto;

import jakarta.validation.constraints.*;
import trabp2appnativos.demo.domain.ReadingStatus;

public record BookRequest(

        @NotBlank(message = "Título é obrigatório")
        String title,

        @NotBlank(message = "Autor é obrigatório")
        String author,

        @NotBlank(message = "Gênero é obrigatório")
        String genre,

        @NotNull(message = "Ano de publicação é obrigatório")
        @Min(value = 1000, message = "Ano deve ser maior ou igual a 1000")
        @Max(value = 2100, message = "Ano deve ser menor ou igual a 2100")
        Integer publicationYear,

        @NotNull(message = "Status de leitura é obrigatório")
        ReadingStatus readingStatus
) {

}

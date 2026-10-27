package trabp2appnativos.demo.exception;

public class BookException {

    public static class NotFound extends RuntimeException {

        public NotFound() {
            super("Livro não encontrado");
        }
    }
}

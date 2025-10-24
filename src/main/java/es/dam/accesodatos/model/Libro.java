package es.dam.accesodatos.model;

    public class Libro {
        private Integer id;
        private String titulo;
        private String isbn;
        private int precio;
        private Autor autor;
        private int autorId;

        public Libro(int id, String titulo, String isbn, int precio, int autorId) {
            this.id = id;
            this.titulo = titulo;
            this.isbn = isbn;
            this.precio = precio;
            this.autorId = autorId;
        }


        public Libro(String titulo, String isbn, int precio, int autorId) {

            this.titulo = titulo;
            this.autorId = autorId;
            this.isbn = isbn;
            this.precio = precio;
        }

        public Libro() {
        }

        public Libro(Integer id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "Libro{" +
                    "id=" + id +
                    ", titulo='" + titulo + '\'' +
                    ", isbn='" + isbn + '\'' +
                    ", precio=" + precio +
                    ", autorId=" + autorId +
                    '}';
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }

        public void setIsbn(String isbn) {
            this.isbn = isbn;
        }

        public void setPrecio(int precio) {
            this.precio = precio;
        }

        public void setAutor(Autor autor) {
            this.autor = autor;
        }

        public void setAutorId(int autorId) { this.autorId = autorId;}

        public int getAutorId() { return autorId; }

        public Integer getId() {
            return id;
        }

        public int getPrecio() {
            return precio;
        }

        public String getIsbn() {
            return isbn;
        }

        public Autor getAutor() {
            return autor;
        }

        public String getTitulo() {
            return titulo;
        }
    }

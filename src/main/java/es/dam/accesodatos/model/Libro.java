package es.dam.accesodatos.model;


    public class Libro {
        private Integer id;
        private String titulo;
        private String autor;
        private String isbn;
        private int precio;

        public Libro(Integer id, String titulo, String autor, String isbn, int precio) {
            this.id = id;
            this.titulo = titulo;
            this.autor = autor;
            this.isbn = isbn;
            this.precio = precio;
        }
        public Libro(String titulo, String autor, String isbn, int precio) {

            this.titulo = titulo;
            this.autor = autor;
            this.isbn = isbn;
            this.precio = precio;
        }





        @Override
        public String toString() {
            return "Libro{" +
                    "id=" + id +
                    ", titulo='" + titulo + '\'' +
                    ", autor='" + autor + '\'' +
                    ", isbn='" + isbn + '\'' +
                    ", precio=" + precio +
                    '}';
        }

        public Libro() {
        }

        public Libro(Integer id) {
            this.id = id;
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

        public void setAutor(String autor) {
            this.autor = autor;
        }

        public Integer getId() {
            return id;
        }

        public int getPrecio() {
            return precio;
        }

        public String getIsbn() {
            return isbn;
        }

        public String getAutor() {
            return autor;
        }

        public String getTitulo() {
            return titulo;
        }
    }

package es.dam.accesodatos.app;

import es.dam.accesodatos.controller.Controller;
import es.dam.accesodatos.service.AutorService;
import es.dam.accesodatos.service.LibroService;
import es.dam.accesodatos.view.ConsolaView;
/*aqui es donde empieza a ejecutarse la aplicación!!*/
public class Application {
    public static void main(String[] args) {
        var view = new ConsolaView();
        var libroService = new LibroService();
        var autorService = new AutorService();
        var controller = new Controller(view, libroService, autorService);
        controller.run();
    }
}
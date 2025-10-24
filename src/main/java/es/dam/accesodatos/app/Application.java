package es.dam.accesodatos.app;

import es.dam.accesodatos.controller.LibroController;
import es.dam.accesodatos.service.LibroService;
import es.dam.accesodatos.view.ConsolaView;
/*aqui es donde empieza a ejecutarse la aplicación!!*/
public class Application {
    public static void main(String[] args) {
        var view = new ConsolaView();
        var service = new LibroService();
        var controller = new LibroController(view, service);
        controller.run();
    }
}
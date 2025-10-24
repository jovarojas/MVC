package es.dam.accesodatos.controller;

import es.dam.accesodatos.service.AutorService;
import es.dam.accesodatos.view.ConsolaView;

import java.util.Date;

/**
 * El Controlador: coordina Vista ↔ Servicio. No muestra ni accede a BD directamente!!!! Por favor, tened esto muy en cuenta!!!.
 Revisad el fichero excel de los apuntes en Aules si tenéis dudas*/
public class AutorController {

    private final ConsolaView view;
    private final AutorService service;

    public AutorController(ConsolaView view, AutorService service) {
        this.view = view;
        this.service = service;
    }


    private void crear() {

    }


}

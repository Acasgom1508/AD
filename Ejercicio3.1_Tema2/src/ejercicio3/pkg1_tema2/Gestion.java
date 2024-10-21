/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejercicio3.pkg1_tema2;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author DAM_M
 */
public class Gestion extends DefaultHandler {

    boolean imprimir = false;

    public Gestion() {
        super();
    }

    public void StartDocument() {
        System.out.println("Inicio del documento:");
    }

    public void endContenido() {
        System.out.println("Fin del documento.");
    }

    public void startElement(String uri, String nombre, String nombreC, Attributes atts) {
        if (nombreC.equalsIgnoreCase("Titulo") || nombreC.equalsIgnoreCase("Fecha")
                || nombreC.equalsIgnoreCase("Duracion") || nombreC.equalsIgnoreCase("Actores")) {
            imprimir = true;
            System.out.printf("%n%s:", nombreC);
        }
    }

    public void endElement(String uri, String nombre, String nombreC) {
        if (nombreC.equalsIgnoreCase("Titulo") || nombreC.equalsIgnoreCase("Fecha")
                || nombreC.equalsIgnoreCase("Duracion") || nombreC.equalsIgnoreCase("Actores")) {
            imprimir = false;
        }
    }

    public void characters(char[] ch, int inicio, int longitud) throws SAXException {
        if (imprimir) {
            String car = new String(ch, inicio, longitud);
            System.out.printf("%s", car);
        }
    }
}

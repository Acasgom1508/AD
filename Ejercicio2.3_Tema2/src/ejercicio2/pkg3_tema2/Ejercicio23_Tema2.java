
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejercicio2.pkg3_tema2;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 *
 * @author diego
 */
public class Ejercicio23_Tema2  {

    /**
     * @param args the command line arguments
     * @throws javax.xml.parsers.ParserConfigurationException
     * @throws org.xml.sax.SAXException
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        Document document = (Document) DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File("RecetaDOM.xml"));

        Element recetario = (Element) document.getElementsByTagName("Recetario").item(0);

        NodeList recetas = document.getElementsByTagName("Receta");

        Element receta;

        int op;
        do {
            op = menu();
            switch (op) {
                case 1 -> {
                    leerRecetas(recetas);
                }
                case 2 -> {
                    receta = crearReceta(document);
                    recetario.appendChild(receta);
                }
                case 3 -> {
                    receta = BuscarReceta(recetas);
                    modificarReceta(document, receta);
                }
                case 4 -> {
                    receta = BuscarReceta(recetas);
                    if (recetas.getLength() != 0) {
                        recetario.removeChild(receta);
                    }
                }
                default -> {
                }

            }
        } while (op != 0);
    }

    public static void leerRecetas(NodeList r) {
        for (int i = 0; i < r.getLength(); i++) {
            leerReceta((Element) r.item(i));
        }
    }

    private static void leerReceta(Element e) {
        Element elemReceta;
        NodeList titulos, procedimientos, ingredientes, ingrediente, tiempos;
        Element elemento;
        String texto;

        elemReceta = e;

        titulos = elemReceta.getElementsByTagName("titulo");
        elemento = (Element) titulos.item(0);

        texto = elemento.getNodeName() + ": " + elemento.getTextContent();
        System.out.println("\n" + texto);

        ingredientes = elemReceta.getElementsByTagName("ingredientes");
        elemento = (Element) ingredientes.item(0);
        System.out.println(elemento.getNodeName() + ":");

        ingrediente = elemento.getElementsByTagName("ingrediente");

        for (int j = 0; j < ingrediente.getLength(); j++) {
            elemento = (Element) ingrediente.item(j);
            System.out.println("\t" + elemento.getTextContent());
        }

        procedimientos = elemReceta.getElementsByTagName("procedimiento");
        elemento = (Element) procedimientos.item(0);

        texto = elemento.getNodeName() + ": " + elemento.getTextContent();
        System.out.println(texto);

        tiempos = elemReceta.getElementsByTagName("tiempo");
        elemento = (Element) tiempos.item(0);

        texto = elemento.getNodeName() + ": " + elemento.getTextContent();
        System.out.println(texto);
    }

    public static Element crearReceta(Document d) {
        Scanner sc4 = new Scanner(System.in);
        Element receta, ingredientes;
        String valor;

        receta = d.createElement("Receta");

        System.out.println("Introduzca el titulo: ");
        valor = sc4.nextLine();
        crearElemento("titulo", valor, receta, d);

        crearElemento("ingredientes", "", receta, d);

        ingredientes = (Element) receta.getElementsByTagName("ingredientes").item(0);

        System.out.println("Cuantos ingredientes tiene: ");
        int numIngre = sc4.nextInt();
        sc4.nextLine();

        while (numIngre > 0) {
            System.out.println("Introduzca ingrediente:");
            valor = sc4.nextLine();
            crearElemento("ingrediente", valor, ingredientes, d);

            numIngre--;
        }

        System.out.println("Introduzca el procedimiento:");
        valor = sc4.nextLine();
        crearElemento("procedimiento", valor, receta, d);

        System.out.println("Introduzca el tiempo:");
        valor = sc4.nextLine();
        crearElemento("tiempo", valor, receta, d);

        return receta;
    }

    public static Element BuscarReceta(NodeList recetas) {
        Scanner sc2 = new Scanner(System.in);
        int i = 0, opcion;
        Element receta = null;

        if (recetas.getLength() > 0) {
            System.out.println("Existen " + recetas.getLength() + " recetas. ¿Cual quieres seleccionar?");
            opcion = sc2.nextInt();
            sc2.nextLine();

            while (opcion > recetas.getLength()) {
                System.out.println("No hay tantas recetas, el maximo a introducir es " + recetas.getLength());
                opcion = sc2.nextInt();
            }

            while (i < opcion) {
                if (i == (opcion - 1)) {
                    receta = (Element) recetas.item(i);

                }
                i++;
            }
        } else {
            System.out.println("No hay ninguna receta.");
        }
        return receta;
    }

    public static void modificarReceta(Document doc, Element receta) {
        int num, menu2;
        String valor;
        Scanner sc3 = new Scanner(System.in);
        NodeList etiqueta;
        Element elemento;

        System.out.println("Menu");
        System.out.println("----");
        System.out.println("Elige una opcion: ");
        System.out.println("1.- Titulo.");
        System.out.println("2.- Ingredientes.");
        System.out.println("3.- Procedimiento.");
        System.out.println("4.- Tiempo.");
        System.out.println("0.- Salir");

        menu2 = sc3.nextInt();
        sc3.nextLine();
        switch (menu2) {
            case 1 -> {
                etiqueta = receta.getElementsByTagName("titulo");
                elemento = (Element) etiqueta.item(0);

                System.out.println("Antiguo valor para titulo:" + elemento.getTextContent());
                System.out.print("Nuevo valor para titulo: ");
                valor = sc3.nextLine();

                elemento.setTextContent(valor);
            }

            case 2 -> {
                int contador = 0;
                etiqueta = receta.getElementsByTagName("ingredientes");
                elemento = (Element) etiqueta.item(0);

                while (elemento.hasChildNodes()) {
                    elemento.removeChild(elemento.getFirstChild());
                }

                System.out.println("Numero de ingredientes");
                num = sc3.nextInt();
                while (num > 0) {
                    contador++;
                    System.out.print("Ingrediente "+contador+": ");
                    valor = sc3.nextLine();
                    crearElemento("ingrediente", valor, elemento, doc);
                    num--;
                }
            }

            case 3 -> {
                etiqueta = receta.getElementsByTagName("procedimiento");
                elemento = (Element) etiqueta.item(0);

                System.out.println("Antiguo valor para procedimiento:" + elemento.getTextContent());
                System.out.print("Nuevo valor para procedimiento: ");
                valor = sc3.nextLine();

                elemento.setTextContent(valor);
            }

            case 4 -> {
                etiqueta = receta.getElementsByTagName("tiempo");
                elemento = (Element) etiqueta.item(0);

                System.out.println("Antiguo valor para tiempo:" + elemento.getTextContent());
                System.out.print("Nuevo valor para tiempo: ");
                valor = sc3.nextLine();

                elemento.setTextContent(valor);
            }

            case 0 -> {
                return;
            }
        }
    }

    public static int menu() {
        System.out.println("Menu");
        System.out.println("-----");
        System.out.println("1.- Leer recetas.");
        System.out.println("2.- Crear receta.");
        System.out.println("3.- Modificar receta.");
        System.out.println("4.- Eliminar receta.");
        System.out.println("0.- Salir");

        return leerEntero();
    }

    public static int leerEntero() {
        Scanner sc = new Scanner(System.in);
        boolean valido = false;
        do {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                valido = false;
            }
        } while (!valido);
        return 0;
    }

    public static void crearElemento(String elemento, String valor, Element raiz, Document document) {
        Element elem = document.createElement(elemento);
        Text text = document.createTextNode(valor);
        raiz.appendChild(elem);
        elem.appendChild(text);
    }

}

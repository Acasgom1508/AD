
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejercicio3.pkg1_tema2;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

/**
 *
 * @author DAM_M
 */
public class Ejercicio31_Tema2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            SAXParserFactory parserFactory = SAXParserFactory.newInstance();
            SAXParser parser = parserFactory.newSAXParser();
            XMLReader procesadorXML = parser.getXMLReader();

            Gestion g = new Gestion();
            procesadorXML.setContentHandler(g);

            InputSource file = new InputSource("peliculas.xml");

            procesadorXML.parse(file);
        } catch (ParserConfigurationException | IOException | SAXException e) {
        }
    }

}

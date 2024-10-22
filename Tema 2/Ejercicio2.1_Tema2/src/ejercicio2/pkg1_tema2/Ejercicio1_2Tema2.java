    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejercicio2.pkg1_tema2;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 * @author DAM_M
 */
public class Ejercicio1_2Tema2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            NodeList nlt = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse("peliculas.xml").getElementsByTagName("Titulo");
            NodeList nlf = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse("peliculas.xml").getElementsByTagName("Fecha");
            NodeList nld = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse("peliculas.xml").getElementsByTagName("Director");
            NodeList nla = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse("peliculas.xml").getElementsByTagName("Actores");
            for (int i = 0; i < nlt.getLength(); i++) {
                Element e1 = (Element) nlt.item(i);
                Element e2 = (Element) nlf.item(i);
                Element e3 = (Element) nld.item(i);
                Element e4 = (Element) nla.item(i);
                System.out.println(e1.getTextContent() + "\n");
                System.out.println(e2.getTextContent() + "\n");
                System.out.println(e3.getTextContent() + "\n");
                System.out.println(e4.getTextContent() + "\n");
            }
        } catch (IOException | ParserConfigurationException | SAXException e) {
        }
    }

}

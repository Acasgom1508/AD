/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejercicio2_2tema2;

import java.io.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 *
 * @author DAM_M
 */
public class Ejercicio2_2Tema2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        Document document = null;
        try {
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            File f = new File("Productos.xml");
            document = builder.parse(f);
            Element doc = document.getDocumentElement();
            NodeList nlp = doc.getElementsByTagName("Producto");
            for (int i = 0; i < nlp.getLength(); i++) {
                Element prod = (Element) nlp.item(i);
                Element stock = (Element) prod.getElementsByTagName("Stock").item(0);
                if (Integer.parseInt(stock.getTextContent().trim()) < 5) {
                    prod.setAttribute("aLaVenta", "false");
                }
            }
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("NuevoDoc.xml"));
            transformer.transform(source, result);
        } catch (IOException | NumberFormatException | ParserConfigurationException | TransformerConfigurationException | DOMException | SAXException e) {
        } catch (TransformerException ex) {
        }
    }
    
    
    
    
    
    
    
    
    
    
    

}

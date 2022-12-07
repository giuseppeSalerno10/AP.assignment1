/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package xmlserializertester;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import xmlserializer.XMLSerializer;
import xmlserializertester.Models.*;

/**
 *
 * @author Giuse
 */
public class XMLSerializerTester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Object[] arr = new Object[]
        {
            new NestedModel(100,200, new NotNestedModel(1000,2000), "Test", new int[]{1,2}, new String[]{"","te"}),
            new NotNestedModel(10, 20),
            new NotXMLableModel(1,2),
            new XMLableButEmptyModel()
        };
        
        String fileName = "fileName";
        XMLSerializer.serialize(arr, fileName);

        File file = new File("fileName.xml");        
        FileReader fr = new FileReader(file);
       
        String expectedString = "";
    
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package xmlserializer;

import xmlserializer.Annotations.XMLable;
import xmlserializer.Annotations.XMLField;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Arrays;

/**
 *
 * @author Giuse
 */
public class XMLSerializer {

    public static void serialize(Object [ ] arr, String fileName){
        try{
            String serializedArray = "<?xml version=\"1.0\" encoding=\"utf-8\" ><Objects>";
            
            for (Object elem : arr) {
                Class elemClass = elem.getClass();

                if(elemClass.isAnnotationPresent(XMLable.class)){
                    serializedArray += String.format("<%s>", elemClass.getName());
                    serializedArray += serializeObject(elem);
                    serializedArray += String.format("</%s>", elemClass.getName());

                }
                else{
                    serializedArray += "<notXMLable />";
                }
            }
            
            serializedArray += "</Objects>";
        
            writeOnFile(serializedArray, fileName);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        

        
    }
    
    private static String serializeObject(Object object) throws IllegalArgumentException, IllegalAccessException{
        String serializedObject = "";
        Class objectClass = object.getClass();
        
        Field[] classFields = objectClass.getFields();

        for(Field field : classFields){
            if(field.isAnnotationPresent(XMLField.class)){               
                serializedObject += serializeField(field,object);
            }
        }
        
        return serializedObject;
    }
    private static String serializeField(Field field, Object object) throws IllegalArgumentException, IllegalAccessException{
        String serializedField = "";
        Object fieldValue = field.get(object);
        Class fieldType = field.getType();
        XMLField fieldMetadata = field.getAnnotation(XMLField.class);
        String fieldName = field.getName();
            
        if(fieldType.isPrimitive() || fieldType == String.class){
            serializedField = fieldValue.toString();
        }
        else if(fieldType.isArray()){
            int arrayLength = Array.getLength(fieldValue);
            for(int i = 0; i<arrayLength;i++){
                serializedField += String.format("<arrayElement>%s</arrayElement>",Array.get(fieldValue, i).toString());
            }
        }
        else{
            serializedField = serializeObject(fieldValue);
        }
        
        return String.format("<%s type=\"%s\" %s>%s</%s>", 
            fieldName,
            fieldMetadata.type(),
            fieldMetadata.name().equals("") ? "" : String.format("name=\"%s\"", fieldMetadata.name()),
            serializedField,
            fieldName
            );
    }
    
    private static void writeOnFile(String string, String fileName) throws IOException{
        String fullName = fileName + ".xml";
        
        //File Creation
        File file = new File(fullName);
        if(file.exists()){
            file.delete();
        }
        file.createNewFile();

        //File Writing
        FileWriter fw = new FileWriter(fullName);
        fw.write(string);
        fw.close();
    }
}

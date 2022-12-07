/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xmlserializertester.Models;

import xmlserializer.Annotations.XMLField;

/**
 *
 * @author Giuse
 */
public class NotXMLableModel {
    
    @XMLField(type = "int", name = "field1") //This field should not be serialized
    public int field1;
    public int field2;
    public NotXMLableModel(int field1, int field2){
        this.field1 = field1;
        this.field2 = field2;
    }
}

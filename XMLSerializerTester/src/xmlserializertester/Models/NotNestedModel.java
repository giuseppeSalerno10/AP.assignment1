/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xmlserializertester.Models;

import xmlserializer.Annotations.XMLField;
import xmlserializer.Annotations.XMLable;

/**
 *
 * @author Giuse
 */
@XMLable
public class NotNestedModel {
    @XMLField(type = "int", name = "notNestedField1")
    public int field1;
    public int field2;
    
    public NotNestedModel(int field1, int field2){
        this.field1 = field1;
        this.field2 = field2;
    }
}

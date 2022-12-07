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
public class NestedModel {
    
    @XMLField(type = "int", name = "field1")
    public int field1;
    
    public int field2;
    
    @XMLField(type = "String", name = "string1")
    public String str;

    @XMLField(type = "int[]", name = "array1")
    public int[] arr1;
    
    @XMLField(type = "String[]")
    public String[] arr2;

    @XMLField(type = "NotNestedItem")
    public NotNestedModel notNestedItem;
    
    public NestedModel(int field1, int field2, NotNestedModel notNestedItem, String str, int[] arr1, String[] arr2 ){
        this.field1 = field1;
        this.field2 = field2;
        this.notNestedItem = notNestedItem;
        this.arr1 = arr1;
        this.arr2 = arr2;
        this.str = str;
    }
}

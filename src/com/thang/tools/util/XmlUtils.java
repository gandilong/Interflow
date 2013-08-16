/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thang.tools.util;

import java.io.FileOutputStream;
import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 *
 * @author Gandilong
 */
public class XmlUtils {
    
    private static SAXReader reader=new SAXReader();
    private static XMLWriter writer=null;
    
    static{
         OutputFormat out=OutputFormat.createPrettyPrint();
         out.setEncoding("UTF-8");
         try{
            writer=new XMLWriter(out);
         }catch(Exception e){
           e.printStackTrace();
         }
    }
    
    public static Document getXML(String file){
        try{
            return reader.read(file);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    public static void setXML(Document doc,String file){
        try{
            doc.setXMLEncoding("UTF-8");
            FileOutputStream out= new FileOutputStream(file);
            writer.setOutputStream(out);
            writer.write(doc);
            out.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

package com.thang;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.text.AttributeSet;
import javax.swing.text.Element;
import javax.swing.text.ElementIterator;
import javax.swing.text.StyleConstants;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;

public class MainClass {
	 public static void main(String args[]) throws Exception {
		    URL url = new URL("http://www.baidu.com");
		    URLConnection connection = url.openConnection();
		    InputStream is = connection.getInputStream();
		    InputStreamReader isr = new InputStreamReader(is);
		    BufferedReader br = new BufferedReader(isr);

		    HTMLEditorKit htmlKit = new HTMLEditorKit();
		    
		    HTMLDocument htmlDoc = (HTMLDocument) htmlKit.createDefaultDocument();
		    
		    HTMLEditorKit.Parser parser = new ParserDelegator();
		    
		    HTMLEditorKit.ParserCallback callback = htmlDoc.getReader(0);
		    
		    parser.parse(br, callback, true);

		    ElementIterator iterator = new ElementIterator(htmlDoc);
		    Element element;
		    while ((element = iterator.next()) != null) {
		      AttributeSet attributes = element.getAttributes();
		      Object name = attributes.getAttribute(StyleConstants.NameAttribute);
		      if ((name instanceof HTML.Tag) && (name == HTML.Tag.H1)) {
		        StringBuffer text = new StringBuffer();
		        int count = element.getElementCount();
		        for (int i = 0; i < count; i++) {
		          Element child = element.getElement(i);
		          AttributeSet childAttributes = child.getAttributes();
		          if (childAttributes.getAttribute(StyleConstants.NameAttribute) == HTML.Tag.CONTENT) {
		            int startOffset = child.getStartOffset();
		            int endOffset = child.getEndOffset();
		            int length = endOffset - startOffset;
		            text.append(htmlDoc.getText(startOffset, length));
		          }
		        }
		        System.out.println(name + ": " + text.toString());
		      }
		    }
		  }
}

package com.thang;

import java.awt.*;  
import java.awt.event.*;  
import java.beans.*;  
import java.io.*;  
import java.net.*;  
import java.util.*;  
import javax.swing.*;  
import javax.swing.event.*;  
import javax.swing.tree.*;  
import javax.swing.text.*;  
import javax.swing.text.html.*;  
import javax.swing.text.rtf.*;  

public class EditorPaneExample19 extends JFrame {  
	  public EditorPaneExample19() {  
	    super("JEditorPane Example 19");  
	  
	    pane = new JEditorPane();  
	    pane.setEditable(true);    // Editable  
	    getContentPane().add(new JScrollPane(pane), "Center");  
	  
	    // Add a menu bar  
	    menuBar = new JMenuBar();  
	    setJMenuBar(menuBar);  
	  
	    // Populate it  
	    createMenuBar();  
	  
	    // Build the panel of controls  
	    JPanel panel = new JPanel();  
	  
	    panel.setLayout(new GridBagLayout());  
	    GridBagConstraints c = new GridBagConstraints();  
	    c.gridwidth = 1;  
	    c.gridheight = 1;  
	    c.anchor = GridBagConstraints.EAST;  
	    c.fill = GridBagConstraints.NONE;  
	    c.weightx = 0.0;  
	    c.weighty = 0.0;  
	  
	    JLabel urlLabel = new JLabel("URL: ", JLabel.RIGHT);  
	    panel.add(urlLabel, c);  
	    JLabel loadingLabel = new JLabel("State: ", JLabel.RIGHT);  
	    c.gridy = 1;  
	    panel.add(loadingLabel, c);  
	    JLabel typeLabel = new JLabel("Type: ", JLabel.RIGHT);  
	    c.gridy = 2;  
	    panel.add(typeLabel, c);  
	    c.gridy = 3;  
	    panel.add(new JLabel(LOAD_TIME), c);  
	  
	    c.gridy = 4;  
	    c.gridwidth = 2;  
	    c.weightx = 1.0;  
	    c.anchor = GridBagConstraints.WEST;  
	    onlineLoad = new JCheckBox("Online Load");  
	    panel.add(onlineLoad, c);  
	    onlineLoad.setSelected(true);  
	    onlineLoad.setForeground(typeLabel.getForeground());  
	  
	    c.gridy = 5;  
	    c.gridwidth = 2;  
	    c.weightx = 1.0;  
	    c.anchor = GridBagConstraints.WEST;  
	    editableBox = new JCheckBox("Editable JEditorPane");  
	    panel.add(editableBox, c);  
	    editableBox.setSelected(true);  
	    editableBox.setForeground(typeLabel.getForeground());  
	  
	    c.gridy = 6;  
	    c.weightx = 0.0;  
	    JButton saveButton = new JButton("Save");  
	    panel.add(saveButton, c);  
	    saveButton.addActionListener(new ActionListener() {  
	      public void actionPerformed(ActionEvent evt) {  
	        EditorKit kit = pane.getEditorKit();  
	        try {  
	          if (kit instanceof RTFEditorKit) {  
	            kit.write(System.out, pane.getDocument(),  
	              0, pane.getDocument().getLength());  
	            System.out.flush();  
	          } else {  
	            if (writer == null) {  
	              writer = new OutputStreamWriter(System.out);  
	              pane.write(writer);  
	              writer.flush();  
	            }  
	            kit.write(writer, pane.getDocument(),  
	              0, pane.getDocument().getLength());  
	            writer.flush();  
	          }  
	        } catch (Exception e) {  
	          System.out.println("Write failed");  
	        }  
	      }  
	    });  
	  
	    c.gridx = 1;  
	    c.gridy = 0;  
	    c.weightx = 1.0;  
	    c.anchor = GridBagConstraints.EAST;  
	    c.fill = GridBagConstraints.HORIZONTAL;  
	  
	    urlCombo = new JComboBox();  
	    panel.add(urlCombo, c);  
	    urlCombo.setEditable(true);  
	    loadingState = new JLabel(spaces, JLabel.LEFT);  
	    loadingState.setForeground(Color.black);  
	    c.gridy = 1;  
	    panel.add(loadingState, c);  
	    loadedType = new JLabel(spaces, JLabel.LEFT);  
	    loadedType.setForeground(Color.black);  
	    c.gridy = 2;  
	    panel.add(loadedType, c);  
	    timeLabel = new JLabel("");  
	    c.gridy = 3;  
	    panel.add(timeLabel, c);  
	  
	    getContentPane().add(panel, "South");  
	  
	    // Register a custom EditorKit for HTML  
	    ClassLoader loader = getClass().getClassLoader();  
	    if (loader != null) {  
	      // Java 2  
	      JEditorPane.registerEditorKitForContentType("text/html",  
	          "AdvancedSwing.Chapter4.EnhancedHTMLEditorKit",  
	          loader);  
	    } else {  
	      // JDK 1.1  
	      JEditorPane.registerEditorKitForContentType("text/html",  
	          "AdvancedSwing.Chapter4.EnhancedHTMLEditorKit");  
	    }  
	  
	    // Allocate the empty tree model  
	    DefaultMutableTreeNode emptyRootNode = new DefaultMutableTreeNode("Empty");  
	    emptyModel = new DefaultTreeModel(emptyRootNode);  
	  
	    // Create and place the heading tree  
	    tree = new JTree(emptyModel);  
	    tree.setPreferredSize(new Dimension(200, 200));  
	    getContentPane().add(new JScrollPane(tree), "East");  
	  
	    // Change page based on combo selection  
	    urlCombo.addActionListener(new ActionListener() {  
	      public void actionPerformed(ActionEvent evt) {  
	        if (populatingCombo == true) {  
	          return;  
	        }  
	        Object selection = urlCombo.getSelectedItem();  
	        loadNewPage(selection);  
	      }  
	    });  
	  
	    // Change editability based on the checkbox  
	    editableBox.addActionListener(new ActionListener() {  
	      public void actionPerformed(ActionEvent evt) {  
	        pane.setEditable(editableBox.isSelected());  
	        pane.revalidate();  
	        pane.repaint();  
	      }  
	    });  
	  
	    // Listen for page load to complete  
	    pane.addPropertyChangeListener(new PropertyChangeListener() {  
	      public void propertyChange(PropertyChangeEvent evt) {  
	        if (evt.getPropertyName().equals("page")) {  
	          loadComplete();  
	          displayLoadTime();  
	          populateCombo(findLinks(pane.getDocument(), null));  
	          TreeNode node = buildHeadingTree(pane.getDocument());  
	          tree.setModel(new DefaultTreeModel(node));  
	  
	          createMenuBar();  
	          enableMenuBar(true);  
	          getRootPane().revalidate();  
	  
	          enableInput();  
	          loadingPage = false;  
	        }  
	      }  
	    });  
	  
	    // Listener for tree selection  
	    tree.addTreeSelectionListener(new TreeSelectionListener() {  
	      public void valueChanged(TreeSelectionEvent evt) {  
	        TreePath path = evt.getNewLeadSelectionPath();  
	        if (path != null) {  
	          DefaultMutableTreeNode node =  
	                (DefaultMutableTreeNode)path.getLastPathComponent();  
	          Object userObject = node.getUserObject();  
	          if (userObject instanceof Heading) {  
	            Heading heading = (Heading)userObject;  
	            try {  
	              Rectangle textRect = pane.modelToView(heading.getOffset());  
	              textRect.y += 3 * textRect.height;  
	              pane.scrollRectToVisible(textRect);  
	            } catch (BadLocationException e) {  
	            }  
	          }  
	        }  
	      }  
	    });  
	  
	    // Listener for hypertext events  
	    pane.addHyperlinkListener(new HyperlinkListener() {  
	      public void hyperlinkUpdate(HyperlinkEvent evt) {  
	        // Ignore hyperlink events if the frame is busy  
	        if (loadingPage == true) {  
	          return;  
	        }  
	        if (evt.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {  
	          JEditorPane sp = (JEditorPane)evt.getSource();  
	          if (evt instanceof HTMLFrameHyperlinkEvent) {  
	            HTMLDocument doc = (HTMLDocument)sp.getDocument();  
	            doc.processHTMLFrameHyperlinkEvent((HTMLFrameHyperlinkEvent)evt);  
	          } else {  
	            loadNewPage(evt.getURL());  
	          }  
	        } else if (evt.getEventType() == HyperlinkEvent.EventType.ENTERED) {  
	          pane.setCursor(handCursor);  
	        } else if (evt.getEventType() == HyperlinkEvent.EventType.EXITED) {  
	          pane.setCursor(defaultCursor);  
	        }  
	      }  
	    });  
	  }  
	  
	  public void loadNewPage(Object page) {  
	    try {  
	      loadingPage = true;  
	  
	      // Check if the new page and the old  
	      // page are the same.  
	      URL url;  
	      if (page instanceof URL) {  
	        url = (URL)page;  
	      } else {  
	        url = new URL((String)page);  
	      }  
	  
	      urlCombo.setSelectedItem(page);  
	  
	      URL loadedURL = pane.getPage();  
	      if (loadedURL != null && loadedURL.sameFile(url)) {  
	        return;  
	      }  
	  
	      enableMenuBar(false);  
	      urlCombo.paintImmediately(0, 0,  
	                  menuBar.getSize().width,  
	                  menuBar.getSize().height);  
	  
	      // Try to display the page  
	      urlCombo.setEnabled(false);    // Disable input  
	      urlCombo.paintImmediately(0, 0,  
	                  urlCombo.getSize().width,  
	                  urlCombo.getSize().height);  
	      setCursor(waitCursor);    // Busy cursor  
	      loadingState.setText("Loading...");  
	      loadingState.paintImmediately(0, 0,  
	                  loadingState.getSize().width,  
	                  loadingState.getSize().height);  
	      loadedType.setText("");  
	      loadedType.paintImmediately(0, 0,  
	                  loadedType.getSize().width,  
	                  loadedType.getSize().height);  
	  
	      timeLabel.setText("");  
	      timeLabel.paintImmediately(0, 0,  
	                  timeLabel.getSize().width,  
	                  timeLabel.getSize().height);  
	  
	      // Display an empty tree while loading  
	      tree.setModel(emptyModel);  
	      tree.paintImmediately(0, 0,  
	                  tree.getSize().width,  
	                  tree.getSize().height);  
	  
	      startTime = System.currentTimeMillis();  
	  
	      // Choose the loading method  
	      if (onlineLoad.isSelected()) {  
	        // Usual load via setPage  
	        pane.setPage(url);  
	        loadedType.setText(pane.getContentType());  
	      } else {  
	        pane.setContentType("text/html");  
	        loadedType.setText(pane.getContentType());  
	        if (loader == null) {  
	          loader = new HTMLDocumentLoader();  
	        }  
	        HTMLDocument doc = loader.loadDocument(url);  
	  
	        loadComplete();  
	        pane.setDocument(doc);  
	        displayLoadTime();  
	        populateCombo(findLinks(doc, null));  
	        TreeNode node = buildHeadingTree(doc);  
	        tree.setModel(new DefaultTreeModel(node));  
	  
	        createMenuBar();  
	        enableMenuBar(true);  
	        getRootPane().revalidate();  
	  
	        enableInput();  
	        loadingPage = false;  
	      }  
	    } catch (Exception e) {  
	      System.out.println(e);  
	      JOptionPane.showMessageDialog(pane,  
	        new String[] {  
	          "Unable to open file",  
	          page.toString()  
	        }, "File Open Error",  
	        JOptionPane.ERROR_MESSAGE);  
	      loadingState.setText("Failed");  
	      enableMenuBar(true);  
	      enableInput();  
	      loadingPage = false;  
	    }  
	  }  
	  
	  public void loadComplete() {  
	    loadingState.setText("Page loaded.");  
	  }  
	  
	  public void enableInput() {  
	    urlCombo.setEnabled(true);    // Allow entry of new URL  
	    setCursor(defaultCursor);  
	    pane.setCursor(defaultCursor);  
	  }  
	  
	  public void displayLoadTime() {  
	    double loadingTime = ((double)(System.currentTimeMillis() - startTime))/1000d;  
	    timeLabel.setText(loadingTime + " seconds");  
	  }  
	  
	  public void populateCombo(URL[] urls) {  
	    // Save existing selection  
	    Object o = urlCombo.getSelectedItem();  
	    populatingCombo = true;  
	    urlCombo.setModel(new DefaultComboBoxModel(urls));  
	    // Restore original selection  
	    urlCombo.setSelectedItem(o);  
	    populatingCombo = false;  
	  }  
	  
	  public void enableMenuBar(boolean cond) {  
	    int count = menuBar.getMenuCount();  
	    for (int i = 0; i < count; i++) {  
	      menuBar.getMenu(i).setEnabled(cond);  
	    }  
	  }  
	  
	  public void createMenuBar() {  
	    // Remove the existing menu items  
	    int count = menuBar.getMenuCount();  
	    for (int i = 0; i < count; i++) {  
	      menuBar.remove(menuBar.getMenu(0));  
	    }  
	  
	    // Build the new menu.  
	    Action[] actions = pane.getActions();  
	    Hashtable actionHash = new Hashtable();  
	    count = actions.length;  
	    for (int i = 0; i < count; i++) {  
	      actionHash.put(actions[i].getValue(Action.NAME), actions[i]);  
	    }  
	  
	    // Add the font menu  
	    JMenu menu = MenuBuilder.buildMenu("Font", fontSpec, actionHash);  
	    if (menu != null) {  
	      menuBar.add(menu);  
	    }  
	  
	    // Add the alignment menu  
	    menu = MenuBuilder.buildMenu("Align", alignSpec, actionHash);  
	    if (menu != null) {  
	      menuBar.add(menu);  
	    }  
	  
	    // Add the HTML menu  
	    menu = MenuBuilder.buildMenu("HTML", htmlSpec, actionHash);  
	    if (menu != null) {  
	      menuBar.add(menu);  
	    }  
	  }  
	  
	  public URL[] findLinks(Document doc, String protocol) {  
	    Vector links = new Vector();  
	    Vector urlNames = new Vector();  
	    URL baseURL = (URL)doc.getProperty(Document.StreamDescriptionProperty);  
	  
	    if (doc instanceof HTMLDocument) {  
	      Element elem = doc.getDefaultRootElement();  
	      ElementIterator iterator = new ElementIterator(elem);  
	  
	      while ((elem = iterator.next()) != null) {  
	        AttributeSet attrs = elem.getAttributes();  
	        Object link = attrs.getAttribute(HTML.Tag.A);  
	        if (link instanceof AttributeSet) {  
	          Object linkAttr = ((AttributeSet)link).getAttribute(HTML.Attribute.HREF);  
	          if (linkAttr instanceof String) {  
	            try {  
	              URL linkURL = new URL(baseURL, (String)linkAttr);  
	              if (protocol == null ||  
	                  protocol.equalsIgnoreCase(linkURL.getProtocol())) {  
	                String linkURLName = linkURL.toString();  
	                if (urlNames.contains(linkURLName) == false) {  
	                  urlNames.addElement(linkURLName);  
	                  links.addElement(linkURL);  
	                }  
	              }  
	            } catch (MalformedURLException e) {  
	              // Ignore invalid links  
	            }  
	          }  
	        }  
	      }  
	    }  
	  
	    URL[] urls = new URL[links.size()];  
	    links.copyInto(urls);  
	    links.removeAllElements();  
	    urlNames.removeAllElements();  
	  
	    return urls;  
	  }  
	  
	  public TreeNode buildHeadingTree(Document doc) {  
	    String title = (String)doc.getProperty(Document.TitleProperty);  
	    if (title == null) {  
	      title = "[No title]";  
	    }  
	    Heading rootHeading = new Heading(title, 0, 0);  
	    DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(rootHeading);  
	  
	    DefaultMutableTreeNode lastNode[] = new DefaultMutableTreeNode[7];  
	    int lastLevel = 0;  
	    lastNode[lastLevel] = rootNode;  
	  
	    if (doc instanceof HTMLDocument) {  
	      Element elem = doc.getDefaultRootElement();  
	      ElementIterator iterator = new ElementIterator(elem);  
	      Heading heading;  
	  
	      while ((heading = getNextHeading(doc, iterator)) != null) {  
	        // Add the node to the tree  
	        DefaultMutableTreeNode hNode = new DefaultMutableTreeNode(heading);  
	        int level = heading.getLevel();  
	  
	        if (level > lastLevel) {  
	          for (int i = lastLevel + 1; i < level; i++) {  
	            lastNode[i] = null;  
	          }  
	          lastNode[lastLevel].add(hNode);  
	        } else {  
	          int prevLevel = level - 1;  
	          while (prevLevel >= 0) {  
	            if (lastNode[prevLevel] != null) {  
	              break;  
	            }  
	            lastNode[prevLevel] = null;  
	            prevLevel--;  
	          }  
	          lastNode[prevLevel].add(hNode);  
	        }  
	        lastNode[level] = hNode;  
	        lastLevel = level;  
	      }  
	    }  
	    return rootNode;  
	  }  
	  
	  public Heading getNextHeading(Document doc, ElementIterator iter) {  
	    Element elem;  
	  
	    while ((elem = iter.next()) != null) {  
	      AttributeSet attrs = elem.getAttributes();  
	      Object type = attrs.getAttribute(StyleConstants.NameAttribute);  
	      int level = getHeadingLevel(type);  
	      if (level > 0) {  
	        // It is a heading - get the text  
	        String headingText = "";  
	        int count = elem.getElementCount();  
	        for (int i = 0; i < count; i++) {  
	          Element child = elem.getElement(i);  
	          AttributeSet cattrs = child.getAttributes();  
	          if (cattrs.getAttribute(StyleConstants.NameAttribute) ==  
	            HTML.Tag.CONTENT) {  
	            try {  
	              int offset = child.getStartOffset();  
	              headingText += doc.getText(offset,  
	                      child.getEndOffset() - offset);  
	            } catch (BadLocationException e) {  
	            }  
	          }  
	        }  
	        headingText = headingText.trim();  
	        return new Heading(headingText,  
	                    level, elem.getStartOffset());  
	      }  
	    }  
	    return null;  
	  }  
	  
	  public int getHeadingLevel(Object type) {  
	    if (type instanceof HTML.Tag) {  
	      if (type == HTML.Tag.H1) {  
	        return 1;  
	      }  
	      if (type == HTML.Tag.H2) {  
	        return 2;  
	      }  
	      if (type == HTML.Tag.H3) {  
	        return 3;  
	      }  
	      if (type == HTML.Tag.H4) {  
	        return 4;  
	      }  
	      if (type == HTML.Tag.H5) {  
	        return 5;  
	      }  
	      if (type == HTML.Tag.H6) {  
	        return 6;  
	      }  
	    }  
	  
	    return -1;  
	  }  
	  
	  static class Heading {  
	    public Heading(String text, int level, int offset) {  
	      this.text = text;  
	      this.level = level;  
	      this.offset = offset;  
	    }  
	  
	    public String getText() {  
	      return text;  
	    }  
	  
	    public int getOffset() {  
	      return offset;  
	    }  
	  
	    public int getLevel() {  
	      return level;  
	    }  
	  
	    public String toString() {  
	      return text;  
	    }  
	  
	    protected String text;  
	    protected int level;  
	    protected int offset;  
	  }  
	  
	  public JEditorPane getEditorPane() {  
	    return pane;  
	  }  
	  
	  public static void main(String[] args) {  
	    try {  
	        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");  
	    } catch (Exception evt) {}  
	  
	    JFrame f = new EditorPaneExample19();  
	    f.addWindowListener(new WindowAdapter() {  
	      public void windowClosing(WindowEvent evt) {  
	        System.exit(0);  
	      }  
	    });  
	    f.setSize(500, 400);  
	    f.setVisible(true);  
	  }  
	  
	  private static final String spaces = "                    ";  
	  private static final String LOAD_TIME = "Load time: ";  
	  private HTMLDocumentLoader loader;  
	  private JEditorPane pane;  
	  private JLabel loadingState;  
	  private JLabel loadedType;  
	  private JLabel timeLabel;  
	  private JComboBox urlCombo;  
	  private JCheckBox onlineLoad;  
	  private JCheckBox editableBox;  
	  private JTree tree;  
	  private TreeModel emptyModel;  
	  private JMenuBar menuBar;  
	  private long startTime;  
	  private boolean populatingCombo;  
	  private boolean loadingPage;  
	  private static final Cursor waitCursor =  
	          Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);  
	  private static final Cursor defaultCursor =  
	          Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);  
	  private static final Cursor handCursor =  
	          Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);  
	  private OutputStreamWriter writer;  
	  
	  
	  private static MenuSpec[] sizeSpec = new MenuSpec[] {  
	      new MenuSpec("Size 8",  "font-size-8"),  
	      new MenuSpec("Size 10", "font-size-10"),  
	      new MenuSpec("Size 12", "font-size-12"),  
	      new MenuSpec("Size 14", "font-size-14"),  
	      new MenuSpec("Size 16", "font-size-16"),  
	      new MenuSpec("Size 18", "font-size-18"),  
	      new MenuSpec("Size 24", "font-size-24"),  
	      new MenuSpec("Size 36", "font-size-36"),  
	      new MenuSpec("Size 48", "font-size-48")  
	  };  
	  
	  private static MenuSpec[] familySpec = new MenuSpec[] {  
	      new MenuSpec("Sans Serif", "font-family-SansSerif"),  
	      new MenuSpec("Monospaced", "font-family-Monospaced"),  
	      new MenuSpec("Serif", "font-family-Serif")  
	  };  
	  
	  private static MenuSpec[] styleSpec = new MenuSpec[] {  
	      new MenuSpec("Bold", "font-bold"),  
	      new MenuSpec("Italics", "font-italic"),  
	      new MenuSpec("Underline", "font-underline")  
	  };  
	  
	  // Menu definitions for fonts  
	  private static MenuSpec[] fontSpec = new MenuSpec[] {  
	      new MenuSpec("Size", sizeSpec),  
	      new MenuSpec("Family", familySpec),  
	      new MenuSpec("Style", styleSpec)  
	  };  
	  
	  // Alignment  
	  private static MenuSpec[] alignSpec = new MenuSpec[] {  
	      new MenuSpec("Left", "left-justify"),  
	      new MenuSpec("Center", "center-justify"),  
	      new MenuSpec("Right", "right-justify")  
	  };  
	  
	  // HTML menu  
	  private static MenuSpec[] headingSpec = new MenuSpec[] {  
	    new MenuSpec("Heading 1", "Heading 1"),  
	    new MenuSpec("Heading 2", "Heading 2")  
	  };  
	  
	  private static MenuSpec[] htmlSpec = new MenuSpec[] {  
	    new MenuSpec("Table", "InsertTable"),  
	    new MenuSpec("Table Row", "InsertTableRow"),  
	    new MenuSpec("Table Cell", "InsertTableDataCell"),  
	    new MenuSpec("Unordered List", "InsertUnorderedList"),  
	    new MenuSpec("Unordered List Item", "InsertUnorderedListItem"),  
	    new MenuSpec("Ordered List", "InsertOrderedList"),  
	    new MenuSpec("Ordered List Item", "InsertOrderedListItem"),  
	    new MenuSpec("Preformatted Paragraph", "InsertPre"),  
	    new MenuSpec("Horizontal Rule", "InsertHR"),  
	    new MenuSpec("Headings", headingSpec)  
	  };  
	}  
	  
	class MenuSpec {  
	  public MenuSpec(String name, MenuSpec[] subMenus) {  
	    this.name = name;  
	    this.subMenus = subMenus;  
	  }  
	  
	  public MenuSpec(String name, String actionName) {  
	    this.name = name;  
	    this.actionName = actionName;  
	  }  
	  
	  public MenuSpec(String name, Action action) {  
	    this.name = name;  
	    this.action = action;  
	  }  
	  
	  public boolean isSubMenu() {  
	    return subMenus != null;  
	  }  
	  
	  public boolean isAction() {  
	    return action != null;  
	  }  
	  
	  public String getName() {  
	    return name;  
	  }  
	  
	  public MenuSpec[] getSubMenus() {  
	    return subMenus;  
	  }  
	  
	  public String getActionName() {  
	    return actionName;  
	  }  
	  
	  public Action getAction() {  
	    return action;  
	  }  
	  
	  private String name;  
	  
	  private String actionName;  
	  
	  private Action action;  
	  
	  private MenuSpec[] subMenus;  
	}  
	  
	class HTMLDocumentLoader {  
	  public HTMLDocument loadDocument(HTMLDocument doc, URL url, String charSet)  
	      throws IOException {  
	    doc.putProperty(Document.StreamDescriptionProperty, url);  
	  
	    /* 
	     * This loop allows the document read to be retried if the character 
	     * encoding changes during processing. 
	     */  
	    InputStream in = null;  
	    boolean ignoreCharSet = false;  
	  
	    for (;;) {  
	      try {  
	        // Remove any document content  
	        doc.remove(0, doc.getLength());  
	  
	        URLConnection urlc = url.openConnection();  
	        in = urlc.getInputStream();  
	        Reader reader = (charSet == null) ? new InputStreamReader(in)  
	            : new InputStreamReader(in, charSet);  
	  
	        HTMLEditorKit.Parser parser = getParser();  
	        HTMLEditorKit.ParserCallback htmlReader = getParserCallback(doc);  
	        parser.parse(reader, htmlReader, ignoreCharSet);  
	        htmlReader.flush();  
	  
	        // All done  
	        break;  
	      } catch (BadLocationException ex) {  
	        // Should not happen - throw an IOException  
	        throw new IOException(ex.getMessage());  
	      } catch (ChangedCharSetException e) {  
	        // The character set has changed - restart  
	        charSet = getNewCharSet(e);  
	  
	        // Prevent recursion by suppressing further exceptions  
	        ignoreCharSet = true;  
	  
	        // Close original input stream  
	        in.close();  
	  
	        // Continue the loop to read with the correct encoding  
	      }  
	    }  
	  
	    return doc;  
	  }  
	  
	  public HTMLDocument loadDocument(URL url, String charSet)  
	      throws IOException {  
	    return loadDocument((HTMLDocument) kit.createDefaultDocument(), url,  
	        charSet);  
	  }  
	  
	  public HTMLDocument loadDocument(URL url) throws IOException {  
	    return loadDocument(url, null);  
	  }  
	  
	  // Methods that allow customization of the parser and the callback  
	  public synchronized HTMLEditorKit.Parser getParser() {  
	    if (parser == null) {  
	      try {  
	        Class c = Class  
	            .forName("javax.swing.text.html.parser.ParserDelegator");  
	        parser = (HTMLEditorKit.Parser) c.newInstance();  
	      } catch (Throwable e) {  
	      }  
	    }  
	    return parser;  
	  }  
	  
	  public synchronized HTMLEditorKit.ParserCallback getParserCallback(  
	      HTMLDocument doc) {  
	    return doc.getReader(0);  
	  }  
	  
	  protected String getNewCharSet(ChangedCharSetException e) {  
	    String spec = e.getCharSetSpec();  
	    if (e.keyEqualsCharSet()) {  
	      // The event contains the new CharSet  
	      return spec;  
	    }  
	  
	    // The event contains the content type  
	    // plus ";" plus qualifiers which may  
	    // contain a "charset" directive. First  
	    // remove the content type.  
	    int index = spec.indexOf(";");  
	    if (index != -1) {  
	      spec = spec.substring(index + 1);  
	    }  
	  
	    // Force the string to lower case  
	    spec = spec.toLowerCase();  
	  
	    StringTokenizer st = new StringTokenizer(spec, " \t=", true);  
	    boolean foundCharSet = false;  
	    boolean foundEquals = false;  
	    while (st.hasMoreTokens()) {  
	      String token = st.nextToken();  
	      if (token.equals(" ") || token.equals("\t")) {  
	        continue;  
	      }  
	      if (foundCharSet == false && foundEquals == false  
	          && token.equals("charset")) {  
	        foundCharSet = true;  
	        continue;  
	      } else if (foundEquals == false && token.equals("=")) {  
	        foundEquals = true;  
	        continue;  
	      } else if (foundEquals == true && foundCharSet == true) {  
	        return token;  
	      }  
	  
	      // Not recognized  
	      foundCharSet = false;  
	      foundEquals = false;  
	    }  
	  
	    // No charset found - return a guess  
	    return "8859_1";  
	  }  
	  
	  protected static HTMLEditorKit kit;  
	  
	  protected static HTMLEditorKit.Parser parser;  
	  
	  static {  
	    kit = new HTMLEditorKit();  
	  }  
	}  
	  
	class MenuBuilder {  
	  public static JMenu buildMenu(String name, MenuSpec[] menuSpecs,  
	      Hashtable actions) {  
	    int count = menuSpecs.length;  
	  
	    JMenu menu = new JMenu(name);  
	    for (int i = 0; i < count; i++) {  
	      MenuSpec spec = menuSpecs[i];  
	      if (spec.isSubMenu()) {  
	        // Recurse to handle a sub menu  
	        JMenu subMenu = buildMenu(spec.getName(), spec.getSubMenus(),  
	            actions);  
	        if (subMenu != null) {  
	          menu.add(subMenu);  
	        }  
	      } else if (spec.isAction()) {  
	        // It's an Action - add it directly to the menu  
	        menu.add(spec.getAction());  
	      } else {  
	        // It's an action name - add it if possible  
	        String actionName = spec.getActionName();  
	        Action targetAction = (Action) actions.get(actionName);  
	  
	        // Create the menu item  
	        JMenuItem menuItem = menu.add(spec.getName());  
	        if (targetAction != null) {  
	          // The editor kit knows the action  
	          menuItem.addActionListener(targetAction);  
	        } else {  
	          // Action not known - disable the menu item  
	          menuItem.setEnabled(false);  
	        }  
	      }  
	    }  
	  
	    // Return null if nothing was added to the menu.  
	    if (menu.getMenuComponentCount() == 0) {  
	      menu = null;  
	    }  
	  
	    return menu;  
	  }  
	}  
package com.thang.swt;
import org.eclipse.swt.*;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.*;
public class TreeInsertItem {
	public static void main (String [] args) {
		Display display = new Display ();
		Shell shell = new Shell (display);
		Tree tree = new Tree (shell, SWT.BORDER | SWT.MULTI);
		Rectangle clientArea = shell.getClientArea ();
		tree.setBounds (clientArea.x, clientArea.y, 200, 200);
		for (int i=0; i<12; i++) {
			TreeItem item = new TreeItem (tree, SWT.NONE);
			item.setText ("Item " + i);
		}
		TreeItem item = new TreeItem (tree, SWT.NONE, 1);
		TreeItem [] items = tree.getItems ();
		int index = 0;
		while (index < items.length && items [index] != item) index++;
		item.setText ("*** New Item " + index + " ***");
		shell.pack ();
		shell.open ();
		while (!shell.isDisposed ()) {
			if (!display.readAndDispatch ()) display.sleep ();
		}
		display.dispose ();
	}

}

package com.thang.swt;
import org.eclipse.swt.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;
public class DetectTree {
	public static void main (String [] args) {
		Display display = new Display ();
		Shell shell = new Shell (display);
		final Tree tree = new Tree (shell, SWT.BORDER | SWT.MULTI);
		for (int i=0; i<12; i++) {
			TreeItem treeItem = new TreeItem (tree, SWT.NONE);
			treeItem.setText ("Item " + i);
		}
		tree.addListener (SWT.MouseDown, new Listener () {
			public void handleEvent (Event event) {
				Point point = new Point (event.x, event.y);
				TreeItem item = tree.getItem (point);
				if (item != null) {
					System.out.println ("Mouse down: " + item);
				}
			}
		});
		Rectangle clientArea = shell.getClientArea ();
		tree.setBounds (clientArea.x, clientArea.y, 200, 200);
		shell.setSize (300, 300);
		shell.open ();
		while (!shell.isDisposed ()) {
			if (!display.readAndDispatch ()) display.sleep ();
		}
		display.dispose ();
	}
}

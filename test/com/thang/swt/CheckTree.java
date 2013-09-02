package com.thang.swt;
import org.eclipse.swt.*;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.*;
public class CheckTree {
	public static void main (String [] args) {
		Display display = new Display ();
		Shell shell = new Shell (display);
		Tree tree = new Tree (shell, SWT.CHECK | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		for (int i=0; i<12; i++) {
			TreeItem item = new TreeItem (tree, SWT.NONE);
			item.setText ("Item " + i);
		}
		Rectangle clientArea = shell.getClientArea ();
		tree.setBounds (clientArea.x, clientArea.y, 100, 100);
		tree.addListener (SWT.Selection, new Listener () {
			public void handleEvent (Event event) {
				String string = event.detail == SWT.CHECK ? "Checked" : "Selected";
				System.out.println (event.item + " " + string);
			}
		});
		shell.setSize (200, 200);
		shell.open ();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch ()) display.sleep ();
		}
		display.dispose ();
	}
}

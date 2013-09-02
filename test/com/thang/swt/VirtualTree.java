package com.thang.swt;
import org.eclipse.swt.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
public class VirtualTree {
	public static void main(String[] args) {
		Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setLayout (new FillLayout());
		final Tree tree = new Tree(shell, SWT.VIRTUAL | SWT.BORDER);
		tree.addListener(SWT.SetData, new Listener() {
			public void handleEvent(Event event) {
				TreeItem item = (TreeItem)event.item;
				TreeItem parentItem = item.getParentItem();
				String text = null;
				if (parentItem == null) {
					text = "node "+tree.indexOf(item);
				} else {
					text = parentItem.getText()+" - "+parentItem.indexOf(item);
				}
				item.setText(text);
				item.setItemCount(10);
			}
		});
		tree.setItemCount(20);
		shell.setSize(400, 300);
		shell.open();
		while (!shell.isDisposed ()) {
			if (!display.readAndDispatch ()) display.sleep ();
		}
		display.dispose ();
	}
}

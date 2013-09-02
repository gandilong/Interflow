package com.thang.swt;
import org.eclipse.swt.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.graphics.*;
public class IndentText {
	public static void main(String[] args) {
		Display display = new Display();
		final Shell shell = new Shell(display, SWT.SHELL_TRIM | SWT.DOUBLE_BUFFERED);	
		shell.setText("Indent, Justify, Align");
		String[] texts = {
			"Plans do not materialize out of nowhere, nor are they entirely static. To ensure the planning process is transparent and open to the entire Eclipse community, we (the Eclipse PMC) post plans in an embryonic form and revise them throughout the release cycle.",
			"The first part of the plan deals with the important matters of release deliverables, release milestones, target operating environments, and release-to-release compatibility. These are all things that need to be clear for any release, even if no features were to change.",
			"The remainder of the plan consists of plan items for the various Eclipse subprojects. Each plan item covers a feature or API that is to be added to Eclipse, or some aspect of Eclipse that is to be improved. Each plan item has its own entry in the Eclipse bugzilla database, with a title and a concise summary (usually a single paragraph) that explains the work item at a suitably high enough level so that everyone can readily understand what the work item is without having to understand the nitty-gritty detail.",
		};
		int[] alignments = {SWT.LEFT, SWT.CENTER, SWT.RIGHT};
		final TextLayout[] layouts = new TextLayout[texts.length];
		for (int i = 0; i < layouts.length; i++) {
			TextLayout layout = new TextLayout(display);
			layout.setText(texts[i]);
			layout.setIndent(30);
			layout.setJustify(true);
			layout.setAlignment(alignments[i]);		
			layouts[i] = layout;
		}
		shell.addListener(SWT.Paint, new Listener() {
			public void handleEvent(Event event) {
				Point point = new Point(10, 10);
				int width = shell.getClientArea().width - 2 * point.x;
				for (int i = 0; i < layouts.length; i++) {
					TextLayout layout = layouts[i];
					layout.setWidth(width);
					layout.draw(event.gc, point.x, point.y);
					point.y += layout.getBounds().height + 10;
				}			
			}
		});
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) display.sleep();
		}
		for (int i = 0; i < layouts.length; i++) {
			layouts[i].dispose();		
		}
		display.dispose();
	}
}

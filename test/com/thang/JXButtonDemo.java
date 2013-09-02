package com.thang;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImageOp;

import javax.swing.JPanel;

import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.JXFrame;
import org.jdesktop.swingx.image.StackBlurFilter;
import org.jdesktop.swingx.painter.AbstractPainter;

public class JXButtonDemo extends JPanel {
    public JXButtonDemo() {
        //simple demo that blurs the button's text
        final JXButton b = new JXButton("Executeddddddddddddddddd");
        final AbstractPainter<?> fgPainter = (AbstractPainter<?>)b.getForegroundPainter();
        final StackBlurFilter filter = new StackBlurFilter();
        fgPainter.setFilters(filter);

        b.addMouseListener(new MouseAdapter() {
            boolean entered = false;
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                if (!entered) {
                    fgPainter.setFilters(new BufferedImageOp[0]);
                    b.repaint();
                    entered = true;
                }
            }
            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                if (entered) {
                    fgPainter.setFilters(filter);
                    b.repaint();
                    entered = false;
                }
            }
        });
        add(b);
    }

    public static void main(String[] args) {
        JXFrame f = new JXFrame("JXButton Demo", true);
        f.add(new JXButtonDemo());
        f.setSize(400, 300);
        f.setStartPosition(JXFrame.StartPosition.CenterInScreen);
        f.setVisible(true);
    }
}

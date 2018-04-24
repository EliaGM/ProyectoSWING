/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PnGenerico extends JPanel {

    public PnGenerico(int x, int y, int w, int h) {
        this.setLayout(new FlowLayout());
        this.setBackground(new Color(230, 230, 230));
        this.setBounds(x, y, w, h);
        this.setOpaque(true);
    }
    public PnGenerico(int x, int y, int w, int h, int t, int l, int b, int r) {
        this(x,y,w,h);
        this.setBorder(new EmptyBorder(t, l, b, r));
    }
}

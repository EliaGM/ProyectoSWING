/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;


public class Boton extends JButton {

    public Boton() {
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setFocusPainted(false);

        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                setOpaque(true);
                setContentAreaFilled(true);
                setBorderPainted(true);
                setBackground(new Color(210, 210, 210));
                setBorder(new EtchedBorder(new Color(210, 210, 210), new Color(136, 136, 136)));
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                setOpaque(false);
                setContentAreaFilled(false);
                setBorderPainted(false);
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent evt) {
            }

            @Override
            public void mouseClicked(MouseEvent evt) {
            }
        });
    }

    public Boton(String texto) {
        super(texto);
        this.setBorderPainted(false);
        this.setBackground(new Color(221, 221, 221));
        this.setFont(new Font("Arial", Font.BOLD, 15));
        this.setFocusPainted(false);

        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                setForeground(Color.BLACK);
                setBorderPainted(true);
                setBackground(new Color(210, 210, 210));
                setBorder(new EtchedBorder(new Color(210, 210, 210), new Color(136, 136, 136)));
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                setForeground(Color.darkGray);
                setBorderPainted(true);
                setBackground(new Color(221, 221, 221));
                setBorder(new EtchedBorder(new Color(210, 210, 210), new Color(136, 136, 136)));
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent evt) {
                setForeground(Color.BLACK);
                setBorderPainted(true);
                setBackground(new Color(179, 178, 178));
                setBorder(new EtchedBorder(Color.lightGray, Color.black));
                repaint();
            }

            @Override
            public void mouseClicked(MouseEvent evt) {
                setForeground(Color.BLACK);
                setBorderPainted(true);
                setBackground(new Color(179, 178, 178));
                setBorder(new EtchedBorder(Color.lightGray, Color.black));
                repaint();
            }
        });
    }
}

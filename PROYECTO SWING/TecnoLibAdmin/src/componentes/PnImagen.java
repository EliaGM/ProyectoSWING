/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class PnImagen extends JPanel {
    
    public BufferedImage image;

    public PnImagen() {
        this.setBackground(Color.white);
        this.setOpaque(true);
        this.setPreferredSize(new Dimension(80, 130));
        this.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.lightGray, Color.darkGray));
    }

    public void cargarImagen(File file) {
        try {
            image = ImageIO.read(file);
            repaint();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, 80, 130, null);

    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import componentes.Boton;
import static interfaz.IScreen.height;
import static interfaz.IScreen.width;
import static interfaz.Menu.ANCHO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import listeners.ListenerControlador;


public class Ventana extends JFrame implements IScreen {

    public static final int ANCHO = 750;
    public int y, x;
    public JPanel pnportada;
    public JLabel lbtitulo;
    public JPanel pncontenido;
    public JButton btatras;
    public JButton btclose;

    public Ventana() {
        super("Ventana por defecto");
        this.setBounds(getXScreen(500), getYScreen(500), 500, 500);
        this.getContentPane().setLayout(null);
    }

    public Ventana(String titulo, int w, int h) {
        super(titulo);
        this.setBounds(getXScreen(w), getYScreen(h), w, h);
        this.getContentPane().setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.white);
        pnportada = new JPanel();
        pnportada.setLayout(new BorderLayout());
        pnportada.setPreferredSize(new Dimension(ANCHO, 130));
        pnportada.setBackground(Color.BLACK);
        pnportada.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        ImageIcon icon = new ImageIcon(getClass().getResource("../images/libros.png"));
        lbtitulo = new JLabel("TecnoLib", JLabel.CENTER);
        lbtitulo.setIcon(icon);
        lbtitulo.setForeground(new Color(195, 194, 194));
        lbtitulo.setFont(new Font("Arial", Font.BOLD, 50));
        
        
        pncontenido = new JPanel() {
                @Override
                public void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    BufferedImage image=null;
                    try {
                        image = ImageIO.read(interfaz.Inicio.class.getResourceAsStream("../images/fondopapel.jpg"));
                    } catch (IOException ex) {
                        System.out.println("Archivo no encontrado");
                    }
                    g.drawImage(image, 0, 0, null);
                }
            };
            pncontenido.setBackground(Color.white);
        btatras = new Boton();
        btatras.setText("<html><b><span style='font-size:50px;color:black;'>A</span><span style='font-size:13px;color:gray'>TRAS</span></b></html>");
        
        btclose = new Boton();
        btclose.setText("<html><b><span style='font-size:50px;color:black;'>C</span><span style='font-size:13px;color:gray'>ERRAR</span></b></html>");
        
        this.getContentPane().add(pnportada, BorderLayout.NORTH);
        pnportada.add(lbtitulo, BorderLayout.CENTER);
        this.getContentPane().add(pncontenido, BorderLayout.CENTER);
        
        btclose.addActionListener(new ListenerControlador(this));
        btatras.addActionListener(new ListenerControlador(this));
    }

    @Override
    public final int getXScreen(int w) {
        this.x = (int) width / 2 - w / 2;
        return this.x;
    }

    @Override
    public final int getYScreen(int h) {
        this.y = (int) height / 2 - h / 2;
        return this.y;
    }
}

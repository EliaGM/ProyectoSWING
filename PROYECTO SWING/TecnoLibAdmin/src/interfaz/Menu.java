/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import componentes.Boton;
import componentes.Texto;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import listeners.ListenerControlador;


public class Menu extends Ventana {
    
    
    public JLabel lbmenu;
    public JButton btinsertar;
    public JButton btmodificar;
    public JButton bteliminar;
    public JPanel pnfooter;
    
    

    public Menu(String titulo, int w, int h) {
        super(titulo, w, h);
        try {
            
            pncontenido.setLayout(new GridLayout(3, 2, 20, 20));
            pncontenido.setBorder(new EmptyBorder(100, 200, 100, 200));
            lbmenu = new JLabel("<html><b><span style='font-size:80px;color:black;'>M</span><span style='font-size:20px;color:gray'>ENU</span></b></html>");
            Texto t = new Texto();
            lbmenu.setBorder(new EmptyBorder(10, 10, 20, 0));
            btinsertar = new Boton(t.getInicio()+t.getNegro1()+"N"+t.getSpanfin()
                    +t.getGris()+"ue"+t.getSpanfin()+t.getNegro2()+"v"+t.getSpanfin()
                    +t.getGris()+"o"+t.getSpanfin()+t.getSalto()
                    +t.getNegro1()+"R"+t.getSpanfin()+t.getGris()+"eg"+t.getSpanfin()
                    +t.getNegro2()+"i"+t.getSpanfin()+t.getGris()+"str"+t.getSpanfin()
                    +t.getNegro2()+"o"+t.getSpanfin()+t.getFin());
            btmodificar = new Boton(t.getInicio()+t.getNegro1()+"M"+t.getSpanfin()
                    +t.getGris()+"odi"+t.getSpanfin()+t.getNegro2()+"f"+t.getSpanfin()
                    +t.getGris()+"ica"+t.getSpanfin()+t.getNegro2()+"r"+t.getSpanfin()+t.getSalto()
                    +t.getNegro1()+"R"+t.getSpanfin()+t.getGris()+"eg"+t.getSpanfin()
                    +t.getNegro2()+"i"+t.getSpanfin()+t.getGris()+"str"+t.getSpanfin()
                    +t.getNegro2()+"o"+t.getSpanfin()+t.getFin());
            bteliminar = new Boton(t.getInicio()+t.getNegro1()+"E"+t.getSpanfin()
                    +t.getGris()+"lim"+t.getSpanfin()+t.getNegro2()+"i"+t.getSpanfin()
                    +t.getGris()+"na"+t.getSpanfin()+t.getNegro2()+"r"+t.getSpanfin()+t.getSalto()
                    +t.getNegro1()+"R"+t.getSpanfin()+t.getGris()+"eg"+t.getSpanfin()
                    +t.getNegro2()+"i"+t.getSpanfin()+t.getGris()+"str"+t.getSpanfin()
                    +t.getNegro2()+"o"+t.getSpanfin()+t.getFin());
            
            btinsertar.addActionListener(new ListenerControlador(this));
            btmodificar.addActionListener(new ListenerControlador(this));
            bteliminar.addActionListener(new ListenerControlador(this));
            
            pncontenido.add(lbmenu);
            pncontenido.add(btinsertar);
            pncontenido.add(btmodificar);
            pncontenido.add(bteliminar);
            pncontenido.add(btclose);
            
            this.setVisible(true);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    

}

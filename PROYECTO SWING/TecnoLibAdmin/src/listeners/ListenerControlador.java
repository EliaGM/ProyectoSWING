/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import interfaz.Eliminacion;
import interfaz.Insercion;
import interfaz.Menu;
import interfaz.Modificacion;
import interfaz.Ventana;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRootPane;

public class ListenerControlador implements ActionListener {

    private Ventana v;

    public ListenerControlador(Ventana v) {
        this.v = v;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Class<Menu> Cmenu = Menu.class;
        Class<Insercion> Cinsert = Insercion.class;
        Class<Modificacion> Cmod = Modificacion.class;
        
        JRootPane rp = (JRootPane)((JPanel) ((JButton) e.getSource()).getParent()).getParent().getParent().getParent();
        
        System.out.println((rp.getParent()).getClass().getSimpleName());
        
        if (Cmenu.isInstance(rp.getParent())) {
            System.out.println(((JPanel) ((JButton) e.getSource()).getParent()).getParent().getParent().getParent().getParent().getClass().getCanonicalName());
            if (((Menu) v).btinsertar.equals((JButton) e.getSource())) {
                OpenInsertar();
            } else if (((Menu) v).btmodificar.equals((JButton) e.getSource())) {
                OpenModificar();
            } else if (((Menu) v).bteliminar.equals((JButton) e.getSource())) {
                OpenEliminar();
            } else {
                cerrar();
            }
        } else if (Cinsert.isInstance(rp.getParent())) {
            if (((Insercion) v).btatras.equals((JButton) e.getSource())) {
                ((Insercion) v).dispose();
                Menu menu = new Menu("TecnoLib Administrador", 750, 750);
                
            }else{
                cerrar();
            }
        } else if (Cmod.isInstance(rp.getParent())) {
            if (((Modificacion) v).btatras.equals((JButton) e.getSource())) {
                ((Modificacion) v).dispose();
                Menu menu = new Menu("TecnoLib Administrador", 750, 750);
                
            }else{
                cerrar();
            }
        }else{
            if (((Eliminacion) v).btatras.equals((JButton) e.getSource())) {
                ((Eliminacion) v).dispose();
                Menu menu = new Menu("TecnoLib Administrador", 750, 750);
                
            }else{
                cerrar();
            }
        }


    }

    public void OpenInsertar() {
        ((Menu) v).dispose();
        Insercion in = new Insercion("Insertar productos", 750, 750);
    }

    public void OpenModificar() {
        ((Menu) v).dispose();
        Modificacion mod = new Modificacion("Modificar productos", 750, 750);
    }

    public void OpenEliminar() {
        ((Menu) v).dispose();
        Eliminacion el = new Eliminacion("Eliminar productos", 750, 750);
    }

    public void cerrar() {
        System.exit(0);
    }
}

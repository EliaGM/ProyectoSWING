/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import interfaz.Modificacion;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ListenerBusqueda implements DocumentListener, ItemListener {

    private Modificacion mod;

    public ListenerBusqueda(Modificacion mod) {
        this.mod = mod;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        
        modoBusqueda();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        modoBusqueda();

    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        System.out.println("CAMBIO 3");
    }
    

    @Override
    public void itemStateChanged(ItemEvent e) {
        mod.btguardar.setEnabled(true);
        mod.btcancelar.setEnabled(true);
        mod.btclean.setEnabled(false);
    }
    
    private void modoBusqueda(){
        if (!mod.pntitulo.tfcampo.isEnabled()) {
            if (mod.tfisbn.getText().equals("") && mod.tfidprod.getText().equals("")) {
                mod.tfisbn.setEnabled(true);
                mod.tfidprod.setEnabled(true);
            } else {
                if (!mod.tfisbn.getText().equals("")) {
                    mod.tfidprod.setText("");
                    mod.tfidprod.setEnabled(false);
                    mod.tfisbn.setEnabled(true);
                } else if (!mod.tfidprod.getText().equals("")) {
                    mod.tfisbn.setText("");
                    mod.tfisbn.setEnabled(false);
                    mod.tfidprod.setEnabled(true);
                }
            }
        } else {
            mod.tfisbn.setEnabled(true);
            mod.tfidprod.setEnabled(true);
        }
    }
}

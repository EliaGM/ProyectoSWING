/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import componentes.CheckDatos;
import componentes.PnTexto;
import interfaz.Modificacion;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class ListenerChangeMod implements DocumentListener{
    
    private PnTexto pn;
    private Modificacion mod;

    public ListenerChangeMod(Modificacion mod, PnTexto pn) {
        this.mod = mod;
        this.pn = pn;
    }
   
    @Override
    public void insertUpdate(DocumentEvent e) {
        mod.btguardar.setEnabled(true);
        mod.btcancelar.setEnabled(true);
        CheckDatos c = new CheckDatos();
        if(pn.equals(mod.pntitulo)){
            if(!c.checkTexto(60, pn, 0, "Titulo (*)",true)){
               c.valoresDefecto(pn,false);
            }
        }else if(pn.equals(mod.pnautor)){
            if(!c.checkTexto(80, pn, 0, "Autor (*)",true)){
                c.valoresDefecto(pn,false);
            }
        }else if(pn.equals(mod.pneditorial)){
            if(!c.checkTexto(45, pn, 0, "Editorial (*)",true)){
               c.valoresDefecto(pn,false);
            }
        }else if(pn.equals(mod.pnstock)){
            if(!c.checkTexto(-1, pn, 0, "Stock (*)",true)){
               c.valoresDefecto(pn,false);
            }
        }else if(pn.equals(mod.pnprecio)){
            if(!c.checkTexto(-2, pn, 0, "Precio (*)",true)){
                c.valoresDefecto(pn,false);
            }
        }else if(pn.equals(mod.pnpaginas)){
            if(!c.checkTexto(-1, pn, 0, "NºPaginas (*)",true)){
                c.valoresDefecto(pn,false);
            }
        }else if(pn.equals(mod.pnaño)){
            if(!c.checkTexto(-4, pn, 4,"Año (*)",true)){
                c.valoresDefecto(pn,false);
            }
        }else if(pn.equals(mod.tadesc)){
            if(!c.CheckArea(1000,"Descripcion (*)",true,pn)){
                c.valoresDefectoArea(pn,false);
            }
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        mod.btguardar.setEnabled(true);
        mod.btcancelar.setEnabled(true);
        CheckDatos c = new CheckDatos();
       if(pn.equals(mod.pntitulo)){
            if(!c.checkTexto(60, pn, 0, "Titulo (*)",true)){
               c.valoresDefecto(pn,false);
            }
        }else if(pn.equals(mod.pnautor)){
            if(!c.checkTexto(80, pn, 0, "Autor (*)",true)){
                c.valoresDefecto(pn,false);
            }
        }else if(pn.equals(mod.pneditorial)){
            if(!c.checkTexto(45, pn, 0, "Editorial (*)",true)){
               c.valoresDefecto(pn,false);
            }
        }else if(pn.equals(mod.pnstock)){
            if(!c.checkTexto(-1, pn, 0, "Stock (*)",true)){
               c.valoresDefecto(pn,false);
            }
        }else if(pn.equals(mod.pnprecio)){
            if(!c.checkTexto(-2, pn, 0, "Precio (*)",true)){
                c.valoresDefecto(pn,false);
            }
        }else if(pn.equals(mod.pnpaginas)){
            if(!c.checkTexto(-1, pn, 0, "NºPaginas (*)",true)){
                c.valoresDefecto(pn,false);
            }
        }else if(pn.equals(mod.pnaño)){
            if(!c.checkTexto(-4, pn, 4,"Año (*)",true)){
                c.valoresDefecto(pn,false);
            }
        }else if(pn.equals(mod.tadesc)){
            if(!c.CheckArea(1000,"Descripcion (*)",true,pn)){
                c.valoresDefectoArea(pn,false);
            }
        }
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        System.out.println("CAMBIO3");
    }
    
}

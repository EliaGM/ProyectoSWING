/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import componentes.CheckDatos;
import componentes.PnTexto;
import interfaz.Insercion;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class ListenerChange implements DocumentListener{
    
    private PnTexto pn;
    private Insercion in;

    public ListenerChange(Insercion in, PnTexto pn) {
        this.in = in;
        this.pn = pn;
    }
   
    @Override
    public void insertUpdate(DocumentEvent e) {
        CheckDatos c = new CheckDatos();
        if(pn.equals(in.pnisbn)){
            if(!c.checkTexto(-3, pn, 13,"ISBN (*)",true)){
                c.valoresDefecto(pn,false);
            }
        }else if(pn.equals(in.pntitulo)){
            if(!c.checkTexto(60, pn, 0, "Titulo (*)",true)){
               c.valoresDefecto(pn,false);
            }
        }else if(pn.equals(in.pnautor)){
            if(!c.checkTexto(80, pn, 0, "Autor (*)",true)){
                c.valoresDefecto(pn,false);
            }
        }else if(pn.equals(in.pneditorial)){
            if(!c.checkTexto(45, pn, 0, "Editorial (*)",true)){
               c.valoresDefecto(pn,false);
            }
        }else if(pn.equals(in.pnstock)){
            if(!c.checkTexto(-1, pn, 0, "Stock (*)",true)){
               c.valoresDefecto(pn,false);
            }
        }else if(pn.equals(in.pnprecio)){
            if(!c.checkTexto(-2, pn, 0, "Precio (*)",true)){
                c.valoresDefecto(pn,false);
            }
        }else if(pn.equals(in.pnpaginas)){
            if(!c.checkTexto(-1, pn, 0, "NºPaginas (*)",true)){
                c.valoresDefecto(pn,false);
            }
        }else if(pn.equals(in.pnaño)){
            if(!c.checkTexto(-4, pn, 4,"Año (*)",true)){
                c.valoresDefecto(pn,false);
            }
        }else if(pn.equals(in.tadesc)){
            if(!c.CheckArea(1000,"Descripcion (*)",true,pn)){
                c.valoresDefectoArea(pn,false);
            }
            
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        CheckDatos c = new CheckDatos();
        if(pn.equals(in.pnisbn)){
            if(!c.checkTexto(-3, pn, 13,"ISBN (*)",true)){
                c.valoresDefecto(pn,false);
            }
        }else if(pn.equals(in.pntitulo)){
            if(!c.checkTexto(60, pn, 0, "Titulo (*)",true)){
               c.valoresDefecto(pn,false);
            }
        }else if(pn.equals(in.pnautor)){
            if(!c.checkTexto(80, pn, 0, "Autor (*)",true)){
                c.valoresDefecto(pn,false);
            }
        }else if(pn.equals(in.pneditorial)){
            if(!c.checkTexto(45, pn, 0, "Editorial (*)",true)){
               c.valoresDefecto(pn,false);
            }
        }else if(pn.equals(in.pnstock)){
            if(!c.checkTexto(-1, pn, 0, "Stock (*)",true)){
               c.valoresDefecto(pn,false);
            }
        }else if(pn.equals(in.pnprecio)){
            if(!c.checkTexto(-2, pn, 0, "Precio (*)",true)){
                c.valoresDefecto(pn,false);
            }
        }else if(pn.equals(in.pnpaginas)){
            if(!c.checkTexto(-1, pn, 0, "NºPaginas (*)",true)){
                c.valoresDefecto(pn,false);
            }
        }else if(pn.equals(in.pnaño)){
            if(!c.checkTexto(-4, pn, 4,"Año (*)",true)){
                c.valoresDefecto(pn,false);
            }
        }else if(pn.equals(in.tadesc)){
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

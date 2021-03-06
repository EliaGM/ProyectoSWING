/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import componentes.PnGenerico;
import componentes.PnImagen;
import componentes.PnTexto;
import entidades.Idioma;
import entidades.Seccion;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import listeners.ListenerBusqueda;
import listeners.ListenerChangeMod;
import listeners.ListenerMod;
import modulo.Operaciones;


public class Modificacion extends Ventana{
    
    public Seccion seccion;
    public JPanel pnresumen;
    public JPanel pndetalle;
    public PnGenerico pnfoto;
    public PnGenerico pnbotones;
    public PnGenerico pnclose;
    public PnGenerico pnatras;
    public JPanel pnbusqueda;
    public JPanel pnbtnbusqueda;
    
    public JTextField tfidprod;
    public PnTexto pntitulo;
    public PnTexto pnautor;
    public PnTexto pneditorial;
    public JTextField tfisbn;
    public PnTexto pnaño;
    public PnTexto pnpaginas;
    public PnTexto pnprecio;
    public PnTexto pnstock;
    public PnTexto tadesc;
    public PnTexto cbidioma;
    public PnImagen pnimagen;
    public JFileChooser fc;
    public JButton btopen;
    public JButton btclean;
    public JButton btguardar;
    public JButton btcancelar;
    public JButton btbuscar;
    
    public DefaultComboBoxModel mcmbLengua;
    
    private Operaciones op = new Operaciones();
    
    public Modificacion(String titulo, int w, int h) {
        
        super(titulo,w, h);
        try {
            pncontenido.setLayout(new GridLayout(2, 2, 20, 0));
            pncontenido.setBorder(new EmptyBorder(30, 50, 40, 50));
            
            pnfoto = new PnGenerico(570, 270, 80, 170,0, 0, 0, 0);
            pnbotones = new PnGenerico(50, 550, 650, 100,28, 0, 0, 0);
            pnclose = new PnGenerico(570, 570, 110, 110);
            pnatras = new PnGenerico(50, 570, 110, 110);
            pnbusqueda = new JPanel();
            pnbtnbusqueda = new JPanel();
            
            pnresumen = new JPanel();
            pnresumen.setBackground(new Color(230,230,230));
            pnresumen.setOpaque(true);
            pnresumen.setLayout(new GridLayout(5, 2));
            pnresumen.setBorder(new EmptyBorder(10, 50, 10, 10));
            
            
            JLabel lbisbn = new JLabel("ISBN");
            JLabel lbid = new JLabel("Id Producto");
            lbid.setForeground(Color.white);
            lbisbn.setForeground(Color.white);
            tfisbn = new JTextField();
            tfisbn.setPreferredSize(new Dimension(115,25));
            tfidprod = new JTextField();
            tfidprod.setPreferredSize(new Dimension(50,25));
            pntitulo = new PnTexto("Titulo (*)",0,0,210,40);
            pnautor = new PnTexto("Autor (*)",0,0,210,40);
            pneditorial = new PnTexto("Editorial (*)",20,20,210,40);
            pnprecio = new PnTexto("Precio (*)",20,20,60,40);
            pnstock = new PnTexto("Stock (*)",20,20,60,40);
            pnaño = new PnTexto("Año (*)",20,20,60,40);
            pnpaginas = new PnTexto("NºPaginas (*)",20,20,60,40);
            tadesc= new PnTexto("Descripción (*)",20,20,400,100,5,1);
            tadesc.arcampo.setLineWrap(true);
            
            btbuscar = new JButton("Buscar");
            btbuscar.addActionListener(new ListenerMod(this));
            
            
            tfisbn.getDocument().addDocumentListener(new ListenerBusqueda(this));
            tfidprod.getDocument().addDocumentListener(new ListenerBusqueda(this));
            pntitulo.tfcampo.getDocument().addDocumentListener(new ListenerChangeMod(this, pntitulo));
            pnautor.tfcampo.getDocument().addDocumentListener(new ListenerChangeMod(this, pnautor));
            pneditorial.tfcampo.getDocument().addDocumentListener(new ListenerChangeMod(this, pneditorial));
            pnprecio.tfcampo.getDocument().addDocumentListener(new ListenerChangeMod(this, pnprecio));
            pnstock.tfcampo.getDocument().addDocumentListener(new ListenerChangeMod(this, pnstock));
            pnpaginas.tfcampo.getDocument().addDocumentListener(new ListenerChangeMod(this, pnpaginas));
            pnaño.tfcampo.getDocument().addDocumentListener(new ListenerChangeMod(this, pnaño));
            tadesc.arcampo.getDocument().addDocumentListener(new ListenerChangeMod(this, tadesc));
            
            pntitulo.tfcampo.setEnabled(false);
            pnautor.tfcampo.setEnabled(false);
            pneditorial.tfcampo.setEnabled(false);
            pnprecio.tfcampo.setEnabled(false);
            pnstock.tfcampo.setEnabled(false);
            pnpaginas.tfcampo.setEnabled(false);
            pnaño.tfcampo.setEnabled(false);
            tadesc.arcampo.setEnabled(false);
            
            mcmbLengua = new DefaultComboBoxModel();
            List<Idioma> idiomas = op.getIdiomas();
            for(Idioma i : idiomas){
                mcmbLengua.addElement(i.getIdioma());
            }
            cbidioma = new PnTexto("Idioma",20,20,150,40,mcmbLengua);
            cbidioma.cbcampo.setEnabled(false);
            cbidioma.cbcampo.addItemListener(new ListenerBusqueda(this));
            pnimagen = new PnImagen();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "jpeg");
            fc = new JFileChooser();
            fc.setFileFilter(filter);
            fc.setAcceptAllFileFilterUsed(false);
            String path = new File(".").getCanonicalPath() + "\\src\\images\\sinportada.jpg";
            pnimagen.cargarImagen(new File(path));
            btopen = new JButton("Abrir");
            btopen.addActionListener(new ListenerMod(this));
            
            btclean = new JButton("Nueva Busqueda");
            btclean.addActionListener(new ListenerMod(this));
            btguardar = new JButton("Guardar");
            btguardar.addActionListener(new ListenerMod(this));
            btcancelar = new JButton("Cancelar");
            btcancelar.addActionListener(new ListenerMod(this));
            btclose.setBounds(500, 0, btclose.getWidth(), btclose.getHeight());
            btclose.setOpaque(true);
            
            btclean.setEnabled(false);
            btguardar.setEnabled(false);
            btcancelar.setEnabled(false);
            btopen.setEnabled(false);
            
            pndetalle = new JPanel();
            pndetalle.setOpaque(true);
            pndetalle.setLayout(new GridLayout(2, 1, 20, 5));
            pndetalle.setBorder(new EmptyBorder(10, 50, 10, 10));
            pndetalle.setBackground(new Color(230,230,230));
            pndetalle.add(tadesc);
            
            pnfoto.add(btopen);
            pnfoto.add(pnimagen);
            
            pnbotones.add(btclean);
            pnbotones.add(btguardar);
            pnbotones.add(btcancelar);
            
            pnbusqueda.setBackground(Color.darkGray);
            pnbtnbusqueda.setBackground(Color.darkGray);
            
            pnbusqueda.add(lbisbn);
            pnbusqueda.add(tfisbn);
            pnbusqueda.add(lbid);
            
            pnbusqueda.add(tfidprod);
            
            
            pnbtnbusqueda.add(btbuscar);
            pnresumen.add(pnbusqueda);
            pnresumen.add(pnbtnbusqueda);
            pnresumen.add(pntitulo);
            pnresumen.add(pnautor);
            pnresumen.add(pneditorial);
            pnresumen.add(cbidioma);
            
            pnresumen.add(pnprecio);
            pnresumen.add(pnstock);
            pnresumen.add(pnaño);
            pnresumen.add(pnpaginas);
            
            btclose.setBackground(new Color(230,230,230));
            pnclose.add(btclose);
            pnatras.add(btatras);
            
            
            
            
            pncontenido.add(pnresumen);
            pncontenido.add(pndetalle);
            this.getContentPane().add(pnclose);
            this.getContentPane().add(pnatras);
            this.getContentPane().add(pnfoto);
            this.getContentPane().add(pnbotones);
            this.getContentPane().add(pncontenido);
            
            this.setVisible(true);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

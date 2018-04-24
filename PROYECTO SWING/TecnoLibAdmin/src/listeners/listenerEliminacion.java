/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import componentes.CheckDatos;
import entidades.Producto;
import excepciones.IsbnException;
import interfaz.Eliminacion;
import interfaz.InformDlg;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Caret;
import modulo.Operaciones;

public class listenerEliminacion implements DocumentListener, ActionListener {

    private Eliminacion el;

    public listenerEliminacion(Eliminacion el) {
        this.el = el;
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

    private void modoBusqueda() {
        if (!el.bteliminar.isEnabled()) {
            if (el.tfisbn.getText().equals("") && el.tfidprod.getText().equals("")) {
                el.tfisbn.setEnabled(true);
                el.tfidprod.setEnabled(true);
            } else {
                if (!el.tfisbn.getText().equals("")) {
                    el.tfidprod.setText("");
                    el.tfidprod.setEnabled(false);
                    el.tfisbn.setEnabled(true);
                } else if (!el.tfidprod.getText().equals("")) {
                    el.tfisbn.setText("");
                    el.tfisbn.setEnabled(false);
                    el.tfidprod.setEnabled(true);
                }
            }
        } else {
            el.tfisbn.setEnabled(false);
            el.tfidprod.setEnabled(false);
            el.btbuscar.setEnabled(false);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (el.btbuscar.equals((JButton) e.getSource())) {
                el.btclean.setEnabled(true);
                    el.bteliminar.setEnabled(true);
                    el.btdescatalogar.setEnabled(true);
                if (!el.tfisbn.getText().equals("")) {
                    if (el.tfisbn.getText().length() == 13) {
                        long isbn = Long.parseLong(el.tfisbn.getText());
                        if (realizarFind(null, isbn)) {
                            el.tfisbn.setEnabled(false);
                            el.tfidprod.setEnabled(false);
                            el.btbuscar.setEnabled(false);
                            el.btclean.setEnabled(true);
                            el.bteliminar.setEnabled(true);
                            el.btdescatalogar.setEnabled(true);
                        } else {
                            el.btclean.setEnabled(false);
                            el.bteliminar.setEnabled(false);
                            el.btdescatalogar.setEnabled(false);
                        }
                    }else {
                        throw new IsbnException("El tamaño del ISBN debe ser 15 numeros");
                    }
                } else if (!el.tfidprod.getText().equals("")) {
                    int idprod = Integer.parseInt(el.tfidprod.getText());
                    if (realizarFind(idprod, null)) {
                        el.tfisbn.setEnabled(false);
                        el.tfidprod.setEnabled(false);
                        el.btbuscar.setEnabled(false);
                        el.btclean.setEnabled(true);
                        el.bteliminar.setEnabled(true);
                        el.btdescatalogar.setEnabled(true);
                    } else {
                        el.btclean.setEnabled(false);
                        el.bteliminar.setEnabled(false);
                        el.btdescatalogar.setEnabled(false);
                    }


                } else {
                    el.tfisbn.setEnabled(true);
                    el.tfidprod.setEnabled(true);
                    el.btbuscar.setEnabled(true);
                    el.btclean.setEnabled(false);
                    el.bteliminar.setEnabled(false);
                    el.btdescatalogar.setEnabled(false);
                    el.tfidprod.setText("");
                    el.tfisbn.setText("");
                }


            } else if (el.bteliminar.equals((JButton) e.getSource())) {
                Operaciones op = new Operaciones();
                op.eliminar(Integer.parseInt(el.tfidprod.getText()));
                limpiar();
                InformDlg dlg = new InformDlg("INFO - ELIMINACION", 350, 300,"Se ha realizado la eliminación de forma correcta.");
            } else if (el.btclean.equals((JButton) e.getSource())) {
                limpiar();
            } else if (el.btdescatalogar.equals((JButton) e.getSource())) {
                Operaciones op = new Operaciones();
                op.descatalogar(Integer.parseInt(el.tfidprod.getText()));
                limpiar();
            }
        } catch (Exception ex) {
            if(!el.tfisbn.getText().equals("") && el.tfisbn.getText().length()==15){
                InformDlg dlg = new InformDlg("ERROR - ISBN", 350, 300, "Error de formato: isbn solo numeros");
            }else if (!el.tfidprod.getText().equals("")){
                InformDlg dlg = new InformDlg("ERROR - ID PRODUCTO", 350, 300, "Error de formato: id producto solo numeros");
            }
            System.out.println("EXCEPTON NUMERO MAL FORMADO");
            el.tfidprod.setText("");
            el.tfisbn.setText("");
            el.tfisbn.setEnabled(true);
            el.tfidprod.setEnabled(true);
            el.btbuscar.setEnabled(true);
            el.btclean.setEnabled(false);
            el.bteliminar.setEnabled(false);
            el.btdescatalogar.setEnabled(false);
        }
    }

    //Nueva Busqueda
    public void limpiar() {
        try {
            CheckDatos c = new CheckDatos();
            c.valoresDefecto(el.pneditorial, true);
            c.valoresDefecto(el.pntitulo, true);
            c.valoresDefecto(el.pnautor, true);
            c.valoresDefecto(el.pnpaginas, true);
            c.valoresDefecto(el.pnprecio, true);
            c.valoresDefecto(el.pnstock, true);
            c.valoresDefecto(el.pnaño, true);
            el.tfisbn.setText("");
            el.tfidprod.setText("");
            c.valoresDefectoArea(el.tadesc, true);
            el.mcmbLengua.setSelectedItem("es");
            el.mcmbSeccion.setSelectedItem("prog");
            String path = new File(".").getCanonicalPath() + "\\src\\images\\sinportada.jpg";
            el.pnimagen.cargarImagen(new File(path));
            el.tfisbn.setEnabled(true);
            el.tfidprod.setEnabled(true);
            el.btbuscar.setEnabled(true);
            el.btclean.setEnabled(false);
            el.bteliminar.setEnabled(false);
            el.btdescatalogar.setEnabled(false);
            el.tfidprod.setText("");
            el.tfisbn.setText("");
        } catch (IOException ex) {
            System.out.println("Probleams al cargar la imagen");
        }

    }

    public boolean realizarFind(Integer idprod, Long isbn) {
        Operaciones op = new Operaciones();
        Producto p = null;
        boolean devuelve = false;
        try {
            if (idprod != null) {
                p = op.getProducto(idprod);
            } else if (isbn != null) {
                p = op.getProducto(String.valueOf(isbn));
            }
            if (p != null) {

                el.pntitulo.tfcampo.setText(p.getTitulo());
                el.pnautor.tfcampo.setText(p.getAutor());
                el.pneditorial.tfcampo.setText(p.getEditorial());
                el.pnaño.tfcampo.setText(p.getAño());
                el.pnprecio.tfcampo.setText(String.valueOf(p.getPrecio()));
                el.pnpaginas.tfcampo.setText(String.valueOf(p.getPaginas()));
                el.pnstock.tfcampo.setText(String.valueOf(p.getStock()));
                el.tadesc.arcampo.setText(p.getResumen() + p.getDescripcion());


                String path = new File(".").getCanonicalPath() + "\\src\\images\\otro.jpg";
                byte[] foto = p.getImagen();
                BufferedImage bfi = null;
                if (foto != null) {
                    InputStream in = new ByteArrayInputStream(foto);

                    bfi = ImageIO.read(in);
                    ImageIO.write(bfi, "jpg", new File(path));
                    el.pnimagen.cargarImagen(new File(path));


                } else {
                    path = new File(".").getCanonicalPath() + "\\src\\images\\sinportada.jpg";
                    el.pnimagen.cargarImagen(new File(path));
                }

                int idio = p.getLengua().getIdidioma();
                el.mcmbLengua.setSelectedItem(op.getIdioma(idio).getIdioma());
                el.tfisbn.setText(String.valueOf(p.getIsbn()));
                el.tfidprod.setText(String.valueOf(p.getIdproducto()));
                devuelve = true;
            }
        } catch (IOException ex) {
            System.out.println("Probleams al cargar la imagen");
        } finally {
            System.out.println(devuelve);
            return devuelve;
        }


    }
}

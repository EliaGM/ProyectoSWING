/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import componentes.CheckDatos;
import entidades.Idioma;
import entidades.Producto;
import excepciones.IsbnException;
import interfaz.InformDlg;
import interfaz.Modificacion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import modulo.Operaciones;

public class ListenerMod implements ActionListener {

    Modificacion mod;
    private Operaciones op = new Operaciones();

    public ListenerMod(Modificacion mod) {
        this.mod = mod;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String path = "";
        try {
            path = new File(".").getCanonicalPath() + "\\src\\images\\sinportada.jpg";
            if (mod.btopen.equals((JButton) e.getSource())) {
                int returnVal = mod.fc.showOpenDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = mod.fc.getSelectedFile();
                    if (file.length() >= 65535) {
                        mod.pnimagen.cargarImagen(new File(path));
                    } else {
                        mod.pnimagen.cargarImagen(file);
                    }
                    mod.btguardar.setEnabled(true);
                    mod.btcancelar.setEnabled(true);
                }
            } else if (mod.btguardar.equals((JButton) e.getSource())) {
                try {
                    boolean errores = false;
                    CheckDatos c = new CheckDatos();
                    ArrayList<Boolean> listaerrores = new ArrayList<Boolean>();
                    listaerrores.add(c.checkTexto(45, mod.pntitulo, 0, "Titulo (*)", true));
                    listaerrores.add(c.checkTexto(45, mod.pnautor, 0, "Autor (*)", true));
                    listaerrores.add(c.checkTexto(45, mod.pneditorial, 0, "Editorial (*)", true));
                    listaerrores.add(c.checkTexto(-2, mod.pnprecio, 0, "Precio (*)", true));
                    listaerrores.add(c.checkTexto(-1, mod.pnstock, 0, "Stock (*)", true));
                    listaerrores.add(c.checkTexto(-1, mod.pnpaginas, 0, "NºPaginas (*)", true));
                    listaerrores.add(c.checkTexto(-1, mod.pnaño, 4, "Año (*)", true));
                    listaerrores.add(c.CheckArea(1000, "Descripcion (*)", true, mod.tadesc));

                    for (Boolean b : listaerrores) {
                        if (b) {
                            errores = true;
                            break;
                        } else {
                            errores = false;
                        }
                    }
                    if (errores == false) {
                        op.modificar(NuevoProducto());
                        habilitar(false, false, true);

                    }
                } catch (Exception ex) {
                    System.out.println("Error en la modificacion del producto");
                }

            } else if (mod.btclean.equals((JButton) e.getSource())) {
                limpiar();
                habilitar(false, false, false);

            } else if (mod.btbuscar.equals((JButton) e.getSource())) {
                habilitar(true, false, false);
                if (!mod.tfisbn.getText().equals("")) {
                    if (mod.tfisbn.getText().length() == 13) {
                        long isb = Long.parseLong(mod.tfisbn.getText());
                        if (realizarFind(null, isb)) {
                            System.out.println("REALIZA FIND");
                            habilitar(true, false, false);
                        } else {
                            System.out.println("NO REALIZA FIND");
                            limpiar();
                            habilitar(false, false, false);
                        }

                    } else {
                        throw new IsbnException("El tamaño del ISBN debe ser 15 numeros");
                    }
                } else if (!mod.tfidprod.getText().equals("")) {
                    int idprod = Integer.parseInt(mod.tfidprod.getText());
                    if (realizarFind(idprod, null)) {
                        System.out.println("REALIZA FIND");
                        habilitar(true, false, false);
                    } else {
                        System.out.println("NO REALIZA FIND");
                        limpiar();
                        habilitar(false, false, false);
                    }

                } else {
                    habilitar(false, false, false);
                    mod.tfidprod.setText("");
                    mod.tfisbn.setText("");
                }


            } else if (mod.btcancelar.equals((JButton) e.getSource())) {
                realizarFind(Integer.parseInt(mod.tfidprod.getText()), null);
                habilitar(true, false, false);
                mod.btguardar.setEnabled(false);
            }
        } catch (Exception ex) {
            if (!mod.tfisbn.getText().equals("") && mod.tfisbn.getText().length() == 15) {
                InformDlg dlg = new InformDlg("ERROR - ISBN", 350, 300, "Error de formato: isbn solo numeros");
            } else if (!mod.tfidprod.getText().equals("")) {
                InformDlg dlg = new InformDlg("ERROR - ID PRODUCTO", 350, 300, "Error de formato: id producto solo numeros");
            }
            System.out.println("EXCEPTON NUMERO MAL FORMADO");
            limpiar();
            habilitar(false, false, false);
        }
    }

    //Realizamos la busqueda
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
                mod.pntitulo.tfcampo.setText(p.getTitulo());
                mod.pnautor.tfcampo.setText(p.getAutor());
                mod.pneditorial.tfcampo.setText(p.getEditorial());
                mod.pnaño.tfcampo.setText(p.getAño());
                mod.pnprecio.tfcampo.setText(String.valueOf(p.getPrecio()));
                mod.pnpaginas.tfcampo.setText(String.valueOf(p.getPaginas()));
                mod.pnstock.tfcampo.setText(String.valueOf(p.getStock()));
                mod.tadesc.arcampo.setText(p.getResumen() + p.getDescripcion());
                mod.seccion = p.getSeccion();


                String path = new File(".").getCanonicalPath() + "\\src\\images\\otro.jpg";
                byte[] foto = p.getImagen();
                BufferedImage bfi = null;
                if (foto != null) {
                    InputStream in = new ByteArrayInputStream(foto);

                    bfi = ImageIO.read(in);
                    ImageIO.write(bfi, "jpg", new File(path));
                    mod.pnimagen.cargarImagen(new File(path));


                } else {
                    path = new File(".").getCanonicalPath() + "\\src\\images\\sinportada.jpg";
                    mod.pnimagen.cargarImagen(new File(path));
                }


                int idio = p.getLengua().getIdidioma();
                mod.mcmbLengua.setSelectedItem(op.getIdioma(idio).getIdioma());
                mod.tfisbn.setText(String.valueOf(p.getIsbn()));
                mod.tfidprod.setText(String.valueOf(p.getIdproducto()));
                devuelve = true;
            }
        } catch (IOException ex) {
            System.out.println("Probleams al cargar la imagen");
        } finally {
            return devuelve;
        }

    }

    //Control de los botones y textfields.
    public void habilitar(boolean habilitado, boolean botones, boolean modoguardar) {
        mod.btbuscar.setEnabled(modoguardar ? habilitado : !habilitado);
        mod.tfidprod.setEnabled(modoguardar ? habilitado : !habilitado);
        mod.tfisbn.setEnabled(modoguardar ? habilitado : !habilitado);
        mod.btguardar.setEnabled(botones);
        mod.btcancelar.setEnabled(botones);
        mod.btclean.setEnabled(modoguardar ? !habilitado : habilitado);
        mod.btopen.setEnabled(habilitado);
        mod.pntitulo.tfcampo.setEnabled(habilitado);
        mod.pnautor.tfcampo.setEnabled(habilitado);
        mod.pneditorial.tfcampo.setEnabled(habilitado);
        mod.pnprecio.tfcampo.setEnabled(habilitado);
        mod.pnstock.tfcampo.setEnabled(habilitado);
        mod.pnpaginas.tfcampo.setEnabled(habilitado);
        mod.pnaño.tfcampo.setEnabled(habilitado);
        mod.tadesc.arcampo.setEnabled(habilitado);
        mod.cbidioma.cbcampo.setEnabled(habilitado);
    }

    //Nueva Busqueda
    public void limpiar() {
        try {
            CheckDatos c = new CheckDatos();
            c.valoresDefecto(mod.pneditorial, true);
            c.valoresDefecto(mod.pntitulo, true);
            c.valoresDefecto(mod.pnautor, true);
            c.valoresDefecto(mod.pnpaginas, true);
            c.valoresDefecto(mod.pnprecio, true);
            c.valoresDefecto(mod.pnstock, true);
            c.valoresDefecto(mod.pnaño, true);
            mod.tfisbn.setText("");
            mod.tfidprod.setText("");
            c.valoresDefectoArea(mod.tadesc, true);
            mod.mcmbLengua.setSelectedItem("es");
            String path = new File(".").getCanonicalPath() + "\\src\\images\\sinportada.jpg";
            mod.pnimagen.cargarImagen(new File(path));
        } catch (IOException ex) {
            System.out.println("Probleams al cargar la imagen");
        }

    }

    public Producto NuevoProducto() {
        Producto p = new Producto();
        try {
            String titulo = mod.pntitulo.tfcampo.getText();
            String autor = mod.pnautor.tfcampo.getText();
            String editorial = mod.pneditorial.tfcampo.getText();
            String resumentodo = mod.tadesc.arcampo.getText();
            String resumen = resumentodo.substring(0, resumentodo.length() > 400 ? 400 : resumentodo.length());
            String año = mod.pnaño.tfcampo.getText();
            String descripcion = null;
            if (resumentodo.length() > 400) {
                descripcion = resumentodo.substring(400, resumentodo.length());
            }

            int stock = Integer.valueOf(mod.pnstock.tfcampo.getText());
            float precio = Float.valueOf(mod.pnprecio.tfcampo.getText());
            Integer paginas = null;
            if (!mod.pnpaginas.tfcampo.getText().equals("")) {
                paginas = Integer.valueOf(mod.pnpaginas.tfcampo.getText());
            }
            p.setIdproducto(Integer.parseInt(mod.tfidprod.getText()));
            p.setIsbn(mod.tfisbn.getText());
            p.setTitulo(titulo);
            p.setAutor(autor);
            p.setEditorial(editorial);
            p.setStock(stock);
            p.setPrecio(precio);
            p.setResumen(resumen);
            p.setAño(año);
            p.setDescatalogado(0);
            p.setDescripcion(descripcion);
            p.setPaginas(paginas);
            p.setDescatalogado(0);
            p.setSeccion(mod.seccion);
            int ididioma = mod.mcmbLengua.getIndexOf(mod.mcmbLengua.getSelectedItem());
            Idioma idioma = op.getIdioma(++ididioma);
            p.setLengua(idioma);
            byte[] image = null;
            if (mod.pnimagen.image != null) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(mod.pnimagen.image, "jpg", baos);
                baos.flush();
                image = baos.toByteArray();
                baos.close();
            }
            p.setImagen(image);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return p;
        }
    }
}

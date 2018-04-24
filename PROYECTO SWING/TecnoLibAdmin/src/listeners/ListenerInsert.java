/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import componentes.CheckDatos;
import entidades.Idioma;
import entidades.Producto;
import entidades.Seccion;
import excepciones.RepetidoException;
import interfaz.Insercion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import modulo.Operaciones;

public class ListenerInsert implements ActionListener {

    Insercion in;
    private Operaciones op = new Operaciones();

    public ListenerInsert(Insercion in) {
        this.in = in;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String path = "";
        try {
            path = new File(".").getCanonicalPath() + "\\src\\images\\sinportada.jpg";
            if (in.btopen.equals((JButton) e.getSource())) {
                int returnVal = in.fc.showOpenDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = in.fc.getSelectedFile();
                    if (file.length() >= 65535) {
                        in.pnimagen.cargarImagen(new File(path));
                    } else {
                        in.pnimagen.cargarImagen(file);
                    }
                } 
            } else if (in.btguardar.equals((JButton) e.getSource())) {
                try {
                    boolean errores = false;
                    CheckDatos c = new CheckDatos();
                    ArrayList<Boolean> listaerrores = new ArrayList<Boolean>();
                    listaerrores.add(c.checkTexto(60, in.pntitulo, 0, "Titulo (*)", true));
                    listaerrores.add(c.checkTexto(-3, in.pnisbn, 13, "ISBN (*)", true));
                    listaerrores.add(c.checkTexto(80, in.pnautor, 0, "Autor (*)", true));
                    listaerrores.add(c.checkTexto(45, in.pneditorial, 0, "Editorial (*)", true));
                    listaerrores.add(c.checkTexto(-2, in.pnprecio, 0, "Precio (*)", true));
                    listaerrores.add(c.checkTexto(-1, in.pnstock, 0, "Stock (*)", true));
                    listaerrores.add(c.checkTexto(-1, in.pnpaginas, 0, "NºPaginas (*)", true));
                    listaerrores.add(c.checkTexto(-1, in.pnaño, 4, "Año (*)", true));
                    listaerrores.add(c.CheckArea(1000, "Descripcion (*)", true, in.tadesc));
                    for (Boolean b : listaerrores) {
                        if (b) {
                            errores = true;
                            break;
                        } else {
                            errores = false;
                        }
                    }
                    
                    if (errores == false) {
                        String titulo = in.pntitulo.tfcampo.getText();
                        String autor = in.pnautor.tfcampo.getText();
                        String isbn = in.pnisbn.tfcampo.getText();
                        String editorial = in.pneditorial.tfcampo.getText();
                        String resumentodo = in.tadesc.arcampo.getText();
                        String resumen = resumentodo.substring(0, resumentodo.length() > 400 ? 400 : resumentodo.length());
                        String año = in.pnaño.tfcampo.getText();
                        String descripcion=null;
                        if(resumentodo.length()>400){
                            descripcion = resumentodo.substring(400,resumentodo.length());
                        }
                        
                        int stock = Integer.valueOf(in.pnstock.tfcampo.getText());
                        float precio = Float.valueOf(in.pnprecio.tfcampo.getText());
                        Integer paginas=null;
                        if(!in.pnpaginas.tfcampo.getText().equals("")){
                            paginas = Integer.valueOf(in.pnpaginas.tfcampo.getText());
                        }
                        
                        
                        Producto p = new Producto();
                        p.setTitulo(titulo);
                        p.setAutor(autor);
                        p.setIsbn(isbn);
                        p.setEditorial(editorial);
                        p.setStock(stock);
                        p.setPrecio(precio);
                        p.setResumen(resumen);
                        p.setAño(año);
                        p.setDescatalogado(0);
                        p.setDescripcion(descripcion);
                        p.setPaginas(paginas);


                        int idseccion = in.mcmbSeccion.getIndexOf(in.mcmbSeccion.getSelectedItem());
                        Seccion seccion = op.getSeccion(++idseccion);
                        int ididioma = in.mcmbLengua.getIndexOf(in.mcmbLengua.getSelectedItem());
                        Idioma idioma = op.getIdioma(++ididioma);
                        p.setSeccion(seccion);
                        p.setLengua(idioma);
                        byte[] image = null;
                        if (in.pnimagen.image != null) {
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            ImageIO.write(in.pnimagen.image, "jpg", baos);
                            baos.flush();
                            image = baos.toByteArray();
                            baos.close();
                        }
                        p.setImagen(image);
                        if (op.addProducto(p) == 0) {
                            throw new RepetidoException("El ISBN ya existe. Debe ser unico");
                        }else{
                            limpiar();
                        }
                    }
                }catch (Exception ex) {
                    System.out.println("Error en la insercion del producto");
                }

            } else if (in.btcancelar.equals((JButton) e.getSource())) {
                limpiar();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            in.pnimagen.cargarImagen(new File(path));
            System.out.println(ex.getMessage());
        }
    }

    

    public void limpiar() {
     try{
        CheckDatos c = new CheckDatos();
        c.valoresDefecto(in.pneditorial,true);
        c.valoresDefecto(in.pntitulo,true);
        c.valoresDefecto(in.pnautor,true);
        c.valoresDefecto(in.pnisbn,true);
        
        c.valoresDefecto(in.pnpaginas,true);
        c.valoresDefecto(in.pnprecio,true);
        c.valoresDefecto(in.pnstock,true);
        c.valoresDefecto(in.pnaño,true);
        
        c.valoresDefectoArea(in.tadesc, true);
        in.mcmbSeccion.setSelectedItem("Programacion");
        in.mcmbLengua.setSelectedItem("es");
        String path = new File(".").getCanonicalPath() + "\\src\\images\\sinportada.jpg";
        in.pnimagen.cargarImagen(new File(path));
     }catch(Exception ex){
         System.out.println(ex.getMessage());
     }

    }

    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes;

import java.awt.Color;
import java.awt.Font;
import java.util.Calendar;

public class CheckDatos {

    public CheckDatos() {
    }

    public boolean checkTexto(int max, PnTexto pn, int min, String etiqueta, boolean requerido) {

        StringBuilder mensaje = null;
        boolean errores = false;
        try {
            if (requerido) {
                if (pn.tfcampo.getText().equals("")) {
                    errores = true;
                    mensaje = new StringBuilder("<html><b>" + etiqueta + " : <span style='font-size:8px;color:red;font-style:italic'>Campo requerido</span></b></html>");
                } else {
                    if (max == -3) {
                        Long.valueOf(pn.tfcampo.getText());

                        if (pn.tfcampo.getText().length() != min) {
                            errores = true;
                            mensaje = new StringBuilder("<html><b>" + etiqueta + " : <span style='font-size:8px;color:red;font-style:italic'>Longitud de tamaño " + min + "</span></b></html>");

                        }
                    } else if (max == -4) {
                        if (!pn.tfcampo.getText().equals("")) {
                            int entero = Integer.valueOf(pn.tfcampo.getText());
                            Calendar año = Calendar.getInstance();
                            Calendar maximo = Calendar.getInstance();
                            Calendar minimo = Calendar.getInstance();
                            minimo.set(Calendar.YEAR, 1950);
                            año.set(Calendar.YEAR, entero);
                            if (pn.tfcampo.getText().length() != min) {
                                errores = true;
                                mensaje = new StringBuilder("<html><b>" + etiqueta + " : <span style='font-size:8px;color:red;font-style:italic'>Longitud de tamaño " + min + "</span></b></html>");
                            } else if (año.after(maximo) || año.before(minimo)) {
                                errores = true;
                                mensaje = new StringBuilder("<html><b>" + etiqueta + " : <span style='font-size:8px;color:red;font-style:italic'>Año entre 1950 y " + maximo.get(Calendar.YEAR) + "</span></b></html>");
                            }
                        }
                    } else if (max == -1) {
                        Integer.valueOf(pn.tfcampo.getText());
                    } else if (max == -2) {
                        Float.valueOf(pn.tfcampo.getText());

                    } else if (pn.tfcampo.getText().length() > max && max > 0) {
                        errores = true;
                        mensaje = new StringBuilder("<html><b>" + etiqueta + " : <span style='font-size:8px;color:red;font-style:italic'>Tamaño maximo " + max + "</span></b></html>");
                    }
                }
            }

        } catch (NumberFormatException ex) {
            errores = true;
            mensaje = new StringBuilder("<html><b>" + etiqueta + " : <span style='font-size:8px;color:red;font-style:italic'>Solo numeros</span></b></html>");

        } finally {
            if (errores) {
                pn.tfcampo.setBackground(Color.red);
                pn.tfcampo.setForeground(Color.white);
                pn.tfcampo.setFont(new Font("Arial", Font.BOLD, 13));
                pn.lbcampo.setText(mensaje.toString());
            }
        }
        return errores;
    }

    public boolean CheckArea(int max, String etiqueta, boolean requerido, PnTexto pn) {
        boolean errores = false;
        String mensaje = "";
        if (pn.arcampo.getText().equals("")) {
            errores = true;
            mensaje = "<html><b>" + etiqueta + " : <span style='font-size:8px;color:red;font-style:italic'>Campo requerido</span></b></html>";

        } else {
            if (pn.arcampo.getText().length() > max) {
                errores = true;
                mensaje = "<html><b>" + etiqueta + " : <span style='font-size:8px;color:red;font-style:italic'>Tamaño maximo " + max + "</span></b></html>";

            }
        }
        if (errores) {
            pn.arcampo.setBackground(Color.red);
            pn.arcampo.setForeground(Color.white);
            pn.arcampo.setFont(new Font("Arial", Font.BOLD, 13));
            pn.lbcampo.setText(mensaje);
        }
        return errores;

    }

    public void valoresDefecto(PnTexto pn, boolean anull) {


        if (anull) {
            pn.tfcampo.setText("");
        }
        pn.tfcampo.setBackground(Color.white);
        pn.tfcampo.setForeground(Color.black);
        pn.tfcampo.setFont(new Font("Arial", Font.PLAIN, 13));
        if (pn.lbcampo.getText().indexOf(":") != -1) {
            pn.lbcampo.setText(pn.lbcampo.getText().substring(0, pn.lbcampo.getText().indexOf(":")));
        }
    }

    public void valoresDefectoArea(PnTexto pn, boolean anull) {

        if (anull) {
            pn.arcampo.setText("");
        }
        pn.arcampo.setBackground(Color.white);
        pn.arcampo.setForeground(Color.black);
        pn.arcampo.setFont(new Font("Arial", Font.PLAIN, 13));
        if (pn.lbcampo.getText().indexOf(":") != -1) {
            pn.lbcampo.setText(pn.lbcampo.getText().substring(0, pn.lbcampo.getText().indexOf(":")));
        }

    }
}

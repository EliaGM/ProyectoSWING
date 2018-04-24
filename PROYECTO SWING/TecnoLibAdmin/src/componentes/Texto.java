/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes;


public class Texto {
    private String inicio="<html><b>";
    private String fin="</b></html>";
    private String salto="<br/>";
    private String negro1 ="<span style='color:black;font-size:25px;'>";
    private String negro2 ="<span style='color:black;font-size:15px;'>";
    private String gris ="<span style='color:gray;font-size:15px;'>";
    private String vacio ="<span style='color:'#dddddd';font-size:15px;'>";
    private String spanfin = "</span>";
    

    public Texto() {
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public String getSalto() {
        return salto;
    }

    public void setSalto(String salto) {
        this.salto = salto;
    }

    public String getNegro1() {
        return negro1;
    }

    public void setNegro1(String negro1) {
        this.negro1 = negro1;
    }

    public String getNegro2() {
        return negro2;
    }

    public void setNegro2(String negro2) {
        this.negro2 = negro2;
    }

    public String getGris() {
        return gris;
    }

    public void setGris(String gris) {
        this.gris = gris;
    }

    public String getVacio() {
        return vacio;
    }

    public void setVacio(String vacio) {
        this.vacio = vacio;
    }

    public String getSpanfin() {
        return spanfin;
    }

    public void setSpanfin(String spanfin) {
        this.spanfin = spanfin;
    }
    
    
    
}

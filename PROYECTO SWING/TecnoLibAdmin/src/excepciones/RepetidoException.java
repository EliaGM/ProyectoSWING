package excepciones;

import interfaz.InformDlg;


public class RepetidoException extends Exception{
    public RepetidoException(String str){
        super(str);
        InformDlg dlg = new InformDlg("ERROR - ISBN", 350, 300, str);
    }
}

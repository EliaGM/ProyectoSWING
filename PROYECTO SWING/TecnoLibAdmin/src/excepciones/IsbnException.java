package excepciones;

import interfaz.InformDlg;


public class IsbnException extends Exception{
    public IsbnException(String str){
        super(str);
        InformDlg dlg = new InformDlg("ERROR - ISBN", 350, 300, str);
    }
}

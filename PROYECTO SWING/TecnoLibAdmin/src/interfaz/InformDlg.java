package interfaz;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;



public class InformDlg extends JDialog implements IScreen{
    
    private int x, y;

    public InformDlg(String title, int w, int h, String info1) {
        
        super(new JFrame(),title, true);
        this.setBounds(getXScreen(w), getYScreen(h), 350, 150);
        this.setLayout(new BorderLayout(0,20));
        this.getContentPane().setBackground(new Color(224,224,224));
        
        JLabel txtInfo = new JLabel(info1, JLabel.CENTER);
        this.getContentPane().add(txtInfo,BorderLayout.CENTER);
        
        this.addWindowListener(new WCerrar());
        
        this.setVisible(true);
    }
    @Override
    public final int getXScreen(int w) {
        this.x = (int)width/2 - w/2;
        return this.x;
    }

    @Override
    public final int getYScreen(int h) {
        this.y = (int)height/2 - h/2;
        return this.y;
    }

}
class BtnCerrar implements ActionListener{
    
    InformDlg dlg;
    
    public BtnCerrar(InformDlg dlg){
        this.dlg = dlg;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Frame f = (Frame)dlg.getParent();
        f.dispose();
    }
    
}

class WCerrar extends WindowAdapter{
    
    
    @Override
    public void windowClosing(WindowEvent e) {
        JDialog d = (JDialog)e.getSource();
        
        JFrame f = (JFrame)d.getParent();
        f.dispose();
    }

    
    
}


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.*;
import javax.swing.border.BevelBorder;

public class PnTexto extends JPanel{
    
    public JTextField tfcampo;
    public JLabel lbcampo;
    public DefaultComboBoxModel mcmb;
    public JComboBox cbcampo;
    public JTextArea arcampo;
    

    public PnTexto(){
        this.setOpaque(true);
        this.setLayout(null);
        this.setBackground(new Color(230,230,230));
        
    }
    public PnTexto(String lb, int x, int y, int w, int h) {
        this();
        lbcampo = new JLabel(lb);
        lbcampo.setBounds(0, 0, 170, 15);
        tfcampo = new JTextField();
        tfcampo.setBounds(0, 15, w, 25);
        this.add(lbcampo);
        this.add(tfcampo);
        
        
    }
    public PnTexto(String lb, int x, int y, int w, int h,DefaultComboBoxModel mcmb) {
        this();
        this.mcmb = mcmb;
        lbcampo = new JLabel(lb);
        lbcampo.setBounds(0, 0, 170, 15);
        cbcampo = new JComboBox(mcmb);
        cbcampo.setBounds(0, 15, w, 25);
        
        this.add(lbcampo);
        this.add(cbcampo);
        
    }
    public PnTexto(String lb, int x, int y, int w, int h, int rows, int cols) {
        this();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(200,100));
        lbcampo = new JLabel(lb);
        lbcampo.setBounds(0, 0, 170, 15);
        arcampo = new JTextArea("",10, 1);
        arcampo.setBounds(0, 15, 100, 100);
        arcampo.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.lightGray, Color.darkGray));
        JScrollPane jp = new JScrollPane(arcampo);
        jp.setPreferredSize(new Dimension(100,100));
        jp.setOpaque(true);
        this.add(lbcampo);
        this.add(jp);
        
        
    }

    
    
    
    
    
}

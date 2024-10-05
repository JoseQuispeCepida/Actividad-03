package Modelo;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Credito extends Venta{
    private int x;
    private String letras;
    
    public Credito(){
    }

    public Credito(int x, String letras) {
        this.x = x;
        this.letras = letras;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public String getLetras() {
        return letras;
    }

    public void setLetras(String letras) {
        this.letras = letras;
    }
    
    public double sumatoriaSubTotal(JTable jTableCredito, JLabel lblNeto) {
        double totalSubtotales = 0.0;
        for (int i = 0; i < jTableCredito.getRowCount(); i++) {
            double subtotal = (double) jTableCredito.getValueAt(i, 4); 
            totalSubtotales += subtotal;
        }
        lblNeto.setText("$" + String.format("%.2f", totalSubtotales));
        return totalSubtotales;
    }
    
}

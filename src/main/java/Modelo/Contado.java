package Modelo;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Contado extends Venta{
    
    private int n;
    
    public Contado(int cantidad, String cliente, String fecha, String hora, String producto, String ruc, int n) {
        super(cantidad, cliente, fecha, hora, producto, ruc); 
        this.n = n;
    }

    public Contado() {       
    }
    
    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }
    
    
    public double sumatoriaSubTotal(JTable jTableContado) {
        double totalSubtotal = 0.0;
        DefaultTableModel modeloTabla = (DefaultTableModel) jTableContado.getModel();
        // Recorrer las filas de la tabla
        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            double subtotal = (double) modeloTabla.getValueAt(i, 4);
            totalSubtotal += subtotal; 
        } 
        return totalSubtotal;
    }
    
    public double calculaDescuento(double subtotal) {
        double descuento = 0.0;
        if (subtotal < 1000.00) {
            descuento = subtotal * 0.05; // 5% de descuento
        } else if (subtotal < 3000.00) {
            descuento = subtotal * 0.08; // 8% de descuento
        } else {
            descuento = subtotal * 0.12; // 12% de descuento
        }
        return descuento;
    }
    
    public double calculaNeto(double subtotal, double descuento) {
        // Calcular el valor neto restando el descuento al subtotal
        double neto = subtotal - descuento;
        return neto;
    }  
}

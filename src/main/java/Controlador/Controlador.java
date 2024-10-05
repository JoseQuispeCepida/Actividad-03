package Controlador;

import Modelo.Contado;
import Modelo.Credito;
import Modelo.Venta;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

public class Controlador {

    public Controlador(){
    }
    
    Contado contado = new Contado();
    Credito credito = new Credito();
    
    private Timer timer; // Timer para actualizar la hora y la fecha
    private JLabel lblHora; // JLabel que muestra la hora
    private JLabel lblFecha; // JLabel que muestra la fecha

    // Constructor que recibe los JLabels
    public Controlador(JLabel lblHora, JLabel lblFecha) {
        this.lblHora = lblHora;
        this.lblFecha = lblFecha;
        inicializarTimer(); // Inicializa el Timer al crear el controlador
    }

    private void inicializarTimer() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarHoraYFecha();
            }
        });
        timer.start(); // Inicia el timer
    }

    private void actualizarHoraYFecha() {
        // Formato de la hora
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        String horaActual = formatoHora.format(new Date());
        lblHora.setText(horaActual); // Actualiza el JLabel con la hora actual

        // Formato de la fecha
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        String fechaActual = formatoFecha.format(new Date());
        lblFecha.setText(fechaActual); // Actualiza el JLabel con la fecha actual
    }
    
    public void adquirirContado(String producto, int cantidad, JTable jTableContado, String cliente, String ruc, JLabel lblHora1, JLabel lblFecha1, JTextArea txtAreaResumen, JLabel lblNeto) {
        Venta venta = new Venta();
        double precio = venta.asignaPrecio(producto);
        double subtotal = venta.calculaSubTotal(precio, cantidad);
        int item = jTableContado.getRowCount() + 1;
        DefaultTableModel modeloTabla = (DefaultTableModel) jTableContado.getModel();
        modeloTabla.addRow(new Object[]{item, producto, cantidad, precio, subtotal});
        double totalSubtotales = contado.sumatoriaSubTotal(jTableContado);
        double descuento = contado.calculaDescuento(totalSubtotales);
        double valorNeto = contado.calculaNeto(totalSubtotales, descuento);
        lblNeto.setText("$" + String.format("%.2f", valorNeto));
        String resumen = "** RESUMEN DE LA VENTA **\n" +
                     "------------------------------------\n" +
                     "CLIENTE: " + cliente + "\n" +
                     "RUC: " + ruc + "\n" +
                     "FECHA: " + lblFecha.getText() + "\n" +
                     "HORA: " + lblHora.getText() + "\n" +
                     "------------------------------------\n" +
                     "SUBTOTAL: " + "$"+totalSubtotales + "\n" +
                     "DESCUENTO: " + "$"+descuento + "\n" +
                     "NETO: " + "$"+valorNeto + "\n";
        txtAreaResumen.setText(resumen);
    }
    
    
    public void adquirirCredito(String producto, int cantidad, JTable jTableCredito, JLabel lblNeto) {
        Venta venta = new Venta();
        double precio = venta.asignaPrecio(producto);
        double subtotal = venta.calculaSubTotal(precio, cantidad);
        int item = jTableCredito.getRowCount() + 1;
        DefaultTableModel modeloTabla = (DefaultTableModel) jTableCredito.getModel();
        modeloTabla.addRow(new Object[]{item, producto, cantidad, precio, subtotal});
        credito.sumatoriaSubTotal(jTableCredito, lblNeto);
    }
    
    public void generarResumen(JTable jTableCredito, JTable tblResumen, JComboBox<String> comboBoxLetras, JLabel lblNeto) {
        int cantidadLetras = Integer.parseInt(comboBoxLetras.getSelectedItem().toString());
        double totalSubtotales = credito.sumatoriaSubTotal(jTableCredito, lblNeto);
        double montoMensual = totalSubtotales / cantidadLetras;
        DefaultTableModel modeloResumen = (DefaultTableModel) tblResumen.getModel();
        modeloResumen.setRowCount(0);
        for (int i = 1; i <= cantidadLetras; i++) {
            modeloResumen.addRow(new Object[]{i, montoMensual});
        }
    }  
}

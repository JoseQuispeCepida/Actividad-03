package Modelo;

public class Venta {
    private int cantidad;
    private String cliente;
    private String fecha;
    private String hora;
    private String producto;
    private String ruc;
    private double precio;
    
    public Venta(){
    }
    
    public Venta(int cantidad, String cliente, String fecha, String hora, String producto, String ruc) {
        this.cantidad = cantidad;
        this.cliente = cliente;
        this.fecha = fecha;
        this.hora = hora;
        this.producto = producto;
        this.ruc = ruc;
        this.precio = asignaPrecio(producto);
    }


    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    @Override
    public String toString() {
        return "Venta{" + "cantidad=" + cantidad + ", cliente=" + cliente + ", fecha=" + fecha + ", hora=" + hora + ", producto=" + producto + ", ruc=" + ruc + '}';
    }    
    
    public double asignaPrecio(String producto) {
        switch (producto) {
            case "Lavadora":
                return 1500.00;
            case "Refrigeradora":
                return 3500.00;
            case "Licuadora":
                return 500.00;
            case "Extractora":
                return 150.00;
            case "Radiograbadora":
                return 750.00;
            case "DVD":
                return 100.00;
            case "Blue Ray":
                return 250.00;
            default:
                return 0.0; // Precio por defecto si el producto no está en la lista
        }
    }
    
    public double getPrecio() {
        return this.precio; // Retornar el precio almacenado
    }

    public double calculaSubTotal(double precio, int cantidad) {
        return precio * cantidad;
    }
}
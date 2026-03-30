package cibertec.ecommerce.model;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Orden {
    private String codigo;
    private Cliente cliente;
    private List<Producto> productos;
    private Map<Long, Integer> cantidades;
    private Double total;
    private LocalDate fecha;
    private String estado;

    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public List<Producto> getProductos() {
        return productos;
    }
    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
    public Map<Long, Integer> getCantidades() {
        return cantidades;
    }
    public void setCantidades(Map<Long, Integer> cantidades) {
        this.cantidades = cantidades;
    }
    public Double getTotal() {
        return total;
    }
    public void setTotal(Double total) {
        this.total = total;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
}

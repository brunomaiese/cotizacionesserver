package db.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CasaCambiaria implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private Double dolarCompra;

    private Double dolarVenta;

    private Double euroCompra;

    private Double euroVenta;

    private Double argentinoCompra;

    private Double argentinoVenta;

    private Double realCompra;

    private Double realVenta;

    public CasaCambiaria(String nombre, Double dolarCompra, Double dolarVenta, Double euroCompra, Double euroVenta, Double argentinoCompra, Double argentinoVenta, Double realCompra, Double realVenta) {
        this.nombre = nombre;
        this.dolarCompra = dolarCompra;
        this.dolarVenta = dolarVenta;
        this.euroCompra = euroCompra;
        this.euroVenta = euroVenta;
        this.argentinoCompra = argentinoCompra;
        this.argentinoVenta = argentinoVenta;
        this.realCompra = realCompra;
        this.realVenta = realVenta;
    }

    public Double getDolarCompra() {
        return dolarCompra;
    }

    public void setDolarCompra(Double dolarCompra) {
        this.dolarCompra = dolarCompra;
    }

    public Double getDolarVenta() {
        return dolarVenta;
    }

    public void setDolarVenta(Double dolarVenta) { this.dolarVenta = dolarVenta; }

    public Double getEuroCompra() {
        return euroCompra;
    }

    public void setEuroCompra(Double euroCompra) {
        this.euroCompra = euroCompra;
    }

    public Double getEuroVenta() {
        return euroVenta;
    }

    public void setEuroVenta(Double euroVenta) {
        this.euroVenta = euroVenta;
    }

    public Double getArgentinoCompra() {
        return argentinoCompra;
    }

    public void setArgentinoCompra(Double argentinoCompra) {
        this.argentinoCompra = argentinoCompra;
    }

    public Double getArgentinoVenta() {
        return argentinoVenta;
    }

    public void setArgentinoVenta(Double argentinoVenta) {
        this.argentinoVenta = argentinoVenta;
    }

    public Double getRealCompra() {
        return realCompra;
    }

    public void setRealCompra(Double realCompra) {
        this.realCompra = realCompra;
    }

    public Double getRealVenta() {
        return realVenta;
    }

    public void setRealVenta(Double realVenta) {
        this.realVenta = realVenta;
    }

    @OneToMany(mappedBy = "casaCambiaria", cascade = CascadeType.ALL)
    private List<Direccion> direcciones = new ArrayList<Direccion>();

    public CasaCambiaria(){}

    public CasaCambiaria(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<Direccion> direcciones) {
        this.direcciones = direcciones;
    }
}

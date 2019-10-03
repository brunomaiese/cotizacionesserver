package db.entidades;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CasaCambiaria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private Double DolarCompra;

    private Double DolarVenta;

    private Double EuroCompra;

    private Double EuroVenta;

    private Double ArgentinoCompra;

    private Double ArgentinoVenta;

    private Double RealCompra;

    private Double RealVenta;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSelectorScrapping() {
        return selectorScrapping;
    }

    public void setSelectorScrapping(String selectorScrapping) {
        this.selectorScrapping = selectorScrapping;
    }

    private String url;

    private String selectorScrapping;

    public Double getDolarCompra() {
        return DolarCompra;
    }

    public void setDolarCompra(Double dolarCompra) {
        DolarCompra = dolarCompra;
    }

    public Double getDolarVenta() {
        return DolarVenta;
    }

    public void setDolarVenta(Double dolarVenta) {
        DolarVenta = dolarVenta;
    }

    public Double getEuroCompra() {
        return EuroCompra;
    }

    public void setEuroCompra(Double euroCompra) {
        EuroCompra = euroCompra;
    }

    public Double getEuroVenta() {
        return EuroVenta;
    }

    public void setEuroVenta(Double euroVenta) {
        EuroVenta = euroVenta;
    }

    public Double getArgentinoCompra() {
        return ArgentinoCompra;
    }

    public void setArgentinoCompra(Double argentinoCompra) {
        ArgentinoCompra = argentinoCompra;
    }

    public Double getArgentinoVenta() {
        return ArgentinoVenta;
    }

    public void setArgentinoVenta(Double argentinoVenta) {
        ArgentinoVenta = argentinoVenta;
    }

    public Double getRealCompra() {
        return RealCompra;
    }

    public void setRealCompra(Double realCompra) {
        RealCompra = realCompra;
    }

    public Double getRealVenta() {
        return RealVenta;
    }

    public void setRealVenta(Double realVenta) {
        RealVenta = realVenta;
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

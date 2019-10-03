package db.entidades;

import javax.persistence.*;
import java.awt.*;

@Entity
public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String direccion;

    private Double latitud;

    private Double longitud;

    @ManyToOne(fetch = FetchType.LAZY)
    private CasaCambiaria casaCambiaria;

    public Direccion(){}

    public Direccion(String nombre) {
        this.direccion = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccon) {
        this.direccion = direccion;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() { return longitud; }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public CasaCambiaria getCasaCambiaria() {
        return casaCambiaria;
    }

    public void setCasaCambiaria(CasaCambiaria casaCambiaria) {
        this.casaCambiaria = casaCambiaria;
    }
}

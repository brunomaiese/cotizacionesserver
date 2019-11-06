package dtypes;

import db.entidades.Direccion;

import java.io.Serializable;

public class RespuestaCotizaciones implements Serializable{

    private double distanciaSir;
    private double distanciaGales;
    private double distanciaIndumex;

    public double getDistanciaSir() {
        return distanciaSir;
    }

    public void setDistanciaSir(double distanciaSir) {
        this.distanciaSir = distanciaSir;
    }

    public double getDistanciaGales() {
        return distanciaGales;
    }

    public void setDistanciaGales(double distanciaGales) {
        this.distanciaGales = distanciaGales;
    }

    public double getDistanciaIndumex() {
        return distanciaIndumex;
    }

    public void setDistanciaIndumex(double distanciaIndumex) {
        this.distanciaIndumex = distanciaIndumex;
    }
}

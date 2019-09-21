package org.selecciondecampeones.baikap.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Lugar {

    private String id;
    private String titulo;
    private String descripción;
    private String detalle;
    private String tipo;
    private String link;
    private int estado;
    private double longitud;
    private double latitud;

    public Lugar(JSONObject event) throws JSONException {
        this.id = event.getString("id");
        this.titulo = event.getString("title");
        this.descripción = event.getString("description");

        this.detalle = event.getString("detail");
        this.tipo = event.getString("type");
        this.link = event.getString("link");

        this.estado = event.getInt("status");

        this.longitud = event.getDouble("longitude");
        this.latitud = event.getDouble("latitude");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }
}

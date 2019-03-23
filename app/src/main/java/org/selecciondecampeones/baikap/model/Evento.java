package org.selecciondecampeones.baikap.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Evento {

    private String id;
    private String nombre;
    private String organizador;
    private String lugar;
    private Date fechaInicio;
    private Date fechaFin;
    private int estado;
    private String tipo;

    public Evento(JSONObject event) throws JSONException {
        this.nombre = event.getString("name");
        this.organizador = event.getString("organizer");
        this.lugar = event.getString("location");
        String dateStart = event.getString("datestart");
        String dateFinish = event.getString("datefinish");

        SimpleDateFormat databaseFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            this.fechaInicio = databaseFormat.parse(dateStart);
            this.fechaFin = databaseFormat.parse(dateFinish);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getOrganizador() {
        return organizador;
    }

    public void setOrganizador(String organizador) {
        this.organizador = organizador;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}

package org.selecciondecampeones.baikap.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Entrenamiento {

    private String id;
    private String descripcion;
    private String tipo;
    private Date fecha;
    private int semana;
    private int estado;

    public Entrenamiento() {

    }

    public Entrenamiento(JSONObject event) throws JSONException {
        this.id = event.getString("id");
        this.descripcion = event.getString("organizer");
        this.tipo = event.getString("location");
        this.semana = event.getInt("semana");
        this.estado = event.getInt("estado");


        String dateStart = event.getString("datestart");
        String dateFinish = event.getString("datefinish");

        SimpleDateFormat databaseFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            this.fecha = databaseFormat.parse(dateStart);

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getSemana() {
        return semana;
    }

    public void setSemana(int semana) {
        this.semana = semana;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}

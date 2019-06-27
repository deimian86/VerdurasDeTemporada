package com.deimian86.verdurasdetemporada.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.Relation;
import androidx.room.TypeConverters;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Verdura {

    @PrimaryKey
    private int id;

    @ColumnInfo(name = "nombre")
    private String nombre;

    @ColumnInfo(name = "foto")
    private String foto;

    @ColumnInfo(name = "fondo")
    private String fondo;

    @Ignore
    private List<Integer> meses = new ArrayList<>();

    @Ignore
    private List<Integer> meses_menos = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public List<Integer> getMeses() {
        return meses;
    }

    public String getFondo() { return fondo; }

    public void setFondo(String fondo) { this.fondo = fondo; }

    public void setMeses(List<Integer> meses) {
        this.meses = meses;
    }

    public List<Integer> getMesesMenos() { return meses_menos; }

    public void setMesesMenos(List<Integer> meses_menos) { this.meses_menos = meses_menos; }

    @Override
    public String toString() {
        return "Verdura{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", foto='" + foto + '\'' +
                '}';
    }
}

package DB.entity;

import android.util.Log;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "actividad")
public class Actividad {

    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String nombre;
    @ForeignKey(entity = Corte.class, parentColumns = "id", childColumns = "id_corte")
    private Long id_corte;
    @ForeignKey(entity = Curso.class, parentColumns = "id", childColumns = "id_materia")
    private Long id_materia;
    private double porcentaje_corte;

    public Actividad() {
    }

    public Actividad(String nombre, Long id_corte, Long id_materia, double porcentaje_corte) {
        this.nombre = nombre;
        this.id_corte = id_corte;
        this.id_materia = id_materia;
        this.porcentaje_corte = porcentaje_corte;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getId_corte() {
        return id_corte;
    }

    public void setId_corte(Long id_corte) {
        this.id_corte = id_corte;
    }

    public Long getId_materia() {
        return id_materia;
    }

    public void setId_materia(Long id_materia) {
        this.id_materia = id_materia;
    }

    public double getPorcentaje_corte() {
        return porcentaje_corte;
    }

    public void setPorcentaje_corte(double porcentaje_corte) {
        this.porcentaje_corte = porcentaje_corte;
    }
}
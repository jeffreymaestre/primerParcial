package DB.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "calificacion")
public class Calificacion {

    @PrimaryKey(autoGenerate = true)
    private Long id;
    @ForeignKey(entity = Corte.class, parentColumns = "id", childColumns = "id_corte")
    private Long id_corte;
    @ForeignKey(entity = Curso.class, parentColumns = "id", childColumns = "id_materia")
    private Long id_materia;
    @ForeignKey(entity = Actividad.class, parentColumns = "id", childColumns = "id_actividad")
    private Long id_actividad;
    private double calificacion;

    public Calificacion(Long id_corte, Long id_materia, Long id_actividad, double calificacion) {
        this.id_corte = id_corte;
        this.id_materia = id_materia;
        this.id_actividad = id_actividad;
        this.calificacion = calificacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getId_actividad() {
        return id_actividad;
    }

    public void setId_actividad(Long id_actividad) {
        this.id_actividad = id_actividad;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }
}

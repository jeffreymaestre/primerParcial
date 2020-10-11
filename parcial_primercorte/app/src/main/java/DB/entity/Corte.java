package DB.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cortes")
public class Corte {

    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String titulo;
    private double porcentaje_definitivo;
    private double porcentaje_corte;


    public Corte() {
    }

    public Corte(String titulo, double porcentaje_definitivo, double porcentaje_corte) {
        this.titulo = titulo;
        this.porcentaje_definitivo = porcentaje_definitivo;
        this.porcentaje_corte = porcentaje_corte;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getPorcentaje_definitivo() {
        return porcentaje_definitivo;
    }

    public void setPorcentaje_definitivo(double porcentaje_definitivo) {
        this.porcentaje_definitivo = porcentaje_definitivo;
    }

    public double getPorcentaje_corte() {
        return porcentaje_corte;
    }

    public void setPorcentaje_corte(double porcentaje_corte) {
        this.porcentaje_corte = porcentaje_corte;
    }
}
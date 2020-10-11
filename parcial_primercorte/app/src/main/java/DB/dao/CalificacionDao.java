package DB.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import DB.entity.Actividad;
import DB.entity.Calificacion;

@Dao
public interface CalificacionDao {

    @Insert
    public void AgregarCalificacion(Calificacion calificacion);

    @Delete
    public void EliminarCalificacion(Calificacion calificacion);

    @Update
    public void EditarCalificacion(Calificacion calificacion);

    @Query("select * from calificacion")
    public List<Calificacion> ObtenerCalificaciones();

    @Query("select * from calificacion where id = :id")
    public Calificacion FindById(long id);

    @Query("select * from calificacion where id_materia = :id")
    public List<Calificacion> FindByMateria(long id);

    @Query("select * from calificacion where id_materia = :id_materia & id_corte = :id_corte & id_actividad = :id_actividad")
    public Calificacion findPorCorte(long id_materia, long id_corte, long id_actividad);
}


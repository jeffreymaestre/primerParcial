package DB.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import DB.entity.Actividad;
import DB.entity.Curso;

@Dao
public interface ActividadDao {

    @Insert
    public void AgregarActividad(Actividad actividad);

    @Delete
    public void EliminarActividad(Actividad actividad);

    @Update
    public void EditarActividad(Actividad actividad);

    @Query("select * from actividad")
    public List<Actividad> ObtenerActividades();

    @Query("select * from actividad where id = :id")
    public Actividad FindById(long id);

    @Query("select * from actividad where nombre = :nombre")
    public Actividad FindByNombre(String nombre);

    @Query("select * from actividad where id_materia = :id")
    public List<Actividad> FindByMateria(long id);

    @Query("select * from actividad where id_materia = :id_materia & id_corte = :id_corte")
    public List<Actividad> FindByCorteyMateria(long id_materia, long id_corte);
}


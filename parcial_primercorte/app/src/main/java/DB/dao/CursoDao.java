package DB.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import DB.entity.Curso;

@Dao
public interface CursoDao {

    @Insert
    public void AgregarCurso(Curso curso);

    @Delete
    public void EliminarCurso(Curso curso);

    @Update
    public void EditarCurso(Curso curso);

    @Query("select * from cursos")
    public List<Curso> ObtenerCursos();

    @Query("select * from cursos where id = :id")
    public Curso FindById(long id);

    @Query("select * from cursos where nombre = :nombre")
    public Curso FindByNombre(String nombre);
}


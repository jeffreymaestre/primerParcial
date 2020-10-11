package DB.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import DB.entity.Corte;
import DB.entity.Curso;

@Dao
public interface CorteDao {

    @Insert
    public void AgregarCorte(Corte corte);

    @Query("select * from cortes")
    public List<Corte> ObtenerCortes();

    @Query("select * from cortes where id = :id")
    public Corte FindById(long id);

    @Query("select * from cortes where titulo = :titulo")
    public Corte FindByNombre(String titulo);
}


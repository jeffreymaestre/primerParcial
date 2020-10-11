package DB;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import DB.dao.ActividadDao;
import DB.dao.CalificacionDao;
import DB.dao.CorteDao;
import DB.dao.CursoDao;
import DB.entity.Actividad;
import DB.entity.Calificacion;
import DB.entity.Corte;
import DB.entity.Curso;

@Database(entities = {Curso.class, Corte.class, Actividad.class, Calificacion.class}, version = 1)
public abstract class UpcDB extends RoomDatabase {

    public abstract CursoDao CursoDao();
    public abstract CorteDao CorteDao();
    public abstract ActividadDao ActividadDao();
    public abstract CalificacionDao CalificacionDao();
}

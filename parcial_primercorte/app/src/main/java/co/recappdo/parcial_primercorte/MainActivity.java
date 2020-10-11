package co.recappdo.parcial_primercorte;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Database;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import DB.UpcDB;
import DB.entity.Actividad;
import DB.entity.Corte;
import DB.entity.Curso;

public class MainActivity extends AppCompatActivity {

    //extraccion de recursos
    EditText EditTxnombreActividad,EditTxnombreMateria, EditTxPorcentaje;
    Spinner spinnerMaterias, spinnerCortes;

    //objetos y recursos base de datos
    UpcDB Database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditTxnombreMateria = findViewById(R.id.editTextNombreMateria);
        spinnerCortes = findViewById(R.id.spinnerCortes);
        spinnerMaterias = findViewById(R.id.spinnerMaterias);
        EditTxPorcentaje = findViewById(R.id.editTextPorcentaje);
        EditTxnombreActividad = findViewById(R.id.editTextTextNombreActividad);

        Database = Room.databaseBuilder(this, UpcDB.class,"sistema").allowMainThreadQueries().build();

        if (Database.CorteDao().ObtenerCortes() == null || Database.CorteDao().ObtenerCortes().isEmpty()){
            RegistroCortes();
        }

        llenarSpinnerCorte();
        llenarSpinnerMaterias();

    }

    public void RegistrarActividad(View view) {
        Curso curso = Database.CursoDao().FindByNombre(spinnerMaterias.getSelectedItem().toString());
        Corte corte = Database.CorteDao().FindByNombre(spinnerCortes.getSelectedItem().toString());

        List<Actividad> actividades = new ArrayList<>();
        actividades = Database.ActividadDao().FindByCorteyMateria(curso.getId(), corte.getId());

        if (Double.parseDouble(EditTxPorcentaje.getText().toString()) > 100 || Double.parseDouble(EditTxPorcentaje.getText().toString()) == 0){
            Toast.makeText(this, "el porcentaje debe estar entre 1 y 100", Toast.LENGTH_SHORT).show();
            return;
        }

        double porcentaje = (Double.parseDouble(EditTxPorcentaje.getText().toString()) / 100);
        String nombreActividad = EditTxnombreActividad.getText().toString();

        double ocupacionPorcentaje = 0;

        for (Actividad actividad: actividades) {
            ocupacionPorcentaje += actividad.getPorcentaje_corte();
        }

        if (ocupacionPorcentaje == 1){
            Toast.makeText(this, "el porcentaje del corte ya esta distribuido en su totalidad", Toast.LENGTH_SHORT).show();
        }

        if (porcentaje <= (1-ocupacionPorcentaje)){

            Actividad actividad = new Actividad(nombreActividad, corte.getId(), curso.getId(), porcentaje);

            Database.ActividadDao().AgregarActividad(actividad);

            Toast.makeText(this, "actividad registrada",Toast.LENGTH_SHORT).show();
        }

        if (porcentaje > (1-ocupacionPorcentaje) && (1-ocupacionPorcentaje) > 0){
            Toast.makeText(this, "el porcentaje disponible es: " + String.valueOf((1-ocupacionPorcentaje)*100)+"%", Toast.LENGTH_SHORT).show();
        }

    }

    public void RegistrarMateria(View view) {
        String nombreMateria = EditTxnombreMateria.getText().toString();

        for (Curso curso:Database.CursoDao().ObtenerCursos()) {
            if (nombreMateria.equals(curso.getNombre())){
                Toast.makeText(this, "el nombre de la materia debe ser unico", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        Curso curso = new Curso(nombreMateria);
        Database.CursoDao().AgregarCurso(curso);
        llenarSpinnerMaterias();
        Toast.makeText(this, "materia registrada",Toast.LENGTH_SHORT).show();

    }

    public void RegistroCortes(){
        Corte corte1 = new Corte("corte 1", 0.3, 1);
        Corte corte2 = new Corte("corte 2", 0.3, 1);
        Corte corte3 = new Corte("corte 3", 0.4, 1);

        Database.CorteDao().AgregarCorte(corte1);
        Database.CorteDao().AgregarCorte(corte2);
        Database.CorteDao().AgregarCorte(corte3);
    }

    private void llenarSpinnerCorte(){

        List<String> cortes = new ArrayList<>();

        for (Corte corte : Database.CorteDao().ObtenerCortes()) {
            cortes.add(corte.getTitulo());
        }

        if (cortes.isEmpty()){
            String[] OpcionesSpinnerCorte = {"Seleccionar corte"};
            ArrayAdapter<String> adaptadorSpinnerCorte = new ArrayAdapter<String>(this, R.layout.textview_spinner, OpcionesSpinnerCorte);
            spinnerCortes.setAdapter(adaptadorSpinnerCorte);
        }else {

            ArrayAdapter<String> adaptadorSpinner = new ArrayAdapter<String>(this, R.layout.textview_spinner, cortes);
            spinnerCortes.setAdapter(adaptadorSpinner);
        }
    }

    private void llenarSpinnerMaterias(){

        List<String> materias =  new ArrayList<>();

        for (Curso curso : Database.CursoDao().ObtenerCursos()) {
            materias.add(curso.getNombre());
        }

        if (materias.isEmpty()){
            String[] OpcionesSpinnerCurso = {"Seleccionar Curso"};
            ArrayAdapter<String> adaptadorSpinnerCurso = new ArrayAdapter<String>(this, R.layout.textview_spinner, OpcionesSpinnerCurso);
            spinnerMaterias.setAdapter(adaptadorSpinnerCurso);
        }else {
            ArrayAdapter<String> adaptadorSpinner = new ArrayAdapter<String>(this, R.layout.textview_spinner, materias);
            spinnerMaterias.setAdapter(adaptadorSpinner);
        }
    }

    public void ConsultarActividades(View view) {
        Intent consultarActividades = new Intent(MainActivity.this, consultar_actividades.class);
        startActivity(consultarActividades);
    }
}
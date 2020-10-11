package co.recappdo.parcial_primercorte;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import DB.UpcDB;
import DB.entity.Actividad;
import DB.entity.Calificacion;
import DB.entity.Corte;
import DB.entity.Curso;

public class consultar_actividades extends AppCompatActivity {

    Spinner spinnerMaterias, spinnerCortes;
    ListView listViewActividades;
    EditText porcentaje;
    UpcDB Database;

    List<Actividad> actividades = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_actividades);

        porcentaje = findViewById(R.id.editTextPorcentaje);
        spinnerMaterias = findViewById(R.id.spinnerMaterias);
        spinnerCortes = findViewById(R.id.spinnerCorte);
        listViewActividades = findViewById(R.id.ListViewActividades);

        Database = Room.databaseBuilder(this, UpcDB.class,"sistema").allowMainThreadQueries().build();
        llenarSpinnerMaterias();
        llenarSpinnerCorte();
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

    public void Consultar(View view) {
        Curso curso = Database.CursoDao().FindByNombre(spinnerMaterias.getSelectedItem().toString());
        Corte corte = Database.CorteDao().FindByNombre(spinnerCortes.getSelectedItem().toString());

        actividades = Database.ActividadDao().FindByCorteyMateria(curso.getId(), corte.getId());

        llenarListview(actividades);

    }

    public void llenarListview(List<Actividad> actividadList) {

        final ListAdapterActividades adapter = new ListAdapterActividades(this, actividadList);
        listViewActividades.setAdapter(adapter);

        listViewActividades.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                DialogResultConsultaActividad Dialogo = new DialogResultConsultaActividad();
                Dialogo.setearDatosExternosEstudiante((Actividad) listViewActividades.getItemAtPosition(position), Database);
                Dialogo.show(fragmentManager, "Confirmacion");
            }
        });


    }

    public void calcular(View view) {
        double definitiva = 0;


        for (Actividad actividad: actividades) {

            Calificacion calificacion  = Database.CalificacionDao().findPorCorte(actividad.getId_materia(), actividad.getId_corte(), actividad.getId());
            definitiva += calificacion.getCalificacion();
        }

        porcentaje.setText(String.valueOf(definitiva));

    }
}
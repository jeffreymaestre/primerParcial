package co.recappdo.parcial_primercorte;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import DB.UpcDB;
import DB.entity.Actividad;
import DB.entity.Calificacion;


public class DialogResultConsultaActividad extends DialogFragment {

    private Actividad actividad;
    private UpcDB upcDB;
    private EditText editTextNombre, editTextPorcentaje, editTextcalificacion;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.dialog_consulta_actividad, null);
        builder.setView(dialogView);

        editTextNombre = (EditText) dialogView.findViewById(R.id.edTxt_nombre);
        editTextPorcentaje = (EditText) dialogView.findViewById(R.id.edTxt_porcentaje);
        editTextcalificacion = (EditText) dialogView.findViewById(R.id.edTxt_nota);


        editTextNombre.setText(actividad.getNombre());
        editTextPorcentaje.setText(String.valueOf(actividad.getPorcentaje_corte()));
        editTextcalificacion.setText("");

        builder
        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int id) {

            }
        });

        final AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();

        //Overriding the handler immediately after show is probably a better approach than OnShowListener as described below
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dialog.cancel();
            }
        });

        return dialog;

    }

    public void registrar_calificacion(){
        Double nota = (Double.parseDouble(editTextcalificacion.getText().toString())*actividad.getPorcentaje_corte());
        Calificacion calificacion = new Calificacion(actividad.getId_corte(), actividad.getId_materia(), actividad.getId(), nota);
        upcDB.CalificacionDao().AgregarCalificacion(calificacion);
    }

    public void show(FragmentManager manager, String tag) {
        FragmentTransaction ft = manager.beginTransaction();
        ft.add(this, tag);
        ft.commit();
    }

    public void setearDatosExternosEstudiante(Actividad actividad, UpcDB upcDB){
        this.actividad = actividad;
        this.upcDB = upcDB;
    }
}

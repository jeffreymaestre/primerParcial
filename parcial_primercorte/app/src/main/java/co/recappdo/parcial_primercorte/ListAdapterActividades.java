package co.recappdo.parcial_primercorte;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import DB.entity.Actividad;
import DB.entity.Curso;

public class ListAdapterActividades extends BaseAdapter {
    private Context mContext;
    private List<Actividad> actividadList;

    //Constructor

    public ListAdapterActividades(Context mContext, List<Actividad> ActividadList) {
        this.mContext = mContext;
        this.actividadList = ActividadList;
    }

    @Override
    public int getCount() {
        return actividadList.size();
    }

    @Override
    public Object getItem(int position) {
        return actividadList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View vista = View.inflate(mContext, R.layout.item_list_view, null);
        TextView TextViewNombre = (TextView) vista.findViewById(R.id.TextViewNombre);
        TextView TextViewPorcentaje = (TextView) vista.findViewById(R.id.TextViewPorcentaje);

        TextViewNombre.setText(actividadList.get(position).getNombre());
        TextViewPorcentaje.setText("Porcentaje: " + actividadList.get(position).getPorcentaje_corte());

        //Save product id to tag
        vista.setTag(actividadList.get(position).getId());

        return vista;
    }
}

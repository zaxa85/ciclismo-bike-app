package org.selecciondecampeones.baikap.util;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.selecciondecampeones.baikap.R;
import org.selecciondecampeones.baikap.model.Entrenamiento;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class EntrenamientoListAdapter extends ArrayAdapter {

    public EntrenamientoListAdapter(Context context, ArrayList<Entrenamiento> entrenamientos) {
        super(context, 0, entrenamientos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Entrenamiento entrenamiento = (Entrenamiento) getItem(position);

        // Setting current date
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_entrenamientos, parent, false);
        }

        SimpleDateFormat viewerFormat = new SimpleDateFormat("dd-MM-yyyy");

        String tempDate = viewerFormat.format(entrenamiento.getFecha());

        StringBuilder row = new StringBuilder();
        row.append("Descripción: ").append(entrenamiento.getDescripcion());
        row.append("\rTipo: ").append(definirTipoEntrenamiento(entrenamiento.getTipo()));
        row.append("\n");
        row.append("\nCalentar durante 20 minutos: 3 min alta cadencia, 5 min yendo a ritmo, 2 min trancado con baja cadencia x2");
        row.append("\nDirigirse a un sector con 3% inclinación: Ritmo Carrera 3 min, luego ir parado en biela 2 minutos, y finalmente ir trancado 2 minutos");
        row.append("\nRepetir 4 veces descanzando un minuto entre cada ejecución");

        // Lookup view for data population
        TextView textBody = (TextView) convertView.findViewById(R.id.entrenamientoTextView);
        TextView textTitle = (TextView) convertView.findViewById(R.id.entrenamientoTextViewTitle);
        // Populate the data into the template view using the data object
        textBody.setText(row);
        textTitle.setText(tempDate);


        if (viewerFormat.format(entrenamiento.getFecha()).equals(viewerFormat.format(date))) {
            textTitle.setText(textTitle.getText() + "[Selected]");
            textTitle.setTypeface(null, Typeface.BOLD_ITALIC);
            textBody.setTypeface(null, Typeface.BOLD_ITALIC);

        } else if (entrenamiento.getFecha().before(date)) {
            // textBody.setTextColor(Color.GRAY);
            textTitle.setTextColor(Color.GRAY);
            textBody.setTextColor(Color.GRAY);
            textTitle.setTypeface(null, Typeface.NORMAL);
            textBody.setTypeface(null, Typeface.NORMAL);

        } else {
            textTitle.setTextColor(Color.BLACK);
            textBody.setTextColor(Color.BLACK);
            textTitle.setTypeface(null, Typeface.NORMAL);
            textBody.setTypeface(null, Typeface.NORMAL);
        }

        // Return the completed view to render on screen
        return convertView;
    }

    private String definirTipoEntrenamiento(String estado) {
        String descripcionTipo = "";

        switch (estado) {
            case "I":
                descripcionTipo = "Intervalos";
                break;
            case "F":
                descripcionTipo = "Fondo";
                break;
            case "R":
                descripcionTipo = "Resistencia";
                break;
            case "T":
                descripcionTipo = "Técnica";
                break;
            case "RF":
                descripcionTipo = "Resistencia a la Fuerza";
                break;
            case "RE":
                descripcionTipo = "Recuperación";
                break;
        }
        return descripcionTipo;
    }

}

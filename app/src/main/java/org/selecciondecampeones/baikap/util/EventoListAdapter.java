package org.selecciondecampeones.baikap.util;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.selecciondecampeones.baikap.R;
import org.selecciondecampeones.baikap.model.Evento;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EventoListAdapter extends ArrayAdapter {

    public EventoListAdapter(Context context, List<Evento> eventos) {
        super(context, 0, eventos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Evento evento = (Evento) getItem(position);

        // Setting current date
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_eventos, parent, false);
        }

        SimpleDateFormat viewerFormat = new SimpleDateFormat("dd-MM-yyyy");

        String tempDateStart = viewerFormat.format(evento.getFechaInicio());
        String tempDateFinish = viewerFormat.format(evento.getFechaFin());

        StringBuilder row = new StringBuilder();
        row.append("Organizador: ").append(evento.getOrganizador());

        if (!evento.getLugar().trim().isEmpty() && !evento.getLugar().equalsIgnoreCase("null")) {
            row.append("\nLugar: ").append(evento.getLugar());
        }

        if (!evento.getFechaFin().equals(evento.getFechaInicio())) {
            row.append("\nFechas: ").append(tempDateStart)
                    .append(" a ").append(tempDateFinish);
        } else {
            row.append("\nFecha: ").append(tempDateStart);
        }

        // Lookup view for data population
        TextView textBody = (TextView) convertView.findViewById(R.id.eventoTextView);
        TextView textTitle = (TextView) convertView.findViewById(R.id.eventoTextViewTitle);
        // Populate the data into the template view using the data object
        textTitle.setText(evento.getNombre());
        textBody.setText(row);


        if (evento.getFechaInicio().equals(date)) {
            textTitle.setTextColor(Color.RED);
            textBody.setTextColor(Color.RED);
            textTitle.setText(textTitle.getText() + "\t" + "¡En curso!");
        }
            else if (evento.getFechaFin().before(date)) {
            textTitle.setTextColor(Color.GRAY);
            textBody.setTextColor(Color.GRAY);

        } else {
            textTitle.setTextColor(Color.BLACK);
            textBody.setTextColor(Color.BLACK);
        }

        // Return the completed view to render on screen
        return convertView;
    }


}

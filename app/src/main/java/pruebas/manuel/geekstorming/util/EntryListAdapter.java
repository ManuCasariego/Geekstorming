package pruebas.manuel.geekstorming.util;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import pruebas.manuel.geekstorming.R;

/**
 * Created by Manuel on 01/02/2015.
 */
public class EntryListAdapter extends ArrayAdapter<Entry> {
    private Activity ctx;

    public EntryListAdapter(Activity context, List<Entry> contactos) {
        super(context, R.layout.list_view_item, contactos);
        this.ctx = context;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = ctx.getLayoutInflater().inflate(R.layout.list_view_item, parent, false);
        }
        Entry actual = this.getItem(position);
        inicializarComponentes(view, actual);
        return view;
    }

    private void inicializarComponentes(View view, Entry actual) {
        //Title
        TextView textView = (TextView) view.findViewById(R.id.textViewTitle);
        textView.setText(actual.getTitle());

        //categories
        textView = (TextView) view.findViewById(R.id.textViewCategory);
        textView.setText(actual.getCategorias());

        //Creator
        textView = (TextView) view.findViewById(R.id.textViewCreator);
        textView.setText(actual.getCreator());
    }
}

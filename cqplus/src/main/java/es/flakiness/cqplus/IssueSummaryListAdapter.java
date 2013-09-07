package es.flakiness.cqplus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 *
 */
public class IssueSummaryListAdapter extends ArrayAdapter<IssueSummary> {
    private LayoutInflater mInflater;

    public IssueSummaryListAdapter(Context context, List<IssueSummary> objects) {
        super(context, 0, objects);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView != null)
            return convertView; // FIXME: fill it.
        return mInflater.inflate(R.layout.issue_summary, parent, false); // FIXME: fill it.
    }
}

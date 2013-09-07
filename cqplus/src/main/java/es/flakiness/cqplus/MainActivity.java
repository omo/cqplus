package es.flakiness.cqplus;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private ListView mListView;
    private ArrayAdapter<IssueSummary> mListAdaptor;
    private List<IssueSummary> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mList = new ArrayList<IssueSummary>();
        mList.add(new IssueSummary());
        mListAdaptor = new IssueSummaryListAdapter(this, mList);
        mListView = (ListView)findViewById(R.id.change_summary_list);
        mListView.setAdapter(mListAdaptor);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}

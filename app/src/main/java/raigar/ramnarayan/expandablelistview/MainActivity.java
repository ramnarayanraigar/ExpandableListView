package raigar.ramnarayan.expandablelistview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private int lastExpandedPosition = -1;
   private ExpandableAdapter mListAdapter;
   private ExpandableListView mExpandableList;
   private List<String> mListDataHeader;
   private HashMap<String, List<String>> mListDataChild;
   private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        mExpandableList = findViewById(R.id.expandableList);
        prepareListData();

        mListAdapter = new ExpandableAdapter(mContext, mListDataHeader, mListDataChild);
        mExpandableList.setAdapter(mListAdapter);


        mExpandableList.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                if (lastExpandedPosition != -1 && groupPosition != lastExpandedPosition) {
                    mExpandableList.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = groupPosition;
            }
        });


    }

    /*
	 * Preparing the list data
	 */
    private void prepareListData() {
        mListDataHeader = new ArrayList<String>();
        mListDataChild = new HashMap<String, List<String>>();

        // Adding child data
        mListDataHeader.add("Android");
        mListDataHeader.add("PHP");
        mListDataHeader.add("Java");

        // Adding child data
        List<String> android = new ArrayList<String>();
        android.add("Ice cream");
        android.add("Jellybean");
        android.add("Kitkat");
        android.add("Lollipop");
        android.add("Marshmallow");
        android.add("Naugat");
        android.add("Oreo");

        List<String> PHP = new ArrayList<String>();
        PHP.add("PHP1");
        PHP.add("PHP2");
        PHP.add("PHP3");
        PHP.add("PHP4");


        List<String> Java = new ArrayList<String>();
        Java.add("Java1");
        Java.add("Java2");
        Java.add("Java3");
        Java.add("Java4");
        Java.add("Java5");

        mListDataChild.put(mListDataHeader.get(0), android); // Header, Child data
        mListDataChild.put(mListDataHeader.get(1), PHP);
        mListDataChild.put(mListDataHeader.get(2), Java);
    }
}

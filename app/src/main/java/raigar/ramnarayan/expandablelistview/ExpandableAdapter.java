package raigar.ramnarayan.expandablelistview;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by kriscent on 6/3/18.
 */

public class ExpandableAdapter extends BaseExpandableListAdapter {

    private static final String TAG = ExpandableAdapter.class.getSimpleName();
    private Context mContext;
    private List<String> mListDataHeader; // header titles

    // child data in format of header title, child title
    private HashMap<String, List<String>> mListDataChild;

    public ExpandableAdapter(Context context, List<String> listDataHeader
                              , HashMap<String, List<String>> listDataChild) {
        this.mContext = context;
        this.mListDataHeader = listDataHeader;
        this.mListDataChild = listDataChild;
    }

    @Override
    public int getGroupCount() {
        return mListDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mListDataChild.get(mListDataHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mListDataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mListDataChild.get(mListDataHeader.get(groupPosition))
                             .get(childPosition);
    }


    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded
                             , View convertView, ViewGroup parent) {

        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_group, null);
        }

        TextView lblListHeader = convertView.findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }


    @Override
    public View getChildView(int groupPosition, int childPosition
            , boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, null);
        }

        TextView textListChild = convertView.findViewById(R.id.lblListItem);
        textListChild.setText(childText);

        View view = convertView.findViewById(R.id.view);

        Log.v(TAG, " Children count: " + getChildrenCount(groupPosition));
        Log.v(TAG, " Children c: " + mListDataChild.get(mListDataHeader.get(groupPosition)).size());
        

        return convertView;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int ChildPosition) {
        return false;
    }
}

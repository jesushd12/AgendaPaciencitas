package com.example.jesus.agendapaciencitas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Jesus on 12/10/2015.
 */
public class CitasAdapter extends BaseExpandableListAdapter {
    private Context ctx;
    private HashMap<String,List<String>> citaPacientes;
    private List<String> listaPacientes;


    public CitasAdapter(Context ctx, HashMap<String,List<String>> citaPacientes , List<String>listaPacientes){
        this.citaPacientes=citaPacientes;
        this.ctx =ctx;
        this.listaPacientes = listaPacientes;

    }

    @Override
    public int getGroupCount() {
        return listaPacientes.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        return citaPacientes.get(listaPacientes.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listaPacientes.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {


        return citaPacientes.get(listaPacientes.get(groupPosition)).get(childPosition);
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
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String group_title = (String)getGroup(groupPosition);
        if(convertView==null){
            LayoutInflater inflator = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflator.inflate(R.layout.header_list_layout,parent,false);
        }
        TextView header_textView =(TextView) convertView.findViewById(R.id.header_list_name);
        header_textView.setText(group_title);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String child_title = (String)getChild(groupPosition,childPosition);
        if(convertView==null){
            LayoutInflater inflator = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflator.inflate(R.layout.child_list_layout,parent,false);
        }
        TextView child_textView =(TextView) convertView.findViewById(R.id.child_list_name);
        child_textView.setText(child_title);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}

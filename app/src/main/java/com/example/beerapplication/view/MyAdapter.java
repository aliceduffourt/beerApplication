package com.example.beerapplication.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.beerapplication.R;
import com.example.beerapplication.Modele.beer;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<beer> mDataset;
    private Context context;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView elementname;
        public TextView elementalcohol_degree;
        public LinearLayout elementlist;
        public MyViewHolder(View v) {
            super(v);
            elementname = v.findViewById(R.id.elementname);
            elementalcohol_degree = v.findViewById(R.id.elementalcohol_degree);
            elementlist = v.findViewById(R.id.elementlist);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<beer> myDataset, Context context) {
        mDataset = myDataset;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.elementlist, parent, false);

        return new MyViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.elementname.setText(mDataset.get(position).getName());
        holder.elementalcohol_degree.setText(String.valueOf(mDataset.get(position).getAlcohol_degree())+"°");
        holder.elementlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentMain = new Intent (context, Main2Activity.class);
                intentMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intentMain.putExtra("id",String.valueOf(mDataset.get(position).getId()));
                context.startActivity(intentMain);
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}

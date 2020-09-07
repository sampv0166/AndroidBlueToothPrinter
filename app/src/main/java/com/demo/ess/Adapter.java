package com.demo.ess;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder>{
    private Context context;
    private List<Data> List;
    private DatabaseHelper databaseHelper;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView data;
        ImageView delete,edit;
        public TextView timestamp;

        public MyViewHolder(View view) {
            super(view);
            data = view.findViewById(R.id.data);
            timestamp = view.findViewById(R.id.timestamp);
            delete = view.findViewById(R.id.delete);
            edit = view.findViewById(R.id.edit);
        }
    }
    public Adapter(Context context, List<Data> List, DatabaseHelper dbhelper){
        this.context = context;
        this.List = List;
        this.databaseHelper = dbhelper;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_content, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        Data diary = List.get(position);

        String word = diary.getPhoneNumber() +" - "+ diary.getCustomerName()+" - "  + diary.getEventLocation()+
                " - "  +diary.getNumberOfPax()+" - "+diary.getAmount() +" - "+ diary.getRemarks()+" - "+diary.getPhoneNumber();

      final Long id = Long.valueOf(diary.getId());

        holder.data.setText(word);
        // Formatting and displaying timestamp
        holder.timestamp.setText(formatDate(diary.getEventDate()));
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteNote(position);
            }
        });
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, String.valueOf(position), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context,EditData.class);
                intent.putExtra("position",String.valueOf(position));
                intent.putExtra("idsingle", String.valueOf(id));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return List.size();
    }
    private String formatDate(String dateStr) {
        try {

            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            Date date = fmt.parse(dateStr);
            SimpleDateFormat fmtOut = new SimpleDateFormat("MMM d yyyy");
            return fmtOut.format(date);
        } catch (ParseException e) {
            Log.e("error",e.getMessage());
        }
        return "";
    }
    private void deleteNote(int position) {
        // deleting the data from db
        databaseHelper.deleteData(List.get(position));

        // removing the data from the list
        List.remove(position);
        MainActivity.notifyAdapter();
    }
}
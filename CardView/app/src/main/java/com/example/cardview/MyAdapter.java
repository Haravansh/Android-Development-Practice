package com.example.cardview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    //1- Data
    private Context context;
    private ArrayList<CourseModel> courseModelArrayList;


    // 2 - Constructor
    public MyAdapter(Context context, ArrayList<CourseModel> courseModelArrayList) {
        this.context = context;
        this.courseModelArrayList = courseModelArrayList;
    }

    // 3 - View Holder
    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView courseImage;
        private TextView courseName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            courseImage = itemView.findViewById(R.id.imageviewCard);
            courseName = itemView.findViewById(R.id.textviewCard);
        }
    }

    //4- Implenting Methods
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout .card_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CourseModel model = courseModelArrayList.get(position);
        holder.courseName.setText(model.getCourse_image());
        holder.courseImage.setImageResource(model.getCourse_image());
    }

    @Override
    public int getItemCount() {
        return courseModelArrayList.size();
    }
}

package com.example.coursex;

//After creating the UI for each item of the Recycler View, we will be initializing the layout file inside the Adapter Class

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CourseRVAdapter extends RecyclerView.Adapter<CourseRVAdapter.ViewHolder>{  //extends RecyclerView.Adapter and implement methods
  // In this class, we will have to inflate our layout file as well as we will be adding animation to recyclerView as well

    //Firstly we are creating variables as follows:
    int lastPos = -1; // creating a last position index as -1
   private ArrayList<CourseRVModal> courseRVModalArrayList;
   private Context context;
   private CourseClickInterface courseClickInterface;

    public CourseRVAdapter(ArrayList<CourseRVModal> courseRVModalArrayList, Context context, CourseClickInterface courseClickInterface) {
        this.courseRVModalArrayList = courseRVModalArrayList;
        this.context = context;
        this.courseClickInterface = courseClickInterface;
    }

    //creating constructors
    @NonNull
    @Override
    public CourseRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { // Create inner class for the ViewHolder
       //Inflating the layout file which we have created inside onCreateViewHolder method
        View view = LayoutInflater.from(context).inflate(R.layout.course_rv_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseRVAdapter.ViewHolder holder, int position) {

        //Setting the data
        CourseRVModal courseRVModal = courseRVModalArrayList.get(position); // getting the data from the ArrayList inside our Modal Class
        holder.courseNameTV.setText(courseRVModal.getCourseName());
        holder.coursePriceTV.setText("Rs. " +courseRVModal.getCourseName());
        Picasso.get().load(courseRVModal.getCourseImg()).into(holder.courseIV);
        setAnimation(holder.itemView,position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {  //adding onClickListener for our itemView
            @Override
            public void onClick(View view) {
                courseClickInterface.onCourseClick(position); //calling the courseClickInterface
            }
        });
    }

    private void setAnimation(View itemView, int position){   // for adding animation to each item of the Recycler View
        if(position>lastPos){
            Animation animation = AnimationUtils.loadAnimation(context,android.R.anim.slide_in_left);
            itemView.setAnimation(animation);
            lastPos = position;
        }
    }
    @Override
    public int getItemCount() {
        return courseRVModalArrayList.size();  //returning the size of our ArrayList
    }

    public interface CourseClickInterface{
        void onCourseClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder { //made it public(from private) and extended RecyclerView.ViewHolder

        // Initializing all the variables
        private TextView courseNameTV, coursePriceTV;
        private ImageView courseIV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            courseNameTV = itemView.findViewById(R.id.idTVCourseName);
            coursePriceTV = itemView.findViewById(R.id.idTVPrice);
            courseIV = itemView.findViewById(R.id.idIVCourse);
        }
    }

}

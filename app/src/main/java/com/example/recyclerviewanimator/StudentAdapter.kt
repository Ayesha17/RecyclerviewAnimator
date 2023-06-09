package com.example.recyclerviewanimator

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class StudentAdapter (val studentDataList:List<studentData>,  val context:Context) : RecyclerView.Adapter<StudentAdapter.MyViewHolder>() {


    @NonNull
    @Override
    override fun  onCreateViewHolder(@NonNull  viewGroup: ViewGroup, i: Int): MyViewHolder {
        val itemView = LayoutInflater.from(viewGroup.getContext())
            .inflate(R.layout.student_list_row, viewGroup, false);
        return    MyViewHolder(itemView)
    }
    @Override
    override fun  onBindViewHolder(viewHolder:MyViewHolder, i:Int) {
     val data=   studentDataList.get(i)

     viewHolder.MyViewHolder(data,i)


    }
    @Override
    override fun  getItemCount() :Int{
        return studentDataList.size
    }
    class MyViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var name:TextView
        lateinit var age:TextView

        private var lastPosition = -1;
        lateinit var parent: LinearLayout
        fun MyViewHolder(data: studentData, position: Int) {



            parent = itemView.findViewById(R.id.parent);
         val   cardView = itemView.findViewById<CardView>(R.id.card_view);
            if (data.isOverlapped)
                cardView.setBackgroundResource(R.drawable.card_border)
            else
                cardView.setBackgroundResource(R.drawable.card_border_8)
            name = itemView.findViewById(R.id.name);
            age = itemView.findViewById(R.id.age);
            parent.setBackgroundColor(data.color);
            name.text = data.name;
            age.text=  data.age.toString()
            setAnimation( parent, position);
        }


        private fun setAnimation(viewToAnimate: View, position:Int) {
            // If the bound view wasn't previously displayed on screen, it's animated
            if (position > lastPosition) {
                val animation = AnimationUtils.loadAnimation(itemView.context, android.R.anim.slide_in_left);
                viewToAnimate.startAnimation(animation);
                lastPosition = position;
            }
        }
    }
}
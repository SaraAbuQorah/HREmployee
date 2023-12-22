package com.example.hremployee.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hremployee.Module.SubmissionsDataClass
import com.example.hremployee.R

class SubmisionsAdapter  (private val list: ArrayList<SubmissionsDataClass>) : RecyclerView.Adapter<SubmisionsAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, ViewType:Int): ViewHolder {
        val v= LayoutInflater.from(parent.context).inflate(R.layout.submmisions_card,parent,false)
        return ViewHolder(v)
    }


    override fun onBindViewHolder(holder : ViewHolder, position: Int){
        val itemList =list[position]
        holder.title1.text=itemList.titel
        holder.itemtext1.text=itemList.dis1
        holder.itemtext2.text=itemList.dis2
        holder.itemimg.setImageResource(itemList.img!!)



    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val title1: TextView =itemView.findViewById(R.id.titlesub1)
        val itemtext1: TextView =itemView.findViewById(R.id.titlesub2)
        val itemtext2: TextView =itemView.findViewById(R.id.titlesub3)
        val itemimg : ImageView =itemView.findViewById(R.id.imgsub)

    }

    override fun getItemCount(): Int {
        return list.size
    }
}
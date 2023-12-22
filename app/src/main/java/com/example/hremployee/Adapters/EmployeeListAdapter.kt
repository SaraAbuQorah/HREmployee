package com.example.hremployee.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hremployee.Module.EmployeeListClass
import com.example.hremployee.Module.EmployeeListNewClass
import com.example.hremployee.R
import java.util.ArrayList

class EmployeeListAdapter(private val list: ArrayList<EmployeeListClass>) : RecyclerView.Adapter<EmployeeListAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, ViewType:Int): ViewHolder {
        val v= LayoutInflater.from(parent.context).inflate(R.layout.emplyeelistcard,parent,false)
        return ViewHolder(v)
    }


    override fun onBindViewHolder(holder : ViewHolder, position: Int){
        val itemList =list[position]
        holder.itemimg.setImageResource(itemList.img!!)
        holder.itemtext.text=itemList.titel
    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val itemtext: TextView =itemView.findViewById(R.id.textcardview)
        val itemimg : ImageView =itemView.findViewById(R.id.imgcardview)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}


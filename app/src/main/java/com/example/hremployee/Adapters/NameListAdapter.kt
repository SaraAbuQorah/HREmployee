package com.example.hremployee.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hremployee.Module.NameListClass
import com.example.hremployee.R

class NameListAdapter (private val list: ArrayList<NameListClass>) : RecyclerView.Adapter<NameListAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, ViewType:Int): ViewHolder {
    val v= LayoutInflater.from(parent.context).inflate(R.layout.name_list_card,parent,false)
    return ViewHolder(v)
}


override fun onBindViewHolder(holder : ViewHolder, position: Int){
    val itemList =list[position]
    holder.itemimg.setImageResource(itemList.img!!)
    holder.itemtext.text=itemList.titel
    holder.itemtext2.text=itemList.titel

    holder.declineButton.setOnClickListener{
        list.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, list.size)
    }
    holder.acceptButton.setOnClickListener{
        list.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, list.size)
    }
}

class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    val itemtext: TextView =itemView.findViewById(R.id.nametextid)
    val itemtext2: TextView =itemView.findViewById(R.id.nametext2id)
    val itemimg : ImageView =itemView.findViewById(R.id.nameimgid)
    val acceptButton : Button =itemView.findViewById(R.id.Accept)
    val declineButton : Button =itemView.findViewById(R.id.Decline)
}

override fun getItemCount(): Int {
    return list.size
}
}
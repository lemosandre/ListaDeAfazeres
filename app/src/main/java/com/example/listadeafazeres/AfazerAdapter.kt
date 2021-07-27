package com.example.listadeafazeres

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView

internal class AfazerAdapter(private var afazerModel: List<AfazerModel>) :
    RecyclerView.Adapter<AfazerAdapter.MyViewHolder>() {
    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.title)
        var body: TextView = view.findViewById(R.id.body)
        var userName: TextView = view.findViewById(R.id.userName)
    }
    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_row, parent, false)
        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = afazerModel[position]
        holder.title.text = movie.getTitle()
        holder.body.text = movie.getBody()
        holder.userName.text = movie.getUserName()
    }
    override fun getItemCount(): Int {
        return afazerModel.size
    }
}
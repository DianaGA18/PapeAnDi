package com.tesji.papeandi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tesji.papeandi.model.User

class MyAdapter (private val userList: ArrayList<User>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_items, parent, false)
        return MyViewHolder(itemView)
    }
    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user : User = userList[position]
        holder.idProd.text = user.ID
        holder.nombreP.text = user.NombreProducto
        holder.descrip.text = user.DescripProducto
        holder.precio.text = user.Precio
    }

    public class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
       val idProd : TextView = itemView.findViewById(R.id.txIdproductov)
        val nombreP : TextView = itemView.findViewById(R.id.txNombreproductov)
        val descrip : TextView = itemView.findViewById(R.id.txDescripproductov)
        val precio : TextView = itemView.findViewById(R.id.txPrecioproductov)
    }
}
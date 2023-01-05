package com.example.loginapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.loginapp.R
import com.example.loginapp.data.User
import de.hdodenhof.circleimageview.CircleImageView


class MainAdapter(val data: List<User>, val clicks: (User) -> Unit): RecyclerView.Adapter<MainAdapter.MyViewHolder>() {



    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {}




    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.chat_item, viewGroup, false)

        return MyViewHolder(view)
    }



    override fun onBindViewHolder(viewHolder: MyViewHolder, position: Int) {


        viewHolder.itemView.rootView.setOnClickListener {
            clicks.invoke(data[position])
        }


        val myImage = viewHolder.itemView.findViewById<CircleImageView>(R.id.userImage)
        val myName = viewHolder.itemView.findViewById<TextView>(R.id.userName)



//        Picasso.get()
//            .load(url)
//            .into(myImage)



        myName.text = data[position].secondName
    }


    override fun getItemCount() = data.size

}

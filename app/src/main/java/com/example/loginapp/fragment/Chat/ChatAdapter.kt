package com.example.loginapp.fragment.Chat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.loginapp.R
import com.example.loginapp.data.Message


class ChatAdapter(val context: Context, val messages: List<Message>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){


    companion object {
        const val THE_FROM_VIEW = 0
        const val THE_TO_VIEW = 1
    }


    private inner class FromViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        var messageText: TextView = itemView.findViewById(R.id.messageFrom)

        fun bind(message: Message) {
            messageText.text = message.text
        }
    }

    private inner class ToViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        var messageText: TextView = itemView.findViewById(R.id.messageTo)

        fun bind(message: Message) {
            messageText.text = message.text
        }
    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == THE_FROM_VIEW) {
            return FromViewHolder(
                LayoutInflater.from(context).inflate(R.layout.message_from, parent, false)
            )
        }
        return ToViewHolder(
            LayoutInflater.from(context).inflate(R.layout.message_to, parent, false)
        )
    }



    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val data = messages[position]


        if (messages[position].type == THE_TO_VIEW) {
            (holder as ToViewHolder).bind(data)
        } else {
            (holder as FromViewHolder).bind(data)
        }

    }

    override fun getItemViewType(position: Int): Int {
        return messages[position].type

    }

    override fun getItemCount(): Int = messages.size
}
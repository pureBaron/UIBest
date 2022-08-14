package com.example.uibest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MessageAdapter(val messageList: List<Message>) : RecyclerView.Adapter<MessageAdapter.MessageViewHolder>(){


    sealed class MessageViewHolder(view: View): RecyclerView.ViewHolder(view){

    }

    inner class LeftViewHolder(view: View): MessageViewHolder(view){
        val leftMsg: TextView = view.findViewById(R.id.leftMsg)
    }
    inner class RightViewHolder(view: View): MessageViewHolder(view){
        val rightMsg: TextView = view.findViewById(R.id.rightMsg)
    }

    override fun getItemViewType(position: Int): Int {
        val msg = messageList[position]
        return msg.type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val vh : View
        if(viewType == Message.RECEIVED) {
            vh = LayoutInflater.from(parent.context)
                .inflate(R.layout.left_message_item, parent, false)
            return LeftViewHolder(vh)
        }else{
            vh = LayoutInflater.from(parent.context)
                .inflate(R.layout.right_message_item, parent, false)
            return RightViewHolder(vh)
        }
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val msg = messageList[position]
        when(holder){
            is RightViewHolder ->{holder.rightMsg.text = msg.text}
            is LeftViewHolder ->{holder.leftMsg.text = msg.text}
        }
    }

    override fun getItemCount(): Int {
        return this.messageList.size
    }


}
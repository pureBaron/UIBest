package com.example.uibest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener{

    private val msgList = ArrayList<Message>()
    private var adapter: MessageAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initMsg()
        val layoutManager = LinearLayoutManager(this)
        messageRecyclerview.layoutManager = layoutManager
        adapter = MessageAdapter(msgList)
        messageRecyclerview.adapter = adapter
        sendBtn.setOnClickListener(this)
    }

    private fun initMsg(){
        val msg = Message("HI THERE", Message.RECEIVED)
        val msg2 =Message("I KNOW YOU BUT I CANT UNDERSTAND YOU", Message.SENT)
        msgList.add(msg)
        msgList.add(msg2)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.sendBtn -> {
                val text = inputMessage.text.toString()
                if(text.isNotEmpty()){
                    val msg = Message(text, Message.SENT)
                    msgList.add(msg)
                    adapter?.notifyItemInserted(msgList.size-1)
                    messageRecyclerview.scrollToPosition(msgList.size-1)
                    inputMessage.setText("")
                }
            }
        }
    }


}
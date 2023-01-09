package com.example.loginapp.fragment.Chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.loginapp.R
import com.example.loginapp.data.Message
import com.example.loginapp.databinding.ChatFragmentBinding


class ChatFragment: Fragment (R.layout.chat_fragment){
    private var _binding: ChatFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ChatFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataFromFirebase = listOf(
            Message("Hello Madamin!", 0),
            Message("How are you?", 0),
            Message("Hello bro!", 1),
            Message("Fine thnx bro, and you?", 1),
            Message("Not bad", 0)
        )

        val chatAdapter = ChatAdapter(requireContext(),dataFromFirebase)
        binding.rv.adapter = chatAdapter
    }

}
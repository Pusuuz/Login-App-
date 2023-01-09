package com.example.loginapp.fragment.Chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.loginapp.R
import com.example.loginapp.databinding.ChatFragmentBinding
import com.example.loginapp.databinding.FinallFragmentBinding

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

}
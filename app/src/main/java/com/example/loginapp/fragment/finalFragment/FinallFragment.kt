package com.example.loginapp.fragment.finalFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.loginapp.R
import com.example.loginapp.databinding.FinallFragmentBinding

import com.example.loginapp.databinding.WelcomeFragmentBinding

class FinallFragment: Fragment(R.layout.finall_fragment) {

    private var _binding: FinallFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FinallFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val secondName = requireArguments().getString("second_name")
        val email = requireArguments().getString("email")

        binding.Somebody.text = secondName



    }







    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
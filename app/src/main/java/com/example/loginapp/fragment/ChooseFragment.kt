package com.example.loginapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.loginapp.R
import com.example.loginapp.databinding.WelcomeFragmentBinding

class ChooseFragment: Fragment(R.layout.welcome_fragment) {

    private var _binding: WelcomeFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = WelcomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonLogin.setOnClickListener{
            findNavController().navigate(R.id.action_chooseFragment_to_loginFragment)
        }
        binding.ButtonRegistration.setOnClickListener {
            findNavController().navigate(R.id.action_chooseFragment_to_registrationFragment)
        }
    }



}
package com.example.loginapp.fragment.registration

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.loginapp.R
import com.example.loginapp.databinding.RegistrationFragmentBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class RegistrationFragment: Fragment(R.layout.registration_fragment) {

    private var _binding: RegistrationFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RegistrationFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.button.setOnClickListener {
            registerUser()
        }



    }


    private fun registerUser(){

        val firstName = binding.firstNameField.text ?: "Unknown"
        val secondName = "Ochilov"
        val email = "usuf_pussuz@mail.uz"
        val password = "12345"

        // Write a message to the database
        val database = Firebase.database.getReference("users")


        database.child(firstName.toString()).apply {
            child("email").setValue(email)
            child("secondName").setValue(secondName)
            child("password").setValue(password)
        }


    }


}
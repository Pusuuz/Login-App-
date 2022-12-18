package com.example.loginapp.fragment.registration

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.loginapp.MainActivity
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

 binding.addImageButton.setOnClickListener {
     openGalleryForImage()
 }

    }
    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, 100)
    }


    private fun registerUser(){

        val firstName = binding.firstNameField.text.toString()
        val secondName = binding.secondNameField.text.toString()
        val email = binding.emailField.text.toString()
        val password = binding.passwordField.text.toString()


        var hasError: Boolean

        binding.firstName.error = null
        binding.secondName.error= null
        binding.email.error= null
        binding.password.error= null
        hasError = false



        if (binding.firstNameField.text.toString()==""){
            binding.firstName.error = "ewfwefwefe"
            hasError = true
        }
        if (binding.secondNameField.text.toString()==""){
            binding.secondName.error = "fwfwefwf"
            hasError=true
        }
        if (binding.emailField.text.toString()==""){
            binding.email.error = "fwfwefwf"
            hasError=true

        }
        if (binding.passwordField.text.toString()==""){
            binding.password.error = "fwfwefwf"
            hasError=true

        }


        if (!hasError){
            // Write a message to the database
            val database = Firebase.database.getReference("users")


            database.child(firstName).apply {
                child("email").setValue(email)
                child("secondName").setValue(secondName)
                child("password").setValue(password)
            }


        }



    }


}
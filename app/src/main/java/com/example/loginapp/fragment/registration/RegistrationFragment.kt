package com.example.loginapp.fragment.registration

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.loginapp.MainActivity
import com.example.loginapp.R
import com.example.loginapp.databinding.RegistrationFragmentBinding
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import java.util.*
import java.util.regex.Pattern

class RegistrationFragment: Fragment(R.layout.registration_fragment) {

    private var _binding: RegistrationFragmentBinding? = null
    private val binding get() = _binding!!

    var imageUrl: String? = null


    private val NAME_PATTERN: Pattern = Pattern.compile("^[a-zA-Z0-9\\p{L}+_ -]+\$")


    private val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"

    fun isEmailValid(email: String): Boolean {

        return EMAIL_REGEX.toRegex().matches(email)
    }
    fun isNameValid(name: String):Boolean{
        return NAME_PATTERN.toRegex().matches(name)
    }

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
//           if (isCorrectFields())
               registerUser()
        }

        binding.addImageButton.setOnClickListener {
//           if(isCorrectFields())
               openGalleryForImage()
        }
    }

    private fun isCorrectFields():Boolean{
        var hasError: Boolean

        val email = binding.emailField.text.toString()
        binding.email.error = null


        val firstName = binding.firstNameField.text.toString()
        binding.firstName.error = null

        val secondName = binding.secondNameField.text.toString()
        binding.secondName.error = null

        if (email.isNotEmpty() && isEmailValid(email)) {
            hasError = false
        } else {
            hasError = true
            binding.email.error = "напиши нормальную почту!!!!!"
        }

        if (firstName.isNotEmpty() && isNameValid(firstName)) {
            hasError = false
        } else {
            hasError = true
            binding.firstName.error = "пиши правильно!!!"
        }
        if (secondName.isNotEmpty() && isNameValid((secondName))) {
            hasError = false
        } else {
            hasError = true
            binding.secondName.error = "ты писать разучился"
        }

        if (binding.passwordField.text.toString().isNotEmpty() && binding.passwordField.text.toString().length >= 8){
            hasError = false
        }else {
            hasError = true
            binding.password.error = "пиши заново"
        }

        return hasError
    }

    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"

        resultLauncher.launch(intent)

    }


    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->

        if (result.resultCode == Activity.RESULT_OK) {


            result.data?.data?.let {

                uploadImageToFirebase(it)
            }

        }
    }

    private fun uploadImageToFirebase(fileUri: Uri?) {

        if (fileUri != null) {

            val fileName = binding.emailField.text.toString() +".jpg"


            val refStorage = FirebaseStorage.getInstance().reference.child("images/$fileName")

            refStorage.putFile(fileUri)
                .addOnSuccessListener { taskSnapshot ->

                    taskSnapshot.storage.downloadUrl.addOnSuccessListener {

                        val url = it.toString()

                        imageUrl = url
                        Log.d("huhuhuh", url)
                    }
                }

                .addOnFailureListener { e ->
                    Log.d("huhuhuh", "error: ${e.message}")

                }
        }
    }

    private fun registerUser(){

        val firstName = binding.firstNameField.text.toString()
        val secondName = binding.secondNameField.text.toString()
        val email = binding.emailField.text.toString()
        val password = binding.passwordField.text.toString()


         // Write a message to the database
            val database = Firebase.database.getReference("users")

            Log.d("sdfdsfadsfgsagds",imageUrl.toString())

            database.child(firstName).apply {
                child("email").setValue(email)
                child("secondName").setValue(secondName)
                child("password").setValue(password)
                child("image_url").setValue(imageUrl)
            }


    }


}
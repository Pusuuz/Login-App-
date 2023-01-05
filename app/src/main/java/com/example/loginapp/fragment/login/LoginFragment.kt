package com.example.loginapp.fragment.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.loginapp.R
import com.example.loginapp.data.User
import com.example.loginapp.databinding.LoginFragmentBinding
import com.example.loginapp.databinding.RegistrationFragmentBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class LoginFragment: Fragment(R.layout.login_fragment){
    private var _binding: LoginFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LoginFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.LoginButton.setOnClickListener {

            val database = Firebase.database.getReference("users")



            database.get().addOnSuccessListener {

                val users = it.children.toList()


                users.forEach{

                    val user = it.getValue(User::class.java)

                    if (user?.email == binding.emailField.text.toString() && user.password == binding.passwordField.text.toString()){
                        Toast.makeText(requireContext(), "Successfuly Logined", Toast.LENGTH_SHORT).show()


                        val data = bundleOf(
                            "second_name" to user.secondName,
                            "email" to user.email
                        )

                        findNavController().navigate(R.id.action_loginFragment_to_finallFragment, data)
                    }
                }

            }.addOnFailureListener{
                Log.e("uhihiuhiu", "Error getting data", it)
            }
        }
    }

}




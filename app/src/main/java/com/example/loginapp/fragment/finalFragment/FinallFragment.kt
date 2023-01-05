package com.example.loginapp.fragment.finalFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.loginapp.R
import com.example.loginapp.adapter.MainAdapter
import com.example.loginapp.data.User
import com.example.loginapp.databinding.FinallFragmentBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

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

        initView()

        fetchUsers()


    }

    private fun fetchUsers(){
        val database = Firebase.database.getReference("users")



        database.get().addOnSuccessListener { dataSnap ->

            val users = mutableListOf<User>()

            dataSnap.children.toList().forEach {

                val data = it.getValue(User::class.java)
                users.add(data!!)
            }

            val adapter = MainAdapter(users) {
                // сделаем позже (клик на элемент списка)
            }

            binding.rv.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

            binding.rv.adapter = adapter


        }.addOnFailureListener{
            Log.e("uhihiuhiu", "Error getting data", it)
        }
    }

    private fun initView(){
        //        val secondName = requireArguments()?.getString("second_name") ?: "lklk"
//        val email = requireArguments()?.getString("email") ?: "lklk"


//        val firebaseStorageImage = FirebaseStorage.getInstance().reference.child("images/${email}.jpg")
//
//        firebaseStorageImage.downloadUrl.addOnSuccessListener {
//
//            Picasso.get().load(it).into(binding.circleImageView)
//
//        }
//            .addOnFailureListener {
//                Log.d("afafakfjj", "error: ${it.message}")
//            }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
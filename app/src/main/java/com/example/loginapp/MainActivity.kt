package com.example.loginapp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import java.util.*

class MainActivity : AppCompatActivity() {

    val REQUEST_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)


    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("huhuhuh", requestCode.toString())

        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE){
            Log.d("huhuhuh", data?.data.toString())

            uploadImageToFirebase(data?.data)

        }

    }


    private fun uploadImageToFirebase(fileUri: Uri?) {

        if (fileUri != null) {

            val fileName = UUID.randomUUID().toString() +".jpg"

            val database = FirebaseDatabase.getInstance()
            val refStorage = FirebaseStorage.getInstance().reference.child("images/$fileName")

            refStorage.putFile(fileUri)
                .addOnSuccessListener { taskSnapshot ->
                    taskSnapshot.storage.downloadUrl.addOnSuccessListener {

                        val imageUrl = it.toString()
                        Log.d("huhuhuh", imageUrl)
                    }
                }

                .addOnFailureListener { e ->
                    print(e.message)
                }
        }
    }
}
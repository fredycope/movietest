package com.example.movietest.data.network

import com.google.android.gms.tasks.Task
import com.google.firebase.Firebase
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.UploadTask
import com.google.firebase.storage.storage
import java.sql.Time
import javax.inject.Inject
import kotlin.random.Random


class Firebase @Inject constructor(): FirebaseService {
     var firebaseStorage = Firebase.storage
     val storageRef = firebaseStorage.reference
     val db = Firebase.firestore

     override fun saveImage(data: ByteArray): UploadTask? {
          val rand = Random.nextInt(10)
          val mountainsRef = storageRef.child("images/${Time(System.currentTimeMillis())}_$rand")
          return mountainsRef.putBytes(data)
     }

     override fun readLocation(): Task<QuerySnapshot> {
         return db.collection("locations").get()
     }

}
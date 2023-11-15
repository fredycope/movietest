package com.example.movietest.data.network

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.storage.UploadTask

interface FirebaseService {
    fun saveImage(data: ByteArray): UploadTask?
    fun readLocation(): Task<QuerySnapshot>
}
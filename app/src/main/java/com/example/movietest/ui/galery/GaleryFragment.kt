package com.example.movietest.ui.galery

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.movietest.databinding.FragmentGaleryBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.tasks.await
import java.io.ByteArrayOutputStream


@AndroidEntryPoint
class GaleryFragment : Fragment() {
    private val galeryViewModel: GaleryViewModel by viewModels()

    private lateinit var binding: FragmentGaleryBinding
    private val PHOTO_SELECTED = 1
    val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {

                val intent = result.data
                val imageBitmap = intent?.extras?.get("data") as Bitmap
                val baos = ByteArrayOutputStream()
                imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                val data = baos.toByteArray()
                val res = galeryViewModel.saveImage(data)

                res?.addOnFailureListener {
                    // Handle unsuccessful uploads
                }?.addOnSuccessListener { taskSnapshot ->
                    Toast.makeText(requireContext(), "File upload",Toast.LENGTH_SHORT).show()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGaleryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListener()
    }

    private fun setListener() {
        binding.btPhoto.setOnClickListener {
            abrirCamara()
        }

        binding.btGalery.setOnClickListener {

            val intent = Intent(MediaStore.ACTION_PICK_IMAGES)
            intent.putExtra(MediaStore.EXTRA_PICK_IMAGES_MAX, 10)
            startActivityForResult(intent, 100)
        }
    }

    private fun abrirCamara() {

        startForResult.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                100 -> {
                    var i = 0
                    val clipCount = data?.clipData!!.itemCount
                    while (i < clipCount) {
                        var imageUri: Uri = data.clipData?.getItemAt(i)!!.uri
                        val bitmap = MediaStore.Images.Media.getBitmap(requireContext().contentResolver, imageUri)

                        val baos = ByteArrayOutputStream()
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                        val data = baos.toByteArray()

                       val res = galeryViewModel.saveImage(data)
                        res?.addOnFailureListener {
                                // Handle unsuccessful uploads
                        }?.addOnSuccessListener { taskSnapshot ->
                                Toast.makeText(requireContext(), "File upload",Toast.LENGTH_SHORT).show()
                        }
                        i++
                    }
                }

            }

        } else { return }
    }
}
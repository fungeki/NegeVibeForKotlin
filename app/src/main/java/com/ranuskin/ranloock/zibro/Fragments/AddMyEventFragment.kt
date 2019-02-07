package com.ranuskin.ranloock.zibro.Fragments

import android.app.Activity
import android.app.Instrumentation
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat.checkSelfPermission
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast

import com.ranuskin.ranloock.zibro.R
import kotlinx.android.synthetic.main.fragment_add_my_event.*
import kotlinx.android.synthetic.main.row_my_tickets.*
import java.util.jar.Manifest



class AddMyEventFragment : Fragment() {

    private val IMAGE_PICK_CODE: Int = 100
    private val REQUEST_CODE_FOR_USING_CAMERA: Int = 101
    private val PERMISSION_CODE = 1001

    var fileUri: Uri? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Create an ArrayAdapter

        return inflater.inflate(R.layout.fragment_add_my_event, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //take photo from gallery
        addEventImage.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
               if(checkSelfPermission(view.context,android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                   //permission denied
                   val permissions  = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                   requestPermissions(permissions,PERMISSION_CODE)
               }else{
                   //permission already granted
                    pickPhotoFromCallery()
               }
            }else{
                //OS < then Marshmallow
                pickPhotoFromCallery()
            }
        }

        val adapter = ArrayAdapter.createFromResource(
            view.context,
            R.array.categoriesHebrew,
            android.R.layout.simple_spinner_item
        )
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Apply the adapter to the spinner
        addEventCategorySpinner.adapter = adapter
        addEventCategorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

            }
        }


    }

    private fun pickPhotoFromCallery(){
//        val intent = Intent(Intent.ACTION_PICK)
//        intent.type = "image/*"
        val pickImageIntent = Intent(Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(pickImageIntent,IMAGE_PICK_CODE)


    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            PERMISSION_CODE->{
                if (grantResults.size>0 && grantResults[0]==PackageManager.PERMISSION_DENIED){
                    pickPhotoFromCallery()
                }else{
                    Toast.makeText(context,"Permission denied",Toast.LENGTH_LONG).show()
                }
            }


        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK
            && requestCode == REQUEST_CODE_FOR_USING_CAMERA) {
            //photo from camera
            //display the photo on the imageview
            addEventCategoryImage.setImageURI(fileUri)
        }else if(resultCode == Activity.RESULT_OK
            && requestCode == IMAGE_PICK_CODE){
            //photo from gallery
            fileUri = data?.data
            addEventCategoryImage.setImageURI(fileUri)
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

}







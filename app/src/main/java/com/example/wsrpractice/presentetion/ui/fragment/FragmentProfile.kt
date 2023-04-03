package com.example.wsrpractice.presentetion.ui.fragment

import android.app.Activity
import android.app.AlertDialog
import android.content.ContentResolver.MimeTypeInfo
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.MimeTypeMap
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.wsrpractice.R
import com.example.wsrpractice.data.network.model.UserPatientCardNetwork
import com.example.wsrpractice.databinding.FragmentProfileBinding
import com.example.wsrpractice.presentetion.mvvm.ProfileViewModel
import com.example.wsrpractice.presentetion.mvvm.factory.ProfileViewModelFactory
import kotlinx.coroutines.launch

private const val CAMERA_REQUEST_CODE = 11
private const val REQUEST_TAKE_PHOTO = 111
class FragmentProfile:Fragment() {

    lateinit var binding:FragmentProfileBinding
    private val viewModel by lazy{
        ViewModelProvider(requireActivity(),ProfileViewModelFactory())[ProfileViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initInputData()
        initAvatars()

    }

    private fun initAvatars(){
        val avatar = binding.userAvatar
        val getContent = registerForActivityResult(ActivityResultContracts.GetContent()){imageUri: Uri? ->
            avatar.setImageURI(imageUri)
        }
        val imageMimeType = "image/*"
        avatar.setOnClickListener{

            AlertDialog.Builder(requireActivity()).setMessage("Выберете, как получить фото").setNegativeButton("Новое фото") { dialogInterface, i ->
                if (ActivityCompat.checkSelfPermission(
                        requireContext(),
                        android.Manifest.permission.CAMERA
                    )
                    !=
                    PackageManager.PERMISSION_GRANTED
                ){
                    ActivityCompat.requestPermissions(requireActivity(),
                        arrayOf(
                            android.Manifest.permission.CAMERA
                        ),
                        CAMERA_REQUEST_CODE)
                }else{
                    val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO)
                }
            }.setPositiveButton("Из галерии") { _,_ ->
                getContent.launch(imageMimeType)
            }.show()

        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_TAKE_PHOTO){
            val bitmap = data?.extras?.get("data") as Bitmap
            binding.userAvatar.setImageBitmap(bitmap)
        }
    }
    private fun initInputData(){


        val userPatientCard = viewModel._liveDataPatientCard
        val serverAnswer = viewModel._liveDataServerAnswer

        val inputData = binding.inputUserData
        val setGenderEditText = inputData.setGenderEt
        val inputName = inputData.inputName
        val inputSurname = inputData.inputSurname
        val inputLastName = inputData.inputOtchevstvo
        val inputDataBorn = inputData.inputDateBorn
        val saveBut = binding.saveBut

        //заполнение строк
        userPatientCard.observe(viewLifecycleOwner){
            inputName.setText(userPatientCard.value?.firstname)
            inputSurname.setText(userPatientCard.value?.middlename)
            inputLastName.setText(userPatientCard.value?.lastname)
            inputDataBorn.setText(userPatientCard.value?.bith)
            setGenderEditText.setText(userPatientCard.value?.pol)
        }

        serverAnswer.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(),it.toString(),Toast.LENGTH_SHORT).show()
        }




        //set genders
        val genders = resources.getStringArray(R.array.gender)
        val adapter = ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1,genders)
        setGenderEditText.setAdapter(adapter)

        setGenderEditText.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus){
                setGenderEditText.text = null
                setGenderEditText.showDropDown()

            }
        }

        setGenderEditText.setOnClickListener {

            setGenderEditText.showDropDown()

        }

        Log.d("emptyTest","${(inputName.toString().isEmpty() ||
                setGenderEditText.toString().isEmpty() ||
                inputDataBorn.toString().isEmpty() ||
                inputLastName.toString().isEmpty() ||
                inputSurname.toString().isEmpty())}")
        //check on empty stroke edit texts
        if(inputName.toString().isEmpty() ||
                setGenderEditText.toString().isEmpty() ||
                inputDataBorn.toString().isEmpty() ||
                inputLastName.toString().isEmpty() ||
                inputSurname.toString().isEmpty()){
            saveBut.text = "Создание карты"
        }

        saveBut.setOnClickListener {
            val name = inputName.text.toString()
            val surname = inputSurname.text.toString()
            val lastname = inputLastName.text.toString()
            val dataBorn = inputDataBorn.text.toString()
            val gender = setGenderEditText.text.toString()

            val patientCard = UserPatientCardNetwork(
                firstname = name,
                middlename = surname,
                lastname = lastname,
                bith = dataBorn,
                pol = gender
            )

            Log.d("UserPatientCardTest",patientCard.toString())


            //todo как то сделать,так чтобы узнавать есть ли аккаунт
            lifecycleScope.launch{
                viewModel.savePatientCard(patientCard = patientCard,false)
            }




        }

    }

}
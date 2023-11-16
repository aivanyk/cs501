package com.bignerdranch.android.criminalintent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bignerdranch.android.criminalintent.databinding.FragmentDialogBinding
import java.io.File
import androidx.fragment.app.DialogFragment

class ZoomInDialogFragment : DialogFragment() {
    private val photouri = "PHOTO_URI"
    private lateinit var binding: FragmentDialogBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_dialog, container, false)
        val zoomedImage = view.findViewById(R.id.zoomedCrimePhoto) as ImageView
        val photoFile = File(requireContext().applicationContext.filesDir.path,arguments?.getSerializable(photouri).toString())
        val imageBitMap = BitmapFactory.decodeFile(photoFile.path,null)
        zoomedImage.setImageBitmap(imageBitMap)
        return view
    }

    companion object {
        private val photouri = "PHOTO_URI"
        private val defaultArgument = Bundle()
        private val defaultDialog = ZoomInDialogFragment()
        fun newInstance(photoFileName: String): ZoomInDialogFragment {
            defaultArgument.putSerializable(photouri, photoFileName)
            defaultDialog.arguments = defaultArgument
            return defaultDialog
        }
    }
}
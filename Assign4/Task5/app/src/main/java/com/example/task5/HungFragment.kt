package com.example.task5

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.task5.databinding.FragmentHungBinding

private const val TAG = "HungFragment"

class HungFragment: Fragment() {
    var _binding: FragmentHungBinding? = null
    val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    val hungViewModel: HungViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Created Hung.")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHungBinding.inflate(inflater, container, false)

        val view = inflater.inflate(R.layout.fragment_hung, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageView = view.findViewById<ImageView>(R.id.imageview_hung)

        val model = ViewModelProvider(requireActivity()).get(HungViewModel::class.java)
        model.hungStatus.observe(viewLifecycleOwner, Observer {
            imageView.setImageResource(hungViewModel.hungPics[hungViewModel.hungStatus.value!!])
        })
    }

}
package com.example.task5

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.task5.databinding.FragmentHintBinding
import java.lang.Integer.min

private const val TAG = "HintFragment"

class HintFragment: Fragment() {
    var _binding: FragmentHintBinding? = null
    val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    val hungViewModel: HungViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Created Hint.")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHintBinding.inflate(inflater, container, false)
        val view = binding.root

        if(hungViewModel.hintStatus > 0) {
            view.findViewById<Button>(R.id.hint).text = " HINT: " + hungViewModel.getCurrQuestion().hint
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val hintButton = view.findViewById<Button>(R.id.hint)
        if(hungViewModel.gameOver()) {
            hintButton.isEnabled = false
            hintButton.setBackgroundColor(ContextCompat.getColor(binding.root.context, R.color.purple_200))
        }

        val model = ViewModelProvider(requireActivity()).get(HungViewModel::class.java)
        model.refreshFlag.observe(viewLifecycleOwner, Observer {
            if(hungViewModel.hintStatus > 0) {
                view.findViewById<Button>(R.id.hint).text = " HINT: " + hungViewModel.getCurrQuestion().hint
            }
            else hintButton.text = " HINT: "
            Log.d(TAG, "refreshFlag is ${model.refreshFlag.value}")
            hintButton.isEnabled = true
            hintButton.setBackgroundColor(ContextCompat.getColor(binding.root.context, R.color.purple_500))
        })

        hintButton.setOnClickListener {
            if(hungViewModel.gameOver()) {
                hintButton.isEnabled = false
                hintButton.setBackgroundColor(ContextCompat.getColor(binding.root.context, R.color.purple_200))
                return@setOnClickListener
            }
            val hintStatus = hungViewModel.hintStatus

            if(hintStatus == 0) {
                hintButton.text = " HINT: " + hungViewModel.getCurrQuestion().hint
                hungViewModel.hintStatus += 1
            }
            else if(hungViewModel.nearHung()){
                Toast.makeText(
                    binding.root.context,
                    "Hint not available!",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else if(hintStatus == 1) {
                hungViewModel.hintStatus += 1
                hungViewModel.showHalf()
                hungViewModel.guessChange()
                hungViewModel.hangOneMore()
            }
            else if(hintStatus == 2) {
                hungViewModel.hintStatus += 1
                hungViewModel.getCurrQuestion().showAll()
                hungViewModel.guessChange()
                hungViewModel.hangOneMore()
            }
            if(hungViewModel.gameOver()) {
                hintButton.isEnabled = false
                hintButton.setBackgroundColor(ContextCompat.getColor(binding.root.context, R.color.purple_200))
            }
        }
    }
}
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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task5.databinding.FragmentChooseBinding

private const val TAG = "ChooseFragment"

open class ChooseFragment : Fragment() {

    var _binding: FragmentChooseBinding? = null
    val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    val hungViewModel: HungViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Total characters: ${hungViewModel.chars.size}")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "ChooseFragment createView")
        _binding = FragmentChooseBinding.inflate(inflater, container, false)

        return binding.root
    }

    open fun decideLayout(){
        binding.chooseRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val model = ViewModelProvider(requireActivity()).get(HungViewModel::class.java)
        model.refreshFlag.observe(viewLifecycleOwner, Observer {
            decideLayout()

            Log.d(TAG, "choose refreshFlag is ${model.refreshFlag.value}")
            val chars = hungViewModel.chars
            val adapter = CharacterAdapter(chars, hungViewModel)
            binding.chooseRecyclerView.adapter = adapter
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

class ChooseFragmentLarge : ChooseFragment() {
    override fun decideLayout(){
        binding.chooseRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val model = ViewModelProvider(requireActivity()).get(HungViewModel::class.java)
        model.refreshFlag.observe(viewLifecycleOwner, Observer {
            decideLayout()

            val chars = hungViewModel.chars
            val adapter = CharacterAdapter(chars, hungViewModel)
            binding.chooseRecyclerView.adapter = adapter
        })
    }
}

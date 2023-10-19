package com.example.task5

import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task5.databinding.FragmentAnswerBinding
import com.example.task5.databinding.FragmentChooseBinding

private const val TAG = "AnswerFragment"

class AnswerFragment: Fragment()  {
    var _binding: FragmentAnswerBinding? = null
    val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    val hungViewModel: HungViewModel by activityViewModels()

    var viewers = listOf<Int>(
        R.id.character1,
        R.id.character2,
        R.id.character3,
        R.id.character4,
        R.id.character5,
        R.id.character6
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Answer Fragment Created")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAnswerBinding.inflate(inflater, container, false)
        val view = binding.root

//        val curr: Question = hungViewModel.getCurrQuestion()
//        for(i in 0..curr.length()-1){
//            val textView = view.findViewById<TextView>(viewers[i])
//            val mSpannableString = SpannableString(curr.getChar(i))
//            mSpannableString.setSpan(UnderlineSpan(), 0, mSpannableString.length, 0)
//            textView.text = mSpannableString
//        }
//        for(i in curr.length()..5){
//            val textView = view.findViewById<TextView>(viewers[i])
//            textView.visibility = View.INVISIBLE
//        }
        initView()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val model = ViewModelProvider(requireActivity()).get(HungViewModel::class.java)

        model.guessChange.observe(viewLifecycleOwner, Observer {
//            Log.d(TAG, "Refresh display")
            val curr: Question = hungViewModel.getCurrQuestion()
            for(i in 0..curr.length()-1){
                val textView = view.findViewById<TextView>(viewers[i])
                val mSpannableString = SpannableString(curr.getChar(i))
                mSpannableString.setSpan(UnderlineSpan(), 0, mSpannableString.length, 0)
                textView.text = mSpannableString
                textView.visibility = View.VISIBLE
            }
            for(i in curr.length()..5){
                val textView = view.findViewById<TextView>(viewers[i])
                textView.visibility = View.INVISIBLE
            }

            if(hungViewModel.gameOver()){
                Toast.makeText(
                    binding.root.context,
                    "Game is over!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

    }

    fun initView() {
        val view = binding.root

        val curr: Question = hungViewModel.getCurrQuestion()
        for(i in 0..curr.length()-1){
            val textView = view.findViewById<TextView>(viewers[i])
            val mSpannableString = SpannableString(curr.getChar(i))
            mSpannableString.setSpan(UnderlineSpan(), 0, mSpannableString.length, 0)
            textView.text = mSpannableString
        }
        for(i in curr.length()..5){
            val textView = view.findViewById<TextView>(viewers[i])
            textView.visibility = View.INVISIBLE
        }
    }
}
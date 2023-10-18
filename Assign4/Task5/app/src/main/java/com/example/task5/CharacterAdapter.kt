package com.example.task5

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.task5.databinding.ListItemCharacterBinding
import androidx.core.content.ContextCompat

private const val TAG = "Adapter"

class CharacterHolder(
    private val binding: ListItemCharacterBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(c: Char, sharedViewModel: HungViewModel) {
        binding.character.text = c.toString()
        binding.character.isEnabled = true
        binding.character.setBackgroundColor(ContextCompat.getColor(binding.root.context, R.color.purple_500))

        if (sharedViewModel.clicked[c.code - 'A'.code]) {
            Log.d(TAG, "current clicked $c")
            binding.character.isEnabled = false
            binding.character.setBackgroundColor(ContextCompat.getColor(binding.root.context, R.color.purple_200))
            return
        }

        binding.character.setOnClickListener {
            if(!sharedViewModel.gameOver() && !sharedViewModel.clicked[c.code - 'A'.code]) {
                if (sharedViewModel.getCurrQuestion().insert(c)) {
                    sharedViewModel.guessChange()
                    Log.d(TAG, "clicking $c")
                    binding.character.isEnabled = false
                    binding.character.setBackgroundColor(ContextCompat.getColor(binding.root.context, R.color.purple_200))
                    sharedViewModel.clicked[c.code - 'A'.code] = true
                }
                else sharedViewModel.hangOneMore()
            }
        }
    }
}

open class CharacterAdapter(
    private val chars: List<Char>,
    private val sharedViewModel: HungViewModel
) : RecyclerView.Adapter<CharacterHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemCharacterBinding.inflate(inflater, parent, false)
        return CharacterHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterHolder, position: Int) {
        val c = chars[position]
        holder.bind(c, sharedViewModel)
    }

    override fun getItemCount() = chars.size
}

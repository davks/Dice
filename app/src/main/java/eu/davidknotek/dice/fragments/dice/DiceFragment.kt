package eu.davidknotek.dice.fragments.dice

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import eu.davidknotek.dice.R
import eu.davidknotek.dice.databinding.FragmentDiceBinding
import eu.davidknotek.dice.model.viewmodel.DiceViewModel
import eu.davidknotek.dice.model.viewmodel.TypeOfDice

class DiceFragment : Fragment() {
    private lateinit var binding: FragmentDiceBinding
    private val diceViewModel: DiceViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDiceBinding.inflate(layoutInflater)

        setObservers()
        setListeners()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val myWindow = (activity as AppCompatActivity).window
        (activity as AppCompatActivity).supportActionBar?.hide()
        myWindow.statusBarColor = ContextCompat.getColor(requireContext(), R.color.desk_background)
        myWindow.navigationBarColor = ContextCompat.getColor(requireContext(), R.color.desk_background)
    }

    private fun setObservers() {
        diceViewModel.rolledNumber.observe(viewLifecycleOwner) {
            binding.diceImageView.setImageDrawable(diceViewModel.getRollImage(it))
        }
    }

    private fun setListeners() {
        binding.startRollButton.setOnClickListener {
            diceViewModel.startRoll()
        }

        binding.chooseD6.setOnClickListener {
            diceViewModel.typeOfDice = TypeOfDice.SIX
            diceViewModel.startRoll()
        }

        binding.chooseD12.setOnClickListener {
            diceViewModel.typeOfDice = TypeOfDice.TWELVE
            diceViewModel.startRoll()
        }

        binding.chooseD20.setOnClickListener {
            diceViewModel.typeOfDice = TypeOfDice.TWENTY
            diceViewModel.startRoll()
        }

        binding.chooseOR6.setOnClickListener {
            diceViewModel.typeOfDice = TypeOfDice.OR_SIX
            diceViewModel.startRoll()
        }

        binding.chooseOR12.setOnClickListener {
            diceViewModel.typeOfDice = TypeOfDice.OR_TWELVE
            diceViewModel.startRoll()
        }
    }
}
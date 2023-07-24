package com.bignerdranch.android.criminalintent

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bignerdranch.android.criminalintent.databinding.FragmentCrimeDetailBinding
import java.util.Date
import java.util.UUID

private const val TAG = "CrimeDetailFragment"
//presents details of a crime
class CrimeDetailFragment : Fragment() {
    //bind fragment crime xml to file
    private var _binding: FragmentCrimeDetailBinding? = null
    private val binding
        get() = checkNotNull(_binding){
            "Cannot access binding because it is null. Is the View Visible?"
        }

    private lateinit var crime : Crime
    private val args: CrimeDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //adding a new crime
        crime = Crime(id = UUID.randomUUID(), title = "", date = Date(), isSolved = false)

        Log.d(TAG, "The crime ID is: ${args.crimeId}")
    }

    //generated binding class that is used to inflate the layout
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCrimeDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    //listener function
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            crimeTitle.doOnTextChanged{ text, _, _, _ ->
                    crime = crime.copy(title = text.toString())
            }
            //Connect button to display date
            crimeDate.apply{
                text = crime.date.toString()
                isEnabled = false
            }
            //listener to check isSolved
            crimeSolved.setOnCheckedChangeListener{_, isChecked ->
                crime = crime.copy(isSolved = isChecked)
            }
        }
    }
    //cleaning up memory
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
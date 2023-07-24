package com.bignerdranch.android.criminalintent

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.android.criminalintent.databinding.FragmentCrimeListBinding
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect

class CrimeListFragment : Fragment() {
    //ask about binding
    private var _binding: FragmentCrimeListBinding? = null
    private val binding
        get() = checkNotNull(_binding){
            "Cannot access binding because it is null. Is the View visible?"
        }

    private val crimeListViewModel : CrimeListViewModel by viewModels()

    //bind the layout to the fragment
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCrimeListBinding.inflate(inflater, container, false)
        //Vertical list layout add line below
        binding.crimeRecyclerView.layoutManager = LinearLayoutManager(context)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //repeats work when brought back from the background
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                crimeListViewModel.crimes.collect { crimes ->
                    binding.crimeRecyclerView.adapter =
                        CrimeListAdapter(crimes) { crimeId ->
                            //navigate to selected crime
                            findNavController().navigate(
                                CrimeListFragmentDirections.showCrimeDetail(crimeId)
                            )
                        }

                }
            }
        }
    }
}
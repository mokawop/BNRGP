package com.bignerdranch.android.criminalintent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.criminalintent.databinding.FragmentCrimeListBinding
import com.bignerdranch.android.criminalintent.databinding.ListItemCrimeBinding
import java.util.UUID

class CrimeHolder(
    private val binding: ListItemCrimeBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(crime: Crime, onCrimeClicked: (crimeId: UUID) -> Unit){
            binding.crimeTitle.text = crime.title
            binding.crimeDate.text = crime.date.toString()

            //make a toast when you select a crime to expand
            binding.root.setOnClickListener{
                //navigates user to detail screen
                 onCrimeClicked(crime.id)
            }
            //visibility based on whether crime is solved
            binding.crimeSolved.visibility = if(crime.isSolved) {
                View.VISIBLE
            } else{
                View.GONE
            }
        }
    }

class CrimeListAdapter (private val crimes: List<Crime>, private val onCrimeCLicked: (crimeId: UUID) -> Unit) : RecyclerView.Adapter<CrimeHolder>(){
    //inflating the view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemCrimeBinding.inflate(inflater, parent, false)
        return CrimeHolder(binding)
    }

    override fun getItemCount() = crimes.size

    //fill in crime data that was selected
    override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
        val crime = crimes[position]
        holder.bind(crime, onCrimeCLicked)
    }

}

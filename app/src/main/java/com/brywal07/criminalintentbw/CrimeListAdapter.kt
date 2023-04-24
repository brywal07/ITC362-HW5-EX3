package com.brywal07.criminalintentbw

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.brywal07.criminalintentbw.databinding.ListItemCrimeBinding
import com.brywal07.criminalintentbw.databinding.ListItemCrimePoliceBinding
import java.text.DateFormat
import java.text.SimpleDateFormat


private const val ViewType = 0
private const val ViewTypePolice = 1

class CrimeHolder (
    private val binding: ListItemCrimeBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(crime: Crime) {
        binding.crimeTitle.text = crime.title
        val date = crime.date
        val dateFormat: DateFormat = SimpleDateFormat("MMMM dd, yyyy")
        val dateFormatted: String = dateFormat.format(date)
        binding.crimeDate.text = dateFormatted

        binding.root.setOnClickListener {
            Toast.makeText(
                binding.root.context,
                "${crime.title} clicked!",
                Toast.LENGTH_SHORT
            ).show()
        }
        binding.crimeSolved.visibility = if (crime.isSolved){
            View.VISIBLE}else{
                View.GONE
        }
    }
}

class PoliceHolder (
    private val binding: ListItemCrimePoliceBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(crime: Crime) {
        binding.crimeTitle.text = crime.title
        val date = crime.date
        val dateFormat: DateFormat = SimpleDateFormat("MMMM dd, yyyy")
        val dateFormatted: String = dateFormat.format(date)
        binding.crimeDate.text = dateFormatted

        binding.root.setOnClickListener {
            Toast.makeText(
                binding.root.context,
                "${crime.title} clicked!",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.contactPoliceButton.setOnClickListener {
            Toast.makeText(
                binding.root.context,
                // Make the entire toast capitalized to emphasize intensity
                "POLICE CONTACTED REGARDING ${crime.title.uppercase()}!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}


class CrimeListAdapter(
    private val crimes: List<Crime>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // override the getItemViewType fun to return the appropriate view type
    override fun getItemViewType(position: Int): Int {
        return if (crimes[position].requiresPolice) {
            ViewTypePolice
        } else {
            ViewType
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewBinding

        // made me alt shift enter cascade when instead of if
        return when (viewType) {
            ViewType -> {
                binding = ListItemCrimeBinding.inflate(inflater, parent, false)
                CrimeHolder(binding as ListItemCrimeBinding)
            }
            ViewTypePolice -> {
                binding = ListItemCrimePoliceBinding.inflate(inflater, parent, false)
                PoliceHolder(binding as ListItemCrimePoliceBinding)
            }
            else -> {
                throw IllegalArgumentException("Invalid view type!")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val crime = crimes[position]

        // changed the if again, extracting a when
        when (holder) {
            is CrimeHolder -> holder.bind(crime)
            is PoliceHolder -> holder.bind(crime)
            else -> throw IllegalArgumentException("Invalid ViewHolder type")
        }
    }

    override fun getItemCount() = crimes.size
}

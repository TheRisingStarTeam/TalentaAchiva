package com.risingstar.talentaachiva.feature.dashboard

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.risingstar.talentaachiva.databinding.FragmentFakeProfileBinding
import com.risingstar.talentaachiva.domain.data.Identity
import java.util.*

class ProfileFragment : Fragment() {

    private lateinit var viewmodel : DebugVM
    private val c = Calendar.getInstance()
    private var time = c.time
    private lateinit var binding : FragmentFakeProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentFakeProfileBinding.inflate(inflater, container, false)
        viewmodel = ViewModelProvider(requireActivity())[DebugVM::class.java]

        val dpd = DatePickerDialog(requireActivity(), { _, year, monthOfYear, dayOfMonth ->
            c.set(year,monthOfYear,dayOfMonth)
            time = c.time
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH))

        viewmodel.postIdentityResult().observe(viewLifecycleOwner){
            if(it == true) Toast.makeText(this.context,"Success",Toast.LENGTH_SHORT).show()
            else Toast.makeText(this.context,"Failed",Toast.LENGTH_SHORT).show()
        }

        binding.dateTako.setOnClickListener {
            dpd.show()
            binding.etDate.text = time.toString()
        }

        binding.button2.setOnClickListener {
            upload()
            viewmodel.postEventResult().observe(viewLifecycleOwner){
                if(it == true) Toast.makeText(this.context,"Success", Toast.LENGTH_SHORT).show()
                else Toast.makeText(this.context,"Failed", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }

    private fun upload() {
        val identity = Identity(
            null,
            binding.etName.text.toString(),
            time,
            null,
            null,
            null,
            stringToWords(binding.etInterest.text.toString()),
            null,
            null,
            null,
            null
        )
        viewmodel.postIdentity(identity)
    }

    private fun stringToWords(s : String) = s.trim().splitToSequence(' ')
        .filter { it.isNotEmpty() }
        .toList()



}
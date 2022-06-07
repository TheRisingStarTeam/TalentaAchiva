package com.risingstar.talentaachiva.feature.organizer

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.risingstar.talentaachiva.databinding.FragmentCreateEventBinding
import com.risingstar.talentaachiva.domain.data.Event
import java.util.*

class CreateEventFragment : Fragment() {
    private lateinit var binding : FragmentCreateEventBinding
    private lateinit var viewmodel : OrganizerVM
    private val c = Calendar.getInstance()
    private var time = c.time

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        viewmodel = ViewModelProvider(requireActivity()).get(OrganizerVM::class.java)
        binding = FragmentCreateEventBinding.inflate(layoutInflater,container,false)

        val dpd = DatePickerDialog(requireActivity(), { _, year, monthOfYear, dayOfMonth ->
            c.set(year+1900,monthOfYear,dayOfMonth)
            time = c.time
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH))

        binding.datePick.setOnClickListener {
            dpd.show()
            binding.etDate.text = time.toString()
        }

        binding.btnUpload.setOnClickListener {
            val event = Event(
                null,
                null,
                false,
                binding.titleEt.text.toString(),
                stringToWords(binding.CategoryEt.text.toString()),
                description = binding.descriptionEt.text.toString(),
                date = time
            )
            viewmodel.createEvent(event)
        }


        return binding.root
    }

    private fun stringToWords(s : String) = s.trim().splitToSequence(' ')
        .filter { it.isNotEmpty() }
        .toList()
}
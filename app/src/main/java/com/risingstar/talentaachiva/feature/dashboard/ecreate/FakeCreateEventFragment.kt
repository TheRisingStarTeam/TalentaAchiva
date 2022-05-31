package com.risingstar.talentaachiva.feature.dashboard.ecreate

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.risingstar.talentaachiva.databinding.FragmentFakeCreateEventBinding
import com.risingstar.talentaachiva.domain.data.Event
import com.risingstar.talentaachiva.feature.dashboard.DebugVM
import java.util.*

class FakeCreateEventFragment : Fragment() {

    private lateinit var binding : FragmentFakeCreateEventBinding
    private lateinit var viewmodel : DebugVM
    private val c = Calendar.getInstance()
    private var time = c.time

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentFakeCreateEventBinding.inflate(inflater, container, false)
        viewmodel = ViewModelProvider(requireActivity())[DebugVM::class.java]
        val dpd = DatePickerDialog(requireActivity(), { _, year, monthOfYear, dayOfMonth ->
            c.set(year+1900,monthOfYear,dayOfMonth)
            time = c.time
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH))
        binding.datePick.setOnClickListener {
            dpd.show()
            binding.etDate.text = time.toString()
        }

        binding.button.setOnClickListener {
            upload()
            viewmodel.postEventResult().observe(viewLifecycleOwner){
                if(it == true) Toast.makeText(this.context,"Success",Toast.LENGTH_SHORT).show()
                else Toast.makeText(this.context,"Failed",Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }

    private fun upload() {
        if(viewmodel.currentUser != null){
            val event = Event(
                eventId = null,
                banner = null,
                active = true,
                name = binding.titleEt.text.toString(),
                categories = stringToWords(binding.CategoryEt.text.toString()),
                hashtags = null,
                rules = null,
                description = binding.descriptionEt.text.toString(),
                participants = null,
                organizers = listOf(viewmodel.currentUser!!.uid),
                tos = null,
                date = time
            )

            viewmodel.postEvents(event)
        }


    }

    private fun stringToWords(s : String) = s.trim().splitToSequence(' ')
        .filter { it.isNotEmpty() }
        .toList()


}
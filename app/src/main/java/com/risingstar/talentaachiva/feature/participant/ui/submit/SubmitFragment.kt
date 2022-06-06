package com.risingstar.talentaachiva.feature.participant.ui.submit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.risingstar.talentaachiva.databinding.FragmentSubmitBinding
import com.risingstar.talentaachiva.domain.data.Submissions
import com.risingstar.talentaachiva.feature.participant.ParticipantVM

class SubmitFragment : Fragment() {

    private lateinit var binding : FragmentSubmitBinding
    private lateinit var viewmodel : ParticipantVM


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        viewmodel = ViewModelProvider(requireActivity()).get(ParticipantVM::class.java)
        binding = FragmentSubmitBinding.inflate(layoutInflater,container,false)
        binding.buttonSubmit.setOnClickListener {
            postSubmission(
                binding.editSubmissionTitleUser.text.toString(),
                binding.editDescriptionPostUser.text.toString()
            )
        }

        return binding.root
    }

    private fun postSubmission(
        title:String,
        description:String
    ) {
        viewmodel.submitSubmission(
            Submissions(
                assignmentId = viewmodel.assignmentId,
                title = title,
                description = description,
                authorId = listOf(viewmodel.userId)
            )
        )
    }
}
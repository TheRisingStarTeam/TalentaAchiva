package com.risingstar.talentaachiva.feature.grading

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.risingstar.talentaachiva.databinding.ActivityGradingBinding
import com.risingstar.talentaachiva.domain.data.Judgement
import com.risingstar.talentaachiva.domain.data.Score
import com.risingstar.talentaachiva.domain.data.Submissions
import com.risingstar.talentaachiva.feature.util.GradingAdapter

class GradingActivity : AppCompatActivity() {

    private lateinit var binding : ActivityGradingBinding
    private lateinit var viewmodel : GradingVM
    private lateinit var userId : String
    private lateinit var submission : Submissions

    private lateinit var rvCriteria : RecyclerView
    private lateinit var rvAdapter : GradingAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userId = intent.getStringExtra(CURRENT_USER_ID).toString()
        submission = intent.getParcelableExtra(CURRENT_SUBMISSION)!!

        binding = ActivityGradingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewmodel = ViewModelProvider(
            this,GradingFactory(userId, submission.submissionId!!,submission.assignmentId!!)
        ).get(GradingVM::class.java)

        rvCriteria = binding.rvCriteria
        Toast.makeText(this,submission.assignmentId.toString(),Toast.LENGTH_SHORT).show()
        rvCriteria.layoutManager = LinearLayoutManager(this)


        binding.tvTitleGrading.text = submission.title
        binding.tvGradingContent.text = submission.content
        viewmodel.assignment().observe(this){ assignment ->
            val scores = mutableListOf<Score>()
            assignment?.criteria?.forEach {criteria ->
                scores.add(Score(
                    criteria,0
                ))
            }
            rvAdapter = GradingAdapter(scores as ArrayList<Score>)
            rvCriteria.adapter = rvAdapter
        }
        binding.buttonSubmit.setOnClickListener{
            val scores = rvAdapter.getScore()
            val judgement = Judgement(
                judge = userId,
                review = binding.etComment.text.toString(),
                score = scores
            )
            grade(judgement)
            //TODO: finish(), use observer to call this function
        }
    }

    private fun grade(judgement: Judgement) {

        viewmodel.gradeSubmission(judgement)
    }

    companion object{
        const val CURRENT_USER_ID = "Fish"
        const val CURRENT_SUBMISSION= "Fwish"
    }
}
package com.mxy.myquizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {
    private var pbProgress: ProgressBar? = null
    private var tvProgress: TextView? = null
    private var tvQuestion: TextView? = null
    private var ivImage: ImageView? = null
    private var tvOptionOne: TextView? = null
    private var tvOptionTwo: TextView? = null
    private var tvOptionThree: TextView? = null
    private var tvOptionFour: TextView? = null
    private var btnSubmit: Button? = null
    private val questions = Constants.getQuestions()
    private var currentQuestionNumber = 1
    private var selectedOption = 0
    private var checkedCorrectAnswer = false
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions2)

        pbProgress = findViewById(R.id.pb_progress)
        tvProgress = findViewById(R.id.tv_progress)
        tvQuestion = findViewById(R.id.tv_question)
        ivImage = findViewById(R.id.iv_image)
        tvOptionOne = findViewById(R.id.tv_option_one)
        tvOptionTwo = findViewById(R.id.tv_option_two)
        tvOptionThree = findViewById(R.id.tv_option_three)
        tvOptionFour = findViewById(R.id.tv_option_four)
        btnSubmit = findViewById(R.id.btn_submit)

        tvOptionOne?.setOnClickListener(this)
        tvOptionTwo?.setOnClickListener(this)
        tvOptionThree?.setOnClickListener(this)
        tvOptionFour?.setOnClickListener(this)
        btnSubmit?.setOnClickListener(this)

        setQuestion()
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.tv_option_one -> updateOptions(1)
            R.id.tv_option_two -> updateOptions(2)
            R.id.tv_option_three -> updateOptions(3)
            R.id.tv_option_four -> updateOptions(4)
            R.id.btn_submit -> onSubmit()
        }
    }

    private fun setQuestion() {
        val currentQuestion = questions[currentQuestionNumber - 1]
        pbProgress?.progress = currentQuestionNumber
        tvProgress?.text = "$currentQuestionNumber/9"
        tvQuestion?.text = currentQuestion.question
        ivImage?.setImageResource(currentQuestion.image)
        tvOptionOne?.text = currentQuestion.optionOne
        tvOptionTwo?.text = currentQuestion.optionTwo
        tvOptionThree?.text = currentQuestion.optionThree
        tvOptionFour?.text = currentQuestion.optionFour
    }

    private fun updateOptions(selected: Int) {
        if (checkedCorrectAnswer) { return }

        selectedOption = selected

        if (selected == 1) {
            tvOptionOne?.setTextColor(ContextCompat.getColor(this, R.color.nearBlack))
            tvOptionOne?.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)
        } else {
            tvOptionOne?.setTextColor(ContextCompat.getColor(this, R.color.gray))
            tvOptionOne?.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
        }

        if (selected == 2) {
            tvOptionTwo?.setTextColor(ContextCompat.getColor(this, R.color.nearBlack))
            tvOptionTwo?.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)
        } else {
            tvOptionTwo?.setTextColor(ContextCompat.getColor(this, R.color.gray))
            tvOptionTwo?.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
        }

        if (selected == 3) {
            tvOptionThree?.setTextColor(ContextCompat.getColor(this, R.color.nearBlack))
            tvOptionThree?.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)
        } else {
            tvOptionThree?.setTextColor(ContextCompat.getColor(this, R.color.gray))
            tvOptionThree?.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
        }

        if (selected == 4) {
            tvOptionFour?.setTextColor(ContextCompat.getColor(this, R.color.nearBlack))
            tvOptionFour?.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)
        } else {
            tvOptionFour?.setTextColor(ContextCompat.getColor(this, R.color.gray))
            tvOptionFour?.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
        }
    }

    private fun onSubmit() {
        if (checkedCorrectAnswer) {
            if (currentQuestionNumber == 9) {
                Toast.makeText(this, "Your final score: $score", Toast.LENGTH_SHORT).show()
            } else {
                currentQuestionNumber++
                selectedOption = 0
                checkedCorrectAnswer = false
                setQuestion()
                updateOptions(0)
                btnSubmit?.text = "SUBMIT"
            }
        } else {
            checkedCorrectAnswer = true
            btnSubmit?.text = "NEXT"
            val currentQuestion = questions[currentQuestionNumber - 1]
            if (selectedOption != currentQuestion.correctAnswer) {
                when (selectedOption) {
                    1 -> tvOptionOne?.background = ContextCompat.getDrawable(this, R.drawable.wrong_option_border_bg)
                    2 -> tvOptionTwo?.background = ContextCompat.getDrawable(this, R.drawable.wrong_option_border_bg)
                    3 -> tvOptionThree?.background = ContextCompat.getDrawable(this, R.drawable.wrong_option_border_bg)
                    4 -> tvOptionFour?.background = ContextCompat.getDrawable(this, R.drawable.wrong_option_border_bg)
                }
            } else {
                score++
            }
            when (currentQuestion.correctAnswer) {
                1 -> tvOptionOne?.background = ContextCompat.getDrawable(this, R.drawable.correct_option_border_bg)
                2 -> tvOptionTwo?.background = ContextCompat.getDrawable(this, R.drawable.correct_option_border_bg)
                3 -> tvOptionThree?.background = ContextCompat.getDrawable(this, R.drawable.correct_option_border_bg)
                4 -> tvOptionFour?.background = ContextCompat.getDrawable(this, R.drawable.correct_option_border_bg)
            }
        }
    }
}

package com.mxy.myquizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView

class QuizQuestionsActivity : AppCompatActivity() {
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
}

package com.example.quizapp

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_question.*

class QuestionActivity : AppCompatActivity() {

    private var Name:String? = null
    private  var score:Int = 0

    private var currentPosition: Int = 1
    private var questionList: ArrayList<QuestionData>? = null
    private var selectOption: Int = 0  //not yet selected anything

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        Name = intent.getStringExtra(setData.name)

        questionList = setData.getQuestion()

        setQuestion()

        opt_1.setOnClickListener {
            selectedOptionStyle(opt_1, 1)
        }
        opt_2.setOnClickListener {
            selectedOptionStyle(opt_2, 2)
        }
        opt_3.setOnClickListener {
            selectedOptionStyle(opt_3, 3)
        }
        opt_4.setOnClickListener {
            selectedOptionStyle(opt_4, 4)
        }

        submit.setOnClickListener {
            if (selectOption != 0) {
                val question = questionList!![currentPosition - 1]
                if (selectOption != question.correct_ans) {
                    setColor(selectOption, R.drawable.wrong_option)
                }else{
                    score++;
                }
                setColor(question.correct_ans, R.drawable.correct_option)
                if (currentPosition == questionList!!.size)
                    submit.text = "FINISH"
                else
                    submit.text = "Next Question"
            } else {
                currentPosition++
                when{
                    currentPosition <= questionList!!.size -> {
                        setQuestion()
                    }
                    else -> {
                        var intent = Intent(this,Result::class.java)
                        intent.putExtra(setData.name,Name.toString())
                        intent.putExtra(setData.score,score.toString())
                        intent.putExtra("Total size",questionList!!.size.toString())
                        startActivity(intent)
                        finish()

                    }
                }
            }
            selectOption = 0
        }
    }


    fun setColor(opt: Int, color: Int) {
        when (opt) {
            1 -> {
                opt_1.background = ContextCompat.getDrawable(this, color)
            }
            2 -> {
                opt_2.background = ContextCompat.getDrawable(this, color)
            }
            3 -> {
                opt_3.background = ContextCompat.getDrawable(this, color)
            }
            4 -> {
                opt_4.background = ContextCompat.getDrawable(this, color)
            }
        }
    }

    fun setQuestion() {
        var question = questionList!![currentPosition - 1]
        setOptionStyle()

        progress_bar.progress = currentPosition
        progress_bar.max = questionList!!.size
        progress_text.text = "${currentPosition}" + "/" + "${questionList!!.size}"
        question_text.text = question.question
        opt_1.text = question.option_one
        opt_2.text = question.option_two
        opt_3.text = question.option_three
        opt_4.text = question.option_four
    }

    //sets style for selected option
    fun setOptionStyle() {
        var optionList: ArrayList<TextView> = arrayListOf()
        optionList.add(0, opt_1)
        optionList.add(1, opt_2)
        optionList.add(2, opt_3)
        optionList.add(3, opt_4)

        for (op in optionList) {
            op.setTextColor(Color.parseColor("#555151"))
            op.background = ContextCompat.getDrawable(this, R.drawable.question_option)
            op.typeface = Typeface.DEFAULT
        }

    }

    //sets color for selected option, stroke color to purple and text to black from grey
    fun selectedOptionStyle(view: TextView, opt: Int) {
        setOptionStyle() //called this to reset the changes if we click once again on another option
        selectOption = opt

        view.background = ContextCompat.getDrawable(this, R.drawable.selected_question_option)
        view.typeface = Typeface.DEFAULT_BOLD
        view.setTextColor(Color.parseColor("#000000"))
    }
}
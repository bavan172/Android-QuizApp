package com.example.quizapp

object setData {

    const val name:String = "name"
    const val score:String = "score"

    fun getQuestion():ArrayList<QuestionData>{

        var que:ArrayList<QuestionData> = arrayListOf()

        var question1 = QuestionData(
            "What is the capital of India?",
            1,
            "Karnataka",
            "Mumbai",
            "Kerala",
            "Delhi",
            4
        )

        var question2 = QuestionData(
            "Who was the first indian women in space?",
            2,
            "Kalpana Chawla",
            "Sunitha williams",
            "Konery Humpy",
            "None of the above",
            1
        )

        var question3 = QuestionData(
            "Who wrote national anthem?",
            3,
            "Arjit Singh",
            "Shreya goshal",
            "Rabindranath Tagore",
            "Badshah",
            3
        )

        var question4 = QuestionData(
            "Who was the first president of India?",
            4,
            "Donald Trumph",
            "Kumara Swamy",
            "Rajendra Prasad",
            "Zakir Khan",
            3
        )

        var question5 = QuestionData(
            "Who built the Jama Mashid?",
            5,
            "Tony Stark",
            "Akbar",
            "Bob the builder",
            "Shah Jahan",
            4
        )
        que.add(question1)
        que.add(question2)
        que.add(question3)
        que.add(question4)
        que.add(question5)

        return que
    }
}
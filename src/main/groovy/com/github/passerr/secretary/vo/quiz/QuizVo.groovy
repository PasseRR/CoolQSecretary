package com.github.passerr.secretary.vo.quiz

import com.google.gson.annotations.SerializedName

/**
 * 竞猜题目
 * @date 2021/07/29 17:23
 * @Copyright (c) wisewe co.,ltd
 * @author xiehai
 */
class QuizVo {
    String type
    int answer
    String[] options
    String school
    @SerializedName("quiz")
    String question
    @SerializedName("answer_str")
    String answerStr
}

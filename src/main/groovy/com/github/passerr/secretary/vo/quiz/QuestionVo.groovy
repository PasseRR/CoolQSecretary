package com.github.passerr.secretary.vo.quiz

/**
 * 问题
 * @date 2021/07/29 19:18
 * @Copyright (c) wisewe co.,ltd
 * @author xiehai
 */
class QuestionVo {
    String type
    String question
    List<String> options
    int ans

    QuestionVo(QuizVo quizVo) {
        this.type = quizVo.type
        this.question = quizVo.question
        this.options = new ArrayList<>(Arrays.asList(quizVo.options))
        Collections.shuffle(this.options)
        this.ans = this.options.indexOf(quizVo.answerStr)
    }

    String toQuestion(int num, int total) {
        String.format("%d/%d [%s] %s\n%s", num + 1, total, this.type, this.question, this.toOptions())
    }

    String toOptions() {
        StringBuilder sb = new StringBuilder()
        0.upto(3) { it ->
            sb << String.format("%c、%s\n", (('A' as char) + it) as char, this.options[it as int])
        }
        sb.deleteCharAt(sb.length() - 1)

        sb.toString()
    }

    char answer() {
        (('A' as char) + this.ans) as char
    }

    static main(agrs) {
        println (1 + 'A' as char) as char
    }
}

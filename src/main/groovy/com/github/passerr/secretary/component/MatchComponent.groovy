package com.github.passerr.secretary.component

import com.github.passerr.secretary.vo.cool.SendAllMessageReq
import com.github.passerr.secretary.vo.quiz.MatchOrderVo
import com.github.passerr.secretary.vo.quiz.QuestionVo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import java.util.concurrent.TimeUnit
import java.util.stream.Collectors

/**
 * 随机出题机器
 * @date 2021/07/29 18:39
 * @Copyright (c) wisewe co.,ltd
 * @author xiehai
 */
@Component
class MatchComponent {
    @Autowired
    QuizComponent quizComponent
    @Autowired
    SendMessageComponent sendMessageComponent

    Map<Long, Match> holder = [:]

    String type(String s) {
        "可用答题分类有" + quizComponent.types.join("、")
    }

    /**
     * 发起答题比赛
     * @param orderVo 命令
     * @return 答题回应消息
     */
    String start(MatchOrderVo orderVo) {
        if (holder.containsKey(orderVo.groupId)) {
            return null
        }

        new Thread() {
            @Override
            void run() {
                def match = new Match(orderVo.groupId, quizComponent.random(orderVo.type))
                holder.put(orderVo.groupId, match)
                match.signUp()
            }
        }.start()

        String type = orderVo.type && this.quizComponent.index(orderVo.type) ? orderVo.type : "随机"

        "${type}答题比赛即将开始，请要参加比赛的选手在10秒内回复任意内容参赛"
    }

    boolean isInQuiz(long groupId) {
        this.holder.containsKey(groupId)
    }

    void answer(long groupId, String message, long userId) {
        def match = this.holder.get(groupId)
        // 比赛不存在或者已结束
        if (!match || match.isFinished) {
            return
        }

        // 比赛报名
        if (!match.isSignUp) {
            match.sign(userId)
            return
        }

        // 比赛答题
        char answer = message.charAt(0).toUpperCase()
        if (answer >= ('A' as char) && answer <= ('D' as char) && match.players.containsKey(userId)) {
            match.answer(answer - ('A' as char), userId)
        }
    }

    class Match {
        long groupId
        Map<Long, Integer> players = [:]
        Map<Long, Integer> paper = [:]
        List<QuestionVo> questions
        int index = 0
        boolean isSignUp = false
        boolean isFinished = false

        Match(long groupId, List<QuestionVo> questions) {
            this.groupId = groupId
            this.questions = questions
        }

        void signUp() {
            TimeUnit.SECONDS.sleep(10)
            if (this.players.isEmpty()) {
                this.finish()
            } else {
                this.isSignUp = true
                sendMessageComponent.sendMsg(
                    new SendAllMessageReq(
                        groupId: this.groupId,
                        message: String.format("比赛即将开始，请%s做好准备", this.players.keySet().collect { it -> "[CQ:at,qq=${it}]" as String }.join(""))
                    )
                )
                TimeUnit.SECONDS.sleep(3)
                this.run()
            }
        }

        void run() {
            while (!this.isFinished) {
                this.paper.each { it ->
                    this.players.merge(it.key, it.value, Integer.&sum)
                }
                this.paper.clear()
                def question = this.questions.get(this.index)
                def msgId = sendMessageComponent.sendMsg(new SendAllMessageReq(
                    groupId: this.groupId,
                    message: question.toQuestion(this.index, this.questions.size())
                ))
                int retry = 0
                while (retry++ < 200 && this.players.size() != this.paper.size()) {
                    TimeUnit.MILLISECONDS.sleep(200)
                }
                sendMessageComponent.sendMsg(
                    new SendAllMessageReq(
                        groupId: this.groupId,
                        message: String.format("[CQ:reply,id=%d] 正确答案是%c %s", msgId, question.answer(), this.answer())
                    )
                )
                this.index++
                TimeUnit.MILLISECONDS.sleep(200)
                if (this.index == questions.size()) {
                    this.finish()
                }
            }
        }

        String answer() {
            def set = this.paper.values().findAll { it -> it > 0 }
            if (set) {
                return "有${set.size()}人回答正确"
            } else {
                return "很遗憾 没有人回答正确"
            }
        }

        void finish() {
            this.isFinished = true
            holder.remove(this.groupId)

            if (!this.isSignUp) {
                sendMessageComponent.sendMsgAsync(
                    new SendAllMessageReq(
                        groupId: this.groupId,
                        message: "无人报名答题比赛，比赛自动结束"
                    )
                )
                return
            }

            StringBuilder sb = new StringBuilder("答题结束")
            int[] faces = [123, 79, 124, 120]

            def all = this.players.entrySet().findAll { it -> it.value > 0 }
            if (all) {
                sb << "\n"
                all.stream()
                    .collect(Collectors.groupingBy({ Map.Entry<Long, Integer> it -> it.value }))
                    .entrySet()
                    .sort({ a, b -> b.key <=> a.key })
                    .eachWithIndex { Map.Entry<Integer, List<Map.Entry<Long, Integer>>> entry, int i ->
                        sb << "[CQ:face,id=${faces[i < 3 ? i : 3]}]"
                        sb << entry.value.collect { it -> "[CQ:at,qq=${it.key}]" }.join("")
                        sb << "共答对${entry.key}道题"
                        sb << "\n"
                    }

                sb.deleteCharAt(sb.length() - 1)
            } else {
                sb << " 所有选手都是交的白卷"
            }

            sendMessageComponent.sendMsgAsync(
                new SendAllMessageReq(
                    groupId: this.groupId,
                    message: sb.toString()
                )
            )
        }

        void sign(long userId) {
            this.players.put(userId, 0)
        }

        void answer(int asn, long userId) {
            this.paper.put(userId, (this.questions[this.index].ans == asn) ? 1 : 0)
        }
    }
}

package com.github.passerr.secretary.component

import com.github.passerr.secretary.vo.quiz.QuestionVo
import com.github.passerr.secretary.vo.quiz.QuizVo
import com.google.gson.Gson
import groovy.transform.PackageScope
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component

import javax.annotation.PostConstruct
import java.nio.charset.StandardCharsets

/**
 * 题目解析
 * @date 2021/07/29 17:22
 * @Copyright (c) wisewe co.,ltd
 * @author xiehai
 */
@Component
@Slf4j
@PackageScope
class QuizComponent {
    @Autowired
    Gson gson
    List<String> types = []
    int[][] typeRange
    List<QuizVo> quizzes = []
    Random random = new Random()

    @PostConstruct
    void init() {
        long now = System.currentTimeMillis()
        log.debug("开始初始化题库...")
        Map<String, List<QuizVo>> map = [:]
        Scanner scan = new Scanner(new ClassPathResource("quizzes.json").getInputStream(), StandardCharsets.UTF_8.name())
        while (scan.hasNextLine()) {
            String it = scan.nextLine()
            def quiz = this.gson.fromJson(it, QuizVo)
            map.merge(quiz.type, [quiz], { o, n ->
                o.addAll(n)
                o
            })
        }
        this.typeRange = new int[map.size()][3]
        int pre = 0
        map.entrySet()
            .sort { a, b -> b.value.size() <=> a.value.size() }
            .eachWithIndex { Map.Entry<String, List<QuizVo>> entry, int i ->
                int size = entry.value.size()
                this.types << entry.key
                this.typeRange[i][0] = pre
                this.typeRange[i][1] = size + pre
                this.typeRange[i][2] = size
                pre += size
                quizzes.addAll(entry.value)
            }
        this.types.eachWithIndex { String type, int i ->
            log.debug("${type}共${this.typeRange[i][2]}题")
        }
        log.debug("题库合计类型{}种, 共{}题, 解析总计耗时{}ms", types.size(), quizzes.size(), System.currentTimeMillis() - now)
        log.debug("题库初始化完成...")
    }

    List<QuestionVo> random(int cnt = 10) {
        this.random(0, this.quizzes.size(), cnt)
    }

    List<QuestionVo> random(String type, int cnt = 10) {
        def index = this.types.indexOf(type)

        if (index < 0) {
            return this.random(cnt)
        }

        this.random(this.typeRange[index][0], this.typeRange[index][1], cnt)
    }

    int index(String type) {
        this.types.indexOf(type)
    }

    List<QuestionVo> random(int from, int to, int cnt) {
        Set<Integer> set = []
        while (set.size() != cnt) {
            set.add(this.next(from, to))
        }

        set.sort { a, b -> a <=> b }
            .collect { it -> new QuestionVo(this.quizzes[it]) }
    }


    int next(int from, int to) {
        this.random.nextInt(to - from - 1) + from
    }
}

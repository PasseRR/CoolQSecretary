package com.github.passerr.secretary.vo.gitlab

import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * 部署任务信息
 * @author xiehai
 * @date 2019/02/18 13:52
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
class ObjectAttributesVo {
    /**
     * pipeline编号
     */
    Long id
    /**
     * 任务状态
     */
    String status
    /**
     * 任务开始时间
     */
    String createdAt
    /**
     * 任务结束时间
     */
    String finishedAt

    String cost() {
        def pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss 'UTC'")
        def start = LocalDateTime.parse(this.createdAt, pattern)
        def end = LocalDateTime.parse(this.finishedAt, pattern)
        def between = (Duration.between(start, end).toMillis() / 1000) as int
        return String.format("%d分%d秒", (between / 60) as int, between % 60)
    }
}

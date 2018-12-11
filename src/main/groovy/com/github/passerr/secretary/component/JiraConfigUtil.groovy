package com.github.passerr.secretary.component

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

/**
 * @author xiehai
 * @date 2018/12/11 10:14
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
@Component
class JiraConfigUtil {
    /**
     * bug的issue类型
     */
    static String bugTypeIds
    /**
     * 任务/bug完成节点
     */
    static String doneTransitions

    private JiraConfigUtil() {

    }

    /**
     * 是否是bug
     * @param typeId 类型id
     * @return true/false
     */
    static boolean isBug(String typeId) {
        return Arrays.asList(bugTypeIds.split(",")).contains(typeId)
    }

    /**
     * 任务/bug是否完成
     * @param transitionId 节点id
     * @return true/false
     */
    static boolean isDone(String transitionId) {
        return Arrays.asList(doneTransitions.split(",")).contains(transitionId)
    }

    @Value("\${secretary.jira.bugTypeIds}")
    void setBugTypeIds(String bugTypeIds) {
        JiraConfigUtil.bugTypeIds = bugTypeIds
    }

    @Value("\${secretary.jira.doneTransitions}")
    void setDoneTransitions(String doneTransitions) {
        JiraConfigUtil.doneTransitions = doneTransitions
    }
}

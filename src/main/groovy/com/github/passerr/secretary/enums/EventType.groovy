package com.github.passerr.secretary.enums
/**
 * 消息类型
 * @author xiehai
 * @date 2018/12/04 15:40
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
enum EventType {
    /**
     * 创建问题
     */
    CREATE("issue_created", "创建"),
    /**
     * 更新问题
     */
    UPDATE("issue_updated", "更新"),
    /**
     * 备注问题
     */
    REMARK("issue_commented", "备注")

    String type
    String name

    EventType(String type, String name) {
        this.type = type
        this.name = name
    }

    static EventType get(String type) {
        return Arrays.stream(values())
                     .filter({ item -> (item.getType() == type) })
                     .findFirst()
                     .orElse(UPDATE)
    }
}
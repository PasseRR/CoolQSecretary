package com.github.passerr.secretary.vo.gitlab

/**
 * gitlab 钩子实体
 * @author xiehai
 * @date 2018/12/04 22:14
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
class GitlabVo {
    String eventName
    String userName
    String ref
    ProjectVo project

    String message() {
        return String.format(
            "【%s】在【%s(%s)】的【%s】分支进行了【%s】，请注意本地代码及时合并！",
            this.userName,
            this.project.getName(),
            this.project.getHttpUrl(),
            this.getBranch(),
            this.eventName
        )
    }

    private String getBranch() {
        return this.ref.substring(this.ref.lastIndexOf("/") + 1, this.ref.length())
    }
}

package com.github.passerr.secretary.vo.gitlab

import groovy.util.logging.Slf4j

/**
 * gitlab 钩子实体
 * @author xiehai
 * @date 2018/12/04 22:14
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
@Slf4j
class GitlabVo {
    // 事件类型
    String objectKind
    String userName
    String ref
    // 执行部署人
    UserVo user
    // 部署信息
    ObjectAttributesVo objectAttributes
    // 项目名称
    ProjectVo project
    // job信息
    List<BuildVo> builds

    String message() {
        switch (this.objectKind) {
            case "push":
                // push事件
                return String.format(
                    "【%s】在【%s(%s)】的【%s】分支进行了【%s】，请注意本地代码及时合并！",
                    this.userName,
                    this.project.getName(),
                    this.project.getHttpUrl(),
                    this.getBranch(),
                    this.objectKind
                )
            case "pipeline":
                // pipeline事件
                switch (this.objectAttributes.status) {
                    case "pending":
                        return String.format(
                            "【%s】创建了【%s】部署任务【#%s】!",
                            this.user.name,
                            this.getJobs(),
                            this.objectAttributes.id
                        )
                    case "running":
                        return String.format("任务【#%s】开始执行!", this.objectAttributes.id)
                    case "success":
                        return String.format(
                            "任务【#%s】执行完成, 耗时%s!",
                            this.objectAttributes.id,
                            this.objectAttributes.cost()
                        )
                    case "failed":
                        return String.format("任务【#%s】执行失败!", this.objectAttributes.id)
                    case "canceled":
                        return String.format("任务【#%s】已被【%s】取消!", this.objectAttributes.id, this.user.name)
                    default:
                        log.debug("未知的pipeline状态:{}", this.objectAttributes.status)
                }
        }
    }

    private String getBranch() {
        return this.ref.substring(this.ref.lastIndexOf("/") + 1, this.ref.length())
    }

    /**
     * 获得部署执行的任务
     * @return 任务列表
     */
    private String getJobs() {
        StringBuilder sb = new StringBuilder()
        this.builds.each {
            sb.append(it.name)
            sb.append(",")
        }

        return sb.substring(0, sb.length() - 1)
    }
}

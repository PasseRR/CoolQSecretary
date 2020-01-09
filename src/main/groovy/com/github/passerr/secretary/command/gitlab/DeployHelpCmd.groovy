package com.github.passerr.secretary.command.gitlab

import com.github.passerr.secretary.command.Command
import com.github.passerr.secretary.vo.cool.MessageReq
import com.github.passerr.secretary.vo.gitlab.DeployMeta

import java.util.regex.Matcher

/**
 * 部署帮助命令
 * @date 2020/01/09 17:21
 * @Copyright (c) wisewe co.,ltd
 * @author xiehai
 */
class DeployHelpCmd extends Command {
    /**
     * 可用任务列表
     */
    Map<String, DeployMeta> metas

    DeployHelpCmd(Map<String, DeployMeta> metas) {
        this.metas = metas
    }

    @Override
    String execute(MessageReq messageReq) {
        StringBuilder sb = new StringBuilder("任务部署对照关系，确保任务名在以下列表中，如英文命令【deploy 成铁教务】，中文命名【部署 成铁教务】\n")
        this.metas.each { key, value ->
            sb << key
            sb << "\t"
            sb << value.desc
            sb << "\n"
        }
        sb.substring(0, sb.length() - 1)
    }

    @Override
    protected Matcher matchCn(String message) {
        return message =~ /部署帮助/
    }

    @Override
    protected Matcher matchEn(String message) {
        return message =~ /dhelp/
    }
}

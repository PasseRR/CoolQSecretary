package com.github.passerr.secretary.command.jira

import com.github.passerr.secretary.command.Command
import com.github.passerr.secretary.vo.cool.MessageReq
import com.github.passerr.secretary.vo.jira.ReleaseIssueVo

import java.util.function.Function
import java.util.regex.Matcher

/**
 * 问题发布命令
 * @author xiehai
 * @date 2019/01/23 16:59
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
class ReleaseIssueCmd extends Command<String> {
    ReleaseIssueCmd(Function<ReleaseIssueVo, String> function) {
        super(function)
    }

    @Override
    String execute(MessageReq messageReq) {
        def matchCn = this.matchCn(messageReq.getLegalMessage())
        def matchEn = this.matchEn(messageReq.getLegalMessage())
        def match = matchCn.matches() ? matchCn : matchEn

        super.executor.apply(new ReleaseIssueVo(
            start: match.group(1),
            end: match.group(2),
            env: match.group(3),
            releaseTime: match.group(4)
        ))
    }

    @Override
    protected Matcher matchCn(String message) {
        message =~ /问题发布 ([\d]{4}\/[\d]{1,2}\/[\d]{1,2} [\d]{1,2}:[\d]{1,2}) \
([\d]{4}\/[\d]{1,2}\/[\d]{1,2} [\d]{1,2}:[\d]{1,2}) ([\s\S]+) ([\d]{1,2}:[\d]{1,2})/
    }

    @Override
    protected Matcher matchEn(String message) {
        message =~ /release ([\d]{4}\/[\d]{1,2}\/[\d]{1,2} [\d]{1,2}:[\d]{1,2}) \
([\d]{4}\/[\d]{1,2}\/[\d]{1,2} [\d]{1,2}:[\d]{1,2}) ([\s\S]+) ([\d]{1,2}:[\d]{1,2})/
    }
}

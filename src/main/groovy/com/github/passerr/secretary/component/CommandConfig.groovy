package com.github.passerr.secretary.component

import com.github.passerr.secretary.command.Command
import com.github.passerr.secretary.command.itpk.PasswordCmd
import com.github.passerr.secretary.command.itpk.QuestionAnswerCmd
import com.github.passerr.secretary.command.itpk.RandomReplyCmd
import com.github.passerr.secretary.command.jira.*
import com.github.passerr.secretary.command.robot.HelpCmd
import com.github.passerr.secretary.command.robot.PhraseCmd
import com.github.passerr.secretary.command.robot.TranslateCmd
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * 机器人命令定义
 * @author xiehai
 * @date 2018/12/27 10:44
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
@Configuration
class CommandConfig {
    @Autowired
    ItpkSettingComponent itpkSettingComponent
    @Autowired
    JiraComponent jiraComponent
    @Autowired
    ItpkComponent itpkComponent
    @Autowired
    BaiduComponent baiduComponent

    @Bean
    List<Command<?>> commands() {
        Arrays.asList(
            // 帮助命令
            new HelpCmd(null),
            // jira 命令
            new IssueCmd(this.jiraComponent.&userIssue),
            new TaskCmd(this.jiraComponent.&userTask),
            new BugCmd(this.jiraComponent.&userBug),
            new DetailCmd(this.jiraComponent.&issueDetail),
            new RemarkCmd(this.jiraComponent.&issueComment),
            // 机器人设置命令
            new PasswordCmd(this.itpkSettingComponent.&login),
            new QuestionAnswerCmd(this.itpkSettingComponent.&study),
            new RandomReplyCmd(this.itpkSettingComponent.&randomReply),
            // 聊天命令
            new PhraseCmd(this.itpkComponent.&phrase),
            // 翻译命令
            new TranslateCmd(this.baiduComponent.&translate)
        )
    }
}

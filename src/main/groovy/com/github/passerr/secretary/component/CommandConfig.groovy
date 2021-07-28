package com.github.passerr.secretary.component

import com.github.passerr.secretary.command.Command
import com.github.passerr.secretary.command.itpk.PasswordCmd
import com.github.passerr.secretary.command.itpk.QuestionAnswerCmd
import com.github.passerr.secretary.command.itpk.RandomReplyCmd
import com.github.passerr.secretary.command.jira.BugCmd
import com.github.passerr.secretary.command.jira.DetailCmd
import com.github.passerr.secretary.command.jira.IssueCmd
import com.github.passerr.secretary.command.jira.ReleaseIssueCmd
import com.github.passerr.secretary.command.jira.RemarkCmd
import com.github.passerr.secretary.command.jira.TaskCmd
import com.github.passerr.secretary.command.robot.HelpCmd
import com.github.passerr.secretary.command.robot.PhraseCmd
import com.github.passerr.secretary.command.robot.TranslateCmd
import com.github.passerr.secretary.command.robot.TranslateHelpCmd
import com.github.passerr.secretary.vo.gitlab.DeployMeta
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration 
/**
 * 机器人命令定义
 * @author xiehai* @date 2018/12/27 10:44
 * @Copyright (c) tellyes tech. inc. co.,ltd
 */
@Configuration
class CommandConfig {
    @Autowired
    ItpkSettingComponent itpkSettingComponent
    @Autowired
    JiraComponent jiraComponent
    @Autowired
    GitlabComponent gitlabComponent
    @Autowired
    ItpkComponent itpkComponent
    @Autowired
    BaiduComponent baiduComponent
    @Autowired
    WorkGroupComponent workGroupComponent
    @Autowired
    @Qualifier("deployMetaMap")
    Map<String, DeployMeta> deployMetaMap

    @Bean
    List<Command<?>> commands() {
        Arrays.asList(
            // 帮助命令
            new HelpCmd(),
            // jira 命令
            new IssueCmd(this.jiraComponent.&userIssue),
            new TaskCmd(this.jiraComponent.&userTask),
            new BugCmd(this.jiraComponent.&userBug),
            new DetailCmd(this.jiraComponent.&issueDetail),
            new RemarkCmd(this.jiraComponent.&issueComment),
            new ReleaseIssueCmd(this.workGroupComponent.&release),
            // 机器人设置命令
            new PasswordCmd(this.itpkSettingComponent.&login),
            new QuestionAnswerCmd(this.itpkSettingComponent.&study),
            new RandomReplyCmd(this.itpkSettingComponent.&randomReply),
            // 翻译命令
            new TranslateCmd(this.baiduComponent.&translate),
            // 翻译帮助命令
            new TranslateHelpCmd(),
//            // 部署帮助命令
//            new DeployHelpCmd(this.deployMetaMap),
//            // 部署命令
//            new DeployCmd(this.gitlabComponent.&deploy),
            // 成语接龙 必须在四个字命令之后
            new PhraseCmd(this.itpkComponent.&phrase)
        )
    }
}

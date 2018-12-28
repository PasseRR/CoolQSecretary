package com.github.passerr.secretary.command.robot

import com.github.passerr.secretary.command.Command
import com.github.passerr.secretary.vo.cool.MessageReq

import java.util.regex.Matcher

/**
 * 翻译帮助命令
 * @author xiehai
 * @date 2018/12/28 13:51
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
class TranslateHelpCmd extends Command {
    @Override
    protected Matcher matchCn(String message) {
        message =~ /翻译帮助/
    }

    @Override
    protected Matcher matchEn(String message) {
        message =~ /thelp/
    }

    @Override
    String execute(MessageReq messageReq) {
        StringBuilder sb = new StringBuilder("语言对照关系，可用中文也可以用英文，如你好的英文【en 你好】，hello的英文【中文 hello】\n")
        TranslateCmd.SUPPORT_EN.eachWithIndex { String entry, int i ->
            sb.append("${entry}\t${TranslateCmd.SUPPORT_CN.get(i)}\n")
        }

        sb.substring(0, sb.length() - 1)
    }
}

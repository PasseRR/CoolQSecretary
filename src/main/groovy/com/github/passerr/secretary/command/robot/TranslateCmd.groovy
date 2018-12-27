package com.github.passerr.secretary.command.robot

import com.github.passerr.secretary.command.Command
import com.github.passerr.secretary.vo.baidu.TranslateReq
import com.github.passerr.secretary.vo.cool.MessageReq

import java.util.function.Function
import java.util.regex.Matcher
import java.util.stream.Collectors

/**
 * 百度翻译
 * @author xiehai
 * @date 2018/12/27 17:41
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
class TranslateCmd extends Command<TranslateReq> {
    private static final List<String> SUPPORT_EN =
        Arrays.asList(
            "zh", "en", "yue", "wyw", "jp", "kor", "fra", "spa", "th", "ara", "ru", "pt", "de", "it",
            "el", "nl", "pl", "bul", "est", "dan", "fin", "cs", "rom", "slo", "swe", "hu", "cht", "vie"
        )
    private static final List<String> SUPPORT_CN =
        Arrays.asList(
            "中文", "英语", "粤语", "文言文", "日语", "韩语", "法语", "西班牙语", "泰语", "阿拉伯语", "俄语", "葡萄牙语",
            "德语", "意大利语", "希腊语", "荷兰语", "波兰语", "保加利亚语", "爱沙尼亚语", "丹麦语", "芬兰语", "捷克语",
            "罗马尼亚语", "斯洛文尼亚语", "瑞典语", "匈牙利语", "繁体中文", "越南语"
        )

    TranslateCmd(Function<TranslateReq, String> function) {
        super(function)
    }

    @Override
    protected Matcher matchCn(String message) {
        message =~ /(${SUPPORT_CN.stream().collect(Collectors.joining("|"))}) ([\s\S]+)/
    }

    @Override
    protected Matcher matchEn(String message) {
        message =~ /(${SUPPORT_EN.stream().collect(Collectors.joining("|"))}) ([\s\S]+)/
    }

    @Override
    String execute(MessageReq messageReq) {
        def to = ""
        def source = ""
        def message = messageReq.getLegalMessage()
        def matchCn = this.matchCn(message)
        def matchEn = this.matchEn(message)
        if (matchCn.matches()) {
            to = SUPPORT_EN.get(SUPPORT_CN.indexOf(matchCn.group(1)))
            source = matchCn.group(2)
        } else if (matchEn.matches()) {
            to = matchEn.group(1)
            source = matchEn.group(2)
        }

        super.executor.apply(TranslateReq.builder().to(to).source(source).build())
    }
}

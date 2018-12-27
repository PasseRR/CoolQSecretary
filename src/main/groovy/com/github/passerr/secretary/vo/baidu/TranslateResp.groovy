package com.github.passerr.secretary.vo.baidu

import groovy.json.StringEscapeUtils
/**
 * 拜服翻译应答
 * @author xiehai
 * @date 2018/12/27 16:25
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
class TranslateResp {
    /**
     * 源语言
     */
    String from
    /**
     * 目标语言
     */
    String to
    /**
     * 翻译结果
     */
    List<TranslateResult> transResult

    String toMessage() {
        // 结果使用分号分隔
        Optional.ofNullable(this.transResult)
                .filter({ it -> it.size() > 0 })
                .map({ it ->
                    StringBuilder sb = new StringBuilder()
                    it.each {tr->sb.append("${tr.getDst()}\n")}
                    sb.substring(0, sb.length()-1)
                 })
                 .orElse("")
    }
}

class TranslateResult {
    /**
     * 源语言
     */
    String src
    /**
     * 目标语言
     */
    String dst

    void setDst(String dst) {
        // unicode解码
        this.dst = StringEscapeUtils.unescapeJava(dst)
    }
}

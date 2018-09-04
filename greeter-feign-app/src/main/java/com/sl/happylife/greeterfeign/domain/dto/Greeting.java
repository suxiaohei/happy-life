package com.sl.happylife.greeterfeign.domain.dto;

/**
 * 逻辑业务类
 *
 * @author suxin
 */
public class Greeting {

    private String content;

    public Greeting() {
    }

    public Greeting(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

}

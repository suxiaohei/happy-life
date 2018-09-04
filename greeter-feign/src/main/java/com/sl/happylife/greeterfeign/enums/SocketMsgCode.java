package com.sl.happylife.greeterfeign.enums;

import java.util.Arrays;
import java.util.Optional;

public enum SocketMsgCode {

    //注册
    INIT(0, "注册"),
    //其他
    OTHER(9999, "其他");

    private Integer msgCode;
    private String desc;

    SocketMsgCode(Integer msgCode, String desc) {
        this.msgCode = msgCode;
        this.desc = desc;
    }

    public static SocketMsgCode getSocketMsgCodeByMsgCode(Integer msgCode) {
        Optional<SocketMsgCode> socketMsgCodeOpt = Arrays.stream(SocketMsgCode.values())
                .filter(x -> x.getMsgCode().equals(msgCode))
                .findFirst();

        if (!socketMsgCodeOpt.isPresent()) {
            return null;
        }

        return socketMsgCodeOpt.get();
    }

    public Integer getMsgCode() {
        return msgCode;
    }

    public String getDesc() {
        return desc;
    }
}

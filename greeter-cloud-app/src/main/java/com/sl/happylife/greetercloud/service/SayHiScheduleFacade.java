package com.sl.happylife.greetercloud.service;

import com.alibaba.fastjson.JSONObject;

/**
 * SayHi 接口
 *
 * @author suxin
 */
public interface SayHiScheduleFacade {

    /**
     * sayHi
     *
     * @param params 参数
     * @return 结果
     */
    String sayHi(JSONObject params);
}

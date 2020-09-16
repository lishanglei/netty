package com.netty.test;

import io.netty.util.NettyRuntime;
import io.netty.util.internal.SystemPropertyUtil;

/**
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/9/11
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/9/11              lishanglei      v1.0.0           Created
 */
public class Test {

    public static void main(String[] args) {

        //当前cpu内核数*2*2
        int max = Math.max(1, SystemPropertyUtil.getInt(
                "io.netty.eventLoopThreads", NettyRuntime.availableProcessors() * 2));

        System.out.println(max);
    }
}

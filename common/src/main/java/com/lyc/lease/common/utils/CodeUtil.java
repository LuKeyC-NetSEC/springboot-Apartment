package com.lyc.lease.common.utils;

import java.util.Random;

/**
 * @ClassName CodeUtil
 * @Description TODO 随机字符生成器
 * @Author LuKey_C
 * @Date 2025/2/10 10:55
 * @Version 1.0
 */
public class CodeUtil {

    /**
     * 生成随机数字串
     *
     * @param length 随机数字串的长度
     * @return 生成的随机数字串
     */
    public static String getRandomCode(Integer length){
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int num = random.nextInt(10);
            builder.append(num);
        }
        return builder.toString();
    }
}

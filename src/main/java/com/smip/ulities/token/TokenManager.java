package com.smip.ulities.token;

import org.springframework.stereotype.Component;

public interface TokenManager {
    /**
     * 创建一个token关联上指定用户
     * @param userId 指定用户的id(smip userid是int类型，打算之后转为long类型)
     * @return 生成的token
     */
    public TokenModel createToken(int userId);

    /**
     * 检查token是否有效
     * @param model token
     * @return 是否有效
     */
    public boolean checkToken(TokenModel model);

    /**
     * 从字符串中解析token
     * @param authentication 加密后的字符串
     * @return
     */
    public TokenModel getToken(String authentication);

    /**
     * 清除token
     * @param userId 登录用户的id
     */
    public void deleteToken(int userId);
}

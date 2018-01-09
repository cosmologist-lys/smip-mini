package com.smip.service.sys;

import com.smip.entity.sys.Secuser;
import com.smip.service.BaseService;
import com.smip.ulities.Q;
import com.smip.ulities.Q_Cipher;
import com.smip.ulities.SysConst;
import org.springframework.cache.annotation.Cacheable;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;


public interface SecuserService extends BaseService<Secuser> {

    Secuser findByName(String username);

    /**
     * 第一次请求，从list里根据用户名密码查找对应secuser
     * 成功，生成token，并且存储为一个key=token,value=secuser的map
     * 失败不存储map
     *
     * @param username
     * @param psw
     * @return token
     */
    @Cacheable(value = "user_valid", key = "#username")
    default String validUser(String username, String psw) {
        System.err.println(username + "===" + psw);
        String encryptedPsw = Q_Cipher.md5(psw);
        String token = null;
        if (Q.notNull(SysConst.SYS_SECUSER_LIST)) {
            Optional<Secuser> secuser = SysConst.SYS_SECUSER_LIST.stream()
                    .filter((user) -> user.getPassWord().equals(encryptedPsw))
                    .filter((user) -> user.getUserName().toLowerCase().equals(username.toLowerCase()))
                    .findFirst();
            if (secuser.isPresent()) {
                Secuser _secuser = secuser.get();
                token = UUID.randomUUID().toString();
                SysConst.SYS_SECUSER_TOKEN.put(token, _secuser);
            }
        }
        return token;
    }

    /**
     * 非第一次请求，携带token的请求
     * 根据key=token查找map里是否存在该用户
     *
     * @param token
     * @return boolean
     */
    default boolean validToken(String token) {
        Map map = SysConst.SYS_SECUSER_TOKEN;
        if (Q.notNull(map)) {
            Secuser user = (Secuser) map.get(token);
            if (Q.notNull(user))
                return true;
        }
        return false;
    }


}

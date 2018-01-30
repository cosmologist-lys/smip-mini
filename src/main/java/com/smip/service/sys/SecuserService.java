package com.smip.service.sys;

import com.smip.entity.json.ConJson;
import com.smip.entity.json.UserJson;
import com.smip.entity.sys.Secuser;
import com.smip.enums.DateFmt;
import com.smip.service.BaseService;
import com.smip.ulities.Q;
import com.smip.ulities.Q_Cipher;
import com.smip.ulities.SysConst;
import org.springframework.cache.annotation.Cacheable;

import java.util.*;


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
    @Cacheable(value = "user_valid", key = "#username+#psw")
    default UserJson validUser(String username, String psw) {
        System.err.println(username + "===" + psw);
        String encryptedPsw = Q_Cipher.md5(psw);
        if (Q.notNull(SysConst.SYS_SECUSER_LIST)) {
            Optional<Secuser> secuser = SysConst.SYS_SECUSER_LIST.stream()
                    .filter((user) -> user.getPassWord().equals(encryptedPsw))
                    .filter((user) -> user.getUserName().toLowerCase().equals(username.toLowerCase()))
                    .findFirst();
            if (secuser.isPresent()) {
                Secuser _secuser = secuser.get();
                String token = UUID.randomUUID().toString().replace("-", "");
                token = Q_Cipher.lysEnigma(token);
                return new UserJson(token, _secuser);
            }
        }
        return null;
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
            UserJson userJson = (UserJson) map.get(token);
            if (Q.notNull(userJson)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 第一次请求存userjson到map
     *
     * @param userJson
     * @param conJson
     */
    default void saveToken_login(UserJson userJson, ConJson conJson) {
        UserJson newJson = new UserJson(conJson, userJson.getSecuser());
        ArrayList list = new ArrayList();
        list.add(conJson.getRequst().getUri());
        newJson.setUris(list);
        SysConst.SYS_SECUSER_TOKEN.put(
                userJson.get_token(),newJson );
    }

    /**
     * 非第一次请求存userjson到map
     * @param conJson,token
     */
    default void saveToken_conns(String token, ConJson conJson) {
        UserJson userJson = SysConst.SYS_SECUSER_TOKEN.get(token);
        try {
            userJson.set_comtick(userJson.get_comtick() + 1)
                    .setLastReqtime(Q.getDateString(new Date(), DateFmt.CUST))
                    .getUris().add(conJson.getRequst().getUri());
        }catch (Exception e){
            e.printStackTrace();
        }
        if (Q.notNull(userJson)) {
            SysConst.SYS_SECUSER_TOKEN.put(token, userJson);
        }
    }

    /**
     * vue端退出登陆,清空map
     *
     * @param token
     */
    default void clearToken(String token) {
        Map map = SysConst.SYS_SECUSER_TOKEN;
        if (Q.notNull(map, token)) {
            map.remove(token);
        }
    }


}

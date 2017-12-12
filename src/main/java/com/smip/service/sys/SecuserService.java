package com.smip.service.sys;

import com.smip.entity.sys.Secuser;
import com.smip.service.BaseService;
import com.smip.ulities.Q;
import com.smip.ulities.Q_Cipher;
import com.smip.ulities.SysConst;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Optional;


public interface SecuserService extends BaseService<Secuser> {

    Secuser findByName(String username);

    @Cacheable(value = "user_valid")
    default boolean validUser(String username,String psw){
        String encryptedPsw = Q_Cipher.md5(psw);
        if (Q.notNull(SysConst.SYS_SECUSER_LIST)){
            return
                Q.notNull(SysConst.SYS_SECUSER_LIST.stream()
                        .filter((user)->user.getPassWord().equals(encryptedPsw))
                        .filter((user)->user.getUserName().toLowerCase().equals(username.toLowerCase()))
                        .findFirst().get());
            }
        return false;
        }
}

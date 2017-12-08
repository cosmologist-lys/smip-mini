package com.smip.handlers;
import com.smip.service.sys.SecuserService;
import com.smip.ulities.SysConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;



/**
 * 运行前加载静态常量
 */
@Component
@Order(value = 0)
public class Sysload implements CommandLineRunner {

    @Autowired
    private SecuserService secuserService;

    @Override
    public void run(String... strings) throws Exception {
        secuserService.findAll().forEach(u->SysConst.SYS_SECUSERS_MAP.put(u.getUserName(),u));
        SysConst.SYS_SECUSER_LIST = secuserService.findAll();
    }
}

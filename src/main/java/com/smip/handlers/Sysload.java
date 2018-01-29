package com.smip.handlers;
import com.smip.service.sys.SecuserService;
import com.smip.ulities.SysConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;



/**
 * 运行前加载静态常量
 */
@Component
@Order(value = 0)
public class Sysload implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private SecuserService secuserService;

    public Sysload(SecuserService service){
        this.secuserService = service;
    }

    @Override
    public void run(String... argsss) throws Exception {
        load(this);
    }

    public static void load(Sysload sys){
        long starttime = System.currentTimeMillis();
        sys.logger.info("static list/map load on system startup");
        sys.secuserService.findAll().forEach(u->SysConst.SYS_SECUSERS_MAP.put(u.getUserName(),u));
        SysConst.SYS_SECUSER_LIST = sys.secuserService.findAll();
        sys.logger.info("static list/map loaded successfully,time waste:"+(System.currentTimeMillis()-starttime)+" millis");
    }
}

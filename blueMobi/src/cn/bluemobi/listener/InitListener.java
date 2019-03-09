package cn.bluemobi.listener;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;

import cn.bluemobi.util.config.Config;

/**
 * Listener - 初始化
 * 
 */
public class InitListener extends ContextLoaderListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        // 初始化配置信息
        Config.get();
    }

}

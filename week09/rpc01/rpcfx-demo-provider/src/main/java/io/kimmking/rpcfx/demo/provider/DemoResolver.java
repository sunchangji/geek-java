package io.kimmking.rpcfx.demo.provider;

import io.kimmking.rpcfx.api.RpcfxResolver;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class DemoResolver implements RpcfxResolver, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

//    @Override
//    public Object resolve(String serviceClass) {
//        return this.applicationContext.getBean(serviceClass);
//    }

    public Object resolve(String serviceClass) {
        try {
            Class<?> clazz = Class.forName(serviceClass);
            return applicationContext.getBean(clazz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

package service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * The Class AppContext.
 */
public class AppContext implements ApplicationContextAware {

    /** The application context. */
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(
            org.springframework.context.ApplicationContext applicationContext)
            throws BeansException {
        AppContext.applicationContext = applicationContext;
    }

    /**
     * Gets the bean.
     *
     * @param beanName the bean name
     * @return the bean
     */
    public static Object getBean(String beanName) {
        return AppContext.applicationContext.getBean(beanName);
    }

    /**
     * Gets the environments.
     *
     * @return the environments
     */
    public static Environments getEnvironments() {
        return (Environments) getBean("env");
    }

}

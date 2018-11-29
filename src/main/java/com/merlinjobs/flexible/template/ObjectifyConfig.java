package com.merlinjobs.flexible.template;

import com.google.cloud.datastore.DatastoreOptions;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyFilter;
import com.googlecode.objectify.ObjectifyService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.logging.Logger;


@Configuration
public class ObjectifyConfig {

  @Value("${use.objectify.datastore.local}")
  private boolean dataStoreLocal;

  @Value("${gc.project.id}")
  private String gcProjectId;

  @Value("${dev.endpoint.datastore}")
  private String endpointDataStore;

  private final Logger logger = Logger.getLogger(ObjectifyConfig.class.getName());

  @Bean
  public FilterRegistrationBean<ObjectifyFilter> objectifyFilterRegistration() {
    final FilterRegistrationBean<ObjectifyFilter> registration = new FilterRegistrationBean<>();
    registration.setFilter(new ObjectifyFilter());
    registration.addUrlPatterns("/*");
    registration.setOrder(1);
    return registration;
  }

  @Bean
  public ServletListenerRegistrationBean<ObjectifyListener> listenerRegistrationBean() {
    ServletListenerRegistrationBean<ObjectifyListener> bean =
        new ServletListenerRegistrationBean<>();
    bean.setListener(new ObjectifyListener());
    return bean;
  }

  @WebListener
  public class ObjectifyListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
      if (dataStoreLocal) {
        // local (gradle bootRun)
        logger.info("starting ObjectifyService in local");
        ObjectifyService.init(new ObjectifyFactory(
            DatastoreOptions.newBuilder()
                .setHost(endpointDataStore)
                .setProjectId(gcProjectId)
                .build()
                .getService()
        ));
      } else {
        // on appengine flex
        logger.info("starting ObjectifyService");
        ObjectifyService.init(new ObjectifyFactory(DatastoreOptions.getDefaultInstance().getService()));

      }
      ObjectifyService.register(Item.class);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
  }


}

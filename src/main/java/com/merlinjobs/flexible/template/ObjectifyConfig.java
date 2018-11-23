package com.merlinjobs.flexible.template;

import com.google.cloud.datastore.DatastoreOptions;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyFilter;
import com.googlecode.objectify.ObjectifyService;
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
//      if (System.getenv("SPRING_PROFILES_ACTIVE") == null) {
//        // local without memcache (gradle bootRun)
//        ObjectifyService.init(new ObjectifyFactory(
//            DatastoreOptions.newBuilder()
//                .setHost("http://localhost:8484")
//                .setProjectId("merlin-dev")
//                .build()
//                .getService()
//        ));
//      } else if ("local".equals(System.getenv("SPRING_PROFILES_ACTIVE"))) {
//        // local with memcache (gradle appengineRun)
//        ObjectifyService.init(new ObjectifyFactory(
//            DatastoreOptions.newBuilder().setHost("http://localhost:8484")
//                .setProjectId("merlin-dev")
//                .build().getService(),
//            new AppEngineMemcacheClientService()
//        ));
//      } else {
//        // on appengine
//
//
//
//      }
        logger.info("starting ObjectifyService");
        ObjectifyService.init(new ObjectifyFactory(DatastoreOptions.getDefaultInstance().getService(),
                new AppEngineMemcacheClientService()));
        ObjectifyService.register(Item.class);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
  }


}

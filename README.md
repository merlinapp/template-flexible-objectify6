# Spring Boot based Hello World app with objectify 6 in flexible environment app engine

This sample shows how to run a [Spring Boot][spring-boot] application on [Google
Cloud Platform][cloud-java]. It uses the [Google App Engine flexible
environment][App Engine-flexible] and [Objectify 6][objectify-6].

[App Engine-flexible]: https://cloud.google.com/appengine/docs/flexible/
[cloud-java]: https://cloud.google.com/java/
[spring-boot]: http://projects.spring.io/spring-boot/
[objectify-6]: https://github.com/objectify/objectify/wiki/UpgradeVersion5ToVersion6

## Before you begin

This sample assumes you have [Java 8][java8] installed.

[java8]: http://www.oracle.com/technetwork/java/javase/downloads/

### Download Maven

These samples use the [Apache Maven][maven] build system. Before getting
started, be sure to [download][maven-download] and [install][maven-install] it.
When you use Maven as described here, it will automatically download the needed
client libraries.

[maven]: https://maven.apache.org
[maven-download]: https://maven.apache.org/download.cgi
[maven-install]: https://maven.apache.org/install.html

### Use a Project in the Google Cloud Platform Console called

Go to [Google Cloud Console][cloud-console] 

[cloud-console]: https://console.cloud.google.com/

### The project should be with enable billing.

Go to [enable billing][enable-billing].  Enabling billing allows the application to
consume billable resources such as running flexible instances and storing data.

[enable-billing]: https://console.cloud.google.com/project/_/settings

### Install the Google Cloud SDK.

If you haven't already installed the Google Cloud SDK, [install and initialize
the Google Cloud SDK][cloud-sdk] now. The SDK contains tools and libraries that
enable you to create and manage resources on Google Cloud Platform.


[cloud-sdk]: https://cloud.google.com/sdk/

### From the console install the Google App Engine SDK for Java

```
gcloud components update app-engine-java
gcloud components update
```

### Configure the `app.yaml` descriptor

The [`app.yaml`][app-yaml] descriptor is used to describe URL
dispatch and resource requirements.  This example sets
[`manual_scaling`][manual-scaling] to 1 to minimize possible costs.
These settings should be revisited for production use.

[app-yaml]: https://cloud.google.com/appengine/docs/flexible/java/configuring-your-app-with-app-yaml
[manual-scaling]: https://cloud.google.com/appengine/docs/flexible/java/configuring-your-app-with-app-yaml#manual-scaling

## Run the application locally (this repo is disable to run locally... coming soon)


1. Set the correct Cloud SDK project via `gcloud config set project
   YOUR_PROJECT` to the ID of your application.
1. Run `mvn spring-boot:run`
1. Visit http://localhost:8080


## Deploy to App Engine flexible environment
1. Set the correct Cloud SDK project via `gcloud config set project
   YOUR_PROJECT` to the ID of your application.
1. `mvn appengine:deploy`
1. Visit `http://YOUR_PROJECT.appspot.com`.

Note that deployment to the App Engine flexible environment requires the new
[`com.google.cloud.tools:appengine-maven-plugin` plugin][new-maven-plugin].

[new-maven-plugin]: https://cloud.google.com/appengine/docs/flexible/java/using-maven

Merlin uses Java a registered trademark of Oracle Corporation and/or its affiliates.


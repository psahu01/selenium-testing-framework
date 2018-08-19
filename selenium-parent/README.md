# Selenium-parent

selenium-parent project is parent project for seamlessly using [selenium-framework].

# How to setup your project:
setup a simple maven project and in your pom add selenium-parent <parent> in your project pom.

```xml
	<parent>
		<groupId>com.adobe.qe</groupId>
		<artifactId>selenium-parent</artifactId>
		<version>0.0.7-SNAPSHOT</version>
	</parent>
```

## Parameters ()
|Name|Type|Since|Description|
|----|----|-----|-----------|
|author.hostname|String |0.0.2|hostname/ip of author - default[localhost]|
|author.port    |String|0.0.2|author port - default[4502]|
|browser|String|0.0.2|browser on which you want to run your tests - default[chrome]. *Other options - firefox, ie*|
|headless|Boolean|0.0.2|whether to run tests in headless mode. Currently we are just supporting headless execution for chrome - default[false]|
|publish.hostname|String|0.0.2|hostname/ip of publish - default[localhost]|
|publish.port|String|0.0.2|publish port - default[4503]|
|testng.groups|String|0.0.4|list of comma separated group names. Note-if you are using this parameter **failsafe-plugin** should be configured to run on the basis of testng groups - default[regression]|

It is recommended to go through pom to better understand what we will get from selenium-parent.
Add plugin in you pom which you want to use. for reference you may refer forms-scenariotests pom file.

kindly go through maven plugin management and dependency management.



[selenium-framework]: https://git.corp.adobe.com/livecycle/forms-qe-scripts/tree/master/selenium-framework
[forms-scenariotests]: https://git.corp.adobe.com/livecycle/forms-scenariotests

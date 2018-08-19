# selenium-framework
Selenium-framework is a common selenium framework which streamlines driver initialization and provided different utilities to make test case writing easy in selenium

## How to use Selenium Framework
To make things easier and common across all the components you may add [selenium-parent] as parent. Since slenium-parent has all selenium-framework dependency, you don't have to include it explicitly in you pom.

## What should I know?
* To run any unit tests that test your Selenium framework you just need to ensure that all unit test file names end, or start with "test" and they will be run as part of the build.
* The maven failsafe plugin has been used to run integration tests, so our main area of concern is maven-failsafe-plugin
* The maven-failsafe-plugin will pick up any files that end in IT by default. You can customize this, if you prefer to use a custom identifier for your Selenium tests. Also since we are using testng framework you can specify testng.xml or groups.

## What else?
You can specify which browser to use by using one of the following switches:
- -Dbrowser=firefox
- -Dbrowser=chrome
- -Dbrowser=ie (Would be supported in next release)

## First steps towards glory...
Since we all may differ in deciding at which level to initialize and quit WebDriver. Selenium-framework gives you the flexibility to decide the level to initialize WebDriver, it could be at suite level, class level, test level or even at method level.

**NOTE:** *Just to keep our defensive line strong.* we are quiting driver at suite level, so even if you forget or choose not to quit driver, we got your back :)

BaseTest.java is selenium-framework facade which gives you methods for getting driver, eventFiringWebDriver(more on this in javadocs) etc. As a recommendation, create a top level BaseTest in your project which extends BaseTest.java of selenium-framework.

```java
public class ScenarioBase extends BaseTest {
.
.
.
}
```

### EventFiringWebDriver Vs WebDriver?
EventFiringWebDriver is a wrapper around an arbitrary WebDriver instance which supports registering of a WebDriverEventListener, e.g. for **logging purposes**.
As the definition cleary says for logging purpose, selenium-framework is also using it for logging purpose.
You will see improvement in logging, if you choose to use EventfiringWebDriver over conventional WebDriver.

**Note:** Logging is still in progress and will imporove in future releases.

#### Is it complex...
Getting a WebDriver or eventFiringWebDriver instance is as easy as printing _Hello world!!!_.
Eg.

```java
protected WebDriver webDriver;
@BeforeClass
	public void launch() {
		webDriver = getDriver();
  }
```

```java
protected EventFiringWebDriver eventFiringWebdriver;
@BeforeClass
	public void launch() {
		eventFiringWebdriver = getAEMEventFiringWebDriver(getDriver());
  }
```
Thats it, nothing more is required, you are ready to write your actual TCs.
Note that webDriver is used as parameter for getting EventFiringWebDriver.

## Parameters
|Name|Type|Since|Description|
|----|----|-----|-----------|
|executeOnGrid|Boolean|1.0.0|whether to run selenium tests on grid. Other parameter, which are provided by selenium-framework, used frequently with this parameter are *hub.hostname* and *hub.port*. default[false]|
|hub.hostname|String|1.0.0|hostname/ip address of Selenium Grid hub - default[localhost]|
|hub.port|String|1.0.0|port on which Selenium Grid hub is running| - default[4444]|
|wait.tinyInSecs|int|1.0.2|wait time for which selenium should wait before confirming element not found - default[5]|
|wait.mediumInSecs|int|1.0.2|default[10]|
|wait.longInSecs|int|1.0.2|default[25]|
|wait.poolTimeInMilliSecs|int|1.0.2|pool time after which selenium should again try to find the element - default[2000]|

Point to note here is that selenium-framework will not configure selenium grid hub if you just pass hub.hostname and hub.port. Selenium-framework has not control on configuring hub and registering nodes. So you need to manually configure Selenium grid and then use these parameter to direct selenium-framework to run TCs on grid.

***Note:*** You may find other parameter list in *[selenium-parent]* README.md

## I have a better way of doing it...
If you want to customize driver initialization according to your project needs or you want to start driver with some specific capabilities,although selenium-framework offers commonly used capabilites(more on this later), you can override `getDriver()` and `getAEMEventFiringWebDriver(WebDriver webDriver)` in your Base class and change the driver initialization mechanisam.

Let say, for example, we want to start maximized chrome browser. For doing this we need to start driver with `--start-maximized` chromeOption.

```java
public class MyBaseTest extends BaseTest {

	private WebDriver webDriver;

	@Override
	public WebDriver getDriver() {
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--start-maximized");
		DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
		desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
		if (null == webDriver) {
			InitializeDriver iDriver = driverFactory.getInitializeDriver();
			webDriver = iDriver.getDriver(iDriver.addToCapability(desiredCapabilities));
		}
		return webDriver;
	}
}
```
Note that we are adding capability to existing default `desiredCapabilities`. If you don't want to merge in default desiredCapabilities you can simple call `getDriver` with your capabilites.

You may have a question on how selenium-framework is deciding which WebDriver instance to return. This is decided by system property `browser`. In above example we have assumed the browser is set to chrome.

selenium-framework has no brain on deciding how to set capabilites to respective WebDriver instance.


[selenium-parent]: https://git.corp.adobe.com/livecycle/forms-qe-scripts/tree/master/selenium-parent

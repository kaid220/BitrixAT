import Bitrix.Config
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver


waiting{
    timeout=100
    retryInterval=2
}

atCheckWaiting{
    timeout = 60
    retryInterval=7
}


environments{
    if(Config.getProperty("isRemote")=="remote"){
        ChromeOptions options = new ChromeOptions();
        options.setCapability("browserVersion", "124.0");
        options.setCapability("selenoid:options", new HashMap<String, Object>() {{
            put("name", this.getClass().getName().toString());
            put("enableVNC", true);
            put("maxInstances", 5)
            put("sessionTimeout", "15m");
            /* How to set timezone */
            put("env", new ArrayList<String>() {{
                add("TZ=UTC");
            }});
            put("labels", new HashMap<String, Object>() {{
                put("manual", "false");
            }});
            put("enableVideo", true)
        }});
         driver ={ new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options)}
    }
    else {
        def localBrowser = 'chrome'

        driver = { System.setProperty("webdriver.chrome.driver", "D:\\ITTOOLS\\wd\\chromedriver.exe");
            new ChromeDriver() }
    }
    quitDriverMode = "AFTER_SPEC"
}

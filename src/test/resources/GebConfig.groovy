import Bitrix.Config
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver


waiting{
    timeout=60
    retryInterval=2
}

atCheckWaiting{
    timeout = 40
    retryInterval=7
}


environments{
    if(Config.getProperty("isRemote")=="remote"){
        ChromeOptions options = new ChromeOptions();
        options.setCapability("browserVersion", "124.0");
        options.setCapability("selenoid:options", new HashMap<String, Object>() {{
            /* How to add test badge */
            put("name", this.getClass().getName().toString());
            put("enableVNC", true);
            put("maxInstances", 5)
            /* How to set session timeout */
            put("sessionTimeout", "15m");

            /* How to set timezone */
            put("env", new ArrayList<String>() {{
                add("TZ=UTC");
            }});

            /* How to add "trash" button */
            put("labels", new HashMap<String, Object>() {{
                put("manual", "false");
            }});

            /* How to enable video recording */
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

import org.openqa.selenium.chrome.ChromeDriver


waiting{ timeout=60}


environments{
    def localBrowser = 'chrome'
    System.setProperty("webdriver.chrome.driver", "D:\\ITTOOLS\\wd\\chromedriver.exe");
    driver ={ new ChromeDriver()}
}

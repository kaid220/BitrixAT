import org.openqa.selenium.chrome.ChromeDriver


waiting{ timeout=20}


environments{
    def localBrowser = 'chrome'
    System.setProperty("webdriver.chrome.driver", "D:\\ITTOOLS\\wd\\chromedriver-win32\\chromedriver.exe");
    driver ={ new ChromeDriver()}
}

import io.github.bonigarcia.wdm.WebDriverManager
import okhttp3.OkHttpClient
import okhttp3.Request
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated
import org.openqa.selenium.support.ui.WebDriverWait
import java.net.HttpURLConnection
import java.util.concurrent.TimeUnit

const val ELEMENT_TIMEOUT = 15L

fun createChromeDriver(): WebDriver {
    WebDriverManager.chromedriver().setup()
    return setupDriver(ChromeDriver())
}
fun createFirefoxDriver(): WebDriver {
    WebDriverManager.firefoxdriver().setup()
    return setupDriver(FirefoxDriver())
}

fun hasConnection(url: String, client: OkHttpClient): Boolean {
    val request = Request.Builder().url(url).build()
    val call = client.newCall(request)
    call.execute().use {
        return it.code() == HttpURLConnection.HTTP_OK
    }
}

private fun setupDriver(driver: WebDriver): WebDriver {
    driver.manage().timeouts().pageLoadTimeout(ELEMENT_TIMEOUT, TimeUnit.SECONDS)
    driver.manage().window().maximize()
    return driver
}

fun WebDriver.tryClick(selector: By) {
    waitElement(selector).click()
}

fun WebDriver.tryEnter(selector: By, string: String) {
    val element = waitElement(selector)
    element.clear()
    element.sendKeys(string)
}

fun WebDriver.waitElement(selector: By): WebElement {
    val wait = WebDriverWait(this, ELEMENT_TIMEOUT)
    return wait.until(visibilityOfElementLocated(selector))
}

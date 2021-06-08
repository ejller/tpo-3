import okhttp3.OkHttpClient
import org.junit.Assume
import org.junit.jupiter.api.*
import org.junit.jupiter.params.provider.Arguments
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import java.lang.Exception
import java.lang.IllegalArgumentException
import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Stream


const val BASE_URL = "https://statcounter.com"

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class BaseTest {


    private lateinit var client: OkHttpClient


    @BeforeAll
    fun init() {
        client = OkHttpClient()
    }

    @BeforeEach
    fun checkConnection() {
        Assume.assumeTrue(hasConnection(BASE_URL, client))
    }


    fun WebDriver.setupTest(action: () -> Unit) {
        get(BASE_URL)
        action.invoke()
        close()
    }

    fun WebDriver.id(): String {
        return when (this) {
            is ChromeDriver -> "1"
            is FirefoxDriver -> "2"
            else -> "0"
        }
    }

    fun providesBrowsers(): Stream<Arguments> {
        try {
            val properties = Files.readAllLines(Paths.get("lab.properties"))
            val driverProperty = properties.find { it.startsWith(DRIVER) } ?: throw IllegalArgumentException()
            return when (driverProperty.split("=").last().toLowerCase().also { println(it) }) {
                CHROME -> Stream.of(Arguments.of(createChromeDriver()))
                FIREFOX -> Stream.of(Arguments.of(createFirefoxDriver()))
                BOTH -> Stream.of(Arguments.of(createChromeDriver()), Arguments.of(createFirefoxDriver()))
                else -> {println("error"); return Stream.of()}

            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw IllegalArgumentException()
        }

    }

    companion object {
        const val DRIVER = "driver"
        const val CHROME = "chrome"
        const val FIREFOX = "firefox"
        const val BOTH = "both"
    }


}
import org.junit.Assert
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.openqa.selenium.WebDriver
import pages.LoginPage
import pages.MainPage


const val USERNAME = "testIfmo"
const val PASSWORD = "1234567890"

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class AuthorizationTest() : BaseTest() {

    @ParameterizedTest
    @MethodSource("providesBrowsers")
    @Order(1)
    fun loginWithInvalidUser(driver: WebDriver) {
        driver.setupTest {
            val loginPage = LoginPage(driver)
            loginPage.login("test", "test")
            driver.waitElement(loginPage.getErrorElement())
        }
    }


    @ParameterizedTest
    @MethodSource("providesBrowsers")
    @Order(2)
    fun loginWithOnlyPassword(driver: WebDriver) {
        driver.setupTest {
            val loginPage = LoginPage(driver)
            loginPage.login(null, "test")
            Assert.assertEquals(driver.currentUrl, loginPage.url)
        }
    }

    @ParameterizedTest
    @MethodSource("providesBrowsers")
    @Order(3)
    fun loginWithOnlyLogin(driver: WebDriver) {
        driver.setupTest {
            val loginPage = LoginPage(driver)
            loginPage.login("test", null)
            Assert.assertEquals(driver.currentUrl, loginPage.url)
        }
    }

    @ParameterizedTest
    @MethodSource("providesBrowsers")
    @Order(4)
    fun loginTest(driver: WebDriver) {
        println(driver.title)
        driver.setupTest {
            val user = login(driver)
            Assert.assertEquals(USERNAME, user)
        }
    }

    companion object {
        fun login(driver: WebDriver) : String {
            val loginPage = LoginPage(driver)
            val main = MainPage(driver)
            loginPage.login(USERNAME, PASSWORD)
            return main.getCurrentUser()
        }
    }
}
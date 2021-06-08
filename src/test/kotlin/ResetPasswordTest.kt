import org.junit.Assert
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.openqa.selenium.WebDriver
import pages.ForgotPage
import pages.LoginPage


@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class ResetPasswordTest() : BaseTest() {

    @ParameterizedTest
    @MethodSource("providesBrowsers")
    @Order(1)
    fun tryChangeWithInvalidEmail(driver: WebDriver) {
        driver.setupTest {
            val forgotPage = ForgotPage(driver)
            forgotPage.changePasswordByEmail("test")
            driver.waitElement(forgotPage.getEmailErrorElement())
        }
    }

    @ParameterizedTest
    @MethodSource("providesBrowsers")
    @Order(2)
    fun tryChangeWithInvalidUsername(driver: WebDriver) {
        driver.setupTest {
            val forgotPage = ForgotPage(driver)
            forgotPage.changePasswordByUsername("te-st")
            driver.waitElement(forgotPage.getUsernameErrorElement())
        }
    }

    @ParameterizedTest
    @MethodSource("providesBrowsers")
    @Order(3)
    fun tryChangeWithNullEmail(driver: WebDriver) {
        driver.setupTest {
            val forgotPage = ForgotPage(driver)
            forgotPage.changePasswordByEmail(null)
            Assert.assertEquals(driver.currentUrl, forgotPage.url)
        }
    }

    @ParameterizedTest
    @MethodSource("providesBrowsers")
    @Order(4)
    fun tryChangeWithNullUsername(driver: WebDriver) {
        driver.setupTest {
            val forgotPage = ForgotPage(driver)
            forgotPage.changePasswordByUsername(null)
            Assert.assertEquals(driver.currentUrl, forgotPage.url)
        }
    }
}
package pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import tryClick
import tryEnter


class ForgotPage(private val driver: WebDriver) {
    val url = "https://statcounter.com/forgot-password/"


    fun getEmailErrorElement(): By = By.xpath("//*[@id=\"password-reminder-email-form\"]/label")

    fun getUsernameErrorElement(): By = By.xpath("//*[@id=\"password-reminder-username-form\"]/label")

    fun changePasswordByEmail(email: String?) {
        startReset()
        if (!email.isNullOrBlank()) enterEmail(email)
        pressChangePasswordByEmail()
    }

    fun changePasswordByUsername(username: String?) {
        startReset()
        if (!username.isNullOrBlank()) enterUsername(username)
        pressChangePasswordByUsername()
    }

    private fun startReset() {
        driver.get(url)
    }

    private fun pressChangePasswordByEmail() {
        val changeBtn = By.xpath("//*[@id=\"password-reminder-email-form\"]/input[2]")
        driver.tryClick(changeBtn)
    }

    private fun pressChangePasswordByUsername() {
        val changeBtn = By.xpath("//*[@id=\"password-reminder-username-form\"]/input[2]")
        driver.tryClick(changeBtn)
    }

    private fun enterEmail(string: String) {
        val emailInput = By.xpath("//*[@id=\"email\"]")
        driver.tryEnter(emailInput, string)
    }

    private fun enterUsername(string: String) {
        val passwordInput = By.xpath("//*[@id=\"new_username\"]")
        driver.tryEnter(passwordInput, string)
    }
}
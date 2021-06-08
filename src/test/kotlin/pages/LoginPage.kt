package pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import tryClick
import tryEnter


class LoginPage(private val driver: WebDriver) {
    val url = "https://statcounter.com/login/"

    fun login(username: String?, password: String?) {
        startLogin()
        if (!username.isNullOrBlank()) enterUsername(username)
        if (!password.isNullOrBlank()) enterPassword(password)
        pressLoginBtn()
    }

    fun getErrorElement(): By = By.xpath("//*[@id=\"main-el\"]/div/div/div")

    private fun startLogin() {
        driver.get(url)
    }

    private fun pressLoginBtn() {
        val loginBtn = By.xpath("//*[@id=\"login-form\"]/input[3]")
        driver.tryClick(loginBtn)
    }

    private fun enterUsername(string: String) {
        val usernameInput = By.xpath("//*[@id=\"username\"]")
        driver.tryEnter(usernameInput, string)
    }

    private fun enterPassword(string: String) {
        val passwordInput = By.xpath("//*[@id=\"password\"]")
        driver.tryEnter(passwordInput, string)
    }
}
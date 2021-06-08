package pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import tryClick
import waitElement


class MainPage(private val driver: WebDriver) {


    val projectDropDown : By
    get() = By.xpath("//*[contains(@id,\"srow_\")]/td[7]")


    val projectStats : By
        get() = By.xpath("//*[contains(@id,\"srow_\")]/td[8]")

    val projectDelete: By
    get() = By.xpath("//*[@id=\"projects-single-delete\"]")

    val editProject: By
    get() = By.xpath("//*[contains(@id, \"project-options-dropdown\")]/div/ul/li[1]")

    val successDelete: By
    get() = By.xpath("//*[contains(@id,\"deleted-project-message\")]")

    fun logOut() {
        val logOutBtn = By.xpath("//*[@id=\"login-form\"]/input[3]")
        driver.tryClick(logOutBtn)
    }

    fun getCurrentUser(): String {
        val currentUserSpan = By.xpath("//*[@id=\"user-menu-trigger\"]/span[2]/strong")
        driver.waitElement(currentUserSpan)
        return driver.findElement(currentUserSpan).getAttribute("innerHTML")
    }

    fun addProject() {
        val addProjectButton = By.xpath("//*[@id=\"group-0\"]/div[2]/a[2]")
        driver.tryClick(addProjectButton)
    }

    fun getProjectsNames() : List<String> {
        val projects = By.xpath("//*[contains(@id,\"project-name\")]")
        driver.waitElement(projects)
        return driver.findElements(projects).map { it.getAttribute("innerHTML") }
    }

    fun clickProjectSettings() {
        driver.tryClick(projectDropDown)
    }

    fun deleteProject() {
        driver.tryClick(projectDelete)
    }

    fun editProject() {
        driver.tryClick(editProject)
    }

    fun openProjectStats() {
        driver.tryClick(projectStats)
    }


}


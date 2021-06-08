package pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import tryClick
import tryEnter

class ProjectPage(private val driver: WebDriver) {

    val title : By
    get() = By.xpath("//*[@id=\"stage\"]/div/h1")

    val error : By
    get() = By.xpath("//*[@id=\"for_projectname\"]/span")

    val noStatsStub : By
    get() = By.xpath("//*[@id=\"stage\"]/div[2]/div/div")

    val successEdit : By
    get() = By.xpath("//*[@id=\"stage\"]/div[1]")

    fun enterWebsite(website: String) {
        val webSiteEnter = By.xpath("//*[@id=\"website_domain\"]")
        driver.tryEnter(webSiteEnter, website)
    }

    fun enterTitle(title: String) {
        val webSiteEnter = By.xpath("//*[@id=\"projectname\"]")
        driver.tryEnter(webSiteEnter, title)
    }

    fun addProject() {
        val addProjectButton = By.xpath("//*[@id=\"add-project-form-post-setup\"]/div/div/div/input")
        driver.tryClick(addProjectButton)
    }

    fun clickProjectAfterDetectingCms() {
        val continueButton = By.xpath("//*[@id=\"detected-continue\"]")
        driver.tryClick(continueButton)
    }

    fun clickSkipVerification() {
        val skipBtn = By.xpath("//*[@id=\"btn-skip-check\"]")
        driver.tryClick(skipBtn)
    }

    fun editTitle(title: String) {
        val input = By.xpath("//*[@id=\"projectname\"]")
        driver.tryEnter(input, title)
    }

    fun saveEdited() {
        val saveBtn = By.xpath("//*[@id=\"stage\"]/form/div/div[2]/button")
        driver.tryClick(saveBtn)
    }


}
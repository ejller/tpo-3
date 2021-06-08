import AuthorizationTest.Companion.login
import org.junit.Assert
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.openqa.selenium.WebDriver
import pages.MainPage
import pages.ProjectPage


@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class ProjectTest() : BaseTest() {


    companion object {
        const val WORDPRESS_URL = "https://wordpress.com/"
        const val TITLE = "Wordpress site"
    }


    @ParameterizedTest
    @MethodSource("providesBrowsers")
    @Order(1)
    fun addProject(driver: WebDriver) {
        driver.setupTest {
            val currentTitle = TITLE + driver.id()
            login(driver)
            val mainPage = MainPage(driver)
            mainPage.addProject()
            val projectPage = ProjectPage(driver)
            driver.waitElement(projectPage.title)
            projectPage.enterTitle(currentTitle)
            projectPage.enterWebsite(WORDPRESS_URL)
            projectPage.addProject()
            projectPage.clickProjectAfterDetectingCms()
            projectPage.clickSkipVerification()
            Assert.assertEquals(currentTitle, mainPage.getProjectsNames().find { it == currentTitle } ?: "")
        }
    }

    @ParameterizedTest
    @MethodSource("providesBrowsers")
    @Order(2)
    fun wrongAddProject(driver: WebDriver) {
        driver.setupTest {
            login(driver)
            val mainPage = MainPage(driver)
            mainPage.addProject()
            val projectPage = ProjectPage(driver)
            driver.waitElement(projectPage.title)
            projectPage.addProject()
            driver.waitElement(projectPage.error)
        }
    }

    @ParameterizedTest
    @MethodSource("providesBrowsers")
    @Order(3)
    fun deleteProject(driver: WebDriver) {
        driver.setupTest {
            login(driver)
            val mainPage = MainPage(driver)
            mainPage.clickProjectSettings()
            mainPage.deleteProject()
            driver.waitElement(mainPage.successDelete)
        }
    }

    @ParameterizedTest
    @MethodSource("providesBrowsers")
    @Order(4)
    fun openEmptyVisitorsProject(driver: WebDriver) {
        driver.setupTest {
            login(driver)
            val mainPage = MainPage(driver)
            mainPage.openProjectStats()
            val projectPage = ProjectPage(driver)
            driver.waitElement(projectPage.noStatsStub)
        }
    }

    @ParameterizedTest
    @MethodSource("providesBrowsers")
    @Order(5)
    fun editProject(driver: WebDriver) {
        driver.setupTest {
            login(driver)
            val mainPage = MainPage(driver)
            mainPage.clickProjectSettings()
            mainPage.editProject()
            val projectPage = ProjectPage(driver)
            projectPage.editTitle("new ${driver.id()}")
            projectPage.saveEdited()
            driver.waitElement(projectPage.successEdit)
        }
    }

}
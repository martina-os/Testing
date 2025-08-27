import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class FirstTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;
    private final String bugUrl = "https://www.bug.hr/";

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        actions = new Actions(driver);
    }

    private void PrihvatiBugKolacice(WebDriver driver) {
        try {
            List<WebElement> banneri = driver.findElements(By.cssSelector(".fc-consent-root"));
            if (!banneri.isEmpty()) {
                System.out.println("Pronađen cookie banner.");
                By PristajemButtonLocator = By.xpath("/html/body/div[4]/div[2]/div[2]/div[2]/div[2]/button[1]");
                WebElement PristajemButton = wait.until(
                        ExpectedConditions.elementToBeClickable(PristajemButtonLocator)
                );
                PristajemButton.click();
                System.out.println("Kliknuto na gumb 'Pristajem'.");
            } else {
                System.out.println("Cookie banner se nije pojavio – nastavljam test.");
            }
        } catch (Exception e) {
            System.out.println("Greška pri prihvaćanju kolačića: " + e.getMessage());
        }
    }


    @Test(priority = 1)
    public void googleSearchAndNavigateTest() throws InterruptedException {
        driver.get("https://www.google.com");
        WebElement rejectCookiesButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("W0wltc")));
        rejectCookiesButton.click();

        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("bug hr");
        searchBox.submit();
        Thread.sleep(20000);

        WebElement bugLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='https://www.bug.hr/']")));
        bugLink.click();

        PrihvatiBugKolacice(driver);
        Thread.sleep(10000);
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("bug.hr"), "Stranica " + bugUrl + " nije otvorena.");
    }

    @Test(priority = 2)
    public void recenzijeMobiteliTest() {
        driver.get(bugUrl);

        WebElement recenzijeMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id='nav']/ul/li[1]/a")));
        actions.moveToElement(recenzijeMenu).perform();

        WebElement mobiteliLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id='nav']/ul/li[1]/ul/li[5]/a")));
        actions.moveToElement(mobiteliLink).perform(); // hover
        mobiteliLink.click(); // klik

        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("recenzije/mobiteli"),
                ExpectedConditions.urlContains("recenzije~mobiteli")
        ));

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(
                currentUrl.contains("recenzije/mobiteli") || currentUrl.contains("recenzije~mobiteli"),
                "Stranica za recenzije mobitela nije otvorena. Trenutni URL: " + currentUrl
        );
    }

    @Test(priority = 3)
    public void znanostAstronomijaTest() {
        driver.get(bugUrl);

        WebElement znanostMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"nav\"]/ul/li[4]/a")));
        actions.moveToElement(znanostMenu).perform();

        WebElement astronomijaLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"nav\"]/ul/li[4]/ul/li[1]/a")));
        actions.moveToElement(astronomijaLink).perform();
        astronomijaLink.click();

        wait.until(ExpectedConditions.urlContains("/astronomija"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/astronomija"), "Stranica o astronomiji nije otvorena.");
    }

    @Test(priority = 4)
    public void igreAvantureTest() {
        driver.get(bugUrl);

        WebElement igreMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id='nav']/ul/li[5]/a")));
        actions.moveToElement(igreMenu).perform();

        WebElement avantureLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id='nav']/ul/li[5]/ul/li[3]/a")));
        actions.moveToElement(avantureLink).perform();
        avantureLink.click();

        wait.until(ExpectedConditions.urlContains("/igre~avanture"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/igre~avanture"), "Stranica za avanture nije otvorena.");
    }

    @Test(priority = 5)
    public void forumTest() {
        driver.get(bugUrl);

        WebElement forumLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("FORUM")));
        forumLink.click();

        wait.until(ExpectedConditions.urlContains("forum.bug.hr"));
        Assert.assertTrue(driver.getCurrentUrl().contains("forum.bug.hr"), "Stranica foruma nije otvorena.");
    }

    @Test(priority = 6)
    public void loginTest() {
        driver.get(bugUrl);

        WebElement loginIconButton = driver.findElement(By.xpath("//*[@id='header']/div/div/div[3]/div[1]/button"));
        loginIconButton.click();
        WebElement loginDropdownLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='header']/div/div/div[3]/div[1]/div/ul[1]/li[1]")));
        loginDropdownLink.click();

        wait.until(ExpectedConditions.urlContains("login"));
        Assert.assertTrue(driver.getCurrentUrl().contains("login"), "Stranica za prijavu (login) nije otvorena.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

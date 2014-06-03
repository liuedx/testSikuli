import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.sikuli.api.DesktopScreenRegion;
import org.sikuli.api.ImageTarget;
import org.sikuli.api.ScreenRegion;
import org.sikuli.api.Target;
import org.sikuli.api.robot.Mouse;
import org.sikuli.api.robot.desktop.DesktopMouse;
import org.sikuli.webdriver.ImageElement;
import org.sikuli.webdriver.SikuliFirefoxDriver;
import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class SikuliWebdriverDemo {

    private static SikuliFirefoxDriver driver;

    public static void main(String args[]) {
        driver = new SikuliFirefoxDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get("http://maps.google.com");
        searchBox().sendKeys("Pune, Maharashtra, India");
        searchBox().sendKeys(Keys.ESCAPE);
        searchButton().click();
        rightClickOnCampArea();
        DirectionToHere().click();
        System.out.println("********************");
        System.out.println("Distance = " + distanceBetweenPuneAndCamp());
        System.out.println("********************");
        driver.quit();
    }

    private static WebElement searchBox() {
        return driver.findElement(By.name("q"));
    }

    private static WebElement searchButton() {
        return driver.findElement(By.className("searchbutton"));
    }

    private static void rightClickOnCampArea() {
        camp().click();
        Target tg = new ImageTarget(new File(System.getProperty("user.dir") + "/src/main/resources/imageLocators/camp.png"));
        ScreenRegion sr = new DesktopScreenRegion();
        Mouse mouse = new DesktopMouse();
        mouse.rightClick(sr.find(tg).getCenter());
    }

    private static ImageElement camp() {
        try {
            return driver.findImageElement(new URL("file:///" + System.getProperty("user.dir") + "/src/main/resources/imageLocators/camp.png"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static ImageElement DirectionToHere() {
        try {
            return driver.findImageElement(new URL("file:///" + System.getProperty("user.dir") + "/src/main/resources/ImageLocators/to_here.png"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String distanceBetweenPuneAndCamp(){
        return driver.findElement(By.cssSelector(".cards-directions-details.cards-directions-distance")).getText();
    }
}


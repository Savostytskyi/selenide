package integration;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;

public class CollectionRefreshTest {
  @Test
  public void reloadsCollectionOnEveryCall() {
    open("http://www.imdb.com/chart/moviemeter?ref_=nv_mv_mpm_8");

    ElementsCollection movieTitles = $$x(".//td[@class='titleColumn']/a");
    assertEquals("Jurassic World: Fallen Kingdom", movieTitles.get(0).getText());

    $(By.name("sort")).selectOption("Release Date");

    $$x(".//td[@class='titleColumn']/a").get(0).shouldHave(Condition.text("It: Chapter Two"));
    movieTitles.get(0).shouldHave(Condition.text("It: Chapter Two"));
    assertEquals("It: Chapter Two", movieTitles.get(0).getText());
  }

  @Test
  public void shouldNotReloadCollectionWhenIterating() {
    open("http://www.imdb.com/chart/moviemeter?ref_=nv_mv_mpm_8");

    ElementsCollection movieTitles = $$x(".//td[@class='titleColumn']/a");
    for (SelenideElement movieTitle : movieTitles) {
      System.out.println(movieTitle.getText());
    }
  }
}

package stepdefs;

import buisness.components.NavigationMenuComponent;
import core.dto.MenuNames;
import io.cucumber.java.en.When;

public class NavigationMenuStepDefs {
  private NavigationMenuComponent navigationMenuComponent = new NavigationMenuComponent();


  @When("User navigates to {} menu")
  public void userNavigatesToMenu(final MenuNames menu) {
    navigationMenuComponent.navigateToMenu(menu.toString());
  }
}

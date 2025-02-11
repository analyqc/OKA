package com.orange.steps;

import com.orange.pages.OrangePage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



public class OrangeSteps {
    WebDriver driver;
    OrangePage orangePage;

    // Default constructor for Cucumber
    public OrangeSteps() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        orangePage = new OrangePage(driver);

    }

    @Given("El usuario ingresa a la pagina de orange")
    public void abrirPagina() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @When("^El usuario ingresa el user \"([^\"]*)\" y el password \"([^\"]*)\"$")
    public void ingresaCredenciales(String user, String pass) {
    	orangePage.ingresaCredenciales(user, pass);
    }


    @Then("aqui se verificara los datos son incorrectos")
    public void hagoClicEnElBotonLogin() {
        // Llamar al método que realiza clic en el botón "Continue"
    	orangePage.verificarNOlogin();

    }
    @Then("aqui se verificara los datos son correctos")
    public void hagoClicEnElBotonContinue() {
        // Llamar al método que realiza clic en el botón "Continue"
    	orangePage.verificarlogin();

    }
    @And("^El usuario ingresa el rol \"([^\"]*)\", el nombre \"([^\"]*)\" el estatus \"([^\"]*)\",el user \"([^\"]*)\" ,el password \"([^\"]*)\" y la confirmación password \"([^\"]*)\"$")
    public void nuevoUsuario(String role, String employeeName, String status, String username, String password, String confirmPassword) {
        // nuevo usuario
    	orangePage.nuevoUsuario(role, employeeName, status, username, password, confirmPassword);

    }
    @Then("Validar que se registre correctamente")
    public void validarAcciones() {
        // Llamar al método que realiza clic en el botón "Continue"
    	orangePage.validarAcciones();

    }
    
    @And("Buscar administradores")
    public void filtrar() {
        // Llamar al método que realiza clic en el botón "Continue"
    	orangePage.filtar();

    }
    
    @Then("Validar usuario \"([^\"]*)\"$")
    public void validausuario( String employeeName) {
        // Validar usuario
    	orangePage.validausuario(employeeName);

    }
     
    
    
}

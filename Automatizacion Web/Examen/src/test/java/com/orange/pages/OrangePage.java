package com.orange.pages;

import java.time.Duration;



import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Keys;

public class OrangePage {
	  WebDriver driver;	
	  WebDriverWait wait;


	  public OrangePage(WebDriver driver) {
	        this.driver = driver;
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    }



	public void ingresaCredenciales(String user, String pass) {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username"))).sendKeys(user);
        driver.findElement(By.name("password")).sendKeys(pass);
        driver.findElement(By.cssSelector("button[type='submit']")).click();
		
	}

	public void verificarlogin() {

	        WebElement dashboardHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Dashboard']")));
	        Assert.assertTrue(dashboardHeader.isDisplayed());
	        System.out.println("Prueba con credenciales correctas: PASSED");
	  
	        cerrar(); 
	    
	    
		
	}
	public void verificarNOlogin() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // Asegura que "driver" no sea null
	    WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("oxd-alert-content-text")));
	    String actualErrorMessage = errorMessage.getText().trim(); // Elimina espacios extra

	    // Imprime el mensaje real para verificarlo
	    System.out.println("Mensaje capturado: [" + actualErrorMessage + "]");

	    // Comparación correcta
	    Assert.assertEquals("Error: El mensaje de credenciales incorrectas no coincide.", "Invalid credentials", actualErrorMessage);

	    System.out.println("Prueba con credenciales incorrectas: PASSED");
	    cerrar(); // Método para cerrar el navegador
	}

	public void buscarAdmin() {
        try {
            // Seleccionar el menú "Admin"
            WebElement adminMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class, 'oxd-main-menu-item') and contains(@href, 'admin/viewAdminModule')]")));
            adminMenu.click();
            System.out.println("✅ Menú 'Admin' seleccionado correctamente.");

            // Abrir el dropdown
            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'oxd-select-text')]")));
            dropdown.click();
            System.out.println("✅ Dropdown abierto.");

            // Seleccionar la opción "Admin" dentro del dropdown
            WebElement opcionAdmin = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='oxd-select-text-input' and text()='Admin']")));
            opcionAdmin.click();
            System.out.println("✅ Opción 'Admin' seleccionada correctamente.");

            // Hacer clic en el botón "Search"
            WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class, 'oxd-button') and contains(text(), 'Search')]")));
            searchButton.click();
            System.out.println("✅ Botón 'Search' clickeado correctamente.");

        } catch (Exception e) {
            System.out.println("❌ Ocurrió un error: " + e.getMessage());
        }
    }
	
	 public void cerrar() {
	        if (driver != null) {
	            driver.quit(); // Cierra el navegador y finaliza el proceso de WebDriver
	        }
	    }



	public void filtar() {
	        try {
	            // Seleccionar el menú "Admin"
	            WebElement adminMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class, 'oxd-main-menu-item') and contains(@href, 'admin/viewAdminModule')]")));
	            adminMenu.click();
	            System.out.println("✅ Menú 'Admin' seleccionado correctamente.");

	            // Abrir el dropdown
	            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'oxd-select-text')]")));
	            dropdown.click();
	            System.out.println("✅ Dropdown abierto.");

	            // Seleccionar la opción "Admin" dentro del dropdown
	            WebElement opcionAdmin = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='oxd-select-text-input' and text()='Admin']")));
	            opcionAdmin.click();
	            System.out.println("✅ Opción 'Admin' seleccionada correctamente.");

	            // Hacer clic en el botón "Search"
	            WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class, 'oxd-button') and contains(text(), 'Search')]")));
	            searchButton.click();
	            System.out.println("✅ Botón 'Search' clickeado correctamente.");

	        } catch (Exception e) {
	            System.out.println("❌ Ocurrió un error: " + e.getMessage());
	        }
	    
		
	}
	
	 public void validarAcciones() {
	        try {
	            // Esperar que la tabla de registros esté visible
	            List<WebElement> filas = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
	                By.xpath("//div[contains(@class, 'oxd-table-cell-actions')]")
	            ));

	            System.out.println("🔍 Total de registros encontrados: " + filas.size());

	            for (int i = 0; i < filas.size(); i++) {
	                WebElement fila = filas.get(i);

	                // Verificar si existe el botón de eliminar (ícono de papelera)
	                boolean existeEliminar = !fila.findElements(By.xpath(".//i[contains(@class, 'bi-trash')]")).isEmpty();

	                // Verificar si existe el botón de editar (ícono de lápiz)
	                boolean existeEditar = !fila.findElements(By.xpath(".//i[contains(@class, 'bi-pencil-fill')]")).isEmpty();

	                if (existeEliminar && existeEditar) {
	                    System.out.println("✅ Registro " + (i + 1) + ": contiene acciones de 'Editar' y 'Eliminar'.");
	                } else {
	                    System.out.println("❌ Registro " + (i + 1) + ": FALTAN acciones.");
	                }
	            }

	        } catch (Exception e) {
	            System.out.println("❌ Error al validar acciones: " + e.getMessage());
	        }
	        
	        cerrar(); // Método para cerrar el navegador
	    }
	 
	 public void validausuario( String employeeName) {
	        try {

            
	            // Ingresar "Employee Name"
	            WebElement employeeInput = driver.findElement(By.xpath("//label[text()='Employee Name']/following::input"));
	            employeeInput.sendKeys(employeeName);
	            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='listbox']//span[contains(text(),'" + employeeName + "')]"))).click();

	            
	            // Hacer clic en el botón "Search"
	            WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class, 'oxd-button') and contains(text(), 'Search')]")));
	            searchButton.click();
	            System.out.println("✅ Botón 'Search' clickeado correctamente.");
	            
	            // Esperar que la tabla de registros esté visible
	            List<WebElement> filas = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
	                By.xpath("//div[contains(@class, 'oxd-table-cell-actions')]")
	            ));

	            System.out.println("🔍 Total de registros encontrados: " + filas.size());

	            for (int i = 0; i < filas.size(); i++) {
	                WebElement fila = filas.get(i);

	                // Verificar si existe el botón de eliminar (ícono de papelera)
	                boolean existeEliminar = !fila.findElements(By.xpath(".//i[contains(@class, 'bi-trash')]")).isEmpty();

	                // Verificar si existe el botón de editar (ícono de lápiz)
	                boolean existeEditar = !fila.findElements(By.xpath(".//i[contains(@class, 'bi-pencil-fill')]")).isEmpty();

	                // Verificar el nombre de usuario

	                
	                assert existeEliminar && existeEditar : "❌ Registro " + (i + 1) + ": FALTAN acciones.";
	                System.out.println("✅ Registro " + (i + 1) + ": contiene acciones de 'Editar' y 'Eliminar'.");
	            }

	        } catch (Exception e) {
	            System.out.println("❌ Ocurrió un error: " + e.getMessage());
	        }
	    
		
	}
	 

	    public  void nuevoUsuario(String role, String employeeName, String status, String username, String password, String confirmPassword) {

	    	// Seleccionar el menú "Admin"
            WebElement adminMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class, 'oxd-main-menu-item') and contains(@href, 'admin/viewAdminModule')]")));
            adminMenu.click();
            System.out.println("✅ Menú 'Admin' seleccionado correctamente.");

            // Hacer clic en el botón "Add"
            WebElement addButton = driver.findElement(By.xpath("//button[contains(., 'Add')]"));
            addButton.click();
            
        
            System.out.println("✅ Botón 'Add' clickeado correctamente.");

         // Seleccionar "User Role"
            WebElement userRoleDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='User Role']/following::div[contains(@class, 'oxd-select-wrapper')]")));
            userRoleDropdown.click();
            driver.findElement(By.xpath("//div[@role='listbox']//span[text()='" + role + "']")).click();

            // Ingresar "Employee Name"
            WebElement employeeInput = driver.findElement(By.xpath("//label[text()='Employee Name']/following::input"));
            employeeInput.sendKeys(employeeName);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='listbox']//span[contains(text(),'" + employeeName + "')]"))).click();

            // Seleccionar "Status"
            WebElement statusDropdown = driver.findElement(By.xpath("//label[text()='Status']/following::div[contains(@class, 'oxd-select-wrapper')]"));
            statusDropdown.click();
            driver.findElement(By.xpath("//div[@role='listbox']//span[text()='" + status + "']")).click();

            // Ingresar "Username"
            driver.findElement(By.xpath("//label[text()='Username']/following::input")).sendKeys(username);

            // Ingresar "Password"
            driver.findElement(By.xpath("//label[text()='Password']/following::input")).sendKeys(password);
            driver.findElement(By.xpath("//label[text()='Confirm Password']/following::input")).sendKeys(confirmPassword);

            // Hacer clic en "Save"
            driver.findElement(By.xpath("//button[text()=' Save ']")).click();
            
            
	    }
	    
}

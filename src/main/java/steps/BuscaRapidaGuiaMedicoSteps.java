package steps;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import cucumber.api.java.pt.*;

public class BuscaRapidaGuiaMedicoSteps{

	 WebDriver driver;
	 
	 @Dado("^que o usuario acesse o portal unimed$")
	 public void usuario_acessa_home() {	 
	 System.setProperty("webdriver.chrome.driver","C:\\bin\\chromedriver.exe");
     driver = new ChromeDriver();
     driver.manage().window().maximize();
     driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
     DesiredCapabilities capabilities = DesiredCapabilities.chrome();
     ChromeOptions options = new ChromeOptions();
     options.addArguments("incognito");
     capabilities.setCapability(ChromeOptions.CAPABILITY, options);
	 driver.get("https://www.unimed.coop.br/");	 
	 }
	 
	 @E("^que ele acesse o menu guia medico$")
	 public void usuario_acessa_guia_medico() {
	 driver.findElement(By.xpath("//*[@id='menuPrincipalItens']/ul/li/a[contains(text(),'Guia Médico')]")).click();
	 }
	 
	 String esp;
	 @Quando("^for informada uma \"(.*)\" valida$")
	 public void usuario_insere_especialidade(String especialidade) {
	 esp = especialidade;
	 driver.findElement(By.name("pesquisa")).sendKeys(especialidade);
	 }
	 
	 @E("^quando for clicado em pesquisar$")
	 public void usuario_clica_pesquisar() {
	 driver.findElement(By.id("btn_pesquisar")).click();	 
	 }
	 
	 @Então("^o sistema deve exibir a tela de selecao de estado e cidade$")
	 public void assert_estado_cidade() {
	 Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(),'Selecione o estado e a cidade para localizar a Unimed onde você deseja ser atendido')]")).isDisplayed());
	 }
	 
	 @Dado("^que o usuario selecione \"(.*)\" e \"(.*)\"$")
	 public void usuario_seleciona_estado_cidade(String estado, String cidade) throws InterruptedException {		 
		 	Thread.sleep(1000);
		    driver.findElement(By.xpath("//div[@id='app']/div/div/div/div/div/form/div/div/div/div/div")).click();
		    Thread.sleep(1000);
		    driver.findElement(By.id("react-select-2-input")).clear();
		    Thread.sleep(1000);
		    driver.findElement(By.id("react-select-2-input")).sendKeys(estado);
		    driver.findElement(By.id("react-select-2-input")).sendKeys(Keys.RETURN);
		    Thread.sleep(1000);
		    driver.findElement(By.xpath("//div[@id='app']/div/div/div/div/div/form/div/div[2]/div/div/div/div")).click();
		    Thread.sleep(1000);
		    driver.findElement(By.id("react-select-3-input")).clear();
		    Thread.sleep(1000);
		    driver.findElement(By.id("react-select-3-input")).sendKeys(cidade);
		    driver.findElement(By.id("react-select-3-input")).sendKeys(Keys.RETURN);  
	 }
	 
	 @E("^que tambem seja selecionada a unidade$")
	 public void usuario_seleciona_unidade() throws InterruptedException {
	 driver.findElement(By.xpath("//div[@id='app']/div/div/div/div/div[2]/form/label/div[2]")).click();
	 }
	 
	 @E("^que seja clicado em continuar$")
	 public void usuario_clica_continuar() {
	 driver.findElement(By.xpath("//div[@id='app']/div/div/div/div/div[3]/button[2]")).click();
	 }
	 
	 @Então("^o sistema deve exibir uma lista de profissionais que atuam na regiao e na especialidade selecionadas$")
	 public void assert_medicos() throws Exception {
		 
	 driver.findElement(By.xpath("//*[contains(text(),'Fechar')]")).click();
	 
	 List <WebElement> listaMedicos = driver.findElements(By.xpath("//*[starts-with(@id,'resultado')]/div[1]/p[2]/span[2][contains(text(),'"+esp+"')]"));
	 List <WebElement> listaSP = driver.findElements(By.xpath("//*[contains(p,'/ SP')]"));
	 WebDriverWait wait = new WebDriverWait(driver, 30);

	 	//Verificação da especialidade & estado página 1
	 	for (int i=1; i<=listaMedicos.size(); i++)
		 { 
		    wait.until(ExpectedConditions.visibilityOf(listaMedicos.get(i-1)));
		    if (listaSP.size() > 0) { throw new Exception("Estado de SP Localizado durante o teste!!! (Página 1)"); }
		 }
	 	
	 	driver.findElement(By.cssSelector("#app > div > div > div.fade-in > div.span12.pagination.text-center.no-margin-left > div > ul > li:nth-child(4) > a")).click();
	 	Thread.sleep(2000);
	 	
	 	//Verificação da especialidade & estado página 2
	 	for (int i=1; i<=listaMedicos.size(); i++)
		 { 
		    wait.until(ExpectedConditions.visibilityOf(listaMedicos.get(i-1)));
		    if (listaSP.size() != 0) { throw new Exception("Estado de SP Localizado durante o teste!!! (Página 2)"); }
		 }
	 	
	 	driver.findElement(By.cssSelector("#app > div > div > div.fade-in > div.span12.pagination.text-center.no-margin-left > div > ul > li:nth-child(5) > a")).click();
	 	Thread.sleep(2000);
	 	
	 	//Verificação da especialidade & estado página 3
	 	for (int i=1; i<=listaMedicos.size(); i++)
		 { 
		    wait.until(ExpectedConditions.visibilityOf(listaMedicos.get(i-1)));
		    if (listaSP.size() > 0) { throw new Exception("Estado de SP Localizado durante o teste!!! (Página 3)"); }
		 }
			 
	 }
	 
	 @Então("^fecha-se o navegador$")
	 public void encerrar_driver(){
		 driver.quit();
	 }
	 
}
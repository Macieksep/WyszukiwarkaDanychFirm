import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WyszukiwarkaFirm {
    public static void main(String[] args){

        //ikonka
        Image img = Toolkit.getDefaultToolkit().getImage("src/main/resources/company.png");

        //okno
        JFrame win = new JFrame("Wyszukiwarka danych firmy");
            win.setBounds(100,100,550,300);
            win.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            win.setResizable(false);
            win.setIconImage(img);

        //elementy okna
        JLabel loadingLabel = new JLabel();
            loadingLabel.setBounds(80,215,30,30);
        ImageIcon loading = new ImageIcon("src/main/resources/loading.gif");
            loadingLabel.setIcon(loading);
            loadingLabel.setVisible(false);

        JLabel titleLabel = new JLabel("WYSZUKIWARKA FIRM");
            titleLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
            titleLabel.setBounds(10,10,170,30);

        // REGON
        JTextField regonTxtField = new JTextField();
            regonTxtField.setBounds(60,45,110,30);

        JLabel regonTxtLabel = new JLabel("REGON");
            regonTxtLabel.setBounds(10,45,100,30);

        //NIP
        JTextField nipTxtField = new JTextField();
            nipTxtField.setBounds(60,85,110,30);

        JLabel nipTxtLabel = new JLabel("NIP");
            nipTxtLabel.setBounds(10, 85,100,30);

        //KRS
        JTextField KRSTxtField = new JTextField();
            KRSTxtField.setBounds(60,125,110,30);

        JLabel KRSTxtLabel = new JLabel("KRS");
            KRSTxtLabel.setBounds(10, 125,100,30);


        JButton button = new JButton("Szukaj");
            button.setBounds(50,175,90,30);
            button.setBackground(Color.white);
            button.setFocusable(false);


        JLabel regonLabel = new JLabel("Regon:");
            regonLabel.setBounds(200,10,300,30);

        JLabel nazwaLabel = new JLabel("Nazwa:");
            nazwaLabel.setBounds(200,40,300,30);

        JLabel wojewodztwoLabel = new JLabel("Województwo:");
            wojewodztwoLabel.setBounds(200,70,300,30);

        JLabel powiatLabel = new JLabel("Powiat:");
            powiatLabel.setBounds(200,100,300,30);

        JLabel gminaLabel = new JLabel("Gmina:");
            gminaLabel.setBounds(200,130,300,30);

        JLabel kodPocztowyLabel = new JLabel("Kod pocztowy:");
            kodPocztowyLabel.setBounds(200,160,300,30);

        JLabel miejscowoscLabel = new JLabel("Miejscowość:");
            miejscowoscLabel.setBounds(200,190,300,30);

        JLabel ulicaLabel = new JLabel("Ulica:");
            ulicaLabel.setBounds(200,220,300,30);

        //panel główny
        JPanel panel = new JPanel();
        {
            panel.setLayout(null);

            panel.add(regonTxtField);
            panel.add(nipTxtField);
            panel.add(KRSTxtField);

            panel.add(titleLabel);
            panel.add(button);
            panel.add(loadingLabel);

            panel.add(regonTxtLabel);
            panel.add(nipTxtLabel);
            panel.add(KRSTxtLabel);

            panel.add(regonLabel);
            panel.add(nazwaLabel);
            panel.add(wojewodztwoLabel);
            panel.add(powiatLabel);
            panel.add(gminaLabel);
            panel.add(kodPocztowyLabel);
            panel.add(miejscowoscLabel);
            panel.add(ulicaLabel);

            win.add(panel);
            win.setVisible(true);
        }

        //działanie guzika
        button.addActionListener(e -> {

            loadingLabel.setVisible(true);

            //wyszukiwanie Selenium
            if (regonTxtField.getText().length() == 9 || regonTxtField.getText().length() == 14){

                new Thread(() -> {

                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--headless");

                    WebDriver driver = new ChromeDriver(options);
                    driver.get("https://wyszukiwarkaregon.stat.gov.pl/appBIR/index.aspx");

                    WebElement regonBox = driver.findElement(By.id("txtRegon"));

                    WebElement searchButton = driver.findElement(By.id("btnSzukaj"));

                    driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

                    regonBox.sendKeys(regonTxtField.getText());
                    searchButton.click();

                    driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

                    WebElement table = driver.findElement(By.className("tabelaZbiorczaListaJednostekAltRow"));

                    List<WebElement> dataRows = table.findElements(By.tagName("td"));

                    String regon = dataRows.get(0).getText();
                    String name = dataRows.get(2).getText();
                    String wojewodztwo = dataRows.get(3).getText();
                    String powiat = dataRows.get(4).getText();
                    String gmina = dataRows.get(5).getText();
                    String kodPocztowy = dataRows.get(6).getText();
                    String miejscowosc = dataRows.get(7).getText();
                    String ulica = dataRows.get(8).getText();

                    regonLabel
                            .setText("Regon:                 " + regon);
                    nazwaLabel
                            .setText("Nazwa:                 " + name);
                    wojewodztwoLabel
                            .setText("Województwo:  " + wojewodztwo);
                    powiatLabel
                            .setText("Powiat:                " + powiat);
                    gminaLabel
                            .setText("Gmina:                 " + gmina);
                    kodPocztowyLabel
                            .setText("Kod pocztowy:   " + kodPocztowy);
                    miejscowoscLabel
                            .setText("Miejscowość:    " + miejscowosc);
                    ulicaLabel
                            .setText("Ulica:                    " + ulica);

                    loadingLabel.setVisible(false);

                }).start();

            } else if (nipTxtField.getText().length() == 10){

                new Thread(() -> {

                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--headless");

                    WebDriver driver = new ChromeDriver(options);
                    driver.get("https://wyszukiwarkaregon.stat.gov.pl/appBIR/index.aspx");

                    WebElement nipBox = driver.findElement(By.id("txtNip"));
                    WebElement searchButton = driver.findElement(By.id("btnSzukaj"));

                    driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

                    nipBox.sendKeys(nipTxtField.getText());
                    searchButton.click();

                    driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

                    WebElement table = driver.findElement(By.className("tabelaZbiorczaListaJednostekAltRow"));

                    List<WebElement> dataRows = table.findElements(By.tagName("td"));

                    String regon = dataRows.get(0).getText();
                    String name = dataRows.get(2).getText();
                    String wojewodztwo = dataRows.get(3).getText();
                    String powiat = dataRows.get(4).getText();
                    String gmina = dataRows.get(5).getText();
                    String kodPocztowy = dataRows.get(6).getText();
                    String miejscowosc = dataRows.get(7).getText();
                    String ulica = dataRows.get(8).getText();

                    regonLabel
                            .setText("Regon:                 " + regon);
                    nazwaLabel
                            .setText("Nazwa:                 " + name);
                    wojewodztwoLabel
                            .setText("Województwo:  " + wojewodztwo);
                    powiatLabel
                            .setText("Powiat:                " + powiat);
                    gminaLabel
                            .setText("Gmina:                 " + gmina);
                    kodPocztowyLabel
                            .setText("Kod pocztowy:   " + kodPocztowy);
                    miejscowoscLabel
                            .setText("Miejscowość:    " + miejscowosc);
                    ulicaLabel
                            .setText("Ulica:                    " + ulica);

                    loadingLabel.setVisible(false);

                }).start();

            } else if (KRSTxtField.getText().length() == 10){

                new Thread(() -> {

                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--headless");

                    WebDriver driver = new ChromeDriver(options);
                    driver.get("https://wyszukiwarkaregon.stat.gov.pl/appBIR/index.aspx");

                    WebElement krsBox = driver.findElement(By.id("txtKrs"));
                    WebElement searchButton = driver.findElement(By.id("btnSzukaj"));

                    driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

                    krsBox.sendKeys(KRSTxtField.getText());
                    searchButton.click();

                    driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

                    WebElement table = driver.findElement(By.className("tabelaZbiorczaListaJednostekAltRow"));

                    List<WebElement> dataRows = table.findElements(By.tagName("td"));

                    String regon = dataRows.get(0).getText();
                    String name = dataRows.get(2).getText();
                    String wojewodztwo = dataRows.get(3).getText();
                    String powiat = dataRows.get(4).getText();
                    String gmina = dataRows.get(5).getText();
                    String kodPocztowy = dataRows.get(6).getText();
                    String miejscowosc = dataRows.get(7).getText();
                    String ulica = dataRows.get(8).getText();

                    regonLabel
                            .setText("Regon:                 " + regon);
                    nazwaLabel
                            .setText("Nazwa:                 " + name);
                    wojewodztwoLabel
                            .setText("Województwo:  " + wojewodztwo);
                    powiatLabel
                            .setText("Powiat:                " + powiat);
                    gminaLabel
                            .setText("Gmina:                 " + gmina);
                    kodPocztowyLabel
                            .setText("Kod pocztowy:   " + kodPocztowy);
                    miejscowoscLabel
                            .setText("Miejscowość:    " + miejscowosc);
                    ulicaLabel
                            .setText("Ulica:                    " + ulica);

                    loadingLabel.setVisible(false);

                }).start();

            } else {
                loadingLabel.setVisible(false);
            }

        });

    }
}

/*

    Tytuł: Wyszukiwarka danych firmy
    Opis: Program, pozwalający na wyszukanie danych firmy po REGON, NIP lub KRS
    Autor: Maciej Sepeta

    Technologie zewnętrzne: Selenium, WebDriverManager
    Źródło danych: https://wyszukiwarkaregon.stat.gov.pl/appBIR/index.aspx

 */
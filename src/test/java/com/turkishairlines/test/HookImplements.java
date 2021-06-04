package com.turkishairlines.test;

import com.thoughtworks.gauge.BeforeScenario;
import com.thoughtworks.gauge.AfterScenario;
import io.appium.java_client.AppiumDriver;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;

public class HookImplements {
    public static AppiumDriver driver;
    JSONParser parser = new JSONParser();

    public String parser(String key){
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("ElementsInfo.json").getFile());
            JSONArray obj = (JSONArray)parser.parse(new FileReader(file.getAbsolutePath()));

            for (Object o : obj) {
                JSONObject myKeys = (JSONObject) o;
                if (key.equals(myKeys.get("key"))){
                    return myKeys.get("value").toString();
                };
            }
        }catch (Exception e) {
            System.out.println(e);
        }
        return null;

    };

    @BeforeScenario
    public void prepareAndroidForAppium() throws MalformedURLException {
        WebDriverWait wait;
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("udid", "emulator-5554");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "8.1");
        capabilities.setCapability("skipUnlock", "true");
        capabilities.setCapability("appPackage", "com.turkishairlines.mobile");
        capabilities.setCapability("appActivity", "com.turkishairlines.mobile.ui.main.MainActivity");
        capabilities.setCapability("noReset", "false");
        URL url = new URL("http://0.0.0.0:4723/wd/hub");
        driver = new AppiumDriver(url, capabilities);
        wait = new WebDriverWait(driver, 10);


    }
    @AfterScenario
    public  void afterScenario(){
        if(driver !=null){

            driver.quit();

        }
}


}

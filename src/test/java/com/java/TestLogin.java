package com.java;

import helper.ExcelWriter;
import model.Response;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;


import static com.java.LoadBrowser.driver;

public class TestLogin {

    @Test(priority = 1)
    public void testLogin() {
        Response response = new Response()
                .setName("Test Login")
                .setTestType("FUNC")
                .setPriority("High")
                .setId("Login_Test_1")
                .setAction("Truy cập vào trang login và tiến hành nhập dữ liệu vào các trường")
                .setExpectedResult("Login thành công")
                .setActualResult("Login thành công")
                .setStatus(true)
                .setTester("Long")
                .setDate("07/08/2021");
        System.out.println("3");
        try {
            System.out.println("4");
            driver.navigate().to("http://localhost:8080/signin");
            driver.findElement(By.xpath("//*[@id=\"content\"]/section/div/div[1]/div[1]/div/form/input[1]")).sendKeys("admin");
            driver.findElement(By.xpath("//*[@id=\"content\"]/section/div/div[1]/div[1]/div/form/input[2]")).sendKeys("123");
            driver.findElement(By.xpath("//*[@id=\"content\"]/section/div/div[1]/div[1]/div/form/button")).click();
            Thread.sleep(1000);

            if (!driver.getCurrentUrl().equals("http://localhost:8080/user/user")) {
                System.out.println("1.1");
                response.setActualResult("Không thành công");
                response.setStatus(false);
                ExcelWriter.responseList.add(response);
                Assert.assertTrue(false);
            }
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            response.setActualResult("Thao tác không thành công").setStatus(false);
            ExcelWriter.responseList.add(response);
            Assert.assertTrue(false);
        }
        ExcelWriter.responseList.add(response);
        Assert.assertTrue(true);

    }

    @Test(priority = 2)
    public void testLoginWithUnknownUser() {
        Response response = new Response()
                .setName("Test Login")
                .setTestType("FUNC")
                .setPriority("High")
                .setId("Login_Test_2")
                .setAction("Truy cập vào trang login và nhập 1 user không có trong database \n" +
                        "Username: test \n" +
                        "Password: 123456789")
                .setExpectedResult("Login không thành công và ở lại trang login")
                .setActualResult("Login không thành công")
                .setStatus(true)
                .setTester("Long")
                .setDate("07/08/2021");
        try {
            driver.navigate().to("http://localhost:8080/signin");
            driver.findElement(By.xpath("//*[@id=\"content\"]/section/div/div[1]/div[1]/div/form/input[1]")).sendKeys("test");
            driver.findElement(By.xpath("//*[@id=\"content\"]/section/div/div[1]/div[1]/div/form/input[2]")).sendKeys("123456789");
            driver.findElement(By.xpath("//*[@id=\"content\"]/section/div/div[1]/div[1]/div/form/button")).click();
            Thread.sleep(2000);

            if (!driver.getCurrentUrl().equals("http://localhost:8080/signin")) {
                response.setActualResult("Login thành công");
                response.setStatus(false);
                ExcelWriter.responseList.add(response);
                Assert.assertTrue(false);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            response.setActualResult("Thao tác không thành công").setStatus(false);
            ExcelWriter.responseList.add(response);
            Assert.assertTrue(false);
        }

        ExcelWriter.responseList.add(response);
        Assert.assertTrue(true);
    }

    @Test(priority = 3)
    public void testLoginUnknownPass() {
        Response response = new Response()
                .setName("Test Login")
                .setTestType("FUNC")
                .setPriority("High")
                .setId("Login_Test_3")
                .setAction("Truy cập vào trang login chỉ nhập username")
                .setExpectedResult("Login không thành công và ở lại trang login")
                .setActualResult("Login không thành công")
                .setStatus(true)
                .setTester("Long")
                .setDate("07/08/2021");
        try {
            driver.navigate().to("http://localhost:8080/signin");
            driver.findElement(By.xpath("//*[@id=\"content\"]/section/div/div[1]/div[1]/div/form/input[1]")).sendKeys("admin");
            driver.findElement(By.xpath("//*[@id=\"content\"]/section/div/div[1]/div[1]/div/form/input[2]")).sendKeys("");
            driver.findElement(By.xpath("//*[@id=\"content\"]/section/div/div[1]/div[1]/div/form/button")).click();
            Thread.sleep(2000);

            if (!driver.getCurrentUrl().equals("http://localhost:8080/signin")) {
                response.setActualResult("Login thành công");
                response.setStatus(false);
                ExcelWriter.responseList.add(response);
                Assert.assertTrue(false);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            response.setActualResult("Thao tác không thành công").setStatus(false);
            ExcelWriter.responseList.add(response);
            Assert.assertTrue(false);
        }

        ExcelWriter.responseList.add(response);
        Assert.assertTrue(true);
    }

    @Test(priority = 4)
    public void testTitle() {
        driver.navigate().to("http://localhost:8080/signin");
        if (driver.getTitle().equals("PM")) {
            Response response = new Response()
                    .setName("Test Login")
                    .setTestType("FUNC")
                    .setPriority("High")
                    .setId("Login_Test_4")
                    .setAction("Truy cập vào trang web")
                    .setExpectedResult("Title ='PM'")
                    .setActualResult("Title ='PM'")
                    .setStatus(true)
                    .setTester("Long")
                    .setDate("07/08/2021");
            ExcelWriter.responseList.add(response);
            Assert.assertTrue(true);
        } else {

            Response response = new Response()
                    .setName("Test Login")
                    .setTestType("FUNC")
                    .setPriority("High")
                    .setId("Login_Test_4")
                    .setAction("Truy cập vào trang login")
                    .setExpectedResult("Tile ='PM'")
                    .setActualResult("Title = '" + driver.getTitle() + "'")
                    .setStatus(false)
                    .setTester("LongVT")
                    .setDate("20/06/2020");
            ExcelWriter.responseList.add(response);
            Assert.assertTrue(false);
        }

    }
}
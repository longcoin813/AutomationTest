package com.java;

import helper.ExcelWriter;
import model.Response;
import model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import service.UserService;

import java.util.List;

import static com.java.LoadBrowser.driver;

public class TestUser {

    @Test(priority = 1)
    public void testInsert() {
        if (!driver.getCurrentUrl().equals("http://localhost:8080/user/user")) {
            driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/ul/li[1]/a")).click();
        } else {
            driver.navigate().refresh();
        }
        Response response = new Response()
                .setName("Test User")
                .setTestType("FUNC")
                .setPriority("High")
                .setId("User_Insert_Test_1")
                .setAction("Truy cập vào trang người dùng\n"+"Bấm thêm người dùng, "+"Thêm người dùng chưa có trong database")
                .setExpectedResult("Thêm thành công")
                .setActualResult("Thêm thành công")
                .setStatus(true)
                .setTester("Long")
                .setDate("07/08/2021");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/button")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"fullName\"]")).sendKeys("Hoàng Long");
            driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("hLong");
            driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("123456789");
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/button[2]")).click();
            Thread.sleep(1000);
            if (!driver.findElement(By.xpath("//*[@id=\"message_title\"]")).getText().equalsIgnoreCase("Thêm thành công")) {
                response.setActualResult("Thêm không thành công").setStatus(false);
                ExcelWriter.responseList.add(response);
                Assert.assertTrue(false);
            }
        } catch (InterruptedException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            response.setActualResult("Thao tác không thành công").setStatus(false);
            ExcelWriter.responseList.add(response);
            Assert.assertTrue(false);
        }
        ExcelWriter.responseList.add(response);
        Assert.assertTrue(true);
    }
    @Test(priority = 2)
    public void testInsertWithNotFullname() {

        if (!driver.getCurrentUrl().equals("http://localhost:8080/user/user")) {
            driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/ul/li[1]/a")).click();
        } else {
            driver.navigate().refresh();
        }
        Response response = new Response()
                .setName("Test User")
                .setTestType("FUNC")
                .setPriority("High")
                .setId("User_Insert_Test_2")
                .setAction("Truy cập vào trang người dùng\n không điền Fullname")
                .setExpectedResult("Thêm không thành công")
                .setActualResult("Thêm không thành công")
                .setStatus(true)
                .setTester("Long")
                .setDate("07/08/2021");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/button")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"fullName\"]")).sendKeys("");
            driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("test");
            driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("123456789");
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/button[2]")).click();
            Thread.sleep(1000);
            if (!driver.findElement(By.xpath("//*[@id=\"message_title\"]")).getText().equalsIgnoreCase("Thêm thất bại")) {
                response.setActualResult("Thêm thành công").setStatus(false);
                ExcelWriter.responseList.add(response);
                Assert.assertTrue(false);
            }
        } catch (InterruptedException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            response.setActualResult("Thao tác không thành công").setStatus(false);
            ExcelWriter.responseList.add(response);
            Assert.assertTrue(false);
        }
        ExcelWriter.responseList.add(response);
        Assert.assertTrue(true);
    }
    @Test(priority = 3)
    public void testInsertWithExitsUser() {

        if (!driver.getCurrentUrl().equals("http://localhost:8080/user/user")) {
            driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/ul/li[1]/a")).click();
        } else {
            driver.navigate().refresh();
        }
        Response response = new Response()
                .setName("Test User")
                .setTestType("FUNC")
                .setPriority("High")
                .setId("User_Insert_Test_3")
                .setAction("Truy cập vào trang người dùng\n Nhấn nút thêm người dùng \n" +
                        "Thêm người dùng đã có trong database")
                .setExpectedResult("Thêm không thành công")
                .setActualResult("Thêm không thành công")
                .setStatus(true)
                .setTester("Long")
                .setDate("07/08/2021");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/button")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"fullName\"]")).sendKeys("admin");
            driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("admin");
            driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("123456789");
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/button[2]")).click();
            Thread.sleep(1000);
            if (!driver.findElement(By.xpath("//*[@id=\"message_title\"]")).getText().equalsIgnoreCase("user đã tồn tại!")) {
                response.setActualResult("Thêm thành công").setStatus(false);
                ExcelWriter.responseList.add(response);
                Assert.assertTrue(false);
            }
        } catch (InterruptedException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            response.setActualResult("Thao tác không thành công").setStatus(false);
            ExcelWriter.responseList.add(response);
            Assert.assertTrue(false);
        }
        ExcelWriter.responseList.add(response);
        Assert.assertTrue(true);
    }
    @Test(priority = 4)
    public void testUpdate() {

        if (!driver.getCurrentUrl().equals("http://localhost:8080/user/user")) {
            driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/ul/li[1]/a")).click();
        } else {
            driver.navigate().refresh();
        }
        Response response = new Response()
                .setName("Test User")
                .setTestType("FUNC")
                .setPriority("High")
                .setId("User_Update_Test")
                .setAction("Truy cập vào trang người dùng\n Nhấn nút update \n" +
                        "update người dùng đã có trong database")
                .setExpectedResult("update thành công")
                .setActualResult("update thành công")
                .setStatus(true)
                .setTester("Long")
                .setDate("07/08/2021");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr[1]/td[3]/button")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"editFullName\"]")).sendKeys("update");
            driver.findElement(By.xpath("//*[@id=\"editPassword\"]")).sendKeys("123456789");
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"editUser\"]/div[4]/button[2]")).click();
            Thread.sleep(1000);
            if (!driver.findElement(By.xpath("//*[@id=\"message_title\"]")).getText().equalsIgnoreCase("Cập nhật thành công!")) {
                response.setActualResult("Update không thành công").setStatus(false);
                ExcelWriter.responseList.add(response);
                Assert.assertTrue(false);
            }
        } catch (InterruptedException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            response.setActualResult("Thao tác không thành công").setStatus(false);
            ExcelWriter.responseList.add(response);
            Assert.assertTrue(false);
        }
        ExcelWriter.responseList.add(response);
        Assert.assertTrue(true);
    }
    @Test(priority = 5)
    public void testDelete() {

        if (!driver.getCurrentUrl().equals("http://localhost:8080/user/user")) {
            driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/ul/li[1]/a")).click();
        } else {
            driver.navigate().refresh();
        }
        Response response = new Response()
                .setName("Test User")
                .setTestType("FUNC")
                .setPriority("High")
                .setId("User_Delete_Test")
                .setAction("Truy cập vào trang người dùng\n Nhấn nút delete \n" +
                        "Xóa người dùng đã có trong database")
                .setExpectedResult("Xóa thành công")
                .setActualResult("Xóa thành công")
                .setStatus(true)
                .setTester("Long")
                .setDate("07/08/2021");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr[2]/td[3]/a")).click();
            Thread.sleep(50);
            if (!driver.findElement(By.xpath("//*[@id=\"message_title\"]")).getText().equalsIgnoreCase("Xóa thành công!")) {
                response.setActualResult("Xóa không thành công").setStatus(false);
                ExcelWriter.responseList.add(response);
                Assert.assertTrue(false);
            }
        } catch (InterruptedException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            response.setActualResult("Thao tác không thành công").setStatus(false);
            ExcelWriter.responseList.add(response);
            Assert.assertTrue(false);
        }
        ExcelWriter.responseList.add(response);
        Assert.assertTrue(true);
    }
}

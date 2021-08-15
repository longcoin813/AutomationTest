package com.java;

import helper.ExcelWriter;
import model.Response;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.java.LoadBrowser.driver;

public class TestStaff {
    @Test(priority = 1)
    public void testInsert() {
        if (!driver.getCurrentUrl().equals("http://localhost:8080/user/staff")) {
            driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/ul/li[2]/a")).click();
        } else {
            driver.navigate().refresh();
        }
        Response response = new Response()
                .setName("Test Staff")
                .setTestType("FUNC")
                .setPriority("High")
                .setId("Staff_Insert_Test_1")
                .setAction("Truy cập vào trang nhân viên\n Bấm thêm nhân viên \n Điền tất cả đúng tất cả thông tin")
                .setExpectedResult("Thêm thành công")
                .setActualResult("Thêm thành công")
                .setStatus(true)
                .setTester("Duy")
                .setDate("07/08/2021");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/button")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"staffid\"]")).sendKeys("010");
            driver.findElement(By.xpath("//*[@id=\"staffname\"]")).sendKeys("Long");
            driver.findElement(By.xpath("//*[@id=\"radio1\"]")).sendKeys("true");
            driver.findElement(By.xpath("//*[@id=\"radio2\"]")).click();
            driver.findElement(By.xpath("//*[@id=\"birthday\"]")).sendKeys("2000-02-01");
            driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("longdev813@gmail.com");
            driver.findElement(By.xpath("//*[@id=\"phone\"]")).sendKeys("123456789");
            driver.findElement(By.xpath("//*[@id=\"salary\"]")).sendKeys("10000000");
            driver.findElement(By.xpath("//*[@id=\"notes\"]")).sendKeys("note");
            driver.findElement(By.xpath("//*[@id=\"departsByDepartid\"]")).sendKeys("IT");
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"form\"]/div[10]/button[2]")).click();
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
    public void testInsertWithNotSalary() {
        if (!driver.getCurrentUrl().equals("http://localhost:8080/user/staff")) {
            driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/ul/li[2]/a")).click();
        } else {
            driver.navigate().refresh();
        }
        Response response = new Response()
                .setName("Test Staff")
                .setTestType("FUNC")
                .setPriority("High")
                .setId("Staff_Insert_Test_2")
                .setAction("Truy cập vào trang người dùng\n Bấm thêm nhân viên \n không điền Salyry")
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
            driver.findElement(By.xpath("//*[@id=\"staffid\"]")).sendKeys("007");
            driver.findElement(By.xpath("//*[@id=\"staffname\"]")).sendKeys("Long");
            driver.findElement(By.xpath("//*[@id=\"radio1\"]")).sendKeys("true");
            driver.findElement(By.xpath("//*[@id=\"radio2\"]")).click();
            driver.findElement(By.xpath("//*[@id=\"birthday\"]")).sendKeys("2000-02-01");
            driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("longdev813@gmail.com");
            driver.findElement(By.xpath("//*[@id=\"phone\"]")).sendKeys("123456789");
            driver.findElement(By.xpath("//*[@id=\"salary\"]")).sendKeys("");
            driver.findElement(By.xpath("//*[@id=\"notes\"]")).sendKeys("note");
            driver.findElement(By.xpath("//*[@id=\"departsByDepartid\"]")).sendKeys("IT");
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"form\"]/div[10]/button[2]")).click();
            Thread.sleep(1000);
            if (!driver.findElement(By.xpath("//*[@id=\"message_title\"]")).getText().equalsIgnoreCase("Thêm không thành công")) {
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
    public void testInsertWithExitsStaff() {

        if (!driver.getCurrentUrl().equals("http://localhost:8080/user/staff")) {
            driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/ul/li[2]/a")).click();
        } else {
            driver.navigate().refresh();
        }
        Response response = new Response()
                .setName("Test Staff")
                .setTestType("FUNC")
                .setPriority("High")
                .setId("Staff_Insert_Test_3")
                .setAction("Truy cập vào trang người dùng\n Nhấn nút thêm nhân viên\n" +
                        "Thêm nhân viên đã có trong database")
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
            driver.findElement(By.xpath("//*[@id=\"staffid\"]")).sendKeys("001");
            driver.findElement(By.xpath("//*[@id=\"staffname\"]")).sendKeys("Long");
            driver.findElement(By.xpath("//*[@id=\"radio1\"]")).sendKeys("true");
            driver.findElement(By.xpath("//*[@id=\"radio2\"]")).click();
            driver.findElement(By.xpath("//*[@id=\"birthday\"]")).sendKeys("2000-02-01");
            driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("longdev813@gmail.com");
            driver.findElement(By.xpath("//*[@id=\"phone\"]")).sendKeys("123456789");
            driver.findElement(By.xpath("//*[@id=\"salary\"]")).sendKeys("10000000");
            driver.findElement(By.xpath("//*[@id=\"notes\"]")).sendKeys("note");
            driver.findElement(By.xpath("//*[@id=\"departsByDepartid\"]")).sendKeys("IT");
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"form\"]/div[10]/button[2]")).click();
            Thread.sleep(1000);
            if (!driver.findElement(By.xpath("//*[@id=\"message_title\"]")).getText().equalsIgnoreCase("Mã nhân viên đã tồn tại!")) {
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
    public void testDelete() {
        if (!driver.getCurrentUrl().equals("http://localhost:8080/user/staff")) {
            driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr[7]/td[9]/a")).click();
        } else {
            driver.navigate().refresh();
        }
        Response response = new Response()
                .setName("Test Staff")
                .setTestType("FUNC")
                .setPriority("High")
                .setId("Staff_Delete_Test_1")
                .setAction("Truy cập vào trang nhân viên\n Nhấn nút delete \n" +
                        "Xóa nhân viên chưa có thành tích ")
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
            driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr[6]/td[9]/a")).click();
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
    @Test(priority = 5)
    public void testDeleteHaveRecord() {

        if (!driver.getCurrentUrl().equals("http://localhost:8080/user/staff")) {
            driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr[2]/td[9]/a")).click();
        } else {
            driver.navigate().refresh();
        }
        Response response = new Response()
                .setName("Test Staff")
                .setTestType("FUNC")
                .setPriority("High")
                .setId("Staff_Delete_Test_2")
                .setAction("Truy cập vào trang nhân viên\n Nhấn nút delete \n" +
                        "Xóa nhân viên đã có thành tích")
                .setExpectedResult("Xóa không thành công")
                .setActualResult("Xóa thành công")
                .setStatus(false)
                .setTester("Long")
                .setDate("07/08/2021");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr[2]/td[9]/a")).click();
            Thread.sleep(50);
            if (driver.findElement(By.xpath("//*[@id=\"message_title\"]")).getText().equalsIgnoreCase("Xóa không thành công")) {
                response.setActualResult("Xóa không thành công").setStatus(true);
                ExcelWriter.responseList.add(response);
                Assert.assertTrue(true);
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

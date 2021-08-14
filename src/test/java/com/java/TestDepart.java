package com.java;

import helper.ExcelWriter;
import model.Departs;
import model.Response;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import service.DepartService;

import java.util.List;

import static com.java.LoadBrowser.driver;

public class TestDepart {

    private List<Departs> list;

    @BeforeTest()
    public void startBrowser() {
        list = new DepartService().getList();
    }

    @Test(priority = 1)
    public void testInsert() {
        if (!driver.getCurrentUrl().equals("http://localhost:8080/user/user")) {
            driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/ul/li[4]/a")).click();
        } else {
            driver.navigate().refresh();
        }
        Response response = new Response()
                .setName("Test Depart")
                .setTestType("FUNC")
                .setPriority("High")
                .setId("Depart_Insert_Test_1")
                .setAction("Truy cập vào trang phòng ban\n" +
                        "Nhấn nút thêm phòng ban")
                .setExpectedResult("Thêm thành công")
                .setActualResult("Thêm thành công")
                .setStatus(true)
                .setTester("Long")
                .setDate("07/08/2021");

        list.forEach(depart -> {
            try {
                driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/button")).click();
                Thread.sleep(1000);
                driver.findElement(By.xpath("//*[@id=\"departid\"]")).sendKeys(depart.getDepartid());
                driver.findElement(By.xpath("//*[@id=\"departname\"]")).sendKeys(depart.getDepartname());
                Thread.sleep(500);
                driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/button[2]")).click();
                Thread.sleep(1000);
                if (driver.findElement(By.xpath("//*[@id=\"message_title\"]")).getText().equalsIgnoreCase("Thêm không thành công")) {
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
        });
        ExcelWriter.responseList.add(response);
        Assert.assertTrue(true);
    }

    @Test(priority = 2)
    public void testInserHaveID() {
        driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/ul/li[4]/a")).click();
        Response response = new Response()
                .setName("Test Depart")
                .setTestType("FUNC")
                .setPriority("High")
                .setId("Depart_Insert_Test_2")
                .setAction("Truy cập vào trang phòng ban \n" +
                        "Nhấn nút thêm phòng ban \n" +
                        "Thêm phòng ban đã có trong database")
                .setExpectedResult("Thêm không thành công")
                .setActualResult("Thêm không thành công")
                .setStatus(true)
                .setTester("long")
                .setDate("07/08/2021");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Departs depart = list.get(0);
        try {
            driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/button")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//*[@id=\"departid\"]")).sendKeys(depart.getDepartid());
            driver.findElement(By.xpath("//*[@id=\"departname\"]")).sendKeys(depart.getDepartname());
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/button[2]")).click();
            Thread.sleep(500);
            if (driver.findElement(By.xpath("//*[@id=\"message_title\"]")).getText().equalsIgnoreCase("Thêm thành công")) {
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
    public void testUpdate() {
        Response response = new Response()
                .setName("Test Depart")
                .setTestType("FUNC")
                .setPriority("High")
                .setId("Depart_Update_Test")
                .setAction("Truy cập vào trang quản lý phòng ban \n" +
                        "Nhấn nút cập nhật phòng ban \n" +
                        "Thêm 'updated' vào sau tên phòng ban")
                .setExpectedResult("Cập nhật thành công")
                .setActualResult("Cập nhật thành công")
                .setStatus(true)
                .setTester("long")
                .setDate("07/08/2020");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr[2]/td[3]/button")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//*[@id=\"editName\"]")).sendKeys("Update");
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"editDepart\"]/div[3]/button[2]")).click();
            Thread.sleep(500);
            if (driver.findElement(By.xpath("//*[@id=\"message_title\"]")).getText().equalsIgnoreCase("Cập nhật không thành công!\n")) {
                response.setActualResult("Cập nhật không thành công!\n").setStatus(false);
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
    public void testDeletehadID() {

        Response response = new Response()
                .setName("Test Depart")
                .setTestType("FUNC")
                .setPriority("High")
                .setId("Depart_Delete_Test")
                .setAction("Truy cập vào trang quản lý phòng ban, nhấn nút xoá phòng ban, đang được sử dụng")
                .setExpectedResult("Xoá không thành công")
                .setActualResult("Xoá không thành công")
                .setStatus(true)
                .setTester("Long")
                .setDate("07/08/2021");
        try {
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr[1]/td[3]/a")).click();
            Thread.sleep(50);
            if (driver.findElement(By.xpath("//*[@id=\"message_title\"]")).getText().equalsIgnoreCase("Xóa thành công!")) {
                response.setActualResult("Xóa  thành công").setStatus(false);
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

        Response response = new Response()
                .setName("Test Depart")
                .setTestType("FUNC")
                .setPriority("High")
                .setId("Depart_Delete_Test")
                .setAction("Truy cập vào trang quản lý phòng ban, nhấn nút xoá phòng ban")
                .setExpectedResult("Xoá thành công")
                .setActualResult("Xoá thành công")
                .setStatus(true)
                .setTester("Long")
                .setDate("07/08/2021");
        try {
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr[6]/td[3]/a")).click();
            Thread.sleep(50);
            if (driver.findElement(By.xpath("//*[@id=\"message_title\"]")).getText().equalsIgnoreCase("ID này đang được sử dụng\n")) {
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

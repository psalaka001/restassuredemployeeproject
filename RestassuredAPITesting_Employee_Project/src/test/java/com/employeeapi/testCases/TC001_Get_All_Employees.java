package com.employeeapi.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.employeeapi.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC001_Get_All_Employees extends TestBase
{
	@BeforeClass
	void getAllEmployees() throws InterruptedException
	{
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1/";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/employees");

		Thread.sleep(5000);
	}

	@Test
	void checkResponseBody()
	{
		logger.info("*********** Checking Response Body *********************");

		String responseBody = response.getBody().asString();
		logger.info("Response Body==>"+responseBody);
		Assert.assertTrue(responseBody!=null);
	}

	@Test
	void checkStatusCode()
	{
		logger.info("*********** Checking Status Code *********************");

		int statusCode = response.getStatusCode();//Get status code
		logger.info("Status Code==>"+statusCode);//200
		Assert.assertEquals(statusCode, 200);
	}

	@Test
	void checkResponseTime()
	{
		logger.info("*********** Checking Response Time *********************");

		long responseTime = response.getTime();//Get status line
		logger.info("Response Time is ==>"+ responseTime);
		if(responseTime>8000)
			logger.warn("Response Time is greater than 8000");
		Assert.assertTrue(responseTime<8000);	
	}

	@Test
	void checkStatusLine()
	{
		logger.info("*********** Checking Status Line *********************");

		String statusLine = response.getStatusLine();//Getting status line
		logger.info("Status Line is ==>"+statusLine);//HTTP/1.1 200 OK
		Assert.assertEquals(statusLine,"HTTP/1.1 200 OK");
	}

	@Test
	void checkContentType()
	{
		logger.info("*********** Checking Content-Type  *********************");

		String contentType = response.header("Content-Type");
		logger.info("Content Type is==>"+contentType);
		Assert.assertEquals(contentType, "application/json;charset=utf-8");
		
	}

	@Test
	void checkServerType()
	{
		logger.info("*********** Checking Server *********************");

		String serverType = response.header("Server");
		logger.info("Server Type is ==>"+ serverType);
		Assert.assertEquals(serverType,"nginx/1.16.0");
	}

	@Test
	void checkContentEncoding()
	{
		logger.info("*********** Checking Content-Encoding *********************");

		String contentEncoding = response.header("Content-Encoding");
		logger.info("Content Encoding is ==>"+ contentEncoding);
		Assert.assertEquals(contentEncoding, "gzip");
	}

	@Test
	void checkContentLength()
	{
		logger.info("*********** Checking Content-length *********************");

		String contentLength = response.header("Content-Length");
		logger.info("Content Length is ==>"+contentLength);

		if(Integer.parseInt(contentLength)<100)
			logger.warn("Content Length is less than 100");

		Assert.assertTrue(Integer.parseInt(contentLength)>100);
	}

	@Test
	void checkCookies()
	{
		logger.info("*********** Checking Cookies *********************");

		String cookie = response.getCookie("ashhdfojf");
		//Assert.assertEquals(cookie,"hquowhqery8w4");
	}
	
	@AfterClass
	void tearDown()
	{
		logger.info("*********** Finished TC001_Get_All_Employees *********************");
	}
}

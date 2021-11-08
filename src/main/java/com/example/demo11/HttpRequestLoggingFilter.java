package com.example.demo11;

import org.apache.commons.io.*;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class HttpRequestLoggingFilter extends AbstractRequestLoggingFilter	{

	public static String getBody(HttpServletRequest request) throws IOException {
		String test = IOUtils.toString(request.getInputStream(), StandardCharsets.UTF_8);
		return test;
	}
	
	//Testing output
	@Override
	protected void beforeRequest(HttpServletRequest request, String message) {
		System.out.println("Request URI : " + request.getRequestURI());
		System.out.println("Method : " + request.getMethod());
		System.out.println("Message : " + message);


	}

	@Override
	protected void afterRequest(HttpServletRequest request, String message) {
		
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String realTime = now.format(formatter);
		String uri = request.getRequestURI().toString();
		String method = request.getMethod();
//		String requestMessage = message;

		try
		{
			Class.forName("org.h2.Driver");
			String url = "jdbc:h2:mem:test";
			String username = "sa";
			String password = "password";

			Connection conn = DriverManager.getConnection(url, username, password);
			String query = "INSERT INTO LOGS (URI,METHOD1,BODY,EXECUTEDATE) VALUES (?,?,?,?);";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, uri);
			ps.setString(2, method);
//			ps.setString(3, IOUtils.toString(request.getInputStream(), StandardCharsets.UTF_8));
			ps.setString(3, getBody(request)); //This gives me IOException
			ps.setString(4, realTime);
//			ps.setString(3, requestBody);

			int x = ps.executeUpdate();

			if (x==1) {
//				System.out.println(body);
				System.out.println("Inserted!");
			}

		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}
		
}

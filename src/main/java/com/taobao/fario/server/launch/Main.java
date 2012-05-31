package com.taobao.fario.server.launch;

import java.io.File;
import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

import com.taobao.fario.server.servlet.HelloServlet;
import com.taobao.fario.server.servlet.RegisterServlet;


public class Main {

	public static void main(String[] args) throws Exception {

		String webappDirLocation = "src/main/webapp/";
		Tomcat tomcat = new Tomcat();

		// The port that we should run on can be set into an environment
		// variable
		// Look for that variable and default to 8080 if it isn't there.
		String webPort = System.getenv("PORT");
		if (webPort == null || webPort.isEmpty()) {
			webPort = "8080";
		}

		tomcat.setPort(Integer.valueOf(webPort));

		Context ctx = tomcat.addWebapp("/",
				new File(webappDirLocation).getAbsolutePath());
		tomcat.addServlet("/", "hello", HelloServlet.class.getName());
		ctx.addServletMapping("/hello", "hello");
		tomcat.addServlet("/", "register", RegisterServlet.class.getName());
		ctx.addServletMapping("/register", "register");

		System.out.println("configuring app with basedir: "
				+ new File("./" + webappDirLocation).getAbsolutePath());

		tomcat.start();
		tomcat.getServer().await();
	}
}

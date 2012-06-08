package launch;

import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

import com.taobao.fario.server.servlet.AddShopServlet;
import com.taobao.fario.server.servlet.HelloServlet;
import com.taobao.fario.server.servlet.RegisterServlet;
import com.taobao.fario.server.servlet.RegisterUserServlet;
import com.taobao.fario.server.servlet.ShopListServlet;


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
		
		tomcat.addServlet("/", "registeruser", RegisterUserServlet.class.getName());
		ctx.addServletMapping("/registeruser", "registeruser");
		
		tomcat.addServlet("/", "addshop", AddShopServlet.class.getName());
		ctx.addServletMapping("/addshop", "addshop");
		
		tomcat.addServlet("/", "shoplist", ShopListServlet.class.getName());
		ctx.addServletMapping("/shoplist", "shoplist");
		

		System.out.println("configuring app with basedir: "
				+ new File("./" + webappDirLocation).getAbsolutePath());

		tomcat.start();
		tomcat.getServer().await();
	}
}

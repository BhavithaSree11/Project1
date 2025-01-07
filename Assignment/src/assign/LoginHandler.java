package assign;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.*;


public class LoginHandler implements HttpHandler {
	public void handle(HttpExchange he) throws IOException{
		UserDAO user = new UserDAO();
		 if ("POST".equalsIgnoreCase(he.getRequestMethod())) {
	            
	            BufferedReader reader = new BufferedReader(new InputStreamReader(he.getRequestBody()));
	            String[] formData = reader.readLine().split("&");
	            String un = formData[0].split("=")[1];
	            String ps = formData[1].split("=")[1];

	           
	            if (user.userExists(un, ps)) {
	                
	                he.getResponseHeaders().set("Location", "/adminpro");
	                System.out.println("Logged in successfully");
	                he.sendResponseHeaders(302, -1); 
	            } else {
	               
	                String response = "<html><body><h1>UserName or Passowrd is Wrong</h1>" +
	                        "<a href=\"/login\">Try Again</a></body></html>";
	                he.sendResponseHeaders(200, response.length());
	                OutputStream os = he.getResponseBody();
	                os.write(response.getBytes());
	                os.close();
	            }
	        } else {
	            
	            String response = """ 
	            		<html>
                    <head>
                        <style>
                            body {
                                font-family: Arial, sans-serif;
                                display: flex;
                                justify-content: center;
                                align-items: center;
                                height: 100vh;
                                margin: 0;
                                background-color: #f0f0f0;
                            }
                            .login-container {
                                text-align: center;
                                background: #ffffff;
                                padding: 30px;
                                border-radius: 8px;
                                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
                                width: 300px;
                            }
                           
                            .login-container h1 {
                                font-size: 24px;
                                margin-bottom: 20px;
                                color: #333333;
                            }
                            .login-container label {
                                font-size: 16px;
                                color: #555555;
                                display: block;
                                margin-bottom: 10px;
                            }
                            .login-container input[type="text"], .login-container input[type="password"] {
                                width: 100%;
                                padding: 10px;
                                margin-bottom: 20px;
                                border: 1px solid #cccccc;
                                border-radius: 4px;
                            }
                            .login-container input[type="submit"] {
                                width: 100%;
                                padding: 10px;
                                background-color: #007bff;
                                color: #ffffff;
                                border: none;
                                border-radius: 4px;
                                font-size: 16px;
                                cursor: pointer;
                               
                            }
                            .login-container input[type="submit"]:hover {
                                background-color: #0056b3;
                            }
                        </style>
                    </head>
                    <body>
                        <div class="login-container">
                            
                            <h1>Login</h1>
                            <form method="POST">
                                <label for="username">Username:</label>
                                <input type="text" name="username" id="username" required>
                                <label for="password">Password:</label>
                                <input type="password" name="password" id="password" required>
                                <input type="submit" value="Login">
                            </form>
                        </div>
                    </body>
                    </html>
            """;

	            he.sendResponseHeaders(200, response.length());
	            OutputStream os = he.getResponseBody();
	            os.write(response.getBytes());
	            os.close();
	        }
	}

}

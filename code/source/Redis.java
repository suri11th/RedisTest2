

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import com.wm.data.IData;
// --- <<IS-END-IMPORTS>> ---

public final class Redis

{
	// ---( internal utility methods )---

	final static Redis _instance = new Redis();

	static Redis _newInstance() { return new Redis(); }

	static Redis _cast(Object o) { return (Redis)o; }

	// ---( server methods )---




	public static final void RedisTest (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(RedisTest)>> ---
		// @sigtype java 3.5
 String host = "localhost";  // Redis server address
    int port = 6379;            // Redis server port
    String command = String.format("redis-cli -h %s -p %d KEYS *", host, port);

    try {
        // Execute the Redis command to fetch all keys
        ProcessBuilder processBuilder = new ProcessBuilder("bash", "-c", command);
        Process process = processBuilder.start();

        // Read the output (keys list)
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        StringBuilder output = new StringBuilder();
        
        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n");
        }
        
        // Wait for the process to finish
        process.waitFor();
        
        // Print the output
        if (output.length() > 0) {
            System.out.println("All Redis Keys:\n" + output.toString().trim());
        } else {
            System.out.println("No keys found.");
        }
    } catch (IOException | InterruptedException e) {
        e.printStackTrace();
    }
		// --- <<IS-END>> ---

                
	}



	public static final void RedisTestwithURL (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(RedisTestwithURL)>> ---
		// @sigtype java 3.5
		String host = "int-preprod-01-wmio-001.8wfxp9.0001.usw2.cache.amazonaws.com";  // RedisTest_SVC server address
		int port = 6379;            // RedisTest_SVC server port
		String command = String.format("redis-cli -h %s -p %d KEYS *", host, port);
		
		try {
		    // Execute the RedisTest_SVC command to fetch all keys
		    ProcessBuilder processBuilder = new ProcessBuilder("bash", "-c", command);
		    Process process = processBuilder.start();
		
		    // Read the output (keys list)
		    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		    String line;
		    StringBuilder output = new StringBuilder();
		    
		    while ((line = reader.readLine()) != null) {
		        output.append(line).append("\n");
		    }
		    
		    // Wait for the process to finish
		    process.waitFor();
		    
		    // Print the output
		    if (output.length() > 0) {
		        System.out.println("All RedisTest_SVC Keys:\n" + output.toString().trim());
		    } else {
		        System.out.println("No keys found.");
		    }
		} catch (IOException | InterruptedException e) {
		    e.printStackTrace();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void installRedis (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(installRedis)>> ---
		// @sigtype java 3.5
		String host = "int-preprod-01-wmio-001.8wfxp9.0001.usw2.cache.amazonaws.com";  // Redis server address
		int port = 6379;            // Redis server port
		String[] checkRedisCliCommand = {"redis-cli", "--version"};
		
		try {
		    // Check if redis-cli is installed
		    ProcessBuilder checkProcessBuilder = new ProcessBuilder(checkRedisCliCommand);
		    Process checkProcess = checkProcessBuilder.start();
		    int checkExitCode = checkProcess.waitFor();
		
		    // If redis-cli is not found (non-zero exit code), install it
		    if (checkExitCode != 0) {
		        System.out.println("redis-cli not found. Installing redis-tools...");
		
		        // Install redis-cli (Linux example)
		        String[] installCommand = {"sudo", "apt-get", "install", "-y", "redis-tools"};
		        ProcessBuilder installProcessBuilder = new ProcessBuilder(installCommand);
		        Process installProcess = installProcessBuilder.start();
		        int installExitCode = installProcess.waitFor();
		
		        if (installExitCode == 0) {
		            System.out.println("redis-cli installed successfully.");
		        } else {
		            System.err.println("Failed to install redis-cli. Exit code: " + installExitCode);
		            return; // Exit if installation failed
		        }
		    } else {
		        System.out.println("redis-cli is already installed.");
		    }
		
		    // Execute the Redis command to fetch all keys
		    String[] command = {"redis-cli", "-h", host, "-p", String.valueOf(port), "KEYS", "*"};
		    ProcessBuilder processBuilder = new ProcessBuilder(command);
		    Process process = processBuilder.start();
		
		    // Read the output (keys list)
		    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		    String line;
		    StringBuilder output = new StringBuilder();
		
		    while ((line = reader.readLine()) != null) {
		        output.append(line).append("\n");
		    }
		
		    // Wait for the process to finish
		    int exitCode = process.waitFor();
		    if (exitCode == 0) {
		        if (output.length() > 0) {
		            System.out.println("All Redis Keys:\n" + output.toString().trim());
		        } else {
		            System.out.println("No keys found.");
		        }
		    } else {
		        System.err.println("Failed to execute Redis command. Exit code: " + exitCode);
		
		        // Capture error stream if any
		        BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
		        StringBuilder errorOutput = new StringBuilder();
		        while ((line = errorReader.readLine()) != null) {
		            errorOutput.append(line).append("\n");
		        }
		        if (errorOutput.length() > 0) {
		            System.err.println("Error output:\n" + errorOutput.toString().trim());
		        }
		    }
		} catch (IOException | InterruptedException e) {
		    e.printStackTrace();
		}
		// --- <<IS-END>> ---

                
	}
}


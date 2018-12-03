package com.quantum.listeners;

import static com.qmetry.qaf.automation.core.ConfigurationManager.getBundle;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

/**
 * Created by shaneh on 11/27/2018.
 */
public class PerfectoConnectListener implements ISuiteListener {
	public static final String PERFECTO_FILE_PATH= "perfectoConnect";
	public static final String PERFECTO_HOST= "remote.server";
	public static final String PERFECTO_TOKEN= ".capabilities.securityToken";
	public static final String PERFECTO_TUNNEL_ID = "driver.capabilities.tunnelId";
	@BeforeSuite
	@Override
	public void onStart(ISuite suite) {
		File tunnelID = new File("tunnelID.prop");
		
		String filePath = getBundle().getString(PERFECTO_FILE_PATH);
		String host = getBundle().getString(PERFECTO_HOST).split("/")[2];
		String token = getToken();
		String[] commands = {filePath,"start","--cloudurl="+host,"--securitytoken="+token};
		try {
			runCommand(commands,tunnelID);
			getBundle().setProperty(PERFECTO_TUNNEL_ID, Files.readAllLines(tunnelID.toPath()).get(0));
			System.out.println("Started PerfectoConnect");
		} catch (IOException e) {
			String txt = "";
			if(token == null || token.isEmpty())
			{
				txt = "Security Token must be provided for Perfecto Tunnel." + System.lineSeparator();
			}
			throw new SkipException(txt + e.getStackTrace());
		}
	}
	@AfterSuite
	@Override
	public void onFinish(ISuite suite) {
		String filePath =  getBundle().getString(PERFECTO_FILE_PATH);
		String[] commands = {filePath,"stop"};
		try {
			runCommand(commands);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Ended PerfectoConnect");
	}
	private void runCommand(String[] commands) throws IOException {
		runCommand(commands,null);
	}
	private void runCommand(String[] commands,File tunnelID) throws IOException {
		ProcessBuilder builder = new ProcessBuilder(commands);
		if(tunnelID != null)
		{
			builder.redirectOutput(tunnelID);
		}
		Process proc = builder.start();
		BufferedReader stdInput = new BufferedReader(new 
		     InputStreamReader(proc.getInputStream()));

		BufferedReader stdError = new BufferedReader(new 
		     InputStreamReader(proc.getErrorStream()));

		// read the output from the command
		String s = null;
		while ((s = stdInput.readLine()) != null) {
		    System.out.println(s);
		}

		// read any errors from the attempted command
		while ((s = stdError.readLine()) != null) {
		    System.out.println(s);
		}
	}
	private String getToken() {
		String token = "";
		boolean loop = true;
		@SuppressWarnings("unchecked")
		java.util.Iterator<String> keys = getBundle().getKeys();
		while(keys.hasNext() && loop)
		{
			String key = keys.next();
			if(key.contains(PERFECTO_TOKEN))
			{
				token = getBundle().getString(key);
				loop = false;
			}
		}
		return token;
	}
}

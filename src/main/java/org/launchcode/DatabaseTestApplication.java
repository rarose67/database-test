package org.launchcode;

import org.launchcode.models.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

@SpringBootApplication
public class DatabaseTestApplication implements CommandLineRunner {

	@Autowired
	private Test test;

	public static void main(String[] args) {
		SpringApplication.run(DatabaseTestApplication.class, args);
	}

	/**
	 * Callback used to run the bean.
	 *
	 * @param args incoming main method arguments
	 * @throws Exception on error
	 */
	@Override
	public void run(String... args) throws Exception {

		test.printSymbols();

		Timer timer = new Timer();
		TimerTask timerTask = new TimerTask()
		{
			@Override
			public void run()
			{
				Date date = new Date();
				System.out.println("\n" + date.toString());
			}
		};

		Date date1 = new Date();
		int day = date1.getDate();
		int month = date1.getMonth();
		int year = date1.getYear();
		Date date2 = new Date(year, month, day, 22,4,0);

		timer.scheduleAtFixedRate(timerTask,date2,60000);
	}
}

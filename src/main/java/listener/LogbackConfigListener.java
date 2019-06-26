package listener;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;

/**
 * The type Logback config listener.
 *
 * @version 1.0
 */
@Component
public class LogbackConfigListener implements ServletContextListener {

	/** The constant LOGBACK_CONFIG_LOCATION. */
	public static final String LOGBACK_CONFIG_LOCATION = "logbackConfigLocation";

	@Override
	public void contextInitialized(final ServletContextEvent servletContextEvent) {
		final ServletContext sc = servletContextEvent.getServletContext();
		final ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(sc);
		final String logbackConfigLocation = sc.getInitParameter(LOGBACK_CONFIG_LOCATION);
		String filePath = null;
		try {
			final Path configFile = Paths.get(ac.getResource(logbackConfigLocation).getURI());
			filePath = configFile.toAbsolutePath().toString();

			final File logFolder = new File(ac.getResource("").getFile().getParentFile().getPath()
					+ "/logs");
			if (!logFolder.exists()) {
				if (!logFolder.mkdirs()) {
					throw new IOException("Cannot create: " + logFolder.getAbsolutePath());
				}
			}
			System.setProperty("log.basepath", logFolder.getAbsolutePath());
			final LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
			final JoranConfigurator configurator = new JoranConfigurator();
			configurator.setContext(context);
			context.reset();
			configurator.doConfigure(configFile.toFile());
		} catch (JoranException | IOException e) {
			System.err.println("Failed to locate logback config file: " + filePath);
			return;
		}
		System.out.println("[INFO] Init logback by the config file:" + filePath);
	}

	@Override
	public void contextDestroyed(final ServletContextEvent servletContextEvent) {
		// Do nothing.
	}

}

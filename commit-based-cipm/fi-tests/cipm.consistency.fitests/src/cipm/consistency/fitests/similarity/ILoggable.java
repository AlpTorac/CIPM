package cipm.consistency.fitests.similarity;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

/**
 * TODO Write commentary
 * 
 * @author Alp Torac Genc
 *
 */
public interface ILoggable {
	private static Logger getLoggerFor(String loggerName) {
		return Logger.getLogger(loggerName);
	}

	public static void setUpLogger() {
		/*
		 * Order of precedence in logging levels:
		 * 
		 * OFF > FATAL > ERROR > WARN > INFO > DEBUG > TRACE > ALL
		 */

		Logger logger = getLoggerFor("cipm");
		logger.setLevel(Level.DEBUG);

		// Enable to receive log messages from similarity switches
		// logger = Logger.getLogger("javaswitch");
		// logger.setLevel(Level.ALL);

		// logger = Logger.getLogger("jamopp");
		// logger.setLevel(Level.ALL);

		// TODO Re-think how logging should work

		logger = Logger.getRootLogger();
		logger.setLevel(Level.OFF);
		logger.removeAllAppenders();
		ConsoleAppender ap = new ConsoleAppender(new PatternLayout("[%d{DATE}] %-5p: %c - %m%n"),
				ConsoleAppender.SYSTEM_OUT);
		logger.addAppender(ap);
	}

	public default void logDebugMsg(String msg) {
		var logger = getLoggerFor("cipm." + this.getClass().getSimpleName());
		logger.debug(msg);
	}

	public default void logInfoMsg(String msg) {
		var logger = getLoggerFor("cipm." + this.getClass().getSimpleName());
		logger.info(msg);
	}

	public default void logErrorMsg(String msg) {
		var logger = getLoggerFor("cipm." + this.getClass().getSimpleName());
		logger.error(msg);
	}

	public default void logMsg(String msg, int priority) {
		var logger = getLoggerFor("cipm." + this.getClass().getSimpleName());
		logger.log(Level.toLevel(priority), msg);
	}
}

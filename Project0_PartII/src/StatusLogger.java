import org.apache.log4j.Logger;

public class StatusLogger
{
    final static Logger logger = Logger.getRootLogger();

    public void logMessage(String message)
    {
        logger.info(message);
    }

    public void logIssue(String message)
    {
        logger.error(message);
    }
}

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Log
{
  private BufferedWriter out;
  private static Map<String, Log> allInstances=new HashMap<>();

  private Log(String filename) throws IOException
  {
    File logFile = new File(filename);
    this.out = new BufferedWriter(new FileWriter(logFile, true));
  }
  public static Log getLog(String filename) throws IOException
  {
    Log log=allInstances.get(filename);
    if(log==null)
    {
      synchronized (allInstances)
      {
        log=allInstances.get(filename);
        if(log==null)
        {
          log=new Log(filename);
          allInstances.put(filename, log);
        }
      }
    }
    return log;
  }
  public void addLog(String log)
  {
    LogLine logLine = new LogLine(log);
    addToFile(logLine);
    System.out.println(logLine);
  }

  private synchronized void addToFile(LogLine log)
  {
    if (log == null)
    {
      return;
    }
    BufferedWriter out = null;
    try
    {
      File logFile = new File("Log.txt");
      out = new BufferedWriter(new FileWriter(logFile, true));
      out.write(log + "\n");
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally
    {
      try
      {
        out.close();
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
  }
}

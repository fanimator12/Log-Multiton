import java.io.IOException;

public class Test
{
  public static void main(String[] args) throws IOException
  {
    DateTime time=new DateTime();
    Log log=Log.getLog(time.getSortableDate());
    Log log1=Log.getLog("Lock.txt");
    log.addLog("push the button");
    log1.addLog("don't push the button");
  }
}

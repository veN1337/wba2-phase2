package djproject.gui.client;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class DateStringComparator implements Comparator<String>
{
    public int compare(String strDate1, String strDate2) 
    {
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        
        try 
        {
            if(strDate1 == null || strDate2 == null)
                return 0;
            
            if(strDate1.length() <= 11)
                dateFormat1 = new SimpleDateFormat("dd.MM.yyyy");
            
            if(strDate2.length() <= 11)
                dateFormat2 = new SimpleDateFormat("dd.MM.yyyy");
            
            Date date1 = dateFormat1.parse(strDate1);
            Date date2 = dateFormat2.parse(strDate2);
            
            return date1.compareTo(date2);
        }
        catch (ParseException e) 
        {
            e.printStackTrace();
        }
 
        return 0;
    }
}
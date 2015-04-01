package pattern.guanchazhe;

import java.util.ArrayList;
import java.util.List;

public class WeatherData implements SubjectInter
{
    private long temperture;
    
    private long wind;
    
    private List<DisplayInter> displayInters = new ArrayList<DisplayInter>();
    
    public void register(DisplayInter instance)
    {
        if (!displayInters.contains(instance))
        {
            displayInters.add(instance);
        }
    }
    
    public void remove(DisplayInter instance)
    {
        if (displayInters.contains(instance))
        {
            displayInters.remove(instance);
        }
        
    }
    
    public void update(long temperture, long wind)
    {
        this.temperture = temperture;
        this.wind = wind;
        for (DisplayInter obj : displayInters)
        {
            obj.display();
        }
    }
    
    public long getTemperture()
    {
        return temperture;
    }
    
    public void setTemperture(long temperture)
    {
        this.temperture = temperture;
    }
    
    public long getWind()
    {
        return wind;
    }
    
    public void setWind(long wind)
    {
        this.wind = wind;
    }
    
}

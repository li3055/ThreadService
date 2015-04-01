package pattern.guanchazhe;

public class WebSystem implements DisplayInter
{
    private WeatherData weatherData;
    
    public WebSystem(WeatherData subject)
    {
        this.weatherData = subject;
        this.weatherData.register(this);
        
    }
    
    public void display()
    {
        System.out.print("" + weatherData.getTemperture());
    }
    
    public static void main(String[] args){
        WeatherData subject =new WeatherData();
        subject.setTemperture(100);
        subject.setWind(200);
        WebSystem  wb =new WebSystem(subject);
        subject.update(200, 300);
    }
    
}

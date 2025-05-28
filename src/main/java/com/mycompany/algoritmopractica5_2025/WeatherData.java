package com.mycompany.algoritmopractica5_2025;

public class WeatherData {
    private final String[] dates;
    private final String[] summaries;
    private final String[] precipTypes;
    private final double[] temperatures;
    private final double[] apparentTemps;
    private final double[] humidity;
    private final double[] windSpeeds;
    private final int[] windBearings;
    private final double[] visibility;
    private final double[] cloudCover;
    private final double[] pressure;
    private final String[] dailySummaries;

    public WeatherData(CSVColumnar csv) {
        dates          = csv.getStrings("Formatted Date");
        summaries      = csv.getStrings("Summary");
        precipTypes    = csv.getStrings("Precip Type");
        temperatures   = csv.getDoubles("Temperature (C)");
        apparentTemps  = csv.getDoubles("Apparent Temperature (C)");
        humidity       = csv.getDoubles("Humidity");
        windSpeeds     = csv.getDoubles("Wind Speed (km/h)");
        windBearings   = csv.getInts   ("Wind Bearing (degrees)");
        visibility     = csv.getDoubles("Visibility (km)");
        cloudCover     = csv.getDoubles("Loud Cover"); // ojo nombre
        pressure       = csv.getDoubles("Pressure (millibars)");
        dailySummaries = csv.getStrings("Daily Summary");
    }

    // getters
    public double[] getTemperatures()   { return temperatures;   }
    public double[] getHumidity()       { return humidity;       }
    public int[]    getWindBearings()   { return windBearings;   }
    public double[] getWindSpeeds()     { return windSpeeds;     }
    public double[] getVisibility()     { return visibility;     }
    public double[] getCloudCover()     { return cloudCover;     }
    public double[] getPressure()       { return pressure;       }
    public String[] getDates()          { return dates;          }
    public String[] getSummaries()      { return summaries;      }
    public String[] getPrecipTypes()    { return precipTypes;    }
    public String[] getDailySummaries() { return dailySummaries; }
    public double[] getApparentTemps()  { return apparentTemps;  }
    
}
package ru.pet_project.weather_app.model;

public enum WindDirectionType {
    N("Северный", 0),
    NE("Северо-Восточный", 45),
    E("Восточный", 90),
    SE("Юго-Восточный", 135),
    S("Южный", 180),
    SW("Юго-Западный", 225),
    W("Западный", 270),
    NW("Северо-Западный", 315);
    private String descriptionInRussian;
    private int degrees;


    WindDirectionType(String descriptionInRussian, int degrees) {
        this.descriptionInRussian = descriptionInRussian;
        this.degrees = degrees;
    }

    public static WindDirectionType getTypeByDegree(int degrees) {
        int topOfCircle= 360;
        degrees = degrees % topOfCircle;
        WindDirectionType[] allWindsArray = WindDirectionType.values();
        for (int i = 0; i < allWindsArray.length; i++) {
            WindDirectionType dirL;
            WindDirectionType dirR;
            int degL;
            int degR;
            if (i < allWindsArray.length - 1) {
                dirL = allWindsArray[i];
                dirR = allWindsArray[i+1];
                degL = dirL.getDegrees();
                degR = dirR.getDegrees();
            } else {
                dirL = allWindsArray[i];
                dirR = allWindsArray[0];
                degL = dirL.getDegrees();
                degR = dirR.getDegrees() + topOfCircle;
            }
            if (degrees >= degL && degrees <= degR) {
                int medium = degL + (degR - degL) / 2;
                return medium + 0.5 > degrees ? dirL
                        : dirR;
            }
        }
        return null;
    }

    public int getDegrees() {
        return degrees;
    }

    public String getDescriptionInRussian() {
        return descriptionInRussian;
    }
}

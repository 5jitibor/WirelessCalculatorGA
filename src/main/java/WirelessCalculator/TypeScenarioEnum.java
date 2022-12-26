package WirelessCalculator;

public enum TypeScenarioEnum {
    BIGCITY,
    MEDIUMSMALLCITY,
    SUBURBANCITY,
    RURAL;

    public String toString(){
        return switch (this) {
            case BIGCITY -> "Big City";
            case MEDIUMSMALLCITY -> "Medium-Small City";
            case SUBURBANCITY -> "Suburban City";
            case RURAL -> "Rural";
        };

    }


}

package WirelessCalculator;

public enum TypeCalculationEnum {
    LOSSPROPAGATION,
    COVERAGE;

    public String toString(){
        return switch (this) {
            case LOSSPROPAGATION -> "Propagation Loss";
            case COVERAGE -> "Coverage";
        };
    }


    public String toMetric(){
        return switch (this) {
            case LOSSPROPAGATION -> "dB";
            case COVERAGE -> "Km";
        };
    }
}

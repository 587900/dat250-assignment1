package no.hvl.dat250.h587900.assignment1;

public class Converter {

    private static final double IN_TO_METER = 0.0254;
    private static final double FT_TO_METER = 0.3048;
    private static final double MI_TO_METER = 1609.344;

    public enum Unit {
        INCHES, FEET, MILES, METERS;
        public static Unit fromString(String unit) {
            switch (unit) {
                case "in": return INCHES;
                case "ft": return FEET;
                case "mi": return MILES;
                case "m": return METERS;
                default: return null;
            }
        }
    }

    public static double toMeters(double value, Unit unit) {
        switch (unit) {
            case INCHES: return value * IN_TO_METER;
            case FEET: return value * FT_TO_METER;
            case MILES: return value * MI_TO_METER;
            case METERS: return value;
            default: return Double.NaN;
        }
    }

    public static double fromMeters(double value, Unit unit) {
        switch (unit) {
            case INCHES: return value / IN_TO_METER;
            case FEET: return value / FT_TO_METER;
            case MILES: return value / MI_TO_METER;
            case METERS: return value;
            default: return Double.NaN;
        }
    }

}
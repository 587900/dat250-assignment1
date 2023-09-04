package no.hvl.dat250.h587900.assignment1;

import io.javalin.Javalin;

public class App {

    private static final String WEBPAGE = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <title>Convert units</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "<h1>Unit converter</h1>\n" +
            "<form action=\"/convert\" method=\"post\">\n" +
            "    <fieldset>\n" +
            "    <label for=\"val\">Value:</label>" +
            "    <input id=\"val\" type=\"text\" name=\"value\"><br />\n" +
            "    <label for=\"source-unit\">From unit:</label>\n" +
            "    <select name=\"sunit\" id=\"source-unit\">\n" +
            "        <option value=\"in\">Inches</option>\n" +
            "        <option value=\"ft\">Feet</option>\n" +
            "        <option value=\"mi\">Miles</option>\n" +
            "        <option value=\"m\">Metres</option>\n" +
            "    </select><br />\n" +
            "    <label for=\"target-unit\">To unit:</label>\n" +
            "    <select name=\"tunit\" id=\"target-unit\">\n" +
            "        <option value=\"in\">Inches</option>\n" +
            "        <option value=\"ft\">Feet</option>\n" +
            "        <option value=\"mi\">Miles</option>\n" +
            "        <option value=\"m\">Metres</option>\n" +
            "    </select><br />\n" +
            "    <input type=\"submit\" value=\"Calculate\" />\n" +
            "    </fieldset>\n" +
            "</form>\n" +
            "</body>\n" +
            "</html>";


    public static void main(String[] args) {
        Javalin.create()
                .get("/", ctx -> {
                    ctx.html(WEBPAGE);
                })
                .post("/convert", ctx -> {
                    String strValue = ctx.formParam("value");
                    String fromUnit = ctx.formParam("sunit");
                    String toUnit = ctx.formParam("tunit");

                    if (strValue == null || fromUnit == null || toUnit == null) {
                        ctx.status(400);
                        return;
                    }

                    double value = Double.parseDouble(strValue);
                    Converter.Unit from = Converter.Unit.fromString(fromUnit);
                    Converter.Unit to = Converter.Unit.fromString(toUnit);

                    if (from == null || to == null) {
                        ctx.status(400);
                        return;
                    }

                    double meters = Converter.toMeters(value, from);
                    double result = Converter.fromMeters(meters, to);
                    ctx.result(Double.toString(result));
                })
                .start(9000);
    }

}
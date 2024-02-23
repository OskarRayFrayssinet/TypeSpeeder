package se.ju23.typespeeder.gameLogic;

import org.apache.commons.text.StringEscapeUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class Translator implements Translatable {
    private static final String API_KEY = "AIzaSyBN83ctqBNIknbk2BrRbw6GdfAouyvw2tY";
    private static final String API_ENDPOINT = "https://translation.googleapis.com/language/translate/v2";

    public String translate(String text, String targetLanguage) throws Exception {

        List<String> colorCodes = extractColorCodes(text);

        String textWithoutColorCodes = replaceColorCodesWithPlaceholders(text);

        String encodedText = URLEncoder.encode(textWithoutColorCodes, "UTF-8");
        String url = API_ENDPOINT + "?key=" + API_KEY + "&q=" + encodedText + "&source=en&target=" + targetLanguage;

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        String translatedText = processJSONResponse(response.toString());

        translatedText = replacePlaceholdersWithColorCodes(translatedText, colorCodes);

        return translatedText;
    }

    private List<String> extractColorCodes(String text) {
        List<String> colorCodes = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\u001B\\[[0-9;]*m");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            colorCodes.add(matcher.group());
        }
        return colorCodes;
    }

    private String replaceColorCodesWithPlaceholders(String text) {
        Pattern pattern = Pattern.compile("\\u001B\\[[0-9;]*m");
        Matcher matcher = pattern.matcher(text);
        StringBuffer sb = new StringBuffer();
        int i = 0;
        while (matcher.find()) {
            matcher.appendReplacement(sb, "{colorcode" + i++ + "}");
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    private String processJSONResponse(String jsonResponse) throws JSONException {
        JSONObject json = new JSONObject(jsonResponse);
        JSONObject data = json.getJSONObject("data");
        JSONArray translations = data.getJSONArray("translations");
        JSONObject translationObject = translations.getJSONObject(0);
        String translatedText = translationObject.getString("translatedText");
        translatedText = StringEscapeUtils.unescapeHtml4(translatedText);
        return translatedText;
    }

    private String replacePlaceholdersWithColorCodes(String translatedText, List<String> colorCodes) {

        for (int i = 0; i < colorCodes.size(); i++) {
            translatedText = translatedText.replace("{colorcode" + i + "}", colorCodes.get(i));
        }
        return translatedText;
    }
}

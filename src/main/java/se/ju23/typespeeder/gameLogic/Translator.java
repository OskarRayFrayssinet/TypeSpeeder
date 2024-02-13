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

@Component public class Translator implements Translatable {
    private static final String API_KEY = "AIzaSyBN83ctqBNIknbk2BrRbw6GdfAouyvw2tY";
    private static final String API_ENDPOINT = "https://translation.googleapis.com/language/translate/v2";

    public String translate(String text, String targetLanguage) throws Exception {
        String encodedText = URLEncoder.encode(text, "UTF-8");
        String url = API_ENDPOINT + "?key=" + API_KEY + "&q=" + encodedText + "&target=" + targetLanguage;

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

        // Process the JSON response to extract translated text
        String translatedText = processJSONResponse(response.toString());

        return translatedText;
    }

    private String processJSONResponse(String jsonResponse) throws JSONException {
        JSONObject json = new JSONObject(jsonResponse);
        JSONObject data = json.getJSONObject("data");
        JSONArray translations = data.getJSONArray("translations");
        JSONObject translationObject = translations.getJSONObject(0); // Assuming there is only one translation
        String translatedText = translationObject.getString("translatedText");
        translatedText = StringEscapeUtils.unescapeHtml4(translatedText);
        return translatedText;
    }
}
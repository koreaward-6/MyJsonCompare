package kr.co.wincom.mjc.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.net.URL;
import org.apache.commons.lang3.StringUtils;

public class CommonUtils {

    // URL 형식 체크
    public static boolean isValidURL(String url) {
        try {
            new URL(url).toURI();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // leftUrl에 맞춰서 rightUrl을 생성함
    public static String makeUrl(String strLeftUrl, String strRightUrl) {
        URL leftUrl = null;
        URL rightUrl = null;

        try {
            leftUrl = new URL(strLeftUrl);
            rightUrl = new URL(strRightUrl);
        } catch (Exception ex) {
            new RuntimeException("파라미터 오류!!!");
        }

        // http://ip:port 형식으로 입력시
        if (StringUtils.isBlank(rightUrl.getFile())) {
            int port = rightUrl.getPort();
            String strPort = port == -1 ? "" : ":" + port;

            return (rightUrl.getProtocol() + "://" + rightUrl.getHost() + strPort + leftUrl.getFile());
        } else {
            return rightUrl.toString();
        }
    }

    // JSON 데이터를 보기 좋게 만듦
    public static String makeJsonPrettyData(String jsonData) throws Exception {
        JsonElement jsonElement = JsonParser.parseString(jsonData);

        Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

        return gson.toJson(jsonElement);
    }

    // JSON 데이터일 때 ':' 기준으로 오른쪽 데이터는 녹색으로 치환
    public static String replaceColor(String data) {
        int idx1 = data.indexOf(":");

        if (idx1 > -1) {
            String firstData = data.substring(0, idx1 + 1).replace(" ", "&nbsp;").replace("<", "&lt;").replace(">", "&gt;");
            String lastData = data.substring(idx1 + 1).replace(" ", "&nbsp;").replace("<", "&lt;").replace(">", "&gt;");
            String replaceLastData = "<font style='color:#298A08;'>" + lastData + "</font>";

            return (firstData + replaceLastData);
        } else {
            return data.replace(" ", "&nbsp;").replace("<", "&lt;").replace(">", "&gt;");
        }
    }

    // JSON 형식인지 검증
    public static boolean isJsonValid(String json) {
        int idx1 = json.indexOf("{");
        int idx2 = json.indexOf("}");
        int idx3 = json.indexOf(":");

        if (idx1 == -1 || idx2 == -1 || idx3 == -1) {
            return false;
        }

        try {
            JsonParser.parseString(json);
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}

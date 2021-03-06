package util;

import model.Message;
import model.Request;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import javax.servlet.http.HttpServletRequest;

import java.io.*;
import java.util.List;

public final class MessageExchange {

    private static JSONParser jsonParser = new JSONParser();

    public static JSONArray ListToJSONArray(List<Request> messages)
    {
        JSONArray array = new JSONArray();
        for(int i = 0; i < messages.size(); i++) {
            JSONObject jo = new JSONObject();
            jo.put("id", messages.get(i).getMessage().getId());
            jo.put("user", messages.get(i).getMessage().getUser());
            jo.put("text", messages.get(i).getMessage().getText());
            jo.put("actionToDo", messages.get(i).getActionToDo());
            array.add(jo);
        }
        return array;
    }

    public static Message getClientMessage(JSONObject o) throws ParseException {
        Message m = new Message();
        if (o.containsKey("id"))
            m.setId(Integer.parseInt(o.get("id").toString()));
        if (o.containsKey("user"))
            m.setUser((String) o.get("user"));
        if (o.containsKey("text"))
            m.setText((String) o.get("text"));
        return m;
    }

    public static JSONObject getJSONObject(String json) throws ParseException {
        return (JSONObject) jsonParser.parse(json.trim());
    }

    public static String getMessageBody(HttpServletRequest request) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }
}


import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Iterator;
import JSON.JSONObject;
import JSON.JSONArray;

class SnakeCaseToCamelCase{
    public static void main(String[] args) throws Exception {

		//This code is parsing on keys and then detecting snakecase pattern key if found Convert snakecase to camel case as per the _ detected by the matcher.
        String contents ="[{\"loan\": \"\", \"l_o_an_id\": "", \"product\": \"\", \"loan_type\": \"null\", \"parent_id\": null, \"child_note\": [], \"visibility\": \"true\", \"note_added_by\": \"\", \"notecomment\": \"Notes are\", \"notesubject\": \"\", \"isportalnote\": true, \"workitemname\": \"\", \"datenoteadded\": \"2024-01-11 21:15:10\", \"insertionorderid\": 40}]";
        JSONArray jsonArr = new JSONArray(contents.trim());
        JSONArray jsonNewArr = new JSONArray();
        for(int i = 0; i < jsonArr.length(); i++)
        {		JSONObject jsonNewObject = new JSONObject();
              JSONObject jsonObject = jsonArr.getJSONObject(i);
              Iterator<String> keys = jsonObject.keys();
              while(keys.hasNext()) {
	              String key = keys.next();
	              
	              if (jsonObject.get(key) instanceof String ) {
	            	  
	            	  Pattern pattern = Pattern.compile("_[a-z]");
	                  Matcher matcher = pattern.matcher(key);

	                  StringBuffer keyReplace = new StringBuffer();
	                  while (matcher.find()) {
	                      matcher.appendReplacement(keyReplace, String.valueOf(Character.toUpperCase(matcher.group().charAt(1))));
	                  }
	                  matcher.appendTail(keyReplace);

	            	  
	            	  //String keyReplace = key.replaceAll("_[a-z]",match -> String.valueOf(Character.toUpperCase(match.group().charAt(1))));
	                  
	                  
	            	  jsonNewObject.put(keyReplace.toString(), jsonObject.get(key));
	            	  
	             }
              }
        
              jsonNewArr.put(jsonNewObject);
        }

        System.out.println(jsonNewArr);
    }
}
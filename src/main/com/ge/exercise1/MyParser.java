package com.ge.exercise1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.json.JSONArray;
import org.json.JSONObject;

public class MyParser implements Parser {

	@Override
	public Result parseApplicationData(String content) {
		List<ExtendedUser> userList = new ArrayList();
		List<ExtendedGroup> groupList = new ArrayList();
		List<ExtendedUser> groupUsersList = new ArrayList();
		String input = refactorString(content);
		String result = modifyJsonKeys(input);
		String swords[] = result.split(":");
		List<String> list3 = convertToJson(swords);
		String fresult =list3.stream().collect(Collectors.joining(":"));
		String finalResult = fresult.substring(0,fresult.length()-1);
		JSONObject json = new JSONObject(finalResult);
		String appId = (String) json.get("id"); 
		String appName = (String) json.get("name"); 
		JSONArray groups = (JSONArray) json.get("groups"); 
		JSONArray users = (JSONArray) json.get("users");
		for (int i = 0; i < users.length(); i++) {
			ExtendedUser user = new ExtendedUser( users.getJSONObject(i).getString("id"), users.getJSONObject(i).getString("name"));
			userList.add(user);
		}
		for (int i = 0; i < groups.length(); i++) {
			ExtendedGroup group = new ExtendedGroup( groups.getJSONObject(i).getString("id"), groups.getJSONObject(i).getString("name"));
			JSONArray groupUsersArray = groups.getJSONObject(i).getJSONArray("users");
			for(int j=0;j<groupUsersArray.length();j++){
				ExtendedUser user = new ExtendedUser( groupUsersArray.getJSONObject(i).getString("id"), groupUsersArray.getJSONObject(i).getString("name"));
				groupUsersList.add(user);
			}
			group.setUsers(groupUsersList);
			groupList.add(group);
		} 
		
		Result response = new Result();
		response.setId(appId);
		response.setName(appName);
		response.setGroupList(groupList);
		response.setUserList(userList);
		return response;
	}

	private List<String> convertToJson(String[] swords) {
		List<String> list2 = Arrays.asList(swords); 
		List<String> list3 = new ArrayList(); 

		list2.stream()
		.forEach(s -> {
			if(s.contains(",")){
				int index=s.indexOf(',');
				//Adding double quote to end of the string
				if(s.charAt(index-1)==']'){
					s = addChar(s,'"',index+1);
					if(s.charAt(index-2)=='}'){
						s= addChar(s,'"',index-2);
					}

				}else{
					s = addChar(s,'"',index);
					s = addChar(s,'"',index+2);
				}
			}else
				if(checkChar(s)){
					char[] ch=  swap(s,0,1);
					s= new String(ch);
				}
			if(checkEndofString(s)){
				int index=s.indexOf('}');
				s = addChar(s,'"',index);
			}
			if(s.contains("id") && s.length()==6){
				int pos = s.indexOf("id");
				s = addChar(s,'"',pos);
				s = removeChar(s,1);
			}
			list3.add(s);
		});
		return list3;
	}

	private String modifyJsonKeys(String data) {
		String[] words = data.split(":");
		List<String> list = Arrays.asList(words); 
		return  list.stream()
				.map(s -> "\"" + s + "\"")
				.collect(Collectors.joining(":"));
	}

	//replace some strings to create a json format
	private String refactorString(String data) {
		data = data.replaceAll("\\(", ":{").replaceAll("\\)","}");
		data = data.replaceAll("User:","").replaceAll("Group:","").replace("Application:","");
		return data;
	}

	private boolean checkEndofString(String s) {
		char ch[] = s.toCharArray();
		for(int i=0;i<ch.length;i++){
			if(ch[i]=='}'){
				if(i!=0 && ch[i-1]!='"')
					return true;
			}
		}
		return false;
	}

	private boolean checkChar(String s) {
		char ch[] = s.toCharArray();
		for(int i=0;i<ch.length;i++){
			if(ch[i]=='{' ||  ch[i]==']'){
				return ((i!=0) && ch[i-1]=='[')?false:true;
			}
		}
		return false;
	}

	public static String addChar(String str, char ch, int position) {
		return str.substring(0, position) + ch + str.substring(position);
	}

	public static String removeChar(String str, int position) {
		return str.substring(position);
	}

	static char[] swap(String str, int i, int j) 
	{ 
		char ch[] = str.toCharArray(); 
		char temp = ch[i]; 
		ch[i] = ch[j]; 
		ch[j] = temp; 
		return ch; 
	} 


	public static void main(String[] args) {
		String data = "Application(id: 0,name: MyApp,users:[User(id: 2,name: Beth Jones)],groups:[Group(id: 1,name: SimpleGroup,users:[User(id: 2,name: Beth Jones)])])";
		Result result = new MyParser().parseApplicationData(data);
		System.out.println(result.getUserList().get(0).getName());
		System.out.println(result.getGroupList());
		System.out.println(result.getGroupList().get(0).getUsers());
	}

}

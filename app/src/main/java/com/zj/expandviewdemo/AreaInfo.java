package com.zj.expandviewdemo;

import android.content.Context;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;


public class AreaInfo {

	private  List<Cityinfo> province_list = new ArrayList<Cityinfo>();
	private  HashMap<String, List<Cityinfo>> city_map = new HashMap<String, List<Cityinfo>>();
	private  HashMap<String, List<Cityinfo>> couny_map = new HashMap<String, List<Cityinfo>>();
	private Context context;

	public AreaInfo(Context context){
		this.context=context;
		init();
	}


	public String queryCityNameByCode(String provinceCode,String cityCode){
		List<Cityinfo> cityList= couny_map.get(provinceCode);
		if(cityList==null) return "" ;
		for(Cityinfo city:cityList){
			if(cityCode.equals(city.getId())){
				return city.getCity_name();
			}
		}
		return "";
	}


	public String queryCounyNameByCode(String cityCode,String counyCode){
		List<Cityinfo> counyList= couny_map.get(cityCode);
		if(counyList==null) return "";
		for(Cityinfo couny:counyList){
			if(counyCode.equals(couny.getId())){
				return couny.getCity_name();
			}
		}
		return "";
	}
	
	public void init(){
		JSONParser parser = new JSONParser();
		String area_str = FileUtil.readAssets(context, "area.json");
		province_list = parser.getJSONParserResult(area_str, "area0");
		// citycodeUtil.setProvince_list_code(parser.province_list_code);
		city_map = parser.getJSONParserResultArray(area_str, "area1");
		// System.out.println("city_mapsize" +
		// parser.city_list_code.toString());
		// citycodeUtil.setCity_list_code(parser.city_list_code);
		couny_map = parser.getJSONParserResultArray(area_str, "area2");
		// citycodeUtil.setCouny_list_code(parser.city_list_code);
		// System.out.println("couny_mapsize" +
		// parser.city_list_code.toString());
	}
	// 获取城市信息
	public List<Cityinfo> getProvince() {
		// TODO Auto-generated method stub
		// 读取城市信息string
		return province_list;
	}
	
	public List<Cityinfo> getCityByCode(String provinceCode){
		return city_map.get(provinceCode);
	}
	
	
	public List<Cityinfo> getCounyByCode(String cityCode){
		return couny_map.get(cityCode);
	}
	
	public HashMap<String, List<Cityinfo>> get1(){
		return city_map;
	}
	public HashMap<String, List<Cityinfo>> get2(){
		return couny_map;
	}
	
	public static class JSONParser {
//		public ArrayList<String> province_list_code = new ArrayList<String>();
//		public ArrayList<String> city_list_code = new ArrayList<String>();

		public List<Cityinfo> getJSONParserResult(String JSONString, String key) {
			List<Cityinfo> list = new ArrayList<Cityinfo>();
			JsonObject result = new JsonParser().parse(JSONString)
					.getAsJsonObject().getAsJsonObject(key);

			@SuppressWarnings("rawtypes")
			Iterator iterator = result.entrySet().iterator();

			Cityinfo cityinfo0 = new Cityinfo();
			cityinfo0.setCity_name("全部");
			cityinfo0.setId("");
			list.add(cityinfo0);
			while (iterator.hasNext()) {
				@SuppressWarnings("unchecked")
				Entry<String, JsonElement> entry = (Entry<String, JsonElement>) iterator
						.next();
				Cityinfo cityinfo = new Cityinfo();

				cityinfo.setCity_name(entry.getValue().getAsString());
				cityinfo.setId(entry.getKey());
//				province_list_code.add(entry.getKey());
				list.add(cityinfo);
			}
//			System.out.println(province_list_code.size());
			return list;
		}

		@SuppressWarnings({ "rawtypes", "unchecked" })
		public HashMap<String, List<Cityinfo>> getJSONParserResultArray(
				String JSONString, String key) {
			HashMap<String, List<Cityinfo>> hashMap = new HashMap<String, List<Cityinfo>>();
			JsonObject result = new JsonParser().parse(JSONString)
					.getAsJsonObject().getAsJsonObject(key);

			Iterator iterator = result.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<String, JsonElement> entry = (Entry<String, JsonElement>) iterator
						.next();
				List<Cityinfo> list = new ArrayList<Cityinfo>();
				JsonArray array = entry.getValue().getAsJsonArray();

				Cityinfo cityinfo0 = new Cityinfo();
				cityinfo0.setCity_name("全部");
				cityinfo0.setId("全部");
				list.add(cityinfo0);
				
				for (int i = 0; i < array.size(); i++) {
					Cityinfo cityinfo = new Cityinfo();
					cityinfo.setCity_name(array.get(i).getAsJsonArray().get(0)
							.getAsString());
					cityinfo.setId(array.get(i).getAsJsonArray().get(1)
							.getAsString());
//					city_list_code.add(array.get(i).getAsJsonArray().get(1)
//							.getAsString());
					list.add(cityinfo);
				}
				hashMap.put(entry.getKey(), list);
			}
			return hashMap;
		}
	}
}

package com.chinasofti.postbar.util;

import java.util.HashMap;

import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;

public class AudioSynthesis {

	private static volatile AudioSynthesis audioSynthesis;
	 private AipSpeech client =null;
	 private AudioSynthesis(String APP_ID, String API_KEY, String SECRET_KEY) {
		 client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);
	 }
	 // 调用时传入重要的3个KEY
	 public static AudioSynthesis getInstance(String APP_ID, String API_KEY, String SECRET_KEY) {
       if (audioSynthesis == null) {
           synchronized (AudioSynthesis.class) {
               if (audioSynthesis == null) {
               	audioSynthesis = new AudioSynthesis(APP_ID, API_KEY, SECRET_KEY);
               }
           }
       }
       return audioSynthesis;
   }
	public TtsResponse synthesis(String text,int spd,int pit,int vol,int per){
		// 初始化一个AipSpeech
	    HashMap<String, Object> options = new HashMap<String, Object>();
	    options.put("spd", spd+"");
	    options.put("pit", pit+"");
	    options.put("vol", vol+"");
	    options.put("per", per+"");
	    // 调用接口
	    TtsResponse res = client.synthesis(text, "zh", 1, options);
	    return res;
	 }
	
}

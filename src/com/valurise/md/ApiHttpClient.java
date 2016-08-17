package com.valurise.md;

import com.valurise.md.model.*;
import com.valurise.md.util.HttpUtil;
import com.valurise.md.util.SignUtil;

import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaodong.tan
 */
public class ApiHttpClient {

    private static final String MD_SERVER_URI = "http://mydoctor.valurise.com/apis";

    public static SdkHttpResult getAllSymptom(String cid, String secretKey) throws Exception {
        String ts = String.valueOf(System.currentTimeMillis());
        String sign = SignUtil.sign(null, cid, ts, secretKey);
        String url = MD_SERVER_URI+"/getAllSymptom.json?_cid="+cid+"&_ts="+ts+"&_sign="+sign;
        HttpURLConnection conn = HttpUtil.CreateHttpConnection(url,"GET","application/x-www-form-urlencoded");
        return HttpUtil.returnResult(conn);
    }

    public static SdkHttpResult startSession(String cid, String secretKey, String userId, String symptomId, String sex,
                                             String birthday, String height, String weight) throws Exception {
        String ts = String.valueOf(System.currentTimeMillis());
        Map<String, String> params = new HashMap<String, String>();
        params.put("uid", userId);
        params.put("symptomId", symptomId);
        params.put("sex", sex);
        params.put("birth", birthday);
        params.put("height", height);
        params.put("weight", weight);
        String sign = SignUtil.sign(null, cid, ts, secretKey);
        String url = MD_SERVER_URI+"/startSession.json?_cid="+cid+"&_ts="+ts+"&_sign="+sign;
        HttpURLConnection conn = HttpUtil.CreateHttpConnection(url, "POST", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(params, conn);
        return HttpUtil.returnResult(conn);
    }

    public static SdkHttpResult fetchQuestion(String cid, String secretKey, String sid, UserInput userInput) throws Exception {
        String ts = String.valueOf(System.currentTimeMillis());
        Map<String, String> params = new HashMap<String, String>();
        params.put("sid", sid);
        String sign = SignUtil.sign(params, cid, ts, secretKey);
        String url = MD_SERVER_URI+"/fetchQuestion.json?_cid="+cid+"&_ts="+ts+"&_sign="+sign+"&sid="+sid;
        HttpURLConnection conn = HttpUtil.CreateHttpConnection(url, "POST", "application/json");
        if (userInput!=null) {
            HttpUtil.setBodyParameter(userInput.toString(), conn);
        }
        return HttpUtil.returnResult(conn);
    }

    public static SdkHttpResult rollbackQuestion(String cid, String secretKey, String sid) throws Exception {
        String ts = String.valueOf(System.currentTimeMillis());
        Map<String, String> params = new HashMap<String, String>();
        params.put("sid", sid);
        String sign = SignUtil.sign(params, cid, ts, secretKey);
        String url = MD_SERVER_URI+"/rollbackQuestion.json?_cid="+cid+"&_ts="+ts+"&_sign="+sign+"&sid="+sid;
        HttpURLConnection conn = HttpUtil.CreateHttpConnection(url, "POST", "application/x-www-form-urlencoded");
        return HttpUtil.returnResult(conn);
    }

}

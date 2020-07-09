package com.example.hanapitest;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hanapitest.utils.EncryptUtils;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.han.hanapi.HanApApi;
import com.han.hanapi.HanWanWeiApi;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Headers;


public class TestDetailActivity extends AppCompatActivity {

    private TextView requestState;
    private TextView requestHeaders;
    private TextView requestBody;
    private TextView responseData;
    private TextView responseHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_detai);
        requestState = findViewById(R.id.requestState);
        requestHeaders = findViewById(R.id.requestHeaders);
        requestBody = findViewById(R.id.requestBody);
        responseData = findViewById(R.id.responseData);
        responseHeader = findViewById(R.id.responseHeader);

        HanWanWeiApi.urlAddress="192.168.2.60";

        int type = getIntent().getIntExtra("type", 0);
        switch (type) {
            case 1:
                login();
                break;
            case 2:
                createCspUser();
                break;
            case 3:
                searchCspUser();
                break;
            case 4:
                searchOwnerSites();
                break;
            case 5:
                createCspCorp();
                break;
            case 6:
                createCspSite();
                break;
            case 7:
                createCspSiteSsid();
                break;
            case 8:
                distributeAp();
                break;
            case 9:
                cspAll();
                break;
            case 10:
                searchApStatus();
                break;
            case 11:
                apLogin();
                break;
            case 12:
                apCloudMode();
            default:
                break;
        }
    }

    private void login() {
        String username = getIntent().getStringExtra("username");
        String password = getIntent().getStringExtra("password");
        HashMap<String, String> params = new HashMap<>();
        params.put("account", username);
        params.put("pwd", EncryptUtils.rsaEncrypt(EncryptUtils.md5(password)));
        JSONObject jsonObject = new JSONObject(params);
        OkGo.<String>post(HanWanWeiApi.login())
                .upJson(jsonObject)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        handleResponse(response);
                    }

                    @Override
                    public void onError(Response<String> response) {
                        handleError(response);
                    }
                });
        requestBody.setText(jsonObject.toString());
    }

    private void createCspUser() {
        String username = getIntent().getStringExtra("username");
        String password = getIntent().getStringExtra("password");
        String email = getIntent().getStringExtra("email");
        String phone = getIntent().getStringExtra("phone");
        HashMap<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("pwd", EncryptUtils.rsaEncrypt(EncryptUtils.md5(password)));
        params.put("email", email);
        params.put("telephone", phone);

        JSONObject jsonObject = new JSONObject(params);
        OkGo.<String>post(HanWanWeiApi.createCspUserUrl())
                .upJson(jsonObject)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        handleResponse(response);
                    }

                    @Override
                    public void onError(Response<String> response) {
                        handleError(response);
                    }
                });
        requestBody.setText(jsonObject.toString());
    }

    private void searchCspUser() {
        OkGo.<String>get(HanWanWeiApi.searchCspUser())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        handleResponse(response);
                    }

                    @Override
                    public void onError(Response<String> response) {
                        handleError(response);
                    }
                });
    }

    private void searchOwnerSites() {
        String cspuserid = getIntent().getStringExtra("cspuserid");
        OkGo.<String>get(HanWanWeiApi.searchCspSite())
                .params("cspuserid", cspuserid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        handleResponse(response);
                    }

                    @Override
                    public void onError(Response<String> response) {
                        handleError(response);
                    }
                });
    }

    private void createCspCorp() {
        String userid = getIntent().getStringExtra("userid");
        String corpname = getIntent().getStringExtra("corpname");
        String describe = getIntent().getStringExtra("describe");
        HashMap<String, String> params = new HashMap<>();
        params.put("userid", userid);
        params.put("corpname", corpname);
        params.put("describe", describe);

        JSONObject jsonObject = new JSONObject(params);
        OkGo.<String>post(HanWanWeiApi.createCspCorp())
                .upJson(jsonObject)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        handleResponse(response);
                    }

                    @Override
                    public void onError(Response<String> response) {
                        handleError(response);
                    }
                });
        requestBody.setText(jsonObject.toString());

    }

    private void createCspSite() {
        String userid = getIntent().getStringExtra("userid");
        String corpname = getIntent().getStringExtra("corpname");
        String corpdescribe = getIntent().getStringExtra("corpdescribe");
        String sitename = getIntent().getStringExtra("sitename");
        String sitedescribe = getIntent().getStringExtra("sitedescribe");
        HashMap<String, String> params = new HashMap<>();
        params.put("userid", userid);
        params.put("corpname", corpname);
        params.put("corpdescribe", corpdescribe);
        params.put("sitename", sitename);
        params.put("sitedescribe", sitedescribe);

        JSONObject jsonObject = new JSONObject(params);
        OkGo.<String>post(HanWanWeiApi.createCspSite())
                .upJson(jsonObject)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        handleResponse(response);
                    }

                    @Override
                    public void onError(Response<String> response) {
                        handleError(response);
                    }
                });
        requestBody.setText(jsonObject.toString());

    }

    private void createCspSiteSsid() {
        String userid = getIntent().getStringExtra("userid");
        String siteid = getIntent().getStringExtra("siteid");
        String ssid = getIntent().getStringExtra("ssid");
        String pskpwd = getIntent().getStringExtra("pskpwd");
        HashMap<String, String> params = new HashMap<>();
        params.put("userid", userid);
        params.put("siteid", siteid);
        params.put("ssid", ssid);
        params.put("pskpwd", pskpwd);

        JSONObject jsonObject = new JSONObject(params);
        OkGo.<String>post(HanWanWeiApi.createCspSiteSsid())
                .upJson(jsonObject)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        handleResponse(response);
                    }

                    @Override
                    public void onError(Response<String> response) {
                        handleError(response);
                    }
                });
        requestBody.setText(jsonObject.toString());
    }

    private void distributeAp() {
        String userid = getIntent().getStringExtra("userid");
        String siteid = getIntent().getStringExtra("siteid");
        String sitename = getIntent().getStringExtra("sitename");
        ArrayList<String> apmac = getIntent().getStringArrayListExtra("apmac");
        HashMap<String, Object> params = new HashMap<>();
        params.put("userid", userid);
        params.put("siteid", siteid);
        params.put("sitename", sitename);
        params.put("apmac", apmac != null ? apmac.toArray() : new Object[0]);

        JSONObject jsonObject = new JSONObject(params);
        OkGo.<String>post(HanWanWeiApi.apDistribute())
                .upJson(jsonObject)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        handleResponse(response);
                    }

                    @Override
                    public void onError(Response<String> response) {
                        handleError(response);
                    }
                });
        requestBody.setText(jsonObject.toString());
    }

    private void cspAll() {
        String username = getIntent().getStringExtra("username");
        String pwd = getIntent().getStringExtra("pwd");
        String email = getIntent().getStringExtra("email");
        String telephone = getIntent().getStringExtra("telephone");
        String corpname = getIntent().getStringExtra("corpname");
        String corpdescribe = getIntent().getStringExtra("corpdescribe");
        String sitename = getIntent().getStringExtra("sitename");
        String sitedescribe = getIntent().getStringExtra("sitedescribe");
        String ssidname = getIntent().getStringExtra("ssidname");
        String pskpwd = getIntent().getStringExtra("pskpwd");
        ArrayList<String> apmac = getIntent().getStringArrayListExtra("apmac");

        HashMap<String, Object> params = new HashMap<>();
        params.put("username", username);
        params.put("pwd", EncryptUtils.rsaEncrypt(EncryptUtils.md5(pwd)));
        params.put("email", email);
        params.put("telephone", telephone);
        params.put("corpname", corpname);
        params.put("corpdescribe", corpdescribe);
        params.put("sitename", sitename);
        params.put("sitedescribe", sitedescribe);
        params.put("ssidname", ssidname);
        params.put("pskpwd", pskpwd);
        params.put("apmacs", apmac);

        JSONObject jsonObject = new JSONObject(params);
        OkGo.<String>post("https://192.168.2.60/apiv1/wanwei/csp/all")
                .upJson(jsonObject)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        handleResponse(response);
                    }

                    @Override
                    public void onError(Response<String> response) {
                        handleError(response);
                    }
                });
        requestBody.setText(jsonObject.toString());
    }

    private void searchApStatus() {
        OkGo.<String>get(HanWanWeiApi.searchApStatus())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        handleResponse(response);
                    }

                    @Override
                    public void onError(Response<String> response) {
                        handleError(response);
                    }
                });
    }

    private void apLogin() {
        String username = getIntent().getStringExtra("username");
        String password = getIntent().getStringExtra("password");
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", 1);
        params.put("jsonrpc", "2.0");
        params.put("username", username);
        params.put("method", HanApApi.login());
        HashMap<String, Object> params2 = new HashMap<>();
        params2.put("username", username);
        params2.put("password", EncryptUtils.md5(password));
        params.put("params", params2);
        JSONObject jsonObject = new JSONObject(params);
        OkGo.<String>post(HanApApi.baseUrl())
                .upJson(jsonObject)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        handleResponse(response);
                    }

                    @Override
                    public void onError(Response<String> response) {
                        handleError(response);
                    }
                });
        requestBody.setText(jsonObject.toString());
    }

    private void apCloudMode() {
        String session = getIntent().getStringExtra("session");
        String mac = getIntent().getStringExtra("mac");
        String ip = getIntent().getStringExtra("ip");

        HashMap<String, Object> params5 = new HashMap<>();
        params5.put("mgr_mode", "CLOUD");
        params5.put("mgr_addr", ip);

        HashMap<String, Object> params4 = new HashMap<>();
        params4.put("ap_mgr", params5);

        HashMap<String, Object> params3 = new HashMap<>();
        params3.put("config_type", 1);
        params3.put("content", params4);

        HashMap<String, Object> params2 = new HashMap<>();
        params2.put("version", "3.0");
        params2.put("messageID", 2);
        params2.put("macAddress", mac);
        params2.put("option", "select");
        params2.put("method", "ap_manage.cfg_manage");
        params2.put("contents", params3);

        HashMap<String, Object> params = new HashMap<>();
        params.put("id", 2);
        params.put("jsonrpc", "2.0");
        params.put("method", HanApApi.setIpAddress());
        params.put("username", "Administrator");
        params.put("session", session);
        params.put("topic", "WMA/TEST");
        params.put("params", params2);

        JSONObject jsonObject = new JSONObject(params);
        OkGo.<String>post(HanApApi.baseUrl())
                .upJson(jsonObject)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        handleResponse(response);
                    }

                    @Override
                    public void onError(Response<String> response) {
                        handleError(response);
                    }
                });
        requestBody.setText(jsonObject.toString());
    }


    //=========================================分割线==============================================//

    public static String formatJson(Object src) {
        try {
            JsonParser jp = new JsonParser();
            JsonElement je = jp.parse(new Gson().toJson(src));
            return (new Gson()).toJson(je);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    private <T> void handleResponse(T data) {
        Response<T> response = new Response<>();
        response.setBody(data);
        handleResponse(response);
    }

    private <T> void handleResponse(Response<T> response) {
        StringBuilder sb;
        Call call = response.getRawCall();
        if (call != null) {
            requestState.setText("请求成功  请求方式：" + call.request().method() + "\n" + "url：" + call.request().url());

            Headers requestHeadersString = call.request().headers();
            Set<String> requestNames = requestHeadersString.names();
            sb = new StringBuilder();
            for (String name : requestNames) {
                sb.append(name).append(" ： ").append(requestHeadersString.get(name)).append("\n");
            }
            requestHeaders.setText(sb.toString());
        } else {
            requestState.setText("--");
            requestHeaders.setText("--");
        }
        T body = response.body();
        if (body == null) {
            responseData.setText("--");
        } else {
            if (body instanceof String) {
                responseData.setText((String) body);
            } else if (body instanceof List) {
                sb = new StringBuilder();
                List list = (List) body;
                for (Object obj : list) {
                    sb.append(obj.toString()).append("\n");
                }
                responseData.setText(sb.toString());
            } else if (body instanceof Set) {
                sb = new StringBuilder();
                Set set = (Set) body;
                for (Object obj : set) {
                    sb.append(obj.toString()).append("\n");
                }
                responseData.setText(sb.toString());
            } else if (body instanceof Map) {
                sb = new StringBuilder();
                Map map = (Map) body;
                Set keySet = map.keySet();
                for (Object key : keySet) {
                    sb.append(key.toString()).append(" ： ").append(map.get(key)).append("\n");
                }
                responseData.setText(sb.toString());
            } else if (body instanceof File) {
                File file = (File) body;
                responseData.setText("数据内容即为文件内容\n下载文件路径：" + file.getAbsolutePath());
            } else if (body instanceof Bitmap) {
                responseData.setText("图片的内容即为数据");
            } else {
                responseData.setText(formatJson(body));
            }
        }

        okhttp3.Response rawResponse = response.getRawResponse();
        if (rawResponse != null) {
            Headers responseHeadersString = rawResponse.headers();
            Set<String> names = responseHeadersString.names();
            sb = new StringBuilder();
            sb.append("url ： ").append(rawResponse.request().url()).append("\n\n");
            sb.append("stateCode ： ").append(rawResponse.code()).append("\n");
            for (String name : names) {
                sb.append(name).append(" ： ").append(responseHeadersString.get(name)).append("\n");
            }
            responseHeader.setText(sb.toString());
        } else {
            responseHeader.setText("--");
        }
    }

    private <T> void handleError() {
        Response<T> response = new Response<>();
        handleResponse(response);
    }

    private <T> void handleError(Response<T> response) {
        if (response == null) {
            return;
        }
        if (response.getException() != null) {
            response.getException().printStackTrace();
        }
        StringBuilder sb;
        Call call = response.getRawCall();
        if (call != null) {
            requestState.setText("请求失败  请求方式：" + call.request().method() + "\n" + "url：" + call.request().url());

            Headers requestHeadersString = call.request().headers();
            Set<String> requestNames = requestHeadersString.names();
            sb = new StringBuilder();
            for (String name : requestNames) {
                sb.append(name).append(" ： ").append(requestHeadersString.get(name)).append("\n");
            }
            requestHeaders.setText(sb.toString());
        } else {
            requestState.setText("--");
            requestHeaders.setText("--");
        }

        responseData.setText("--");
        okhttp3.Response rawResponse = response.getRawResponse();
        if (rawResponse != null) {
            Headers responseHeadersString = rawResponse.headers();
            Set<String> names = responseHeadersString.names();
            sb = new StringBuilder();
            sb.append("stateCode ： ").append(rawResponse.code()).append("\n");
            for (String name : names) {
                sb.append(name).append(" ： ").append(responseHeadersString.get(name)).append("\n");
            }
            responseHeader.setText(sb.toString());
        } else {
            responseHeader.setText("--");
        }
    }


}


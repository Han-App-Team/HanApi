package com.example.hanapitest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hanapitest.utils.SimpleDialog;

import java.util.ArrayList;

/**
 * @author bonan
 */
public class TestActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.test_login:
                login();
                break;
            case R.id.test_create_csp_user:
                createCspUser();
                break;
            case R.id.test_search_csp_user:
                searchCspUser();
                break;
            case R.id.test_search_owner_site:
                searchOwnerSites();
                break;
            case R.id.test_create_csp_corp:
                createCspCorp();
                break;
            case R.id.test_create_csp_site:
                createCspSite();
                break;
            case R.id.test_create_csp_site_ssid:
                createCspSiteSsid();
                break;
            case R.id.test_distribute_ap:
                distributeAp();
                break;
            case R.id.test_all:
                cspAll();
                break;
            case R.id.test_search_ap_status:
                searchApStatus();
                break;
            case R.id.test_ap_login:
                apLogin();
                break;
            case R.id.test_ap_aloud_mode:
                apCloudMode();
                break;
            default:
                break;
        }
    }


    private void login() {
        SimpleDialog.newInstance(0, 0)
                .setCustomView(R.layout.activity_test_dialog_4input)
                .setCustomAction((dialog, view) -> {
                    EditText etName = view.findViewById(R.id.et_input_1);
                    EditText etPwd = view.findViewById(R.id.et_input_2);
                    view.findViewById(R.id.et_input_3).setVisibility(View.GONE);
                    view.findViewById(R.id.et_input_4).setVisibility(View.GONE);
                    etName.setHint("用户名-必填");
                    etPwd.setHint("密码-必填");
                })
                .setBtnRightClickedListener((dialog, view) -> {
                    EditText etName = view.findViewById(R.id.et_input_1);
                    EditText etPwd = view.findViewById(R.id.et_input_2);
                    EditText etCode = view.findViewById(R.id.et_input_3);
                    String username = etName.getText().toString();
                    String password = etPwd.getText().toString();
                    String code = etCode.getText().toString();

                    Intent intent = new Intent(this, TestDetailActivity.class);
                    intent.putExtra("type", 1);
                    intent.putExtra("username", username);
                    intent.putExtra("password", password);
                    startActivity(intent);

                })
                .setCancelableOutside(true)
                .setAutoDismiss(false)
                .show(getSupportFragmentManager(), "dialog");
    }

    private void createCspUser() {
        SimpleDialog.newInstance(0, 0)
                .setCustomView(R.layout.activity_test_dialog_4input)
                .setCustomAction((dialog, view) -> {
                    EditText et1 = view.findViewById(R.id.et_input_1);
                    EditText et2 = view.findViewById(R.id.et_input_2);
                    EditText et3 = view.findViewById(R.id.et_input_3);
                    EditText et4 = view.findViewById(R.id.et_input_4);
                    et1.setHint("csp用户名-必填");
                    et2.setHint("密码-必填");
                    et3.setHint("邮箱-必填");
                    et4.setHint("手机号-必填");
                })
                .setBtnRightClickedListener((dialog, view) -> {
                    EditText et1 = view.findViewById(R.id.et_input_1);
                    EditText et2 = view.findViewById(R.id.et_input_2);
                    EditText et3 = view.findViewById(R.id.et_input_3);
                    EditText et4 = view.findViewById(R.id.et_input_4);
                    String username = et1.getText().toString();
                    String password = et2.getText().toString();
                    String email = et3.getText().toString();
                    String phone = et4.getText().toString();

                    Intent intent = new Intent(this, TestDetailActivity.class);
                    intent.putExtra("type", 2);
                    intent.putExtra("username", username);
                    intent.putExtra("password", password);
                    intent.putExtra("email", email);
                    intent.putExtra("phone", phone);
                    startActivity(intent);

                })
                .setCancelableOutside(true)
                .setAutoDismiss(false)
                .show(getSupportFragmentManager(), "dialog");
    }

    private void searchCspUser() {
        Intent intent = new Intent(this, TestDetailActivity.class);
        intent.putExtra("type", 3);
        startActivity(intent);
    }

    private void searchOwnerSites() {
        SimpleDialog.newInstance(0, 0)
                .setCustomView(R.layout.activity_test_dialog_4input)
                .setCustomAction((dialog, view) -> {
                    EditText et1 = view.findViewById(R.id.et_input_1);
                    EditText et2 = view.findViewById(R.id.et_input_2);
                    EditText et3 = view.findViewById(R.id.et_input_3);
                    EditText et4 = view.findViewById(R.id.et_input_4);
                    et1.setHint("万维CSP用户ID-必填");
                    et2.setVisibility(View.GONE);
                    et3.setVisibility(View.GONE);
                    et4.setVisibility(View.GONE);
                })
                .setBtnRightClickedListener((dialog, view) -> {
                    EditText et1 = view.findViewById(R.id.et_input_1);
                    String cspuserid = et1.getText().toString();

                    Intent intent = new Intent(this, TestDetailActivity.class);
                    intent.putExtra("type", 4);
                    intent.putExtra("cspuserid", cspuserid);
                    startActivity(intent);

                })
                .setCancelableOutside(true)
                .setAutoDismiss(false)
                .show(getSupportFragmentManager(), "dialog");
    }

    private void createCspCorp() {
        SimpleDialog.newInstance(0, 0)
                .setCustomView(R.layout.activity_test_dialog_4input)
                .setCustomAction((dialog, view) -> {
                    EditText et1 = view.findViewById(R.id.et_input_1);
                    EditText et2 = view.findViewById(R.id.et_input_2);
                    EditText et3 = view.findViewById(R.id.et_input_3);
                    EditText et4 = view.findViewById(R.id.et_input_4);
                    et1.setHint("csp用户id，必须");
                    et2.setHint("集团名称，必须 ");
                    et3.setHint("集团描述，非必填");
                    et4.setVisibility(View.GONE);
                })
                .setBtnRightClickedListener((dialog, view) -> {
                    EditText et1 = view.findViewById(R.id.et_input_1);
                    EditText et2 = view.findViewById(R.id.et_input_2);
                    EditText et3 = view.findViewById(R.id.et_input_3);
                    String userid = et1.getText().toString();
                    String corpname = et2.getText().toString();
                    String describe = et3.getText().toString();

                    Intent intent = new Intent(this, TestDetailActivity.class);
                    intent.putExtra("type", 5);
                    intent.putExtra("userid", userid);
                    intent.putExtra("corpname", corpname);
                    intent.putExtra("describe", describe);
                    startActivity(intent);

                })
                .setCancelableOutside(true)
                .setAutoDismiss(false)
                .show(getSupportFragmentManager(), "dialog");
    }

    private void createCspSite() {
        SimpleDialog.newInstance(0, 0)
                .setCustomView(R.layout.activity_test_dialog_5input)
                .setCustomAction((dialog, view) -> {
                    EditText et1 = view.findViewById(R.id.et_input_1);
                    EditText et2 = view.findViewById(R.id.et_input_2);
                    EditText et3 = view.findViewById(R.id.et_input_3);
                    EditText et4 = view.findViewById(R.id.et_input_4);
                    EditText et5 = view.findViewById(R.id.et_input_5);
                    et1.setHint("csp用户id，必须");
                    et2.setHint("集团名称，非必须");
                    et3.setHint("集团描述，非必须");
                    et4.setHint("场所名称，非必须");
                    et5.setHint("场所描述，非必须");
                })
                .setBtnRightClickedListener((dialog, view) -> {
                    EditText et1 = view.findViewById(R.id.et_input_1);
                    EditText et2 = view.findViewById(R.id.et_input_2);
                    EditText et3 = view.findViewById(R.id.et_input_3);
                    EditText et4 = view.findViewById(R.id.et_input_4);
                    EditText et5 = view.findViewById(R.id.et_input_5);
                    String userid = et1.getText().toString();
                    String corpname = et2.getText().toString();
                    String corpdescribe = et3.getText().toString();
                    String sitename = et4.getText().toString();
                    String sitedescribe = et5.getText().toString();

                    Intent intent = new Intent(this, TestDetailActivity.class);
                    intent.putExtra("type", 6);
                    intent.putExtra("userid", userid);
                    intent.putExtra("corpname", corpname);
                    intent.putExtra("corpdescribe", corpdescribe);
                    intent.putExtra("sitename", sitename);
                    intent.putExtra("sitedescribe", sitedescribe);
                    startActivity(intent);

                })
                .setCancelableOutside(true)
                .setAutoDismiss(false)
                .show(getSupportFragmentManager(), "dialog");
    }

    private void createCspSiteSsid() {
        SimpleDialog.newInstance(0, 0)
                .setCustomView(R.layout.activity_test_dialog_4input)
                .setCustomAction((dialog, view) -> {
                    EditText et1 = view.findViewById(R.id.et_input_1);
                    EditText et2 = view.findViewById(R.id.et_input_2);
                    EditText et3 = view.findViewById(R.id.et_input_3);
                    EditText et4 = view.findViewById(R.id.et_input_4);
                    et1.setHint("csp用户id，必须");
                    et2.setHint("场所id，必须");
                    et3.setHint("ssid名称，必须");
                    et4.setHint("psk的密码，编码待定，必须");
                })
                .setBtnRightClickedListener((dialog, view) -> {
                    EditText et1 = view.findViewById(R.id.et_input_1);
                    EditText et2 = view.findViewById(R.id.et_input_2);
                    EditText et3 = view.findViewById(R.id.et_input_3);
                    EditText et4 = view.findViewById(R.id.et_input_4);
                    String userid = et1.getText().toString();
                    String siteid = et2.getText().toString();
                    String ssid = et3.getText().toString();
                    String pskpwd = et4.getText().toString();

                    Intent intent = new Intent(this, TestDetailActivity.class);
                    intent.putExtra("type", 7);
                    intent.putExtra("userid", userid);
                    intent.putExtra("siteid", siteid);
                    intent.putExtra("ssid", ssid);
                    intent.putExtra("pskpwd", pskpwd);
                    startActivity(intent);

                })
                .setCancelableOutside(true)
                .setAutoDismiss(false)
                .show(getSupportFragmentManager(), "dialog");
    }

    private void distributeAp() {
        SimpleDialog.newInstance(0, 0)
                .setCustomView(R.layout.activity_test_dialog_4input)
                .setCustomAction((dialog, view) -> {
                    EditText et1 = view.findViewById(R.id.et_input_1);
                    EditText et2 = view.findViewById(R.id.et_input_2);
                    EditText et3 = view.findViewById(R.id.et_input_3);
                    EditText et4 = view.findViewById(R.id.et_input_4);
                    et1.setHint("csp用户id，必须");
                    et2.setHint("场所的id，必须");
                    et3.setHint("场所名称，必须");
                    et4.setHint("apmac 数组，必须");
                })
                .setBtnRightClickedListener((dialog, view) -> {
                    EditText et1 = view.findViewById(R.id.et_input_1);
                    EditText et2 = view.findViewById(R.id.et_input_2);
                    EditText et3 = view.findViewById(R.id.et_input_3);
                    EditText et4 = view.findViewById(R.id.et_input_4);
                    String userid = et1.getText().toString();
                    String siteid = et2.getText().toString();
                    String sitename = et3.getText().toString();
                    String macContent = et4.getText().toString();
                    ArrayList<String> maclist = new ArrayList<>();
                    maclist.add(macContent);

                    Intent intent = new Intent(this, TestDetailActivity.class);
                    intent.putExtra("type", 8);
                    intent.putExtra("userid", userid);
                    intent.putExtra("siteid", siteid);
                    intent.putExtra("sitename", sitename);
                    intent.putStringArrayListExtra("apmac", maclist);
                    startActivity(intent);

                })
                .setCancelableOutside(true)
                .setAutoDismiss(false)
                .show(getSupportFragmentManager(), "dialog");
    }

    private void cspAll() {
        SimpleDialog.newInstance(0, 0)
                .setCustomView(R.layout.activity_test_dialog_11input)
                .setCustomAction((dialog, view) -> {
                    EditText et1 = view.findViewById(R.id.et_input_1);
                    EditText et2 = view.findViewById(R.id.et_input_2);
                    EditText et3 = view.findViewById(R.id.et_input_3);
                    EditText et4 = view.findViewById(R.id.et_input_4);
                    EditText et5 = view.findViewById(R.id.et_input_5);
                    EditText et6 = view.findViewById(R.id.et_input_6);
                    EditText et7 = view.findViewById(R.id.et_input_7);
                    EditText et8 = view.findViewById(R.id.et_input_8);
                    EditText et9 = view.findViewById(R.id.et_input_9);
                    EditText et10 = view.findViewById(R.id.et_input_10);
                    EditText et11 = view.findViewById(R.id.et_input_11);
                    et1.setHint("csp用户名-必填");
                    et2.setHint("csp用户密码-必填");
                    et3.setHint("csp用户邮箱-必填");
                    et4.setHint("csp用户手机号-必填");
                    et5.setHint("集团名称，非必须");
                    et6.setHint("集团描叙，非必须");
                    et7.setHint("场所名称，必填");
                    et8.setHint("场所描叙，非必须");
                    et9.setHint("ssid名称，非必须");
                    et10.setHint("psk的密码，编码待定，创建ssid的情况下必须");
                    et11.setHint("apmac，必填");
                })
                .setBtnRightClickedListener((dialog, view) -> {
                    EditText et1 = view.findViewById(R.id.et_input_1);
                    EditText et2 = view.findViewById(R.id.et_input_2);
                    EditText et3 = view.findViewById(R.id.et_input_3);
                    EditText et4 = view.findViewById(R.id.et_input_4);
                    EditText et5 = view.findViewById(R.id.et_input_5);
                    EditText et6 = view.findViewById(R.id.et_input_6);
                    EditText et7 = view.findViewById(R.id.et_input_7);
                    EditText et8 = view.findViewById(R.id.et_input_8);
                    EditText et9 = view.findViewById(R.id.et_input_9);
                    EditText et10 = view.findViewById(R.id.et_input_10);
                    EditText et11 = view.findViewById(R.id.et_input_11);
                    String username = et1.getText().toString();
                    String pwd = et2.getText().toString();
                    String email = et3.getText().toString();
                    String telephone = et4.getText().toString();
                    String corpname = et5.getText().toString();
                    String corpdescribe = et6.getText().toString();
                    String sitename = et7.getText().toString();
                    String sitedescribe = et8.getText().toString();
                    String ssidname = et9.getText().toString();
                    String pskpwd = et10.getText().toString();
                    String macContent = et11.getText().toString();
                    ArrayList<String> maclist = new ArrayList<>();
                    maclist.add(macContent);

                    Intent intent = new Intent(this, TestDetailActivity.class);
                    intent.putExtra("type", 9);
                    intent.putExtra("username", username);
                    intent.putExtra("pwd", pwd);
                    intent.putExtra("email", email);
                    intent.putExtra("telephone", telephone);
                    intent.putExtra("corpname", corpname);
                    intent.putExtra("corpdescribe", corpdescribe);
                    intent.putExtra("sitename", sitename);
                    intent.putExtra("sitedescribe", sitedescribe);
                    intent.putExtra("ssidname", ssidname);
                    intent.putExtra("pskpwd", pskpwd);
                    intent.putStringArrayListExtra("apmac", maclist);

                    startActivity(intent);

                })
                .setCancelableOutside(true)
                .setAutoDismiss(false)
                .show(getSupportFragmentManager(), "dialog");
    }

    private void searchApStatus() {
        Intent intent = new Intent(this, TestDetailActivity.class);
        intent.putExtra("type", 10);
        startActivity(intent);
    }

    private void apLogin() {
        SimpleDialog.newInstance(0, 0)
                .setCustomView(R.layout.activity_test_dialog_4input)
                .setCustomAction((dialog, view) -> {
                    EditText etName = view.findViewById(R.id.et_input_1);
                    EditText etPwd = view.findViewById(R.id.et_input_2);
                    view.findViewById(R.id.et_input_3).setVisibility(View.GONE);
                    view.findViewById(R.id.et_input_4).setVisibility(View.GONE);
                    etName.setText("Administrator");
                    etPwd.setText("admin");
                })
                .setBtnRightClickedListener((dialog, view) -> {
                    EditText etName = view.findViewById(R.id.et_input_1);
                    EditText etPwd = view.findViewById(R.id.et_input_2);
                    EditText etCode = view.findViewById(R.id.et_input_3);
                    String username = etName.getText().toString();
                    String password = etPwd.getText().toString();

                    Intent intent = new Intent(this, TestDetailActivity.class);
                    intent.putExtra("type", 11);
                    intent.putExtra("username", username);
                    intent.putExtra("password", password);
                    startActivity(intent);

                })
                .setCancelableOutside(true)
                .setAutoDismiss(false)
                .show(getSupportFragmentManager(), "dialog");
    }

    private void apCloudMode() {
        SimpleDialog.newInstance(0, 0)
                .setCustomView(R.layout.activity_test_dialog_4input)
                .setCustomAction((dialog, view) -> {
                    EditText et1 = view.findViewById(R.id.et_input_1);
                    EditText et2 = view.findViewById(R.id.et_input_2);
                    EditText et3 = view.findViewById(R.id.et_input_3);
                    view.findViewById(R.id.et_input_4).setVisibility(View.GONE);
                    et1.setHint("session--必填，登录成功后返回");
                    et2.setHint("mac地址--必填，登录成功后返回");
                    et3.setHint("云服务器IP地址--必填");
                })
                .setBtnRightClickedListener((dialog, view) -> {
                    EditText et1 = view.findViewById(R.id.et_input_1);
                    EditText et2 = view.findViewById(R.id.et_input_2);
                    EditText et3 = view.findViewById(R.id.et_input_3);
                    String session = et1.getText().toString();
                    String mac = et2.getText().toString();
                    String ip = et3.getText().toString();

                    Intent intent = new Intent(this, TestDetailActivity.class);
                    intent.putExtra("type", 12);
                    intent.putExtra("session", session);
                    intent.putExtra("mac", mac);
                    intent.putExtra("ip", ip);
                    startActivity(intent);
                })
                .setCancelableOutside(true)
                .setAutoDismiss(false)
                .show(getSupportFragmentManager(), "dialog");
    }


}


package io.ipfs.videoshare.ipfs_util;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import io.ipfs.videoshare.App;
import io.ipfs.videoshare.updata_bean;
import ipfs.gomobile.android.IPFS;
import ipfs.gomobile.android.RequestBuilder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Util implements _Ipfs {
    private IPFS ipfs;

    //初始化
    @Override
    public Boolean startipfs(final Activity activity) {
        try {
            ipfs= new IPFS(activity);
            ipfs.start();
            return true;
        } catch (IPFS.ConfigCreationException e) {
            e.printStackTrace();
        } catch (IPFS.RepoInitException e) {
            e.printStackTrace();
        } catch (IPFS.SockManagerException e) {
            e.printStackTrace();
        } catch (IPFS.NodeStartException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Boolean startipfs(Activity activity, String url) {
        return null;
    }

    @Override
    public String get_id() {
        if(!ipfs.isStarted())return null;
        try {
            ArrayList<JSONObject> jsonList = ipfs.newRequest("/id").sendToJSONList();
            Log.e("LO2", this.get_updatejson());
            return jsonList.get(0).getString("ID");
        } catch (RequestBuilder.RequestBuilderException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IPFS.ShellRequestException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String resolve(String ipns) throws IPFS.ShellRequestException, RequestBuilder.RequestBuilderException, JSONException {
        Log.e("LO3", ipns);
        ArrayList<JSONObject> jsonList = ipfs.newRequest("/name/resolve").withArgument(ipns).sendToJSONList();
        Log.e("LO1", jsonList.toString());
        return jsonList.toString();
    }

    @Override
    public String get_json(String ipfspath) throws IPFS.ShellRequestException, RequestBuilder.RequestBuilderException, JSONException {
        ArrayList<JSONObject> jsonList = null;
        jsonList = ipfs.newRequest("/cat").withArgument(ipfspath).sendToJSONList();
        Log.e("LO2", jsonList.toString());
        return jsonList.toString();
    }

    @Override
    public String get_updatejson() throws IPFS.ShellRequestException, RequestBuilder.RequestBuilderException, JSONException {
        String hash = this.resolve(App.updata_hash);
        return this.get_json("/"+hash+'/'+"update.json");
    }
    public void getAsyn(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //...
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String head_ipfs = response.header("X-Ipfs-Path:");
                }
            }
        });
    }
}

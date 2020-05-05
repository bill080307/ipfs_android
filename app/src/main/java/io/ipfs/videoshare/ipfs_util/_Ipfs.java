package io.ipfs.videoshare.ipfs_util;

import android.app.Activity;
import android.content.Context;

import com.google.gson.internal.bind.JsonTreeReader;

import org.json.JSONException;

import ipfs.gomobile.android.IPFS;
import ipfs.gomobile.android.RequestBuilder;

public interface _Ipfs {
    /**
     * startipfs
     * @return
     */
    Boolean startipfs(Activity activity);
    Boolean startipfs(Activity activity, String url);
    String get_id();
    String resolve(String ipns) throws IPFS.ShellRequestException, RequestBuilder.RequestBuilderException, JSONException;
    String get_json(String ipfspath) throws IPFS.ShellRequestException, RequestBuilder.RequestBuilderException, JSONException;

    String get_updatejson() throws IPFS.ShellRequestException, RequestBuilder.RequestBuilderException, JSONException;

}

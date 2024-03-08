/**
* Copyright 2016 IBM Corp.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package sample.com.pincodeandroid;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.worklight.wlclient.api.WLClient;
import com.worklight.wlclient.api.challengehandler.SecurityCheckChallengeHandler;

import org.json.JSONException;
import org.json.JSONObject;

public class PinCodeChallengeHandler extends SecurityCheckChallengeHandler {
    private Context context;
    private LocalBroadcastManager broadcastManager;

    public PinCodeChallengeHandler(String securityCheck) {
        super(securityCheck);
        context = WLClient.getInstance().getContext();
        broadcastManager = LocalBroadcastManager.getInstance(context);

        broadcastManager.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                try {
                    submitChallengeAnswer(new JSONObject().put("pin", intent.getStringExtra("pinCodeTxt")));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new IntentFilter(Constants.ACTION_CHALLENGE_SUBMIT_ANSWER));

        broadcastManager.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
               cancel();
            }
        }, new IntentFilter(Constants.ACTION_CHALLENGE_CANCEL));
    }

    @Override
    public void handleFailure(JSONObject jsonObject) {
        Log.d("Failure", jsonObject.toString());
        Intent intent = new Intent();
        intent.setAction(Constants.ACTION_CHALLENGE_FAILURE);
        try {
            if (!jsonObject.isNull("failure")) {
                intent.putExtra("errorMsg", jsonObject.getString("failure"));
            } else {
                intent.putExtra("errorMsg", "Unknown error");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        broadcastManager.sendBroadcast(intent);
    }


    @Override
    public void handleChallenge(JSONObject jsonObject) {
        Log.d("Handle Challenge", jsonObject.toString());
        Log.d("Failure", jsonObject.toString());
        Intent intent = new Intent();
        intent.setAction(Constants.ACTION_CHALLENGE_RECEIVED);
        try{
            if (jsonObject.isNull("errorMsg")){
                intent.putExtra("msg", "This data requires a PIN code.\n Remaining attempts: " + jsonObject.getString("remainingAttempts"));
            } else {
                intent.putExtra("msg", jsonObject.getString("errorMsg") + "\nRemaining attempts: " + jsonObject.getString("remainingAttempts"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        broadcastManager.sendBroadcast(intent);

    }

    public void handleChallenge1(JSONObject jsonObject) {
        Log.d("Handle Challenge", jsonObject.toString());
        Log.d("Failure", jsonObject.toString());
        Intent intent = new Intent();
        intent.setAction(Constants.ACTION_CHALLENGE_RECEIVED);
        try{
            if (jsonObject.isNull("errorMsg")){
                intent.putExtra("msg", "This data requires a PIN code.\n Remaining attempts: " + jsonObject.getString("remainingAttempts"));
            } else {
                intent.putExtra("msg", jsonObject.getString("errorMsg") + "\nRemaining attempts: " + jsonObject.getString("remainingAttempts"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        broadcastManager.sendBroadcast(intent);

    }



}

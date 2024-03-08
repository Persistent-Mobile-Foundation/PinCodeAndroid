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

import android.app.Application;
import android.util.Log;

import com.worklight.common.Logger;
import com.worklight.common.WLAnalytics;
import com.worklight.wlclient.api.WLAccessTokenListener;
import com.worklight.wlclient.api.WLClient;
import com.worklight.wlclient.api.WLFailResponse;
import com.worklight.wlclient.api.WLResponse;
import com.worklight.wlclient.api.WLResponseListener;
import com.worklight.wlclient.auth.AccessToken;

public class PinCodeApplication extends Application {
    public void onCreate () {
        super.onCreate();
        WLClient client = WLClient.createInstance(this);
        WLAnalytics.init(this);
        //Logger.setCapture(false);
        //Logger.setAnalyticsCapture(false);
        //WLAnalytics.disable();
        //WLAnalytics.removeDeviceEventListener(WLAnalytics.DeviceEvent.LIFECYCLE);
        //WLAnalytics.removeDeviceEventListener(WLAnalytics.DeviceEvent.NETWORK);

        PinCodeChallengeHandler pinCodeChallengeHandler = new PinCodeChallengeHandler("PinCodeAttempts");
        client.registerChallengeHandler(pinCodeChallengeHandler);

/*        client.connect(new WLResponseListener() {
            @Override
            public void onSuccess(WLResponse wlResponse) {
                Log.d("connect", "auto login success");
            }

            @Override
            public void onFailure(WLFailResponse wlFailResponse) {
                Log.d("connect", "auto login failure");
            }
        });*/

    }
}

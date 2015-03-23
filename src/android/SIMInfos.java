package com.jnuine.cordova;

import java.util.Locale;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.LOG;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;

import android.content.Context;
import android.telephony.TelephonyManager;

public class SIMInfos extends CordovaPlugin {

    private static final String TAG = "SIMInfos";

    private static final String GET_PHONE_NUMBER = "getPhoneNumber";
    private static final String GET_COUNTRY = "getCountry";

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
        String result = null;
        final TelephonyManager telephonyManager = this.getTelephonyManager();
        if (GET_PHONE_NUMBER.equals(action)) {
            result = telephonyManager.getLine1Number();
        }
        else if (GET_COUNTRY.equals(action)) {
            result = getCountry(telephonyManager);
        }
        if (result != null) {
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, result));
           return true;
        }
        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR));
        return false;
    }

    private TelephonyManager getTelephonyManager() {
        return (TelephonyManager) this.cordova
                                      .getActivity()
                                      .getSystemService(Context.TELEPHONY_SERVICE);
    }

    private String getCountry(TelephonyManager telephonyManager) {
        // From http://stackoverflow.com/a/19415296
        try {
            final String simCountry = telephonyManager.getSimCountryIso();
            if (simCountry != null && simCountry.length() == 2) { // SIM country code is available
                LOG.d(TAG, "Sim Country found " + simCountry);
                return simCountry.toUpperCase(Locale.US);
            }
            else if (telephonyManager.getPhoneType() != TelephonyManager.PHONE_TYPE_CDMA) { // device is not 3G (would be unreliable)
                final String networkCountry = telephonyManager.getNetworkCountryIso();
                LOG.d(TAG, "Network Country found " + networkCountry);
                if (networkCountry != null && networkCountry.length() == 2) { // network country code is available
                    return networkCountry.toUpperCase(Locale.US);
                }
            }
        }
        catch (Exception e) {}
        LOG.d(TAG, "NO COUNTRY FOUND :(");
        return null;
    }
}

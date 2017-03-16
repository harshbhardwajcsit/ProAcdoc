package com.medical.proadoc.HelperClasses;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;

public class MyLocationListener {
    public static Location location = null;
    private LocationManager locationManager = null;
    private Context context;
    // flag for GPS status
    boolean isGPSEnabled = false;
    // flag for network status
    boolean isNetworkEnabled = false;

    public MyLocationListener(Context context) {
        this.context = context;
        location();

    }

    private void location() {
        try {

            locationManager = (LocationManager) context
                    .getSystemService(Context.LOCATION_SERVICE);
            locationManager.getProviders(true);
            // getting GPS status
            isGPSEnabled = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);

            // getting network status
            isNetworkEnabled = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            if (!isGPSEnabled && !isNetworkEnabled) {
            } else {
                // if(isNetworkEnabled){

                // }
                Criteria locationCritera = new Criteria();
                locationCritera.setAccuracy(Criteria.ACCURACY_FINE);
                locationCritera.setAltitudeRequired(false);
                locationCritera.setBearingRequired(false);
                locationCritera.setCostAllowed(true);
                locationCritera.setPowerRequirement(Criteria.NO_REQUIREMENT);

                String providerName = locationManager.getBestProvider(
                        locationCritera, true);
                location = locationManager.getLastKnownLocation(providerName);

            }
        } catch (Exception e) {
        }
    }

    public String getCurrentLatitude() {
        if (!isGPSEnabled && !isNetworkEnabled) {
        } else {
            try {
                if (location.getLatitude() != 0.0) {
                    return Double.toString(location.getLatitude());
                } else {
                    return "26.915972900000000000";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "26.915972900000000000";
    }

    public String getCurrentLongitude() {
        if (!isGPSEnabled && !isNetworkEnabled) {
        } else {
            try {

                if (location.getLongitude() != 0.0) {
                    return Double.toString(location.getLongitude());
                } else {
                    return "75.740056299999990000";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "75.740056299999990000";
    }

}

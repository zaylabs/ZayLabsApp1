/*
package com.zaylabs.zaylabsapp1.api;

import android.location.Address;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class APIgeocoder {

    public static List<Address> getAddressFromLocation(double lat, double lng, int maxResult){

        String address = String.format(Locale.ENGLISH,"http://maps.googleapis.com/maps/api/geocode/json?latlng=%1$f,%2$f&sensor=true&language="+Locale.getDefault().getCountry(), lat, lng);
        HttpGet httpGet = new HttpGet(address);
        DefaultHttpClient client = new DefaultHttpClient();
        HttpResponse response;
        StringBuilder stringBuilder = new StringBuilder();

        List<Address> retList = null;

        try {
            response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            InputStream stream = entity.getContent();
            int b;
            while ((b = stream.read()) != -1) {
                stringBuilder.append((char) b);
            }

            JSONObject jsonObject = new JSONObject(stringBuilder.toString());


            retList = new ArrayList<Address>();


            if("OK".equalsIgnoreCase(jsonObject.getString("status"))){
                JSONArray results = jsonObject.getJSONArray("results");
                for (int i=0;i<maxResult;i++ ) {
                    JSONObject result = results.getJSONObject(i);
                    Address addr = new Address(Locale.ENGLISH);
                    JSONArray addressComp = result.getJSONArray("address_components");

                    int addressline = 0;
                    for(int j=0; j<addressComp.length(); j++) {
                        if(addressComp.getJSONObject(j).getJSONArray("types").getString(0).equals("route")) {
                            addr.setAddressLine(addressline, addressComp.getJSONObject(j).getString("long_name"));
                            addressline++;
                        }
                        if(addressComp.getJSONObject(j).getJSONArray("types").getString(0).equals("sublocality")) {
                            addr.setAddressLine(addressline, addressComp.getJSONObject(j).getString("long_name"));
                            addressline++;
                        }
                        if(addressComp.getJSONObject(j).getJSONArray("types").getString(0).equals("locality")) {
                            addr.setLocality(addressComp.getJSONObject(j).getString("long_name"));
                        }
                        if(addressComp.getJSONObject(j).getJSONArray("types").getString(0).equals("postal_code")) {
                            addr.setPostalCode(addressComp.getJSONObject(j).getString("long_name"));
                        }
                        if(addressComp.getJSONObject(j).getJSONArray("types").getString(0).equals("administrative_area_level_1")) {
                            addr.setAdminArea(addressComp.getJSONObject(j).getString("long_name"));
                        }
                        if(addressComp.getJSONObject(j).getJSONArray("types").getString(0).equals("country")) {
                            addr.setCountryName(addressComp.getJSONObject(j).getString("long_name"));
                        }

                    }

                    retList.add(addr);
                }
            }


        } catch (ClientProtocolException e) {
            Log.e("hitapp", "Error calling Google geocode webservice.", e);
        } catch (IOException e) {
            Log.e("hitapp", "Error calling Google geocode webservice.", e);
        } catch (JSONException e) {
            Log.e("hitapp", "Error parsing Google geocode webservice response.", e);
        }

        return retList;
    }

}
*/

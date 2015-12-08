package br.unicamp.cotuca.popover.remote.task;

import android.content.Context;
import android.os.AsyncTask;
import android.provider.Settings;

import br.unicamp.cotuca.popover.R;
import br.unicamp.cotuca.popover.listener.OnReadPlacesFinishedListener;
import br.unicamp.cotuca.popover.model.Place;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by joao on 31/08/15.
 */
public class LoadPlacesTask extends AsyncTask<Void, Void, Place[]>{
    private Context mContext;
    private OnReadPlacesFinishedListener mListener;

    public LoadPlacesTask(Context context , OnReadPlacesFinishedListener listener){
        mContext = context;
        mListener = listener;
    }


    @Override
    protected Place[] doInBackground(Void... params) {
        String fileName = mContext.getResources().getString(R.string.places_file);
        File f = new File(mContext.getFilesDir().getPath().toString() + "/" + fileName);
        BufferedReader in = null;
        ArrayList<Place> places = new ArrayList<Place>();
        if(f.exists()){
            try {
                in = new BufferedReader(new InputStreamReader(new FileInputStream(f)));

                while(in.ready()){
                    double lat = Double.valueOf(in.readLine());
                    double lng = Double.valueOf(in.readLine());
                    String name = in.readLine();
                    String address = in.readLine();
                    String description = in.readLine();
                    String imageURL = in.readLine();
                    Place p = new Place(lat, lng, name, address,description,imageURL);
                    places.add(p);
                }
            }catch(Exception e) {
                e.printStackTrace();
            }finally {
                if(in != null)
                    try {
                        in.close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
            }
            if(places.size() == 0)
                return null;
            Place[] ps = new Place[places.size()];
            int i=0;
            for(Place p: places){
                ps[i] = p;
                i++;
            }

            return ps;
        }else{
            return null;
        }
    }

    @Override
    protected void onPostExecute(Place[] places) {
        super.onPostExecute(places);
        mListener.onReadPlacesFinished(places);
    }
}

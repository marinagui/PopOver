package br.unicamp.cotuca.popover.remote.task;

import android.content.Context;
import android.os.AsyncTask;

import br.unicamp.cotuca.popover.R;
import br.unicamp.cotuca.popover.listener.OnReadPlacesFinishedListener;
import br.unicamp.cotuca.popover.listener.OnWritePlacesFinishedListener;
import br.unicamp.cotuca.popover.model.Place;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * Created by joao on 31/08/15.
 */
public class AddPlaceTask extends AsyncTask<Place, Void, Void> {
    private Context mContext;
    private OnWritePlacesFinishedListener mListener;

    public AddPlaceTask(Context context, OnWritePlacesFinishedListener listener) {
        mContext = context;
        mListener = listener;
    }

    @Override
    protected Void doInBackground(Place... places) {
        String fileName = mContext.getResources().getString(R.string.places_file);
        File f = new File(mContext.getFilesDir().getPath().toString() + "/" + fileName);
        if(!f.exists())
            try{
                f.createNewFile();
            }catch(Exception e){
                e.printStackTrace();
            }

        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(mContext.getFilesDir().getPath().toString() + "/" + fileName, true)));
            for(Place p:places) {
                out.println(String.valueOf(p.getLat()));
                out.println(String.valueOf(p.getLng()));
                out.println(p.getName());
                out.println(p.getAddress());
                out.println(p.getDescription());
                out.println(p.getImgURL());
            }
            out.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        mListener.onWritePlacesFinished();
    }
}
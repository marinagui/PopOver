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
import java.io.OutputStreamWriter;

/**
 * Created by joao on 31/08/15.
 */
public class WritePlacesTask extends AsyncTask<Place, Void, Void> {
    private Context mContext;
    private OnWritePlacesFinishedListener mListener;

    public WritePlacesTask(Context context, OnWritePlacesFinishedListener listener) {
        mContext = context;
        mListener = listener;
    }

    @Override
    protected Void doInBackground(Place... places) {
        String fileName = mContext.getResources().getString(R.string.places_file);
        File f = new File(mContext.getFilesDir().getPath().toString() + "/" + fileName);
        try {
            f.createNewFile();
        }catch(Exception e){
            e.printStackTrace();
        }

        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f)));
            for(Place p:places) {
                writer.write(String.valueOf(p.getLat()));
                writer.newLine();
                writer.write(String.valueOf(p.getLng()));
                writer.newLine();
                writer.write(p.getName());
                writer.newLine();
                writer.write(p.getAddress());
                writer.newLine();
                writer.write(p.getDescription());
                writer.newLine();
                writer.write(p.getImgURL());
                writer.newLine();
            }
            writer.close();
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
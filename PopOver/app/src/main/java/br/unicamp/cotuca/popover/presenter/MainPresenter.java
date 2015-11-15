package br.unicamp.cotuca.popover.presenter;

import android.content.Context;

import br.unicamp.cotuca.popover.listener.OnReadPlacesFinishedListener;
import br.unicamp.cotuca.popover.listener.OnWritePlacesFinishedListener;
import br.unicamp.cotuca.popover.model.Place;
import br.unicamp.cotuca.popover.remote.task.LoadPlacesTask;
import br.unicamp.cotuca.popover.remote.task.WritePlacesTask;
import br.unicamp.cotuca.popover.view.MainView;

/**
 * Created by joao on 31/08/15.
 */
public class MainPresenter implements OnReadPlacesFinishedListener, OnWritePlacesFinishedListener{

    private Context mContext;
    private MainView mView;

    public MainPresenter(Context context, MainView view) {
        this.mContext = context;
        this.mView = view;
    }

    @Override
    public void onReadPlacesFinished(Place[] places) {
        mView.displayMarkers(places);
    }

    public void loadPlaces(){
        new LoadPlacesTask(mContext,this).execute();
    }

    public void writePlaces(Place[] places){
        new WritePlacesTask(mContext, this).execute(places);
    }

    @Override
    public void onWritePlacesFinished() {


    }
}

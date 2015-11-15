package br.unicamp.cotuca.popover.presenter;

import android.content.Context;

import br.unicamp.cotuca.popover.listener.OnWritePlacesFinishedListener;
import br.unicamp.cotuca.popover.model.Place;
import br.unicamp.cotuca.popover.remote.task.AddPlaceTask;
import br.unicamp.cotuca.popover.view.NewPlaceView;

/**
 * Created by joao on 01/09/15.
 */
public class NewPlacePresenter implements OnWritePlacesFinishedListener {
    private NewPlaceView mView;
    private Context mContext;

    public NewPlacePresenter(Context context, NewPlaceView view) {
        this.mView = view;
        this.mContext = context;
    }

    public void addNewPlace(Place p){

        new AddPlaceTask(mContext,this).execute(p);
    }


    @Override
    public void onWritePlacesFinished() {
        mView.closeView();
    }
}

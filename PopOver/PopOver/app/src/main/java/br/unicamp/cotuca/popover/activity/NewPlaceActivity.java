package br.unicamp.cotuca.popover.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.unicamp.cotuca.popover.R;
import br.unicamp.cotuca.popover.model.Place;
import br.unicamp.cotuca.popover.presenter.NewPlacePresenter;
import br.unicamp.cotuca.popover.view.NewPlaceView;

/**
 * Created by joao on 01/09/15.
 */
public class NewPlaceActivity extends AppCompatActivity implements NewPlaceView{
    private NewPlacePresenter mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_place_activity);
        mPresenter = new NewPlacePresenter(this,this);
        configConfirmClick();
    }

    @Override
    public void closeView() {
        finish();
    }

    private void configConfirmClick(){
        Button confirm = (Button)findViewById(R.id.new_place_activity_confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name = (EditText)findViewById(R.id.new_place_activity_name);
                EditText address = (EditText)findViewById(R.id.new_place_activity_address);
                EditText description = (EditText)findViewById(R.id.new_place_activity_description);
                EditText imageURL = (EditText)findViewById(R.id.new_place_activity_img_url);
                EditText lat = (EditText)findViewById(R.id.new_place_activity_lat);
                EditText lng = (EditText)findViewById(R.id.new_place_activity_lng);
                Place p = new Place(Double.valueOf(lat.getText().toString()),
                        Double.valueOf(lng.getText().toString()),
                        name.getText().toString(), address.getText().toString(),
                        description.getText().toString(), imageURL.getText().toString());
                mPresenter.addNewPlace(p);
            }
        });
    }
}

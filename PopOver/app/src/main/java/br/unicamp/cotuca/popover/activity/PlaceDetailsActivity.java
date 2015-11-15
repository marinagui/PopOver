package br.unicamp.cotuca.popover.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import br.unicamp.cotuca.popover.R;
import br.unicamp.cotuca.popover.model.Place;
import com.google.android.gms.drive.query.Filters;

import org.w3c.dom.Text;

/**
 * Created by joao on 01/09/15.
 */
public class PlaceDetailsActivity extends AppCompatActivity {
    public static final String EXTRA_PLACE_NAME = "place_details_name_extra";
    public static final String EXTRA_PLACE_ADRESS = "place_details_adress_extra";
    public static final String EXTRA_PLACE_DESCRIPTION = "place_details_description_extra";
    public static final String EXTRA_PLACE_IMAGE_URL = "place_details_image_url_extra";

    private Place mPlace;

    private void setPlaceFromIntent(Intent intent){
        Bundle extras = intent.getExtras();
        String name = extras.getString(EXTRA_PLACE_NAME);
        String address = extras.getString(EXTRA_PLACE_ADRESS);
        String description = extras.getString(EXTRA_PLACE_DESCRIPTION);
        String imageURL = extras.getString(EXTRA_PLACE_IMAGE_URL);

        mPlace = new Place(0,0,name, address, description, imageURL);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_details_activity);

        setPlaceFromIntent(getIntent());

        displayPlace();
    }



    private void displayPlace(){
        TextView name = (TextView)findViewById(R.id.place_details_name);
        TextView address = (TextView)findViewById(R.id.place_details_address);
        TextView description = (TextView)findViewById(R.id.place_details_description);

        name.setText(mPlace.getName());
        address.setText(mPlace.getAddress());
        description.setText(mPlace.getDescription());

        Glide
                .with(this)
                .load(mPlace.getImgURL())
                .placeholder(R.drawable.placeholder)
                .centerCrop()
                .into(((ImageView) findViewById(R.id.place_details_image)));

    }
}

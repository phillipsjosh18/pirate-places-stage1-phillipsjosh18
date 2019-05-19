package edu.ecu.cs.pirateplaces;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;

public class EditPiratePlaceActivity extends AppCompatActivity {

    private static final String EXTRA_PIRATE_PLACE = "edu.ecu.cs.pirateplaces.pirate_place";

    private static final String KEY_PIRATE_PLACE = "pirate place";

    private PiratePlace mPiratePlace;
    private TextView mNameTextView;
    private TextView mDateTextView;
    private Button mResetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pirate_place);

        if(savedInstanceState != null) {
            mPiratePlace = (PiratePlace)savedInstanceState.getSerializable(KEY_PIRATE_PLACE);
            setNewDate();
        }
        else {
            mPiratePlace = (PiratePlace)getIntent().getSerializableExtra(EXTRA_PIRATE_PLACE);
        }

        mNameTextView = findViewById(R.id.pirate_name);
        mNameTextView.setText(mPiratePlace.getTextResId());

        mDateTextView = findViewById(R.id.pirate_date);
        mDateTextView.setText(mPiratePlace.getDate().toString());

        mResetButton = findViewById(R.id.reset_button);
        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPiratePlace.setDate(new Date(System.currentTimeMillis()));
                mDateTextView.setText(mPiratePlace.getDate().toString());

                setNewDate();
            }
        });
    }

    private void setNewDate() {
        Intent data = new Intent();
        data.putExtra(EXTRA_PIRATE_PLACE,mPiratePlace);
        setResult(RESULT_OK,data);
    }

    public static Date newDate(Intent result) {
        PiratePlace temp = (PiratePlace)result.getSerializableExtra(EXTRA_PIRATE_PLACE);
        return temp.getDate();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable(KEY_PIRATE_PLACE, mPiratePlace);
    }

}

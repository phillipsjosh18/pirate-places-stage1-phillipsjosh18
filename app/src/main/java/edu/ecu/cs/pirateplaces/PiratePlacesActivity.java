package edu.ecu.cs.pirateplaces;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.LinkedList;

public class PiratePlacesActivity extends AppCompatActivity
{

    private static final String KEY_INDEX = "index";
    private static final String KEY_TIME = "time";
    private static final String EXTRA_PIRATE_PLACE = "edu.ecu.cs.pirateplaces.pirate_place";
    private static final int REQUEST_CODE = 0;

    private Button mPrevButton;
    private Button mNextButton;
    private TextView mNameTextView;
    private TextView mDateTextView;
    private int mCurrentIndex = 0;
    private PiratePlace mPlace;
    private int mNumPlaces;
    private long mStartTime;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pirate_places);

        if(savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX);
            mStartTime = savedInstanceState.getLong(KEY_TIME);
        }
        else {
            mStartTime = System.currentTimeMillis();
        }

        final LinkedList<PiratePlace> mPlaceList = new LinkedList<PiratePlace>();
        mPlaceList.add(new PiratePlace(R.string.place_scitech,
                new Date(mStartTime)));
        mPlaceList.add(new PiratePlace(R.string.place_wright,
                new Date(mStartTime)));
        mPlaceList.add(new PiratePlace(R.string.place_brewster,
                new Date(mStartTime)));

        mNumPlaces = mPlaceList.size();

        mNameTextView = findViewById(R.id.pirate_name);
        mPlace = mPlaceList.get(mCurrentIndex);
        mNameTextView.setText(mPlace.getTextResId());

        mDateTextView = findViewById(R.id.pirate_date);
        mDateTextView.setText(mPlace.getDate().toString());
        mDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PiratePlacesActivity.this,
                        EditPiratePlaceActivity.class);
                intent.putExtra(EXTRA_PIRATE_PLACE,mPlaceList.get(mCurrentIndex));
                startActivityForResult(intent,REQUEST_CODE);
            }
        });

        mPrevButton = findViewById(R.id.prev_button);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCurrentIndex-1<0){
                    Toast.makeText(PiratePlacesActivity.this,
                            R.string.first_toast,
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    mCurrentIndex--;
                    mPlace = mPlaceList.get(mCurrentIndex);
                    mNameTextView.setText(mPlace.getTextResId());
                    mDateTextView.setText(mPlace.getDate().toString());
                }
            }
        });

        mNextButton = findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCurrentIndex+1>=mNumPlaces){
                    Toast.makeText(PiratePlacesActivity.this,
                            R.string.last_toast,
                            Toast.LENGTH_SHORT).show();
                }
                else{
                    mCurrentIndex++;
                    mPlace = mPlaceList.get(mCurrentIndex);
                    mNameTextView.setText(mPlace.getTextResId());
                    mDateTextView.setText(mPlace.getDate().toString());
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode != Activity.RESULT_OK) {
            return;
        }

        if(requestCode == REQUEST_CODE) {
            if (data == null) {
                return;
            }
            PiratePlace temp = (PiratePlace)data.getSerializableExtra(EXTRA_PIRATE_PLACE);
            mPlace.setDate(temp.getDate());
            mDateTextView.setText(mPlace.getDate().toString());
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_INDEX,mCurrentIndex);
        outState.putLong(KEY_TIME,mStartTime);
    }
}

package com.hassan.sehtakamanah.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hassan.sehtakamanah.R;
import com.hassan.sehtakamanah.activities.MainActivity;

public class ViewSectionFragment extends Fragment {

    //Declaration
    CardView cardViewMental, cardViewBody, cardViewNutrition;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_view_section_fragment, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //initialization
        cardViewMental = getView().findViewById(R.id.cardViewMental);
        cardViewBody = getView().findViewById(R.id.cardViewBody);
        cardViewNutrition = getView().findViewById(R.id.cardViewNutrition);

        //action for cardView
        cardViewMental.setOnClickListener(this::isClick);
        cardViewBody.setOnClickListener(this::isClick);
        cardViewNutrition.setOnClickListener(this::isClick);


    }


    private void isClick(View view) {

        //which section is clicked
        //go to MainActivity and send type to use it to show section's clicked from MainActivity
        switch (view.getId()){
            case R.id.cardViewMental:
                Intent i = new Intent(getContext(), MainActivity.class);
                i.putExtra("type","Mental");
                getActivity().startActivity(i);
                getActivity().finish();
                break;
            case R.id.cardViewBody:
                Intent i2 = new Intent(getContext(), MainActivity.class);
                i2.putExtra("type","Body");
                getActivity().startActivity(i2);
                getActivity().finish();
                break;

            case R.id.cardViewNutrition:
                Intent i3 = new Intent(getContext(), MainActivity.class);
                i3.putExtra("type","Nutrition");
                getActivity().startActivity(i3);
                getActivity().finish();
                break;

        }
    }

}
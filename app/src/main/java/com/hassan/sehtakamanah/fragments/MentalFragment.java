package com.hassan.sehtakamanah.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.hassan.sehtakamanah.R;
import com.hassan.sehtakamanah.activities.MainActivity;
import com.hassan.sehtakamanah.adapters.SectionAdapter;
import com.hassan.sehtakamanah.model.Section;

import java.util.ArrayList;
import java.util.List;

public class MentalFragment extends Fragment {

    //declaration
    RecyclerView recyclerView;
    Section [] section = new Section[6];

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_mental_fragment, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //initialization
        recyclerView = getView().findViewById(R.id.recyclerView);

        //get Resources to get data from string.xml resources
        Resources resources = getResources();

        String [] names = resources.getStringArray(R.array.mentalNames);
        Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
        int [] imageId = {R.drawable.depression,R.drawable.schizophrenia,R.drawable.social_phobia,
                R.drawable.bipolar, R.drawable.ocd, R.drawable.insomnia};

        String [] effects = resources.getStringArray(R.array.effects);
        String [] causesM = resources.getStringArray(R.array.causesM);
        String [] treatments = resources.getStringArray(R.array.treatments);

        //store data in section array to assign this data to RecyclerView
        for (int i = 0; i < names.length; i++)
        {
            section[i] = new Section(names[i], imageId[i], effects[i], causesM[i], treatments[i], false);

        }

        //Link RecyclerView with adapter
        SectionAdapter adapter = new SectionAdapter(section, new SectionAdapter.ItemClickListener() {
            @Override
            public void OnItemClick(Section section) {
                Toast.makeText(getContext(), "the item ---> "+section.getName()+" is clicked!", Toast.LENGTH_SHORT).show();
            }
        },"Mental");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);


    }
}
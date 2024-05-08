package com.hassan.sehtakamanah.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.hassan.sehtakamanah.R;
import com.hassan.sehtakamanah.adapters.QuestionsAdapter;
import com.hassan.sehtakamanah.apis.RetrofitGetQuestions;
import com.hassan.sehtakamanah.model.Questions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionsFragment extends Fragment {

    //declaration
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_questions_fragment, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //initialization
        recyclerView = getView().findViewById(R.id.recyclerViewQ);

        //get Questions and show in recyclerView
        Call<List<Questions>> getQuestionsCall = RetrofitGetQuestions.getInstance().getMyApi().getQuestions();
        getQuestionsCall.enqueue(new Callback<List<Questions>>() {
            @Override
            public void onResponse(Call<List<Questions>> call, Response<List<Questions>> response) {

                List<Questions> questions = response.body();

                QuestionsAdapter adapter = new QuestionsAdapter(questions, getContext());
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(adapter);



            }

            @Override
            public void onFailure(Call<List<Questions>> call, Throwable t) {

                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });



    }
}
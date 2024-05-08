package com.hassan.sehtakamanah.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hassan.sehtakamanah.R;
import com.hassan.sehtakamanah.activities.MainActivity;
import com.hassan.sehtakamanah.apis.RetrofitSendResult;
import com.hassan.sehtakamanah.model.Questions;
import com.hassan.sehtakamanah.model.Result;
import com.hassan.sehtakamanah.model.Section;
import com.hassan.sehtakamanah.sharedPreferences.SharedPreferencesManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.QuestionsViewHolder> {

    List<Questions> questionsList;
    //the context object
    private Context mCtx;
    int score = 0;
    int arrScore[] = {-1, -1, -1, -1, -1, -1, -1, -1, -1};

    //constructor
    public QuestionsAdapter(List<Questions> questionsList, Context mCtx) {
        this.questionsList = questionsList;
        this.mCtx = mCtx;
    }

    @NonNull
    @Override
    public QuestionsAdapter.QuestionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //link with list_item_questions
        return new QuestionsAdapter.QuestionsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_questions,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionsAdapter.QuestionsViewHolder holder, @SuppressLint("RecyclerView") int position) {

        //set data to Views
        holder.tvQuestion.setText(questionsList.get(position).getQuestion());

        //if position = 8 show Button submit to submit user answers
        if (position == 8)
            holder.btnSubmit.setVisibility(View.VISIBLE);

        //action for submit button
        holder.btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean flag = true;
                //save score for user and check if user answers all questions or not
                for (int i = 0; i < 9; i++){
                    if (arrScore[i] != -1){
                        score += arrScore[i];

                    }else {
                        score = 0;
                        Toast.makeText(mCtx, "Plz, Answer all Questions", Toast.LENGTH_SHORT).show();
                        flag = false;
                        break;

                    }
                }

                //if user answers all questions
                if (flag){
                    //go to MainActivity with send score to use it for AlertDialog to show result for user
                    Intent i = new Intent(mCtx, MainActivity.class);
                    i.putExtra("score", score);
                    mCtx.startActivity(i);
                    Call<Result> resultCall = RetrofitSendResult.getInstance().getMyApi().sendResult(SharedPreferencesManager.getInstance(mCtx).getUsers().getId(), score);
                    resultCall.enqueue(new Callback<Result>() {
                        @Override
                        public void onResponse(Call<Result> call, Response<Result> response) {

                        }

                        @Override
                        public void onFailure(Call<Result> call, Throwable t) {

                            Toast.makeText(mCtx, t.getMessage(), Toast.LENGTH_LONG).show();
                            Log.d("Retrofit ERROR -->",t.getMessage());
                        }
                    });
                }


            }
        });

        //action for RadioButtons
        holder.choice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrScore[position] = 0;
            }
        });

        holder.choice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrScore[position] = 1;
            }
        });

        holder.choice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrScore[position] = 2;
            }
        });

        holder.choice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrScore[position] = 3;
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return questionsList.size();
    }

    public class QuestionsViewHolder extends RecyclerView.ViewHolder {

        //declaration
        TextView tvQuestion;
        RadioButton choice1;
        RadioButton choice2;
        RadioButton choice3;
        RadioButton choice4;
        Button btnSubmit;

        public QuestionsViewHolder(@NonNull View itemView) {
            super(itemView);

            //initialization
             tvQuestion = itemView.findViewById(R.id.question);
             choice1 = itemView.findViewById(R.id.choice1);
             choice2 = itemView.findViewById(R.id.choice2);
             choice3 = itemView.findViewById(R.id.choice3);
             choice4 = itemView.findViewById(R.id.choice4);
             btnSubmit = itemView.findViewById(R.id.btnSubmit);

        }
    }
}



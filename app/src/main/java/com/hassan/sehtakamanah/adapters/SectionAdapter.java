package com.hassan.sehtakamanah.adapters;


import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.hassan.sehtakamanah.R;
import com.hassan.sehtakamanah.model.Section;



public class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.SectionViewHolder> {

    //Declaration
    Section [] sectionList;
    ItemClickListener itemClickListener;
    String type;

    //full parameter constructor
    public SectionAdapter(Section[] sectionList, ItemClickListener itemClickListener, String type) {
        this.sectionList = sectionList;
        this.itemClickListener = itemClickListener;
        this.type = type;
    }

    @NonNull
    @Override
    public SectionAdapter.SectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //link with list_item
        return new SectionViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull SectionAdapter.SectionViewHolder holder, int position) {

        //set Data to views
        holder.name.setText(sectionList[position].getName());
        holder.image.setImageResource(sectionList[position].getImg());
        holder.effects.setText(sectionList[position].getEffects());
        holder.causesM.setText(sectionList[position].getCausesM());
        holder.treatments.setText(sectionList[position].getTreatments());
        holder.information.setText(sectionList[position].getInformation());
        holder.diseasesAfflicted.setText(sectionList[position].getDiseasesAfflicted());
        holder.causesB.setText(sectionList[position].getCausesB());
        holder.avoidDiseasesMethods.setText(sectionList[position].getAvoidDiseasesMethods());

        holder.itemView.setOnClickListener(view -> {

            itemClickListener.OnItemClick(sectionList[position]);

        });

        //actionListener for button (More Details)
        holder.btnMoreD.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //expand cardView with details for Mental Section
                if (type.equals("Mental")) {

                    if (holder.layoutM.getVisibility() == v.VISIBLE){

                        TransitionManager.beginDelayedTransition(holder.cardView, new AutoTransition());
                        holder.layoutM.setVisibility(v.GONE);

                    }

                    else {
                        TransitionManager.beginDelayedTransition(holder.cardView, new AutoTransition());
                        holder.layoutM.setVisibility(v.VISIBLE);


                    }

                    //expand cardView with details for Body Section
                }else{
                    if (holder.layoutB.getVisibility() == v.VISIBLE){

                        TransitionManager.beginDelayedTransition(holder.cardView, new AutoTransition());
                        holder.layoutB.setVisibility(v.GONE);

                    }

                    else {
                        TransitionManager.beginDelayedTransition(holder.cardView, new AutoTransition());
                        holder.layoutB.setVisibility(v.VISIBLE);


                    }

                }
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
        return sectionList.length;
    }

    public interface ItemClickListener{
        void OnItemClick(Section section);
    }



    public class SectionViewHolder extends RecyclerView.ViewHolder {

        //declaration
        TextView name, effects, causesM, treatments, information,
                 diseasesAfflicted, causesB, avoidDiseasesMethods;
        ImageView image;
        LinearLayout layoutM;
        LinearLayout layoutB;
        CardView cardView;
        Button btnMoreD;

        public SectionViewHolder(@NonNull View itemView) {
            super(itemView);
            //initialization
            name = itemView.findViewById(R.id.nameId);
            image = itemView.findViewById(R.id.imageViewId);
            effects = itemView.findViewById(R.id.tvEffects);
            causesM = itemView.findViewById(R.id.tvCausesM);
            treatments = itemView.findViewById(R.id.tvTreatment);
            information = itemView.findViewById(R.id.tvInfo);
            diseasesAfflicted = itemView.findViewById(R.id.tvDiseasesAfflicted);
            causesB = itemView.findViewById(R.id.tvCausesB);
            avoidDiseasesMethods = itemView.findViewById(R.id.tvAvoid);
            layoutM = itemView.findViewById(R.id.linearLayoutIdMental);
            layoutB = itemView.findViewById(R.id.linearLayoutIdBody);
            cardView = itemView.findViewById(R.id.cardViewId);
            btnMoreD = itemView.findViewById(R.id.btnMoreId);

        }
    }
}

package kr.or.hanium.mojjak.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import java.util.List;

import kr.or.hanium.mojjak.R;
import kr.or.hanium.mojjak.activities.PlaceActivity;
import kr.or.hanium.mojjak.models.Bike;

public class BikesAdapter extends RecyclerView.Adapter<BikesAdapter.BikesViewHolder> {
    List<Bike> bikes;
    Activity activity;

    public BikesAdapter(List<Bike> bikes, Activity activity) {
        this.bikes = bikes;
        this.activity = activity;
    }

    @Override
    public BikesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bike, parent, false);
        BikesViewHolder bikesViewHolder = new BikesViewHolder(view);
        return bikesViewHolder;
    }

    @Override
    public void onBindViewHolder(BikesViewHolder holder, final int position) {
        String firstLetter = String.valueOf(bikes.get(position).getName().charAt(0));

        ColorGenerator generator = ColorGenerator.MATERIAL; // or use DEFAULT
        // generate random color
        int color = generator.getRandomColor();
        // generate color based on a key (same key returns the same color), useful for list/grid views
//        int color = generator.getColor(firstLetter);

        TextDrawable drawable = TextDrawable.builder()
                .buildRound(firstLetter, color);

        holder.ivBike.setImageDrawable(drawable);
        holder.tvName.setText(bikes.get(position).getName());

        holder.cvBike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, PlaceActivity.class);
                intent.putExtra("placeType", "bike");
                intent.putExtra("placeId", bikes.get(position).getId());
                intent.putExtra("placeCount", bikes.get(position).getCount());
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        // 모델 객체의 크기 전달
        return bikes.size();
    }

    public static class BikesViewHolder extends RecyclerView.ViewHolder {
        public CardView cvBike;
        public ImageView ivBike;
        public TextView tvName;

        public BikesViewHolder(View view) {
            super(view);
            cvBike = view.findViewById(R.id.cv_bike);
            ivBike = view.findViewById(R.id.iv_bike);
            tvName = view.findViewById(R.id.tv_name);
        }
    }
}

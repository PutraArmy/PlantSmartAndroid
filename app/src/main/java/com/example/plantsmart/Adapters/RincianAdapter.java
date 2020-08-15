package com.example.plantsmart.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plantsmart.Models.RincianModel;
import com.example.plantsmart.R;

import java.util.List;

public class RincianAdapter {
    private Context mContext;
    private Adapter mHomeAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<RincianModel> rincianModels) {
        mContext = context;
        mHomeAdapter = new Adapter(rincianModels);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mHomeAdapter);
    }

    class homeItemView extends RecyclerView.ViewHolder {

        private TextView tvJam, tvHumiTanah, tvHumi, tvTemp;

        public homeItemView(ViewGroup parent) {
            super(LayoutInflater.from(mContext).
                    inflate(R.layout.adapter_rincian, parent, false));

            tvJam       = itemView.findViewById(R.id.tv_jam_rincian);
            tvHumiTanah = itemView.findViewById(R.id.tv_humi_tanah_rincian);
            tvHumi      = itemView.findViewById(R.id.tv_humi_rincian);
            tvTemp      = itemView.findViewById(R.id.tv_temp_rincian);

        }

        public  void bind(final RincianModel rincianModel) {

            tvJam.setText(String.valueOf(rincianModel.getWaktu()));
            tvHumiTanah.setText(rincianModel.getHumidityTanah() + " %");
            tvHumi.setText(rincianModel.getHumidity() + " %");
            tvTemp.setText(rincianModel.getSuhu() + "°C");

        }
    }

    class Adapter extends RecyclerView.Adapter<homeItemView> {
        private List<RincianModel> rincianModels;

        public Adapter(List<RincianModel> rincianModels) {
            this.rincianModels = rincianModels;
        }

        @NonNull
        @Override
        public homeItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new homeItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull homeItemView holder, int position) {
            holder.bind(rincianModels.get(position));
        }

        @Override
        public int getItemCount() {

            return rincianModels.size();
//            return 0;
        }
    }
}

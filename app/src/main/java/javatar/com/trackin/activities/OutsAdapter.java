package javatar.com.trackin.activities;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import javatar.com.trackin.R;
import javatar.com.trackin.data.APIService;
import javatar.com.trackin.data.Client;
import javatar.com.trackin.data.Data;
import javatar.com.trackin.data.MyResponse;
import javatar.com.trackin.data.NotificationSender;
import javatar.com.trackin.data.Out;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OutsAdapter extends RecyclerView.Adapter<OutsAdapter.OutsViewHolder> {
    private List<Out> listOuts = new ArrayList<>();

    private static final String TAG = "OutsAdapter";
    @NonNull
    @Override
    public OutsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OutsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_out, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OutsViewHolder holder, int position) {
        Out itemOuts = listOuts.get(position);
        holder.switch_updates.setChecked(itemOuts.isLocation_updates());
        holder.title.setText(itemOuts.getId());

        holder.switch_cliecker.setOnClickListener(v -> {
            if (itemOuts.isLocation_updates()){
                sendNotifications(itemOuts.getToken(),"0");
            }else {
                sendNotifications(itemOuts.getToken(),"1");
            }
        });
    }

    @Override
    public int getItemCount() {
        return listOuts.size();
    }

    public void setList(List<Out> listOuts) {
        this.listOuts = listOuts;
        notifyDataSetChanged();
    }

    public static class OutsViewHolder extends RecyclerView.ViewHolder {
        Switch switch_updates;
        TextView title;
        Button switch_cliecker;
        public OutsViewHolder(@NonNull View itemView) {
            super(itemView);
            switch_updates = itemView.findViewById(R.id.switch_updates);
            switch_cliecker = itemView.findViewById(R.id.switch_cliecker);
            title = itemView.findViewById(R.id.title);
        }
    }

    public void sendNotifications(String userToken, String message) {
        Log.d(TAG, userToken);
        Data data = new Data("locationUpdates", message);
        NotificationSender sender = new NotificationSender(data, userToken);
        APIService apiService = Client.getClient("https://fcm.googleapis.com/").create(APIService.class);
        apiService.sendNotification(sender).enqueue(new Callback<MyResponse>() {
            @Override
            public void onResponse(@NonNull Call<MyResponse> call, @NonNull Response<MyResponse> response) {
                if (response.code() == 200) {
                    assert response.body() != null;
                    if (response.body().success != 1) {
                        Log.d(TAG, "onResponse: failed");
                    }else {
                        Log.d(TAG, "onResponse: ss");
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<MyResponse> call,@NonNull Throwable t) {
                Log.d(TAG, "onResponse: ee");
            }
        });
    }

}
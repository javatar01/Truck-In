package javatar.com.trackin.data;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAArJvN0dI:APA91bFziZcRxyYTJdwZ64IzDcqcNq8meLNtq5shzDRejcVVtmPoZN0nIH4io9P_zTxlsEFTUTammjTs21nVJHHMPAyqeF5cEQNxJdpdsT_Bbryfk-6Fke3UxS8ueslDmF7UjQTu9OIy" // Your server key refer to video for finding your server key
            }
    )

    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body NotificationSender body);
}


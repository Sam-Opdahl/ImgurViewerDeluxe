package com.test.sam.imgviewerdeluxe;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ImgurService {
    @GET("gallery/r/{sub}/")
    Call<GenericImgurData> receiveData(@Path("sub") String sub);
}

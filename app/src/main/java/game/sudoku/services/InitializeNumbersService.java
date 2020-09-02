package game.sudoku.services;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
public interface InitializeNumbersService {

    @GET("sudoku/initialize")
    Call<int[][]> getInitialNumbers(@Query("root") int root);
}

package noemi.ufpr.com.alunoretrofiraze.resouce;

import java.util.List;

import noemi.ufpr.com.alunoretrofiraze.model.Aluno;
import noemi.ufpr.com.alunoretrofiraze.model.Endereco;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.PATCH;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * Created by noemi on 17/02/2019.
 */

public interface EnderecoAPI {

    @GET("enderecos")
    Call<List<Endereco>> getEndereco();

    @GET("enderecos/{id}")
    Call<List<Endereco>> findOne(@Path("id") int id);

    @POST("enderecos")
    Call<Endereco>  postEndereco(@Body Endereco endereco);

    @FormUrlEncoded
    @POST("enderecos")
    Call<Endereco> postEndereco(
            @Field("logradouro") String logradouro,
            @Field("bairro") String bairro,
            @Field("complemento") int complemento

    );

    @PUT("enderecos/{id}")
    Call<Endereco> putEndereco(@Path("id") int id, @Body Endereco endereco);

    @PATCH("enderecos/{id}")
    Call<Endereco> patchEndereco(@Path("id") int id, @Body Endereco endereco);

    @DELETE("enderecos/{id}")
    Call<Void> deleteEndereco(@Path("id") int id);
}

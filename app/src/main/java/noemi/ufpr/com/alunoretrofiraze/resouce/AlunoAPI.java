package noemi.ufpr.com.alunoretrofiraze.resouce;

import java.util.List;

import noemi.ufpr.com.alunoretrofiraze.model.Aluno;
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
import retrofit.http.Url;

/**
 * Created by noemi on 17/02/2019.
 */

public interface AlunoAPI {

    @GET("alunos")
    Call<List<Aluno>> getAluno();
    @GET("alunos/{id}")
    Call<List<Aluno>> findOne(@Path("id") int i);
    @GET("alunos/{id}")
    //Call<List<Aluno>> findOne(@Path("id") int id); @GET
    Call<List<Aluno>> findOne(@Url String url);

    @POST("alunos")
    Call<Aluno>  postAlunos(@Body Aluno aluno);

    @FormUrlEncoded
    @POST("alunos")
    Call<Aluno> postAlunos(
            @Field("cpf")  String cpf,
            @Field("nome") String nome,
            @Field("idade") int idade
    );

    @PUT("/alunos/alunos/{id}")
    Call<Aluno> putAluno(@Path("id") Integer id, @Body Aluno aluno);

    @PUT("alunos/{id}/cpf")
    Call<Aluno> putCpfAluno(@Path("id") int id, @Body String cpf);

    @PATCH("alunos/{id}")
    Call<Aluno> patchAluno(@Path("id") int id, @Body Aluno aluno);

    @DELETE("alunos/{id}")
    Call<Void> deleteAluno(@Path("id") int id);


}

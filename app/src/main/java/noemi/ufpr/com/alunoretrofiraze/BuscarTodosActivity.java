package noemi.ufpr.com.alunoretrofiraze;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import noemi.ufpr.com.alunoretrofiraze.R;
import noemi.ufpr.com.alunoretrofiraze.model.Aluno;
import noemi.ufpr.com.alunoretrofiraze.model.Endereco;
import noemi.ufpr.com.alunoretrofiraze.resouce.AlunoAPI;
import noemi.ufpr.com.alunoretrofiraze.resouce.EnderecoAPI;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class BuscarTodosActivity extends AppCompatActivity {

    private TextView textViewResult;
    private AlunoAPI alunoApi;
    private EnderecoAPI enderecoApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buscar_todos);

        textViewResult = (TextView) findViewById(R.id.text_view_result);

        Gson gson = new GsonBuilder().serializeNulls().create();
        Retrofit retrofit = new Retrofit.Builder()
                //.baseUrl("http://172.20.118.205:8080/")
                .baseUrl("http://192.168.0.12:8080/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        alunoApi = retrofit.create(AlunoAPI.class);

        getAlunos();

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemFindAluno:
                startActivity(new Intent(this, BuscarTodosActivity.class));
                return true;
            case R.id.itemNovoAluno:
                //finish();
                startActivity(new Intent(this, CriarAlunoActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.exemple_menu,menu);
        return  true;
    }

    private void getAlunos(){
        Call<List<Aluno>> call = alunoApi.getAluno();

        call.enqueue(new Callback<List<Aluno>>() {
            @Override
            public void onResponse(Response<List<Aluno>> response, Retrofit retrofit) {
                if (!response.isSuccess()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                List<Aluno> alunos = response.body();


                for (Aluno aluno : alunos) {
                    String content = "";
                    content += "Id: " + aluno.getId() + "\n";
                    content += "CPF: " + aluno.getCpf() + "\n";
                    content += "Nome: " + aluno.getNome() + "\n";
                    content += "Idade: " + aluno.getIdade() + "\n\n";
                    content += "id" + aluno.getEndereco().getId() +"\n\n";
                    content += "numero" + aluno.getEndereco().getNumero() +"\n\n";
                    content += "Logradouro"+ aluno.getEndereco().getLogradouro() +"\n\n";
                    content += "complemento" + aluno.getEndereco().getComplemento() +"\n\n";
                    content += "bairro"+ aluno.getEndereco().getBairro() +"\n\n";

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }


}

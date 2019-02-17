package noemi.ufpr.com.alunoretrofiraze;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import noemi.ufpr.com.alunoretrofiraze.model.Aluno;
import noemi.ufpr.com.alunoretrofiraze.model.Endereco;
import noemi.ufpr.com.alunoretrofiraze.resouce.AlunoAPI;
import noemi.ufpr.com.alunoretrofiraze.resouce.EnderecoAPI;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;
    private AlunoAPI alunoApi;
    private EnderecoAPI enderecoApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewResult = (TextView) findViewById(R.id.text_view_main);

        Gson gson = new GsonBuilder().serializeNulls().create();
        Retrofit retrofit = new Retrofit.Builder()
                //.baseUrl("http://172.20.118.205:8080/")// ip UFPR
                .baseUrl("http://192.168.0.12:8080/") //ip da rede local de casa
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        alunoApi = retrofit.create(AlunoAPI.class);

        //postAluno();
       // deletClientes();
         findOneAluno();
        // updateAluno();

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

    private void findOneAluno(){
       // Call<List<Aluno>> call = alunoApi
           //     .findOne("http://192.168.0.12:8080/alunos/1");
       Aluno  a = new Aluno(1);
        a.setId(1);
        Call<List<Aluno>> call = alunoApi
             .findOne(1);

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
    private void postAluno() {
        Endereco endereco = new Endereco();
        endereco.setId(1);
        Aluno aluno = new Aluno( "555555555", "NOVO teste 01", 5, endereco);

        Call<Aluno> call = alunoApi.postAlunos(aluno);


        call.enqueue(new Callback<Aluno>() {
            @Override
            public void onResponse(Response<Aluno> response, Retrofit retrofit) {

                if(!response.isSuccess()){
                    textViewResult.setText("Code: "+response.code());
                    return;
                }

                Aluno aluno = response.body();

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

                textViewResult.setText(content);
            }

            @Override
            public void onFailure(Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private void updateAluno(){
        Endereco endereco = new Endereco();
        endereco.setId(1);
        Aluno aluno = new Aluno( "999999999", "UPDATE 01", 5, endereco);

        Call<Aluno> call = alunoApi.putAluno(2,aluno);

        call.enqueue(new Callback<Aluno>() {
            @Override
            public void onResponse(Response<Aluno> response, Retrofit retrofit) {

                if(!response.isSuccess()){
                    textViewResult.setText("Code: "+response.code());
                    return;
                }

                Aluno aluno = response.body();

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

                textViewResult.setText(content);
            }

            @Override
            public void onFailure(Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private void updateCpfAluno(){
        String cpf = "000000000";

        Call<Aluno> call = alunoApi.putCpfAluno(2,cpf);

        call.enqueue(new Callback<Aluno>() {
            @Override
            public void onResponse(Response<Aluno> response, Retrofit retrofit) {

                if(!response.isSuccess()){
                    textViewResult.setText("Code: "+response.code());
                    return;
                }


            }

            @Override
            public void onFailure(Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private void deletClientes() {

        Call<Void> call =  alunoApi.deleteAluno(4);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Response<Void> response, Retrofit retrofit) {
                textViewResult.setText("Code: "+response.code()+ " Exclusão realizada com sucesso!!");
            }

            @Override
            public void onFailure(Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}

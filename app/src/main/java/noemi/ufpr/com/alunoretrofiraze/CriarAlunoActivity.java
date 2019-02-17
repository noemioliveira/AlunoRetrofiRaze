package noemi.ufpr.com.alunoretrofiraze;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
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

public class CriarAlunoActivity extends AppCompatActivity {

    private TextView textViewResult;
    private AlunoAPI alunoApi;
    private EditText nomeeditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.criar_aluno);

       //postAluno();
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

    private void postAluno() {
        Endereco endereco = new Endereco();
        endereco.setId(1);
        TextView nome = nomeeditText.findViewById(R.id.nomenovoTxt);
        Aluno aluno = new Aluno( "55555555", nome.toString(), 5, endereco);

        // Map<String, String> fields = new HashMap<>();
        // fields.put("cpf", "999999999");
        // fields.put("nome", "New Name");
        // fields.put("sobrenome", "New Sobrenome");

        // Call<Cliente> call = clienteApi.postClientes("13131313131","New Cliente ","Teste android 02");
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




}

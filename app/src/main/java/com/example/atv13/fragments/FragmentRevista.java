package com.example.atv13.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.fragment.app.Fragment;
import com.example.atv13.R;
import com.example.atv13.controller.RevistaDao;
import com.example.atv13.model.Revista;
import java.util.List;

public class FragmentRevista extends Fragment {

    private EditText editNome, editAutor, editEdicao, editId;
    private RevistaDao revistaDao;
    private TextView textViewResultado;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_revista, container, false);

        // Referências aos elementos do layout
        editNome = view.findViewById(R.id.editNome);
        editAutor = view.findViewById(R.id.editAutor);
        editEdicao = view.findViewById(R.id.editEdicao);
        editId = view.findViewById(R.id.editId);
        textViewResultado = view.findViewById(R.id.textViewResultado);

        Button buttonCadastrar = view.findViewById(R.id.buttonCadastrar);
        Button buttonAtualizar = view.findViewById(R.id.buttonAtualizar);
        Button buttonExcluir = view.findViewById(R.id.buttonExcluir);
        Button buttonBuscar = view.findViewById(R.id.buttonBuscar);
        Button buttonListar = view.findViewById(R.id.buttonListar);

        // Inicializando o controlador
        revistaDao = new RevistaDao(view.getContext());

        // Botão Cadastrar
        buttonCadastrar.setOnClickListener(v -> {
            try {
                String nome = editNome.getText().toString();
                String autor = editAutor.getText().toString();
                String edicaoString = editEdicao.getText().toString();
                if (nome.isEmpty() || autor.isEmpty() || edicaoString.isEmpty()) {
                    textViewResultado.setText("Erro: Preencha todos os campos.");
                    return;
                }

                int edicao = Integer.parseInt(edicaoString);
                Revista revista = new Revista(nome, autor, edicao);
                revistaDao.insert(revista);
                textViewResultado.setText("Revista cadastrada com sucesso!");

            } catch (NumberFormatException e) {
                textViewResultado.setText("Erro: A edição deve ser um número válido.");
            } catch (Exception e) {
                textViewResultado.setText("Erro ao cadastrar revista: " + e.getMessage());
            }
        });

        // Botão Atualizar
        buttonAtualizar.setOnClickListener(v -> {
            try {
                String idString = editId.getText().toString();
                if (idString.isEmpty()) {
                    textViewResultado.setText("Erro: O ID é obrigatório.");
                    return;
                }
                int id = Integer.parseInt(idString);
                String nome = editNome.getText().toString();
                String autor = editAutor.getText().toString();
                String edicaoString = editEdicao.getText().toString();
                if (nome.isEmpty() || autor.isEmpty() || edicaoString.isEmpty()) {
                    textViewResultado.setText("Erro: Preencha todos os campos.");
                    return;
                }

                int edicao = Integer.parseInt(edicaoString);
                Revista revista = new Revista(nome, autor, edicao);
                revista.setId(id);
                int rows = revistaDao.update(revista);
                if (rows > 0) {
                    textViewResultado.setText("Revista atualizada com sucesso!");
                } else {
                    textViewResultado.setText("Revista não encontrada.");
                }

            } catch (NumberFormatException e) {
                textViewResultado.setText("Erro: A edição e ID devem ser números válidos.");
            } catch (Exception e) {
                textViewResultado.setText("Erro ao atualizar revista: " + e.getMessage());
            }
        });

        // Botão Excluir
        buttonExcluir.setOnClickListener(v -> {
            try {
                String idString = editId.getText().toString();
                if (idString.isEmpty()) {
                    textViewResultado.setText("Erro: O ID é obrigatório.");
                    return;
                }

                int id = Integer.parseInt(idString);
                revistaDao.delete(new Revista(null, null, 0)); // A exclusão ocorre pelo ID.
                textViewResultado.setText("Revista excluída com sucesso!");

            } catch (Exception e) {
                textViewResultado.setText("Erro ao excluir revista: " + e.getMessage());
            }
        });

        // Botão Buscar
        buttonBuscar.setOnClickListener(v -> {
            try {
                String idString = editId.getText().toString();
                if (idString.isEmpty()) {
                    textViewResultado.setText("Erro: O ID é obrigatório.");
                    return;
                }

                int id = Integer.parseInt(idString);
                Revista revista = revistaDao.findOne(id);
                if (revista != null) {
                    textViewResultado.setText(revista.toString());
                } else {
                    textViewResultado.setText("Revista não encontrada.");
                }
            } catch (Exception e) {
                textViewResultado.setText("Erro ao buscar revista: " + e.getMessage());
            }
        });

        // Botão Listar
        buttonListar.setOnClickListener(v -> {
            try {
                List<Revista> revistas = revistaDao.findAll();
                StringBuilder resultado = new StringBuilder();
                for (Revista revista : revistas) {
                    resultado.append(revista.toString()).append("\n");
                }
                textViewResultado.setText(resultado.toString());
            } catch (Exception e) {
                textViewResultado.setText("Erro ao listar revistas: " + e.getMessage());
            }
        });

        return view;
    }
}

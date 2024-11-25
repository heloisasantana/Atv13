package com.example.atv13.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.fragment.app.Fragment;
import com.example.atv13.R;
import com.example.atv13.controller.LivroDao;
import com.example.atv13.model.Livro;
import java.util.List;

public class FragmentLivro extends Fragment {

    private EditText editNome, editAutor, editNumeroPaginas, editId;
    private LivroDao livroDao;
    private TextView textViewResultado;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_livro, container, false);

        // Referências dos elementos do layout
        editNome = view.findViewById(R.id.editNome);
        editAutor = view.findViewById(R.id.editAutor);
        editNumeroPaginas = view.findViewById(R.id.editNumeroPaginas);
        editId = view.findViewById(R.id.editId);
        textViewResultado = view.findViewById(R.id.textViewResultado);

        Button buttonCadastrar = view.findViewById(R.id.buttonCadastrar);
        Button buttonAtualizar = view.findViewById(R.id.buttonAtualizar);
        Button buttonExcluir = view.findViewById(R.id.buttonExcluir);
        Button buttonBuscar = view.findViewById(R.id.buttonBuscar);
        Button buttonListar = view.findViewById(R.id.buttonListar);

        // Inicializando o controlador
        livroDao = new LivroDao(view.getContext());

        // Botão Cadastrar
        buttonCadastrar.setOnClickListener(v -> {
            try {
                String nome = editNome.getText().toString();
                String autor = editAutor.getText().toString();
                String numeroPaginasString = editNumeroPaginas.getText().toString();
                if (nome.isEmpty() || autor.isEmpty() || numeroPaginasString.isEmpty()) {
                    textViewResultado.setText("Erro: Preencha todos os campos.");
                    return;
                }

                int numeroPaginas = Integer.parseInt(numeroPaginasString);
                Livro livro = new Livro(nome, autor, numeroPaginas);
                livroDao.insert(livro);
                textViewResultado.setText("Livro cadastrado com sucesso!");

            } catch (NumberFormatException e) {
                textViewResultado.setText("Erro: O número de páginas deve ser um valor válido.");
            } catch (Exception e) {
                textViewResultado.setText("Erro ao cadastrar livro: " + e.getMessage());
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
                String numeroPaginasString = editNumeroPaginas.getText().toString();
                if (nome.isEmpty() || autor.isEmpty() || numeroPaginasString.isEmpty()) {
                    textViewResultado.setText("Erro: Preencha todos os campos.");
                    return;
                }

                int numeroPaginas = Integer.parseInt(numeroPaginasString);
                Livro livro = new Livro(nome, autor, numeroPaginas);
                livro.setId(id);
                int rows = livroDao.update(livro);
                if (rows > 0) {
                    textViewResultado.setText("Livro atualizado com sucesso!");
                } else {
                    textViewResultado.setText("Livro não encontrado.");
                }

            } catch (NumberFormatException e) {
                textViewResultado.setText("Erro: O número de páginas e ID devem ser valores válidos.");
            } catch (Exception e) {
                textViewResultado.setText("Erro ao atualizar livro: " + e.getMessage());
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
                livroDao.delete(new Livro(null, null, 0)); // A exclusão ocorre pelo ID.
                textViewResultado.setText("Livro excluído com sucesso!");

            } catch (Exception e) {
                textViewResultado.setText("Erro ao excluir livro: " + e.getMessage());
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
                Livro livro = livroDao.findOne(id);
                if (livro != null) {
                    textViewResultado.setText(livro.toString());
                } else {
                    textViewResultado.setText("Livro não encontrado.");
                }
            } catch (Exception e) {
                textViewResultado.setText("Erro ao buscar livro: " + e.getMessage());
            }
        });

        // Botão Listar
        buttonListar.setOnClickListener(v -> {
            try {
                List<Livro> livros = livroDao.findAll();
                StringBuilder resultado = new StringBuilder();
                for (Livro livro : livros) {
                    resultado.append(livro.toString()).append("\n");
                }
                textViewResultado.setText(resultado.toString());
            } catch (Exception e) {
                textViewResultado.setText("Erro ao listar livros: " + e.getMessage());
            }
        });

        return view;
    }
}

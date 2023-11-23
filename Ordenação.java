import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Musica {
    private int id;
    private String nome;
    private boolean ativo;
    private double avaliacao;

    // Construtor, getters e setters aqui...

    // Métodos adicionais, se necessário...
}

public class MusicaDAO {

    // Métodos existentes...

    public ArrayList<Musica> obterMusicasOrdenadasPorAvaliacao() {
        ArrayList<Musica> musicas = new ArrayList<>();
        String url = "jdbc:mysql://localhost:3306/seubanco";
        String usuario = "seuusuario";
        String senha = "suasenha";

        try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {
            String sql = "SELECT * FROM musicas WHERE ativo = 1";
            try (PreparedStatement declaracao = conexao.prepareStatement(sql)) {
                try (ResultSet resultado = declaracao.executeQuery()) {
                    while (resultado.next()) {
                        Musica musica = new Musica();
                        musica.setId(resultado.getInt("id"));
                        musica.setNome(resultado.getString("nome"));
                        musica.setAtivo(resultado.getBoolean("ativo"));
                        musica.setAvaliacao(resultado.getDouble("avaliacao"));
                        musicas.add(musica);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Ordenar por avaliação usando um Comparator
        Collections.sort(musicas, Comparator.comparingDouble(Musica::getAvaliacao).reversed());

        return musicas;
    }

    // Outros métodos relacionados ao acesso ao banco de dados, se necessário...
}

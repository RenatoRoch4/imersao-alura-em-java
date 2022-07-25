import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        // fazer uma conexão HTTP e buscar os top 250 filmes
        String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060"; //pega os dados dessa url
        URI endereco = URI.create(url);  // identificação única 
        var client = HttpClient.newHttpClient(); // fazer requisição
        var request = HttpRequest.newBuilder(endereco).GET().build(); // buscar dados da uri(url)
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString()); //lendo os dados como string 
        String body = response.body(); // guarda os texto numa string
        

        // pegar só os dados que interessam (titulo, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        // exibir e manipular os dados 

        for (Map<String,String> filme : listaDeFilmes) {
            
            var titulo = filme.get("title");
            var nota = filme.get("imDbRating");
            var imagem = filme.get("image");
            var ano = filme.get("year");

            System.out.println(" \u001b[5m\u001b[30m\u001b[43m" + " Titulo: \u001b[m " + " \u001b[1m" + titulo);
            System.out.println(" \u001b[5m\u001b[30m\u001b[43m" + " Ano: \u001b[m " + " \u001b[1m" + ano);
            System.out.println(" \u001b[1m" + " Poster: " + imagem + "\u001b[m");
            System.out.println(" \u001b[38;5;226m\u001b[48;5;53m Classificação: " + nota + " \u001b[m ");

            var rating = Double.parseDouble(nota);
            //System.out.println(nota);
                    if(rating >= 9) {

                       int numeroEstrelas = 1 ;

                       while(numeroEstrelas <= 5){
                           System.out.print(" \u001b[5m\u001b[33m\u2b50 ");
                            numeroEstrelas ++;
                       }
                        System.out.println();
                   } else {
                        int numeroEstrelas = 1 ;

                        while(numeroEstrelas <= 4){
                            System.out.print(" \u001b[5m\u001b[33m\u2b50\u001b[m ");
                            numeroEstrelas ++;
                        }
                        System.out.println();
                    }

            System.out.println();

        }
                       
    }
}

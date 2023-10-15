package immobi.tec.immobitec.filter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class ProfanityFilter {

    private List<String> badWords = new ArrayList<String>();

    public ProfanityFilter() {
        try {
            // Charger la liste de gros mots à partir d'un fichier CSV
            BufferedReader reader = new BufferedReader(
                    new FileReader(
                            "C:\\Users\\MSI\\Documents\\GitHub\\4sae8-nextec-nextec\\src\\main\\java\\immobi\\tec\\immobitec\\EnglishProfanity.csv"));
            String line;
            while ((line = reader.readLine()) != null) {
                badWords.add(line.trim());
            }
            System.out.println("****BADBADWOR*******"+badWords);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String filterMessage(String message) {
        String[] words = message.split("\\s+");
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (badWords.contains(word)) {
                String asterisks = "";
                for (int j = 0; j < word.length(); j++) {
                    asterisks += "*";
                }
                words[i] = asterisks;
            }
        }
        return String.join(" ", words);
    }
}



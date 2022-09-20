import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import java.awt.Desktop;
import java.net.URI;

public class App {
    public static void main(String[] args) throws Exception {
        Configuration config = new Configuration();
        config.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        config.setDictionaryPath("src\\dic.dic");
        config.setLanguageModelPath("src\\lm.lm");
        try {
            LiveSpeechRecognizer rec = new LiveSpeechRecognizer(config);
            rec.startRecognition(true);
            Desktop desk = Desktop.getDesktop();
            while (rec.getResult() != null) {
                String result = rec.getResult().getHypothesis();
                if (result.toLowerCase().equals("google")){
                    System.out.println("Opening: "+result);
                    URI uri = new URI("http://google.com/");
                    desk.browse(uri);
                }else if(result.toLowerCase().equals("youtube")){
                    System.out.println("Opening: "+result);
                    URI uri = new URI("http://youtube.com/");
                    desk.browse(uri);
                }
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}

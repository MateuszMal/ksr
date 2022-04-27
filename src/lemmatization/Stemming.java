package lemmatization;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

import java.util.*;
import java.util.concurrent.*;

public class Stemming {
    protected static StanfordCoreNLP pipeline;
//    private static final ExecutorService executor = Executors.newFixedThreadPool(10);

    public Stemming() {
        Properties props = new Properties();
        props.put("annotators", "tokenize, ssplit, pos, lemma");
        pipeline = new StanfordCoreNLP(props);
    }

    public HashMap<String, List<String>> lemmtizeArticles(HashMap<String, List<String>> articles) {
        for (Map.Entry<String, List<String>> entry : articles.entrySet()) {
            List<String> articleList = entry.getValue();
            for (int i = 0; i < articleList.size(); i++) {
                String articleAfterLemma = lemmatize(articleList.get(i));
                articleList.set(i, articleAfterLemma);
//                Future<String> articleAfterLemma = executor.submit(expensive(articleList.get(i)));
//                try {
//                    articleList.set(i, articleAfterLemma.get());
//                } catch (InterruptedException | ExecutionException e) {
//                    e.printStackTrace();
//                }
            }
//            executor.shutdown();
        }
        return articles;
    }

//    Callable<String> expensive(final String param) {
//        return new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                return lemmatize(param);
//            }
//        };
//    }

    private String lemmatize(String text) {
        List<String> lemmas = new LinkedList<>();
        Annotation document = new Annotation(text);
        pipeline.annotate(document);
        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
        for (CoreMap sentence : sentences) {
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                lemmas.add(token.get(CoreAnnotations.LemmaAnnotation.class));
            }
        }
        var article = String.join(" ", lemmas);
        var articleWithComas = article.replaceAll(" ,", ",");
        return articleWithComas.replaceAll(" \\.", ".");
    }
}

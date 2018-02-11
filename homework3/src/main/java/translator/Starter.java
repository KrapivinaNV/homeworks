package translator;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import translator.service.Translator;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;


public class Starter {

    public static void main(String[] args) throws IOException {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfiguration.class);
        Translator translator = applicationContext.getBean(Translator.class);

        while (true) {
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            List<String> translate = translator.translate(input);
            System.out.println(translate);

            if (input.equals("exit")) {
                return;
            }
        }
    }
}

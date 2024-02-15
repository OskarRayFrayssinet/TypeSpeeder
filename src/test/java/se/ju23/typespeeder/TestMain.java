package se.ju23.typespeeder;

import se.ju23.typespeeder.gameLogic.Translator;
import se.ju23.typespeeder.gameLogic.Translatable;

public class TestMain {


    public static void main(String[] args) throws Exception {
        Translatable translator = new Translator();
        System.out.println(translator.translate("På en bondgård i en avlägsen dal beslöt sig hönan för att utmana grisen till en omgång i hönshus-hopp. Grisen, som sällan kunde motstå en utmaning, kastade sig genast in i leken. De hoppade fram och tillbaka, men precis när hönan trodde att hon vann, sa grisen: Haha, du kan ju flyga. Orättvist!", "en"));

    }


}

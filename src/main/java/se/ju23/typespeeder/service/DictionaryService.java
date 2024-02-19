package se.ju23.typespeeder.service;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.model.Dictionary;

@Service
public class DictionaryService {
    public Dictionary EnglishDictionary(){
        Dictionary englishDictionary = new Dictionary("English");
        englishDictionary.addWord("Hello");
        englishDictionary.addWord("World");
        englishDictionary.addWord("Java");
        englishDictionary.addWord("Programming");
        englishDictionary.addWord("Language");
        englishDictionary.addWord("Cow");
        englishDictionary.addWord("Dog");
        englishDictionary.addWord("Cat");
        englishDictionary.addWord("Bird");
        englishDictionary.addWord("Fox");
        englishDictionary.addWord("Horse");
        englishDictionary.addWord("Mouse");
        englishDictionary.addWord("Fish");
        englishDictionary.addWord("Chicken");
        englishDictionary.addWord("Pig");
        englishDictionary.addWord("Sheep");
        englishDictionary.addWord("Goat");
        englishDictionary.addWord("Camel");
        englishDictionary.addWord("Lion");
        englishDictionary.addWord("Tiger");
        englishDictionary.addWord("Wolf");
        englishDictionary.addWord("Politics");
        englishDictionary.addWord("Economy");
        englishDictionary.addWord("Culture");
        englishDictionary.addWord("Technology");
        englishDictionary.addWord("Science");
        englishDictionary.addWord("Science");
        return englishDictionary;
    }

    public Dictionary SwedishDictionary(){
        Dictionary swedishDictionary = new Dictionary("Svenska");
        swedishDictionary.addWord("Hej");
        swedishDictionary.addWord("Världen");
        swedishDictionary.addWord("Java");
        swedishDictionary.addWord("Programmering");
        swedishDictionary.addWord("Språk");
        swedishDictionary.addWord("Katt");
        swedishDictionary.addWord("Hund");
        swedishDictionary.addWord("Katt");
        swedishDictionary.addWord("Fisk");
        swedishDictionary.addWord("Kyckling");
        swedishDictionary.addWord("Grön");
        swedishDictionary.addWord("Mus");
        swedishDictionary.addWord("Fisk");
        swedishDictionary.addWord("Kyckling");
        swedishDictionary.addWord("Grön");
        swedishDictionary.addWord("Kamel");
        swedishDictionary.addWord("Lejon");
        swedishDictionary.addWord("Tiger");
        swedishDictionary.addWord("Varg");
        swedishDictionary.addWord("Politik");
        return swedishDictionary;
    }

    public Dictionary toungeTwistersSwe(){
        Dictionary toungeTwistersSwe = new Dictionary("Svenska");
        toungeTwistersSwe.addWord("Sju sjösjuka sjömän sköttes av sju undersköna sjuksköterskor på skeppet Shang Hai");
        toungeTwistersSwe.addWord("Knut satt vid en knut och knöt en knut När Knut knutit knuten var knuten knuten");
        return toungeTwistersSwe;
    }

    public Dictionary toungeTwistersEng(){
        Dictionary toungeTwistersEng = new Dictionary("English");
        toungeTwistersEng.addWord("Bettie bought a bit of butter but the bit of butter Bettie bought was bitter so Bettie bought a better bit of butter");
        toungeTwistersEng.addWord("She sells sea shells on the sea shore and the sea shells she sells are sea shells Im sure");
        return toungeTwistersEng;
    }

}

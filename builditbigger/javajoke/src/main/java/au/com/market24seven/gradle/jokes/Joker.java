package au.com.market24seven.gradle.jokes;

import java.util.Random;

public class Joker {

//   http://thoughtcatalog.com/christopher-hudspeth/2013/09/50-terrible-quick-jokes-thatll-get-you-a-laugh-on-demand/

    private static final String[] JOKES =
            {"What is Bruce Lee’s favorite drink? Wataaaaah!",
             "If you want to catch a squirrel just climb a tree and act like a nut.",
             "How does NASA organize their company parties? They planet.",
             "This is totally a funny joke from Gabriel Iglesias"};

    public String getJoke(){
        Random jokeselect = new Random();
        return JOKES[jokeselect.nextInt(JOKES.length)];
    }
}
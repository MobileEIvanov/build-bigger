package com.buildbigger.libJoker;

import java.util.Random;

public class JokeCreator {
    private static String[] jokes = {
            "Hartley's First Law:\n" +
                    "         You can lead a horse to water, but if you can get him to float\n" +
                    "         on his back, you've got something.\n",
            "Cole's Law:\n" +
                    "         Thinly sliced cabbage",
            "A conservative is one who is too cowardly to fight and too fat to run.",
            "Frisbeetarianism: The belief that when you die, your soul goes up the\n" +
                    " on roof and gets stuck.",
            "The probability of someone watching you is proportional to the\n" +
                    " stupidity of your action.",
            "I'm a great housekeeper.  I get devorced.  I keep the house.\n" +
                    "  --  Zsa Zsa Gabor",
            "There are three side effects of acid.  Enchanced long term memory,\n" +
                    "decreased short term memory, and I forget the third.\n" +
                    "                        -Timothy Leary",
            "The shortest distance between two points is through Hell.\n" +
                    "            --Brian Clark",
            "  Ripping Yarns\n" +
                    "\n" +
                    "\"Mind you, not as bad as the night Archie Pettigrew ate some\n" +
                    "sheep's testicles for a bet...God, that bloody sheep kicked him...\"\n",
            "  Monty Python\n" +
                    "\n" +
                    "\"In accordance with our principles of free enterprise and\n" +
                    "healthy competition, I'm going to ask you two to fight to\n" +
                    "the death for it.\"",
            "God finally decided to take Satan to court, to settle their differences\n" +
                    "once and for all.\n" +
                    "\n" +
                    " Upon hearing this, Satan laughed, and said, \"Where do you think you're\n" +
                    "going to find a lawyer?\"",
            "The Search for Signs of Intelligent Life in the Universe:\n" +
                    "\n" +
                    "All my life I said I wanted to be someone...I can see now that\n" +
                    "I should have been more specific."


    };

    public static String jokeGenerator() {
        int max = jokes.length;

        Random random = new Random();
        int index = random.nextInt(max);
        return jokes[index];
    }
}

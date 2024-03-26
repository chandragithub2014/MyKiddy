package kids.dev.mykiddy.data.alphabetinfo

import kids.dev.mykiddy.R
import kids.dev.mykiddy.domain.model.Alphabet
import kids.dev.mykiddy.domain.model.ColorInfo

class KidzInfo {
    fun getAlphabetDetails(): List<Alphabet> {
        return listOf(
            Alphabet(
                alphabetName = "A",
                alphabetVal = "Apple"
            ),
            Alphabet(
                alphabetName = "B",
                alphabetVal = "Ball",
                alphabetImage = R.drawable.ic_ball
            ),
            Alphabet(
                alphabetName = "C",
                alphabetVal = "Cat",
                alphabetImage = R.drawable.ic_cat
            ),
            Alphabet(
                alphabetName = "D",
                alphabetVal = "Dog",
                alphabetImage = R.drawable.ic_dog
            ),
            Alphabet(
                alphabetName = "E",
                alphabetVal = "Elephant",
                alphabetImage = R.drawable.ic_elephant
            ),
            Alphabet(
                alphabetName = "F",
                alphabetVal = "Fox",
                alphabetImage = R.drawable.ic_fox
            ),
            Alphabet(
                alphabetName = "G",
                alphabetVal = "Grapes",
                alphabetImage = R.drawable.ic_grapes
            ),
            Alphabet(
                alphabetName = "H",
                alphabetVal = "Horse",
                alphabetImage = R.drawable.ic_horse
            ),
            Alphabet(
                alphabetName = "I",
                alphabetVal = "Ice cream",
                alphabetImage = R.drawable.ic_ice_cream
            ),
            Alphabet(
                alphabetName = "J",
                alphabetVal = "Jug",
                alphabetImage = R.drawable.ic_jug
            ),
            Alphabet(
                alphabetName = "K",
                alphabetVal = "Kite",
                alphabetImage = R.drawable.ic_kite
            ),
            Alphabet(
                alphabetName = "L",
                alphabetVal = "Lion",
                alphabetImage = R.drawable.ic_lion
            ),
            Alphabet(
                alphabetName = "M",
                alphabetVal = "Monkey",
                alphabetImage = R.drawable.ic_monkey
            ),
            Alphabet(
                alphabetName = "N",
                alphabetVal = "Nose",
                alphabetImage = R.drawable.ic_nose
            ),
            Alphabet(
                alphabetName = "O",
                alphabetVal = "Orange",
                alphabetImage = R.drawable.ic_orange
            ),
            Alphabet(
                alphabetName = "P",
                alphabetVal = "Pen",
                alphabetImage = R.drawable.ic_pen
            ), Alphabet(
                alphabetName = "Q",
                alphabetVal = "Queen",
                alphabetImage = R.drawable.ic_queen
            ), Alphabet(
                alphabetName = "R",
                alphabetVal = "Rabbit",
                alphabetImage = R.drawable.ic_rabbit
            ), Alphabet(
                alphabetName = "S",
                alphabetVal = "Swan",
                alphabetImage = R.drawable.ic_swan
            ), Alphabet(
                alphabetName = "T",
                alphabetVal = "Tiger",
                alphabetImage = R.drawable.ic_tiger
            ), Alphabet(
                alphabetName = "U",
                alphabetVal = "Umbrella",
                alphabetImage = R.drawable.ic_umbrella
            ), Alphabet(
                alphabetName = "V",
                alphabetVal = "Van",
                alphabetImage = R.drawable.ic_van
            ), Alphabet(
                alphabetName = "W",
                alphabetVal = "Watch",
                alphabetImage = R.drawable.ic_watch

            ), Alphabet(
                alphabetName = "X",
                alphabetVal = "Xy-loPhone",
                alphabetImage = R.drawable.ic_xylo
            ), Alphabet(
                alphabetName = "Y",
                alphabetVal = "Yak",
                alphabetImage = R.drawable.ic_yak
            ), Alphabet(
                alphabetName = "Z",
                alphabetVal = "Zebra",
               alphabetImage = R.drawable.ic_zebra
            )


        )
    }

    fun getColorCodeInfo(): List<ColorInfo> {
        return listOf(
            ColorInfo(),
            ColorInfo("#fffb00", "Yellow"),
            ColorInfo("#f50aff", "Pink"),
            ColorInfo("#000000", "Black"),
            ColorInfo("#008000", "Green"),
            ColorInfo("#0000FF", "Blue"),
            ColorInfo("#FFA500", "Orange"),
            ColorInfo("#800080", "Purple"),
            ColorInfo("#FFFFFF", "White"),
            ColorInfo("#808080", "Gray"),
            ColorInfo("#A52A2A", "Brown"),
            ColorInfo("#FF00FF", "Magenta")


        )
    }
}
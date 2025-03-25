package com.example.myapplication.data
import com.example.myapplication.R

object DataProvider {

    val drinkList = listOf(
        Drinki(
            id = 1,
            name = "Mojito",
            recipe = "Rum, mięta, cukier, limonka, woda gazowana",
            imageId = R.drawable.p1,
            steps = listOf(
                "Wymieszaj rum z cukrem.",
                "Dodaj świeżą miętę i limonkę.",
                "Wlej wodę gazowaną.",
                "Wymieszaj wszystko i podawaj w szklance z lodem."
            )
        ),
        Drinki(
            id = 2,
            name = "Pina Colada",
            recipe = "Rum, mleczko kokosowe, sok ananasowy",
            imageId = R.drawable.p2,
            steps = listOf(
                "Wlej rum do shakera.",
                "Dodaj mleczko kokosowe i sok ananasowy.",
                "Wstrząśnij i przefiltruj do szklanki.",
                "Podawaj z kawałkiem ananasa."
            )
        ),
        Drinki(
            id = 3,
            name = "Margarita",
            recipe = "Tequila, triple sec, sok z limonki",
            imageId = R.drawable.p3,
            steps = listOf(
                "Połącz tequilę, triple sec i sok z limonki w shakerze.",
                "Wstrząśnij mocno, a następnie przelej do szklanki z solą na brzegach.",
                "Podawaj z kawałkiem limonki."
            )
        ),
        Drinki(
            id = 4,
            name = "Old Fashioned",
            recipe = "Whiskey, cukier, bitters, woda",
            imageId = R.drawable.p4,
            steps = listOf(
                "Umieść kawałek cukru w szklance.",
                "Dodaj kilka kropli bitters.",
                "Zalej whiskey i wymieszaj.",
                "Dodaj kawałek lodu i podawaj."
            )
        ),
        Drinki(
            id = 5,
            name = "Cosmopolitan",
            recipe = "Wódka, triple sec, sok żurawinowy, sok z limonki",
            imageId = R.drawable.p5,
            steps = listOf(
                "Połącz wódkę, triple sec, sok żurawinowy i sok z limonki w shakerze.",
                "Wstrząśnij i przelej do schłodzonego kieliszka.",
                "Podawaj z kawałkiem limonki."
            )
        ),
        Drinki(
            id = 6,
            name = "Negroni",
            recipe = "Gin, Campari, słodki wermut",
            imageId = R.drawable.p6,
            steps = listOf(
                "Połącz gin, Campari i słodki wermut w szklance z lodem.",
                "Wymieszaj i podawaj z plasterkiem pomarańczy."
            )
        ),
        Drinki(
            id = 7,
            name = "Caipirinha",
            recipe = "Cachaça, limonka, cukier",
            imageId = R.drawable.p7,
            steps = listOf(
                "Pokrój limonkę na kawałki i wymieszaj z cukrem.",
                "Dodaj cachaçę i zmieszaj z lodem.",
                "Podawaj w szklance z lodem."
            )
        ),
        Drinki(
            id = 8,
            name = "Whiskey Sour",
            recipe = "Whiskey, sok z cytryny, syrop cukrowy, białko jajka",
            imageId = R.drawable.p8,
            steps = listOf(
                "Połącz whiskey, sok z cytryny, syrop cukrowy i białko jajka w shakerze.",
                "Wstrząśnij i przelej do szklanki.",
                "Podawaj z kawałkiem cytryny."
            )
        ),
        Drinki(
            id = 9,
            name = "Daiquiri",
            recipe = "Rum, sok z limonki, syrop cukrowy",
            imageId = R.drawable.p9,
            steps = listOf(
                "Połącz rum, sok z limonki i syrop cukrowy w shakerze.",
                "Wstrząśnij i przelej do schłodzonego kieliszka."
            )
        ),
        Drinki(
            id = 10,
            name = "Bloody Mary",
            recipe = "Wódka, sok pomidorowy, sok z cytryny, przyprawy",
            imageId = R.drawable.p10,
            steps = listOf(
                "Połącz wódkę, sok pomidorowy, sok z cytryny i przyprawy w shakerze.",
                "Wstrząśnij i przelej do szklanki.",
                "Podawaj z selerem naciowym i kawałkiem cytryny."
            )
        )
    )
}


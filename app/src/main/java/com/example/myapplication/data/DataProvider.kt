package com.example.myapplication.data

import com.example.myapplication.R

object DataProvider {

    val drinkList = listOf(
        // Koktajle alkoholowe
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
            ),
            isAlcoholic = true,
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
            ),
            isAlcoholic = true,
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
            ),
            isAlcoholic = true,
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
            ),
            isAlcoholic = true,
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
            ),
            isAlcoholic = true,
        ),
        Drinki(
            id = 6,
            name = "Negroni",
            recipe = "Gin, Campari, słodki wermut",
            imageId = R.drawable.p6,
            steps = listOf(
                "Połącz gin, Campari i słodki wermut w szklance z lodem.",
                "Wymieszaj i podawaj z plasterkiem pomarańczy."
            ),
            isAlcoholic = true,
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
            ),
            isAlcoholic = true,
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
            ),
            isAlcoholic = true,
        ),
        Drinki(
            id = 9,
            name = "Daiquiri",
            recipe = "Rum, sok z limonki, syrop cukrowy",
            imageId = R.drawable.p9,
            steps = listOf(
                "Połącz rum, sok z limonki i syrop cukrowy w shakerze.",
                "Wstrząśnij i przelej do schłodzonego kieliszka."
            ),
            isAlcoholic = true,
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
            ),
            isAlcoholic = true,
        ),
        Drinki(
            id = 11,
            name = "Virgin Mojito",
            recipe = "Mięta, cukier, limonka, woda gazowana",
            imageId = R.drawable.p11,
            steps = listOf(
                "Wymieszaj cukier z miętą.",
                "Dodaj sok z limonki.",
                "Wlej wodę gazowaną.",
                "Podawaj w szklance z lodem."
            ),
            isAlcoholic = false,
        ),
        Drinki(
            id = 12,
            name = "Fresh Lemonade",
            recipe = "Sok z cytryny, cukier, woda",
            imageId = R.drawable.p12,
            steps = listOf(
                "Wymieszaj sok z cytryny z cukrem.",
                "Dodaj wodę i wymieszaj.",
                "Podawaj schłodzone."
            ),
            isAlcoholic = false,
        ),
        Drinki(
            id = 13,
            name = "Cranberry Spritzer",
            recipe = "Sok żurawinowy, woda gazowana, plasterki cytryny",
            imageId = R.drawable.p13,
            steps = listOf(
                "Wlej sok żurawinowy do szklanki.",
                "Dodaj wodę gazowaną.",
                "Udekoruj plasterkiem cytryny."
            ),
            isAlcoholic = false,
        ),
        Drinki(
            id = 14,
            name = "Pineapple Ginger Fizz",
            recipe = "Sok ananasowy, sok imbirowy, woda gazowana",
            imageId = R.drawable.p14,
            steps = listOf(
                "Wymieszaj sok ananasowy z sokiem imbirowym.",
                "Dodaj wodę gazowaną.",
                "Podawaj schłodzone."
            ),
            isAlcoholic = false,
        ),
        Drinki(
            id = 15,
            name = "Virgin Piña Colada",
            recipe = "Mleczko kokosowe, sok ananasowy",
            imageId = R.drawable.p15,
            steps = listOf(
                "Wymieszaj mleczko kokosowe z sokiem ananasowym.",
                "Podawaj schłodzone z kawałkiem ananasa."
            ),
            isAlcoholic = false,
        )
    )
}

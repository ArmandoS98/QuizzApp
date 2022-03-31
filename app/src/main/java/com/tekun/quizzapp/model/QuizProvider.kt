package com.tekun.quizzapp.model

class QuizProvider {
    companion object {
        fun random(): QuizModel {
            val position = (0..9).random()
            println("Posicion Obtenida: $position")
            return question[position]
        }

        private val question = listOf(
            QuizModel(
                "Who is the main character of the hzd video game",
                "https://media.vandal.net/m/8-2020/20208518551383_1.jpg",
                "Aloy|Richard|Molly|Lea",
                1
            ),
            QuizModel(
                "Who is the main villain of the game",
                "https://cdn-www.playstationlifestyle.net/assets/uploads/2022/01/Horizon.png",
                "Gaia|Hades|Rost|The eclipse",
                1
            ),
            QuizModel(
                "Aloy's genes were taken from which person",
                "https://static2.cbrimages.com/wordpress/wp-content/uploads/2021/12/Aloy.jpg?q=50&fit=crop&w=960&h=500&dpr=1.5",
                "Gaia|Rost|Elizabeth|Sara",
                1
            ),
            QuizModel(
                "Who took good care of aloy",
                "https://guitar-master.es/wp-content/uploads/2021/04/horizon-zero-dawn-aloy-rost.jpg",
                "Hades|Rost|Bast|Erend",
                1
            ),
            QuizModel(
                "Who is the king of meridian",
                "https://progameguides.com/wp-content/uploads/2022/02/featured-horizon-forbidden-west-avad-900x506.png",
                "Avad|Erend|Marad|Roland",
                1
            ),
            QuizModel(
                "who made the horizon zero dawn video game",
                "https://media.gq-magazine.co.uk/photos/5d1397c89a22c2556f947fe5/16:9/w_1280,c_limit/Horizon-Zero-Dawn-01-GQ-20Feb17_b.jpg",
                "Sony ps4|Wii|Gurilla games|X box",
                1
            ),
            QuizModel(
                "How to you tame machines in the game?",
                "https://cdn.gamer-network.net/2017/usgamer/horizon-zero-dawn-shot-3.jpg",
                "Override|Pat them|Sing a song to them|Kick then twice",
                1
            ),
            QuizModel(
                "Which tribe lives in the cold section of the land",
                "https://static.wikia.nocookie.net/horizonzerodawn/images/8/86/The-Proving-1.PNG/revision/latest?cb=20170313180338",
                "Carja|Norta|Oseram|Banuk",
                1
            ),
            QuizModel(
                "Which mother of the norta helps Aloy the most",
                "https://oyster.ignimgs.com/mediawiki/apis.ign.com/horizon-zero-dawn/1/1e/16.png",
                "Teresa|Jessa|Lansra|ona",
                1
            ),
            QuizModel(
                "Why did Aloy take part in the proving",
                "https://img.redbull.com/images/c_crop,w_1920,h_960,x_0,y_120,f_auto,q_auto/c_scale,w_1200/redbullcom/2021/5/8/anto9il0vwqjjurjfd0r/sed-diablo-horizon-zero-dawn",
                "She wanted money|she wanted to find out about her mother|She wanted revenge for what the tribe did to her|She wanted to be a brave",
                1
            )
        )
    }
}